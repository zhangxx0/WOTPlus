package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.AchieveMentAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
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


        // 从CharedPreference中获取woter
        String woterString = PreferenceUtils.getCustomPrefString(getActivity(), "woter", "woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            // Log.d("GsonWoter", woter.toString());
        }

        Observer<ResponseBody> achieveObserver = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.d("XXX", e.getMessage());
                Snackbar.make(getView(), "Rx错误！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (responseBody != null) {
                    try {
                        String strRead = responseBody.string();
                        Log.d("XXX",strRead);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Snackbar.make(getView(), "ResponseBody 为空！！", Snackbar.LENGTH_LONG).show();
                }


            }
        };

        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");

        Network.getAchieveApi()
                .getAchievesNums()
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
