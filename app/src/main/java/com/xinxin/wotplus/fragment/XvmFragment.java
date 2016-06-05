package com.xinxin.wotplus.fragment;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.activity.AtyXvmTankTable;
import com.xinxin.wotplus.activity.AtyXvmThirtyRecord;
import com.xinxin.wotplus.adapter.XvmDaylistAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.model.XvmMainPageVO;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
import com.xinxin.wotplus.util.mapper.XvmAllInfoToDayMap;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/5/22.
 * XVM Fragment
 */
public class XvmFragment extends BaseFragment {

    @BindView(R.id.xvm_lable)
    TextView xvm_lable;

    @BindView(R.id.xvm_battls_img)
    ImageView xvm_battls_img;
    @BindView(R.id.xvm_wins_img)
    ImageView xvm_wins_img;
    @BindView(R.id.xvm_power_img)
    ImageView xvm_power_img;
    @BindView(R.id.xvm_level_img)
    ImageView xvm_level_img;
    @BindView(R.id.xvm_counts_img)
    ImageView xvm_counts_img;

    @BindView(R.id.xvm_battls)
    TextView xvm_battls;
    @BindView(R.id.xvm_wins)
    TextView xvm_wins;
    @BindView(R.id.xvm_power)
    TextView xvm_power;
    @BindView(R.id.xvm_level)
    TextView xvm_level;
    @BindView(R.id.xvm_counts)
    TextView xvm_counts;

    @BindView(R.id.recyclerview_xvm_recentdays)
    RecyclerView xvm_recentdays;

    private DeathWheelProgressDialog deathWheelProgressDialog;
    private XvmDaylistAdapter adapter;

    private XvmAllInfo xvmAllInfoForOtherPage;
    private XvmMainInfo xvmMainInfoForOtherPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xvm, container, false);
        // butterknife
        ButterKnife.bind(this, view);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();

        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xvm_recentdays.setLayoutManager(lm);
        xvm_recentdays.setItemAnimator(new DefaultItemAnimator());

        // main信息的Observer
        Observer<XvmMainInfo> xvmMainObserver = new Observer<XvmMainInfo>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(xvm_recentdays, "获取XVM信息出错！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNext(XvmMainInfo xvmMainInfo) {
                Log.d("xvm", xvmMainInfo.toString());

            }
        };

        // tanks.js信息的Observer
        Observer<Map> tanksObserver = new Observer<Map>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(xvm_recentdays, "获取XVMtanks信息出错！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNext(Map map) {
                Log.d("xvm9", map.toString());
            }
        };

        // 汇总信息的Observer
        Observer<XvmAllInfo> xvmAllObserver = new Observer<XvmAllInfo>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(xvm_recentdays, "获取XVMALL信息出错！", Snackbar.LENGTH_LONG).show();
                deathWheelProgressDialog.dismiss();
            }

            @Override
            public void onNext(XvmAllInfo xvmAllInfo) {
                Log.d("xvm", xvmAllInfo.toString());
                xvmAllInfoForOtherPage = xvmAllInfo;
                xvmMainInfoForOtherPage = xvmAllInfo.getXvmMainInfo();

                // 五个图标的数据初始化
                XvmMainPageVO xvmMainPageVO = xvmAllInfo.getXvmMainPageVO();
                if (xvmMainPageVO != null) {
                    xvmMainPageHeadData(xvmMainPageVO);
                }

                // 近日数据列表初始化
                Map daymap = xvmAllInfo.getDaymap();
                adapter = new XvmDaylistAdapter(getActivity(), daymap);

                xvm_recentdays.setAdapter(adapter);
                deathWheelProgressDialog.dismiss();
            }
        };

        String name = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "name", "");
        String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");

        /**
         * 获取主信息
         */
//        Network.getXvmInfo()
//                .getXvmMainInfo(name, region)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(xvmMainObserver);
        /**
         * 获取tanks.js
         */
//        Network.getXvmjsApi()
//                .getTanksjs()
//                .map(TanksjsToMapMapper.getInstance())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(tanksObserver);

        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
        deathWheelProgressDialog.show();
        /**
         * 将主信息和tanks.js信息结合
         */
        Observable.zip(Network.getXvmInfo().getXvmMainInfo(name, region),
                Network.getXvmjsApi().getTanksjs().map(TanksjsToMapMapper.getInstance()),
                new Func2<XvmMainInfo, Map, XvmAllInfo>() {
                    @Override
                    public XvmAllInfo call(XvmMainInfo xvmMainInfo, Map map) {
                        XvmAllInfo all = new XvmAllInfo();
                        all.setXvmMainInfo(xvmMainInfo);
                        all.setTanks(map);
                        return all;
                    }
                })
                .map(XvmAllInfoToDayMap.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(xvmAllObserver);

        return view;
    }

    /**
     * 五个图标的数据初始化
     *
     * @param xvmMainPageVO
     */
    private void xvmMainPageHeadData(XvmMainPageVO xvmMainPageVO) {

        DecimalFormat df = new DecimalFormat("0.0");//格式化小数

        // 数据设置
        xvm_lable.setText(xvmMainPageVO.getLable());
        xvm_battls.setText(xvmMainPageVO.getBattals() + "");
        xvm_wins.setText(df.format(xvmMainPageVO.getWinrate()) + "%");
        xvm_power.setText(Math.round(xvmMainPageVO.getActivepower()) + "");
        xvm_level.setText((float) Math.round(xvmMainPageVO.getLevel() * 10) / 10 + "");
        xvm_counts.setText(Math.round(xvmMainPageVO.getActivecount()) + "");

        // 图片设置
        // （1）场次
        if (xvmMainPageVO.getBattals() > 10000) {
            xvm_battls_img.setImageResource(R.drawable.xvm_battles4);
        } else if (xvmMainPageVO.getBattals() > 3000) {
            xvm_battls_img.setImageResource(R.drawable.xvm_battles3);
        } else if (xvmMainPageVO.getBattals() > 100) {
            xvm_battls_img.setImageResource(R.drawable.xvm_battles2);
        } else {
            xvm_battls_img.setImageResource(R.drawable.xvm_battles1);
        }

        // （2）胜率
        if (xvmMainPageVO.getWinrate() > 56) {
            xvm_wins_img.setImageResource(R.drawable.xvm_wins4);
        } else if (xvmMainPageVO.getWinrate() > 52) {
            xvm_wins_img.setImageResource(R.drawable.xvm_wins3);
        } else if (xvmMainPageVO.getWinrate() > 48) {
            xvm_wins_img.setImageResource(R.drawable.xvm_wins2);
        } else {
            xvm_wins_img.setImageResource(R.drawable.xvm_wins1);
        }

        // （3）战力
        if (xvmMainPageVO.getActivepower() > 5000) {
            xvm_power_img.setImageResource(R.drawable.xvm_power4);
        } else if (xvmMainPageVO.getActivepower() > 1000) {
            xvm_power_img.setImageResource(R.drawable.xvm_power3);
        } else if (xvmMainPageVO.getActivepower() > 100) {
            xvm_power_img.setImageResource(R.drawable.xvm_power2);
        } else {
            xvm_power_img.setImageResource(R.drawable.xvm_power1);
        }

        // （4）等级
        if (xvmMainPageVO.getLevel() > 9) {
            xvm_level_img.setImageResource(R.drawable.xvm_level4);
        } else if (xvmMainPageVO.getLevel() > 7) {
            xvm_level_img.setImageResource(R.drawable.xvm_level3);
        } else if (xvmMainPageVO.getLevel() > 5) {
            xvm_level_img.setImageResource(R.drawable.xvm_level2);
        } else {
            xvm_level_img.setImageResource(R.drawable.xvm_level1);
        }

        // （5）车数
        if (xvmMainPageVO.getActivecount() >= 10) {
            xvm_counts_img.setImageResource(R.drawable.xvm_count4);
        } else if (xvmMainPageVO.getActivecount() >= 5) {
            xvm_counts_img.setImageResource(R.drawable.xvm_count3);
        } else if (xvmMainPageVO.getActivecount() >= 3) {
            xvm_counts_img.setImageResource(R.drawable.xvm_count2);
        } else {
            xvm_counts_img.setImageResource(R.drawable.xvm_count1);
        }


    }


    /**
     * 五个功能按钮的点击事件
     */

    @OnClick(R.id.xvm_thirty_record)
    void xvm_thirty_record_click() {
        Intent intent = new Intent(getActivity(), AtyXvmThirtyRecord.class);
        Bundle bundle = new Bundle();
        // 传递xvmAllInfo报错：
        // Caused by: java.io.NotSerializableException: com.xinxin.wotplus.util.mapper.XvmAllInfoToDayMap$2

        // 直接传递tanks map
        bundle.putSerializable(AtyXvmThirtyRecord.TANKS_JS_MAP, (Serializable) xvmAllInfoForOtherPage.getTanks());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.xvm_tank_data)
    void xvm_tank_data_click() {
        Intent intent = new Intent(getActivity(), AtyXvmTankTable.class);
        startActivity(intent);
    }

    @OnClick(R.id.xvm_tank_list)
    void xvm_tank_list_click() {
        Toast.makeText(getActivity(), "坦克榜单", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.xvm_factory)
    void xvm_factory_click() {

    }

    @OnClick(R.id.xvm_lottery)
    void xvm_lottery_click() {

    }


}
