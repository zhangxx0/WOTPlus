package com.xinxin.wotplus.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.Statistics;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.StatisticsJsonToStatisticsMapper;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/4.
 * 徽章与战绩Fragment
 */
public class BadgeRecordFragment extends BaseFragment implements View.OnClickListener {

    private Statistics statistics;
    private DeathWheelProgressDialog deathWheelProgressDialog;

    private ImageView class_ace_img, class_1_img, class_2_img, class_3_img;
    private TextView class_ace_num, class_1_num, class_2_num, class_3_num;

    private TextView finghtNum, victoryNum, failureNum, survivingNum, experienceNum, averageExpNum, maxExpNum;
    private TextView destoryNum, findNum, hitRate, killingNum, averageKillingNum, occupiedBaseNum, defendBaseNum;
    private LinearLayout class_ace_layout,class_1_layout,class_2_layout,class_3_layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_badge_record, container, false);

        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
        deathWheelProgressDialog.show();

        // 从接口获取数据
        Observer<Statistics> observer = new Observer<Statistics>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(getView(), "获取战绩信息错误！", Snackbar.LENGTH_LONG).show();
                deathWheelProgressDialog.dismiss();
            }

            @Override
            public void onNext(Statistics s) {
                statistics = s;
                initData();
                deathWheelProgressDialog.dismiss();
            }
        };

        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");
        String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");
        Network.getKongzhongNewApi(region)
                .getStatistics(woterId, MainFragment.BATTLE_TYPE)
                .map(StatisticsJsonToStatisticsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        initView(view);

        return view;

    }

    private void initData() {

        class_ace_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster1()));
        class_1_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster2()));
        class_2_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster3()));
        class_3_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster4()));

        /*finghtNum.setText(woter.getBadgeAndRecord().getFinghtNum());
        victoryNum.setText(woter.getBadgeAndRecord().getVictoryNum());
        failureNum.setText(woter.getBadgeAndRecord().getFailureNum());
        survivingNum.setText(woter.getBadgeAndRecord().getSurvivingNum());
        experienceNum.setText(woter.getBadgeAndRecord().getExperienceNum());
        averageExpNum.setText(woter.getBadgeAndRecord().getAverageExpNum());
        maxExpNum.setText(woter.getBadgeAndRecord().getMaxExpNum());

        destoryNum.setText(woter.getBadgeAndRecord().getDestoryNum());
        findNum.setText(woter.getBadgeAndRecord().getFindNum());
        hitRate.setText(woter.getBadgeAndRecord().getHitRate());
        killingNum.setText(woter.getBadgeAndRecord().getKillingNum());
        averageKillingNum.setText(woter.getBadgeAndRecord().getAverageKillingNum());
        occupiedBaseNum.setText(woter.getBadgeAndRecord().getOccupiedBaseNum());
        defendBaseNum.setText(woter.getBadgeAndRecord().getDefendBaseNum());*/

        /*class_ace_layout.setOnClickListener(this);
        class_1_layout.setOnClickListener(this);
        class_2_layout.setOnClickListener(this);
        class_3_layout.setOnClickListener(this);*/

    }

    private void initView(View view) {
        class_ace_img = (ImageView) view.findViewById(R.id.class_ace_img);
        class_1_img = (ImageView) view.findViewById(R.id.class_1_img);
        class_2_img = (ImageView) view.findViewById(R.id.class_2_img);
        class_3_img = (ImageView) view.findViewById(R.id.class_3_img);

        class_ace_num = (TextView) view.findViewById(R.id.class_ace_num);
        class_1_num = (TextView) view.findViewById(R.id.class_1_num);
        class_2_num = (TextView) view.findViewById(R.id.class_2_num);
        class_3_num = (TextView) view.findViewById(R.id.class_3_num);

        finghtNum = (TextView) view.findViewById(R.id.finghtNum);
        victoryNum = (TextView) view.findViewById(R.id.victoryNum);
        failureNum = (TextView) view.findViewById(R.id.failureNum);
        survivingNum = (TextView) view.findViewById(R.id.survivingNum);
        experienceNum = (TextView) view.findViewById(R.id.experienceNum);
        averageExpNum = (TextView) view.findViewById(R.id.averageExpNum);
        maxExpNum = (TextView) view.findViewById(R.id.maxExpNum);

        destoryNum = (TextView) view.findViewById(R.id.destoryNum);
        findNum = (TextView) view.findViewById(R.id.findNum);
        hitRate = (TextView) view.findViewById(R.id.hitRate);
        killingNum = (TextView) view.findViewById(R.id.killingNum);
        averageKillingNum = (TextView) view.findViewById(R.id.averageKillingNum);
        occupiedBaseNum = (TextView) view.findViewById(R.id.occupiedBaseNum);
        defendBaseNum = (TextView) view.findViewById(R.id.defendBaseNum);

        class_ace_layout = (LinearLayout) view.findViewById(R.id.class_ace_layout);
        class_1_layout = (LinearLayout) view.findViewById(R.id.class_1_layout);
        class_2_layout = (LinearLayout) view.findViewById(R.id.class_2_layout);
        class_3_layout = (LinearLayout) view.findViewById(R.id.class_3_layout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.class_ace_layout:
                showDesDialog("最牛逼！");
                break;
            case R.id.class_1_layout:
                showDesDialog("很牛逼！");
                break;
            case R.id.class_2_layout:
                showDesDialog("牛逼！");
                break;
            case R.id.class_3_layout:
                showDesDialog("bi!");
                break;
        }
    }

    private void showDesDialog(String classAceDes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("徽章描述")
                .setMessage(classAceDes)
                .setPositiveButton("了解", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
