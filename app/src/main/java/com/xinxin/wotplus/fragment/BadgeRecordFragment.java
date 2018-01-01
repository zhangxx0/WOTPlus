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

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private TextView destoryNum, findNum, hitRate, killingNum, averageKillingNum, occupiedBaseNum, defendBaseNum;
    private LinearLayout class_ace_layout,class_1_layout,class_2_layout,class_3_layout;

    @BindView(R.id.max_kill)
    TextView max_kill;
    @BindView(R.id.max_exp)
    TextView max_exp;
    @BindView(R.id.max_hit)
    TextView max_hit;

    @BindView(R.id.finghtNum)
    TextView finghtNum;
    @BindView(R.id.victoryNum)
    TextView victoryNum;
    @BindView(R.id.failureNum)
    TextView failureNum;
    @BindView(R.id.survivingNum)
    TextView survivingNum;
    @BindView(R.id.damage_coefficient)
    TextView damage_coefficient;
    @BindView(R.id.frags_count_coefficient)
    TextView frags_count_coefficient;
    @BindView(R.id.damage_blocked_coefficient)
    TextView damage_blocked_coefficient;
    @BindView(R.id.capture_points)
    TextView capture_points;
    @BindView(R.id.dropped_capture_points)
    TextView dropped_capture_points;

    @BindView(R.id.exp)
    TextView exp;
    @BindView(R.id.damage_dealt_avg)
    TextView damage_dealt_avg;
    @BindView(R.id.damage_received_avg)
    TextView damage_received_avg;
    @BindView(R.id.stun_num_avg)
    TextView stun_num_avg;
    @BindView(R.id.damage_assisted_avg)
    TextView damage_assisted_avg;
    @BindView(R.id.damage_assisted_stun_avg)
    TextView damage_assisted_stun_avg;
    @BindView(R.id.spotted_count_avg)
    TextView spotted_count_avg;
    @BindView(R.id.frags_count_avg)
    TextView frags_count_avg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_badge_record, container, false);
        ButterKnife.bind(this,view);

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

        class_ace_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster4()));
        class_1_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster3()));
        class_2_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster2()));
        class_3_num.setText(String.valueOf(statistics.getData().getMaster_level_counts().getMaster1()));

        max_kill.setText(String.valueOf(statistics.getData().getFrags_max()));
        max_exp.setText(String.valueOf(statistics.getData().getXp_max()));
        max_hit.setText(String.valueOf(statistics.getData().getDamage_max()));

        DecimalFormat df = new DecimalFormat("0.0");
        float winrate = (float) statistics.getData().getWins_count() * 100 / statistics.getData().getBattles_count();
        float failrate = (float) statistics.getData().getLosses_count() * 100 / statistics.getData().getBattles_count();
        float survrate = (float) statistics.getData().getSurvived_battles() * 100 / statistics.getData().getBattles_count();
        finghtNum.setText(String.valueOf(statistics.getData().getBattles_count()));
        victoryNum.setText(String.valueOf(statistics.getData().getWins_count()) + "("+df.format(winrate)+"%)");
        failureNum.setText(String.valueOf(statistics.getData().getLosses_count()) + "("+df.format(failrate)+"%)");
        survivingNum.setText(String.valueOf(statistics.getData().getSurvived_battles()) + "("+df.format(survrate)+"%)");
        damage_coefficient.setText(String.valueOf(statistics.getData().getDamage_coefficient()));
        frags_count_coefficient.setText(String.valueOf(statistics.getData().getFrags_count_coefficient()));
        damage_blocked_coefficient.setText(String.valueOf(statistics.getData().getDamage_blocked_coefficient()));
        capture_points.setText(String.valueOf(statistics.getData().getCapture_points()));
        dropped_capture_points.setText(String.valueOf(statistics.getData().getDropped_capture_points()));

        exp.setText(String.valueOf(statistics.getData().getXp_amount_avg()));
        damage_dealt_avg.setText(String.valueOf(statistics.getData().getDamage_dealt_avg()));
        damage_received_avg.setText(String.valueOf(statistics.getData().getDamage_received_avg()));
        stun_num_avg.setText(String.valueOf(statistics.getData().getStun_num_avg()));
        damage_assisted_avg.setText(String.valueOf(statistics.getData().getDamage_assisted_avg()));
        damage_assisted_stun_avg.setText(String.valueOf(statistics.getData().getDamage_assisted_stun_avg()));
        spotted_count_avg.setText(String.valueOf(statistics.getData().getSpotted_count_avg()));
        frags_count_avg.setText(String.valueOf(statistics.getData().getFrags_count_avg()));

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
