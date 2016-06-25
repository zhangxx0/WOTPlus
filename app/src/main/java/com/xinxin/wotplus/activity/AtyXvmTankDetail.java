package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.XvmTankDetailAdapter;
import com.xinxin.wotplus.base.SwipeBackBaseActivity;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.model.XvmTankDetail;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.CommonUtil;
import com.xinxin.wotplus.util.Constant;
import com.xinxin.wotplus.util.MathExtend;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.widget.FireWotProgressDialog;
import com.xinxin.wotplus.widget.RevealBackgroundView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/6/22.
 * 坦克战斗明细
 */
public class AtyXvmTankDetail extends SwipeBackBaseActivity implements RevealBackgroundView.OnStateChangeListener {

    public static final String TANKID = "TANKID";
    public static final String XVMACTIVETANKS = "XVMACTIVETANKS";

    @BindView(R.id.xvm_tank_detail_appbar)
    AppBarLayout xvm_tank_detail_appbar;
    @BindView(R.id.xvm_tank_detail_toolbar)
    Toolbar xvm_tank_detail_toolbar;
    @BindView(R.id.recyclerview_xvm_tank_detail)
    RecyclerView recyclerview_xvm_tank_detail;

    /*点击扩散使用*/
    @BindView(R.id.xvm_revealBackgroundView)
    RevealBackgroundView vRevealBackground;
    @BindView(R.id.tank_detail_scroll)
    NestedScrollView tank_detail_scroll;
    @BindView(R.id.tank_detail_linear)
    LinearLayout tank_detail_linear;

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
            Snackbar.make(recyclerview_xvm_tank_detail, "获取30日数据出错！", Snackbar.LENGTH_LONG).show();
            firWotProgressDialog.dismiss();
        }

        @Override
        public void onNext(List<XvmMainInfo.DaylistEntity> dayinfolist) {

            XvmTankDetail tankDetail = reBuildDataForTankDetail(dayinfolist, xvmActiveTanks);

            TankInfo tank = (TankInfo) tankDetail.getTankDict().get(tankId);
            XvmMainInfo.TanklistEntity tankInfo = (XvmMainInfo.TanklistEntity) tankDetail.getTankmap().get(tankId);

            // 该战车日战斗列表数据源
            List<XvmMainInfo.DaylistEntity> tankdaylist = tankInfo.getTankdaylist();
            List<XvmMainInfo.DaylistEntity> sortedtankdaylist = CommonUtil.dayListSortByDate(tankdaylist);

            // 日战斗列表
            adapter = new XvmTankDetailAdapter(AtyXvmTankDetail.this, sortedtankdaylist, tank);
            // RecyclerView 动画
            SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(adapter, recyclerview_xvm_tank_detail);
            recyclerview_xvm_tank_detail.setAdapter(animatorAdapter);

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
        DecimalFormat df = new DecimalFormat("0.0");

        int battles = tankInfo.getBattles() - tankInfo.getTeambattles();
        tank_detail_battle.setText(battles + "");

        String wins = "--%";
        if (battles > 0) {
            float winsf = (float) 100 * (tankInfo.getWins() - tankInfo.getTeamwins()) / battles;
            tank_detail_wins.setTextColor(this.getResources().getColor(CommonUtil.getWRClass(winsf)));
            wins = df.format(winsf) + "%";
        }
        tank_detail_wins.setText(wins);

        tank_detail_teambattle.setText(tankInfo.getTeambattles() + "");
        String teamwins = "--%";
        if (tankInfo.getTeambattles() > 0) {
            float teamwinsf = (float) 100 * tankInfo.getTeamwins() / tankInfo.getTeambattles();
            tank_detail_teamwins.setTextColor(this.getResources().getColor(CommonUtil.getWRClass(teamwinsf)));
            teamwins = df.format(teamwinsf) + "%";
        }
        tank_detail_teamwins.setText(teamwins);

        tank_detail_eff.setText((int) (tankInfo.getTotalpower() / tankInfo.getBattles()) + "");
        tank_detail_recent_eff.setText(MathExtend.round(tankInfo.getMovingpower(), 0) + "");

        // 有误差
        tank_detail_perdam.setText(tankInfo.getDamage() / tankInfo.getBattles() + "");
        tank_detail_perkill.setText(MathExtend.round((double) tankInfo.getFrags() / tankInfo.getBattles(), 2) + "");

        tank_detail_perlight.setText(MathExtend.round((double) tankInfo.getSpotted() / tankInfo.getBattles(), 2) + "");
        tank_detail_perassist.setText(tankInfo.getAssist() / tankInfo.getBattles() + "");

        tank_detail_peroccupy.setText(MathExtend.round((double) tankInfo.getCapture() / tankInfo.getBattles(), 2) + "");
        tank_detail_perdef.setText(MathExtend.round((double) tankInfo.getDefense() / tankInfo.getBattles(), 2) + "");

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

        // daylist与tankmap对应
        for (int i = 0; i < dayinfolist.size(); i++) {
            XvmMainInfo.DaylistEntity day = dayinfolist.get(i);

            if (tankmap.containsKey(day.getId().getVehicleTypeCd() + "")) {
                XvmMainInfo.TanklistEntity temptank = (XvmMainInfo.TanklistEntity) tankmap.get(day.getId().getVehicleTypeCd() + "");
                temptank.getTankdaylist().add(day);
            }
        }

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

        tank_detail_scroll.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        tankId = intent.getStringExtra(TANKID);
        xvmActiveTanks = (XvmActiveTanks) intent.getSerializableExtra(XVMACTIVETANKS);

        // 添加车名***-战斗明细
        TankInfo tank = (TankInfo) xvmActiveTanks.getTanks().get(tankId + "");
        setTitle(tank.getAlias() + " - 战斗明细");

        firWotProgressDialog = FireWotProgressDialog.createDialog(this);
        // 为了全屏扩散效果，去掉加载框
        // firWotProgressDialog.show();

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");

        Network.getXvmInfo()
                .getXvmThirtyDay(woterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(thirtyDayList);

        setupRevealBackground(savedInstanceState);
    }

    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(Constant.START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
        }
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

    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
            // tankMainContent.setVisibility(View.VISIBLE);
            xvm_tank_detail_appbar.setVisibility(View.VISIBLE);
            tank_detail_scroll.setVisibility(View.VISIBLE);
            // 有自动滚动，拉上来；
            tank_detail_scroll.scrollTo(0, 0);
        }
    }
}
