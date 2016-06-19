package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.AchieveMentAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.AchieveNew;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.AchieveJsonToMapMapper;

import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/3.
 * 成就Fragment
 */
public class AchieveMentFragment extends BaseFragment {

    private RecyclerView recyclerview_achieve;
    private AchieveMentAdapter adapter;
    private Woter woter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achieve, container, false);

        recyclerview_achieve = (RecyclerView) view.findViewById(R.id.recyclerview_achieve);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerview_achieve.setLayoutManager(lm);
        recyclerview_achieve.setItemAnimator(new DefaultItemAnimator());

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();


        Observer<List<AchieveNew>> achieveObserver = new Observer<List<AchieveNew>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.d("XXX", "111");

                Snackbar.make(getView(), "Rx错误！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<AchieveNew> achieveNews) {
                if (achieveNews != null) {
                    Snackbar.make(getView(), "转换成功！", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(getView(), "ResponseBody 为空！！", Snackbar.LENGTH_LONG).show();
                }


            }
        };

        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");

        Network.getAchieveApi()
                .getAchievesNums(woterId)
                .map(AchieveJsonToMapMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(achieveObserver);

        /*adapter = new AchieveMentAdapter(getActivity(),woter);
        // RecyclerView 动画
        SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(adapter, recyclerview_achieve);
        recyclerview_achieve.setAdapter(animatorAdapter);*/

        return view;
    }
}
