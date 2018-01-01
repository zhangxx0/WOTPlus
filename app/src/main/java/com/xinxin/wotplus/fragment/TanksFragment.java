package com.xinxin.wotplus.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.activity.AtyTanks;
import com.xinxin.wotplus.adapter.TanksAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Tanks;
import com.xinxin.wotplus.model.TanksType;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.Statistics;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.Constant;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.StatisticsJsonToStatisticsMapper;
import com.xinxin.wotplus.util.mapper.TankTypeJsonCorrectAndToVoMapper;
import com.xinxin.wotplus.util.mapper.TypeCountryJsonCorrectMapper;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import java.util.List;

import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/6.
 * 战车Fragment
 */
public class TanksFragment extends BaseFragment {

    private RecyclerView recyclerview_tanks;
    private TanksAdapter tanksAdapter;
    private Woter woter;
    private DeathWheelProgressDialog deathWheelProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tanks, container, false);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();

        recyclerview_tanks = (RecyclerView) view.findViewById(R.id.recyclerview_tanks);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerview_tanks.setLayoutManager(lm);
        recyclerview_tanks.setItemAnimator(new DefaultItemAnimator());

        // 从CharedPreference中获取woter
        String woterString = PreferenceUtils.getCustomPrefString(getActivity(), "woter", "woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
        }

        // 数据改为动态获取之后，在此补充数据
        Observer<Statistics> observer = new Observer<Statistics>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(getView(), "获取坦克类型信息出错！", Snackbar.LENGTH_LONG).show();
                deathWheelProgressDialog.dismiss();
            }

            @Override
            public void onNext(Statistics statistics) {
                woter.setStatistics(statistics);

                tanksAdapter = new TanksAdapter(getActivity(), woter);
                // 点击事件
                tanksAdapter.setOnItemClickLitener(new TanksAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Context context = view.getContext();
                        // 全屏扩散
                        int[] startingLocation = new int[2];
                        view.getLocationOnScreen(startingLocation);
                        startingLocation[0] += view.getWidth() / 2;

                        Intent intent = new Intent(context, AtyTanks.class);
                        intent.putExtra(Constant.START_LOCATION, startingLocation);
                        intent.putExtra(AtyTanks.TANKS_TYPE, String.valueOf(position));

                        context.startActivity(intent);
                        getActivity().overridePendingTransition(0, 0);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                    }
                });
                // RecyclerView 动画
                SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(tanksAdapter, recyclerview_tanks);
                recyclerview_tanks.setAdapter(animatorAdapter);

                deathWheelProgressDialog.dismiss();

            }
        };
        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
        deathWheelProgressDialog.show();
        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");
        // 区分南北区
        String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");
        subscription = Network.getKongzhongNewApi(region)
                .getStatistics(woterId, MainFragment.BATTLE_TYPE)
                .map(StatisticsJsonToStatisticsMapper.getInstance())
//                .map(TankTypeJsonCorrectAndToVoMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return view;
    }
}
