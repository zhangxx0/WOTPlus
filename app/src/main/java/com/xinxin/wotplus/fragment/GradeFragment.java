package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.GradeAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Grade;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/4.
 * 等级Fragment
 */
public class GradeFragment extends BaseFragment {

    private RecyclerView recyclerview_grade;
    private GradeAdapter adapter;
    private DeathWheelProgressDialog deathWheelProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grade, container, false);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();

        recyclerview_grade = (RecyclerView) view.findViewById(R.id.recyclerview_grade);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerview_grade.setLayoutManager(lm);
        recyclerview_grade.setItemAnimator(new DefaultItemAnimator());

        Observer<Grade> gradeObserver = new Observer<Grade>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(getView(), "获取等级信息出错！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNext(Grade grade) {
                adapter = new GradeAdapter(getActivity(), grade);
                // RecyclerView 动画
                SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(adapter, recyclerview_grade);
                recyclerview_grade.setAdapter(animatorAdapter);
                deathWheelProgressDialog.dismiss();
            }
        };

        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
        deathWheelProgressDialog.show();

        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");
        // 区分南北区
        String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");

        subscription = Network.getGradeInfo(region)
                .getGradeInfo(woterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gradeObserver);

        return view;
    }
}
