package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.model.XvmTankDetail;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.widget.FireWotProgressDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/6/22.
 * 坦克战斗明细
 */
public class AtyXvmTankDetail extends BaseActivity {

    public static final String TANKID = "TANKID";
    public static final String XVMACTIVETANKS = "XVMACTIVETANKS";

    @BindView(R.id.xvm_tank_detail_appbar)
    AppBarLayout xvm_tank_detail_appbar;
    @BindView(R.id.xvm_tank_detail_toolbar)
    Toolbar xvm_tank_detail_toolbar;
//    @BindView(R.id.recyclerview_xvm_tank_detail)
//    RecyclerView recyclerview_xvm_tank_detail;

//    @BindView(R.id.tank_detail_battle)
//    TextView tank_detail_battle;

    String tankId = "";
    XvmActiveTanks xvmActiveTanks;

    private FireWotProgressDialog firWotProgressDialog;

    Observer<List<XvmMainInfo.DaylistEntity>> thirtyDayList = new Observer<List<XvmMainInfo.DaylistEntity>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Snackbar.make(xvm_tank_detail_appbar, "获取30日数据出错！", Snackbar.LENGTH_LONG).show();
            firWotProgressDialog.dismiss();
        }

        @Override
        public void onNext(List<XvmMainInfo.DaylistEntity> dayinfolist) {

            Log.d("+++", String.valueOf(dayinfolist.size()));
            Log.d("+++", dayinfolist.toString());

            XvmTankDetail tankDetail = reBuildDataForTankDetail(dayinfolist, xvmActiveTanks);

            TankInfo tank = (TankInfo) tankDetail.getTankDict().get(tankId);
            XvmMainInfo.TanklistEntity tankInfo = (XvmMainInfo.TanklistEntity) tankDetail.getTankmap().get(tankId);

//            tank_detail_battle.setText("战斗场次："+tankInfo.getBattles()+"");

            firWotProgressDialog.dismiss();
        }
    };

    /**
     * 数据重组
     *
     * @param dayinfolist
     * @param xvmActiveTanks
     */
    private XvmTankDetail reBuildDataForTankDetail(List<XvmMainInfo.DaylistEntity> dayinfolist, XvmActiveTanks xvmActiveTanks) {

        XvmTankDetail tankDetail = new XvmTankDetail();
        Map tankmap = new HashMap();

        List<XvmMainInfo.TanklistEntity> tankinfolist = xvmActiveTanks.getTanklistEntities();
        Map tankDictMap = xvmActiveTanks.getTanks();

        // 构建tankmap
        for (int i = 0; i < tankinfolist.size(); i++) {
            XvmMainInfo.TanklistEntity ti = tankinfolist.get(i);

            tankmap.put(ti.getId().getVehicleTypeCd() + "", ti);
        }
        Log.d("+++", tankmap.toString());

        // daylist与tankmap对应
        for (int i = 0; i < dayinfolist.size(); i++) {
            XvmMainInfo.DaylistEntity day = dayinfolist.get(i);

            if (tankmap.containsKey(day.getId().getVehicleTypeCd() + "")) {

                XvmMainInfo.TanklistEntity temptank = (XvmMainInfo.TanklistEntity) tankmap.get(day.getId().getVehicleTypeCd() + "");

                temptank.getTankdaylist().add(day);

            }
        }

        Log.d("++++", String.valueOf(tankmap.size()));
        Log.d("++++",tankmap.toString());

        tankDetail.setTankDict(tankDictMap);
        tankDetail.setTankmap(tankmap);

        return tankDetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_tank_detail);
        ButterKnife.bind(this);
        
        setSupportActionBar(xvm_tank_detail_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("战斗明细");

        Intent intent = getIntent();
        tankId = intent.getStringExtra(TANKID);
        xvmActiveTanks = (XvmActiveTanks) intent.getSerializableExtra(XVMACTIVETANKS);
        Log.d("++++", xvmActiveTanks.toString());
        Log.d("+++++", tankId);

        firWotProgressDialog = FireWotProgressDialog.createDialog(this);
        firWotProgressDialog.show();

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");

        Network.getXvmInfo()
                .getXvmThirtyDay(woterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(thirtyDayList);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
