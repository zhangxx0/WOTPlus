package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.XvmTankDetailAdapter;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.model.XvmTankDetail;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.CommonUtil;
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
    @BindView(R.id.recyclerview_xvm_tank_detail)
    RecyclerView recyclerview_xvm_tank_detail;

    @BindView(R.id.tank_detail_battle)
    TextView tank_detail_battle;
    @BindView(R.id.tank_detail_wins)
    TextView tank_detail_wins;
    @BindView(R.id.tank_detail_teambattle)
    TextView tank_detail_teambattle;
    @BindView(R.id.tank_detail_teamwins)
    TextView tank_detail_teamwins;

    @BindView(R.id.tank_detail_eff)
    TextView tank_detail_eff;
    @BindView(R.id.tank_detail_recent_eff)
    TextView tank_detail_recent_eff;
    @BindView(R.id.tank_detail_perdam)
    TextView tank_detail_perdam;
    @BindView(R.id.tank_detail_perkill)
    TextView tank_detail_perkill;

    @BindView(R.id.tank_detail_perlight)
    TextView tank_detail_perlight;
    @BindView(R.id.tank_detail_perassist)
    TextView tank_detail_perassist;
    @BindView(R.id.tank_detail_peroccupy)
    TextView tank_detail_peroccupy;
    @BindView(R.id.tank_detail_perdef)
    TextView tank_detail_perdef;

    @BindView(R.id.tank_detail_maxdamage)
    TextView tank_detail_maxdamage;
    @BindView(R.id.tank_detail_maxkillship)
    TextView tank_detail_maxkillship;
    @BindView(R.id.tank_detail_maxkillplane)
    TextView tank_detail_maxkillplane;
    @BindView(R.id.tank_detail_maxexp)
    TextView tank_detail_maxexp;


    @BindView(R.id.tank_detail_master4)
    TextView tank_detail_master4;
    @BindView(R.id.tank_detail_master3)
    TextView tank_detail_master3;
    @BindView(R.id.tank_detail_master2)
    TextView tank_detail_master2;
    @BindView(R.id.tank_detail_master1)
    TextView tank_detail_master1;

    String tankId = "";
    XvmActiveTanks xvmActiveTanks;

    private FireWotProgressDialog firWotProgressDialog;
    private XvmTankDetailAdapter adapter;

    Observer<List<XvmMainInfo.DaylistEntity>> thirtyDayList = new Observer<List<XvmMainInfo.DaylistEntity>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.d("XXXXXX", e.getMessage());
            Snackbar.make(recyclerview_xvm_tank_detail, "获取30日数据出错！", Snackbar.LENGTH_LONG).show();
            firWotProgressDialog.dismiss();
        }

        @Override
        public void onNext(List<XvmMainInfo.DaylistEntity> dayinfolist) {

            Log.d("+++", String.valueOf(dayinfolist.size()));
            Log.d("+++", dayinfolist.toString());

            XvmTankDetail tankDetail = reBuildDataForTankDetail(dayinfolist, xvmActiveTanks);

            TankInfo tank = (TankInfo) tankDetail.getTankDict().get(tankId);
            XvmMainInfo.TanklistEntity tankInfo = (XvmMainInfo.TanklistEntity) tankDetail.getTankmap().get(tankId);

            // 该战车日战斗列表数据源
            List<XvmMainInfo.DaylistEntity> tankdaylist = tankInfo.getTankdaylist();
            List<XvmMainInfo.DaylistEntity> sortedtankdaylist = CommonUtil.dayListSortByDate(tankdaylist);

            // 日战斗列表
            adapter = new XvmTankDetailAdapter(AtyXvmTankDetail.this, sortedtankdaylist, tank);
            recyclerview_xvm_tank_detail.setAdapter(adapter);

            // 数据统计与最高纪录Card赋值
            initCard(tankInfo);

            firWotProgressDialog.dismiss();
        }
    };

    /**
     * 数据统计与最高纪录Card赋值 方法
     *
     * @param tankInfo
     */
    private void initCard(XvmMainInfo.TanklistEntity tankInfo) {

        int battles = tankInfo.getBattles() - tankInfo.getTeambattles();
        tank_detail_battle.setText(battles + "");

        String wins = "--%";
        if (battles > 0) {
            float winsf = 100 * (tankInfo.getWins() - tankInfo.getTeamwins()) / battles;
            wins = winsf + "%";
        }
        tank_detail_wins.setText(wins);

        tank_detail_teambattle.setText(tankInfo.getTeambattles() + "");
        String teamwins = "--%";
        if (tankInfo.getTeambattles() > 0) {
            float teamwinsf = 100 * tankInfo.getTeamwins() / tankInfo.getTeambattles();
            teamwins = teamwinsf + "%";
        }
        tank_detail_teamwins.setText(teamwins);

        tank_detail_eff.setText((int) (tankInfo.getTotalpower() / tankInfo.getBattles()) + "");
        tank_detail_recent_eff.setText((int) tankInfo.getMovingpower() + "");

        tank_detail_perdam.setText(tankInfo.getDamage() / tankInfo.getBattles() + "");
        tank_detail_perkill.setText(tankInfo.getFrags() / tankInfo.getBattles() + "");

        tank_detail_perlight.setText(tankInfo.getSpotted() / tankInfo.getBattles() + "");
        tank_detail_perassist.setText(tankInfo.getAssist() / tankInfo.getBattles() + "");

        tank_detail_peroccupy.setText(tankInfo.getCapture() / tankInfo.getBattles() + "");
        tank_detail_perdef.setText(tankInfo.getDefense() / tankInfo.getBattles() + "");

        tank_detail_master4.setText(tankInfo.getMark4() + "");
        tank_detail_master3.setText(tankInfo.getMark3() + "");
        tank_detail_master2.setText(tankInfo.getMark2() + "");
        tank_detail_master1.setText(tankInfo.getMark1() + "");

        tank_detail_maxdamage.setText(tankInfo.getMaxdamage() + "");
        tank_detail_maxkillship.setText(tankInfo.getMaxfrags() + "");
        tank_detail_maxkillplane.setText(tankInfo.getMaxassist() + "");
        tank_detail_maxexp.setText(tankInfo.getMaxxp() + "");

    }

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
        Log.d("++++", tankmap.toString());

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

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview_xvm_tank_detail.setLayoutManager(lm);
        recyclerview_xvm_tank_detail.setItemAnimator(new DefaultItemAnimator());

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
