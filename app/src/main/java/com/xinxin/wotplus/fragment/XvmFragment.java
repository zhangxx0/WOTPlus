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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.XvmDaylistAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.TanksjsToMapMapper;
import com.xinxin.wotplus.util.mapper.XvmAllInfoToDayMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    private XvmDaylistAdapter adapter;


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
        Observer<Map> xvmAllObserver = new Observer<Map>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(xvm_recentdays, "获取XVMALL信息出错！", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNext(Map map) {
                Log.d("xvm", map.toString());

//                Iterator entries = map.entrySet().iterator();
//                while (entries.hasNext()) {
//                    Map.Entry entry = (Map.Entry) entries.next();
//                    String key = (String)entry.getKey();
//                    TankInfo value = (TankInfo)entry.getValue();
//                    System.out.println("Key = " + key + ", Value = " + value);
//                }

                List<String> daylistdate = new ArrayList();
                List<XvmMainInfo.DaylistEntity> daylist = new ArrayList();
                Iterator it = map.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next().toString();
                    daylistdate.add(key);
                    daylist.add((XvmMainInfo.DaylistEntity) map.get(key));
                    Log.d("key", key);
                    Log.d("value", map.get(key).toString());
                }

                adapter = new XvmDaylistAdapter(getActivity(), daylist, daylistdate);

                xvm_recentdays.setAdapter(adapter);
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

    @OnClick(R.id.xvm_thirty_record)
    void xvm_thirty_record_click() {
        Toast.makeText(getActivity(), "30rizhanji", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.xvm_tank_data)
    void xvm_tank_data_click() {

    }

    @OnClick(R.id.xvm_tank_list)
    void xvm_tank_list_click() {

    }

    @OnClick(R.id.xvm_factory)
    void xvm_factory_click() {

    }

    @OnClick(R.id.xvm_lottery)
    void xvm_lottery_click() {

    }


}
