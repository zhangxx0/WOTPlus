package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.xinxin.wotplus.MyApplication;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.widget.WotIndicatorView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xinxin on 2016/3/24.
 * 主页的数据适配器
 */
public class WoterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LayoutInflater layoutInflater;
    private Woter mWoter;

    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;
    private final int TYPE_THR = 2;
    private final int TYPE_FOR = 3;

    public WoterAdapter(Context context, Woter woter) {
        this.mContext = context;
        this.mWoter = woter;
        layoutInflater = LayoutInflater.from(context);
        // 将woter存储到sharedpreference，供其他页面使用；
        putWoterToPreference(woter);
    }

    // 将Woter放入Preference
    public void putWoterToPreference(Woter woter) {
        Gson gson = new Gson();
        String woterString = gson.toJson(woter);
        // Log.d("woter", woterString);

        PreferenceUtils.putCustomPrefString(mContext, "woter", "woterString", woterString);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_ONE) {
            return TYPE_ONE;
        }
        if (position == TYPE_TWO) {
            return TYPE_TWO;
        }
        if (position == TYPE_THR) {
            return TYPE_THR;
        }
//        if (position == TYPE_FOR) {
//            return TYPE_FOR;
//        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        // 可以在这个地方判断军团信息是否存在，控制返回的视图数量；
        if (mWoter.getEnterClanFlag().equals("0")) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = layoutInflater.inflate(R.layout.item_main_top_0, parent, false);
            return new AccountViewHolder(view);
        }
        if (viewType == TYPE_TWO) {
            View view = layoutInflater.inflate(R.layout.item_main_top, parent, false);
            return new MainInfoViewHolder(view);
        }
        if (viewType == TYPE_THR) {
            View view = layoutInflater.inflate(R.layout.item_main_clan, parent, false);
            return new ClanViewHolder(view);
            // View view = layoutInflater.inflate(R.layout.item_main_charts, parent, false);
            // return new ChartsViewHolder(view);
        }
//        if (viewType == TYPE_FOR) {
//            View view = layoutInflater.inflate(R.layout.item_main_clan, parent, false);
//            return new ClanViewHolder(view);
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AccountViewHolder) {
            ((AccountViewHolder) holder).woterName.setText(mWoter.getWoterName());

            String timeStamp = new BigDecimal(mWoter.getTimeStamp()).toString();
            Long creatLong = Long.parseLong(timeStamp);
            String creatDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date(creatLong * 1000));
            ((AccountViewHolder) holder).createAccount.setText(creatDay);

            String lastBattleTime = new BigDecimal(mWoter.getLastBattleTime()).toString();
            Long lastBattleTimeL = Long.parseLong(lastBattleTime);
            String lastBattleTimeS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastBattleTimeL * 1000));
            ((AccountViewHolder) holder).lastBattleTime.setText("最后战斗时间：" + lastBattleTimeS);

            int days = (int) ((new Date().getTime() - new Date(creatLong * 1000).getTime()) / (1000 * 60 * 60 * 24));
            ((AccountViewHolder) holder).fightDays.setText("您已经在坦克世界中战斗了：" + String.valueOf(days) + "天");
        }

        if (holder instanceof MainInfoViewHolder) {

            ((MainInfoViewHolder) holder).personWin.setText(String.valueOf(mWoter.getUserSummary().getData().getWins_ratio()));
            ((MainInfoViewHolder) holder).personFight.setText(String.valueOf(mWoter.getUserSummary().getData().getBattles_count()));
            ((MainInfoViewHolder) holder).personRanking.setText(String.valueOf(mWoter.getUserSummary().getData().getGlobal_rating()));
            ((MainInfoViewHolder) holder).personExp.setText(String.valueOf(mWoter.getUserSummary().getData().getXp_per_battle_average()));
            ((MainInfoViewHolder) holder).personDmg.setText(String.valueOf(mWoter.getUserSummary().getData().getDamage_per_battle_average()));

            ((MainInfoViewHolder) holder).personHitsRatio.setText(String.valueOf(mWoter.getUserSummary().getData().getHits_ratio()+"%"));
            ((MainInfoViewHolder) holder).personMaxKill.setText(String.valueOf(mWoter.getUserSummary().getData().getFrags_max()));
            ((MainInfoViewHolder) holder).personMaxExp.setText(String.valueOf(mWoter.getUserSummary().getData().getXp_max()));
            ((MainInfoViewHolder) holder).personMaster.setText(String.valueOf(mWoter.getUserSummary().getData().getMastery().getMastery_count()+"/"+mWoter.getUserSummary().getData().getMastery().getVehicles_count()));

        }

        // 对击杀／死亡率 伤害原因／收到 仪表盘的赋值，这里暂时用不到了； 2017年12月31日18:01:42
        if (holder instanceof ChartsViewHolder) {

            ((ChartsViewHolder) holder).killdie_indacator_view.setmDesiredAngle(mWoter.getKillDeathRate()!=null ? Float.valueOf(mWoter.getKillDeathRate()):0);
            ((ChartsViewHolder) holder).killdie_indacator_view.animateAngle();
            ((ChartsViewHolder) holder).hurtinjured_indacator_view.setmDesiredAngle(mWoter.getDmgRecRate()!=null ? Float.valueOf(mWoter.getDmgRecRate()):0);
            ((ChartsViewHolder) holder).hurtinjured_indacator_view.animateAngle();

            ((ChartsViewHolder) holder).killdierate.setText(mWoter.getKillDeathRate());
            ((ChartsViewHolder) holder).killdiedata.setText(mWoter.getKillDeathNum());
            ((ChartsViewHolder) holder).hurtinjuredrate.setText(mWoter.getDmgRecRate());
            ((ChartsViewHolder) holder).hurtinjureddata.setText(mWoter.getDmgRecNum());

        }

        if (holder instanceof ClanViewHolder) {
            Glide.with(mContext).load("https:"+mWoter.getClanImgSrc()).into(((ClanViewHolder) holder).clanFlag);

            // 这里使用了省略，若想查看详细，可以设置一个点击的弹出；
            ((ClanViewHolder) holder).clanDescription.setText(mWoter.getClanDescription());
            ((ClanViewHolder) holder).clanPosition.setText(" 职务: " + mWoter.getClanPosition());
            ((ClanViewHolder) holder).clanDays.setText("在军团中服役天数:" + mWoter.getClanDays() + "天");
        }


    }

    /**
     * 账号信息
     * 2016年3月30日21:33:42
     */
    class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView woterName;
        private TextView createAccount;
        private TextView fightDays;
        private TextView lastBattleTime;

        public AccountViewHolder(View itemView) {
            super(itemView);
            woterName = (TextView) itemView.findViewById(R.id.woterName);
            createAccount = (TextView) itemView.findViewById(R.id.createAccount);
            fightDays = (TextView) itemView.findViewById(R.id.fightDays);
            lastBattleTime = (TextView) itemView.findViewById(R.id.lastBattleTime);

        }
    }

    /**
     * 排名信息
     * 2016年3月30日21:34:03
     */
    class MainInfoViewHolder extends RecyclerView.ViewHolder {

        private TextView personWin;
        private TextView personFight;
        private TextView personRanking;
        private TextView personExp;
        private TextView personDmg;

        private TextView personHitsRatio;
        private TextView personMaxKill;
        private TextView personMaxExp;
        private TextView personMaster;

        public MainInfoViewHolder(View itemView) {
            super(itemView);
            personWin = (TextView) itemView.findViewById(R.id.personWin);
            personFight = (TextView) itemView.findViewById(R.id.personFight);
            personRanking = (TextView) itemView.findViewById(R.id.personRanking);
            personExp = (TextView) itemView.findViewById(R.id.personExp);
            personDmg = (TextView) itemView.findViewById(R.id.personDmg);
            personHitsRatio = (TextView) itemView.findViewById(R.id.personHitsRatio);
            personMaxKill = (TextView) itemView.findViewById(R.id.personMaxKill);
            personMaxExp = (TextView) itemView.findViewById(R.id.personMaxExp);
            personMaster = (TextView) itemView.findViewById(R.id.personMaster);
        }
    }

    /**
     * 图表信息
     */
    class ChartsViewHolder extends RecyclerView.ViewHolder {
        private PieChart killdiechart;
        private PieChart hurtinjuredchart;

        private TextView killdierate,killdiedata,hurtinjuredrate,hurtinjureddata;
        private WotIndicatorView killdie_indacator_view, hurtinjured_indacator_view;

        public ChartsViewHolder(View itemView) {
            super(itemView);
//            killdiechart = (PieChart) itemView.findViewById(R.id.killdiechart);
//            hurtinjuredchart = (PieChart) itemView.findViewById(R.id.hurtinjuredchart);
            killdierate = (TextView) itemView.findViewById(R.id.killdierate);
            killdiedata = (TextView) itemView.findViewById(R.id.killdiedata);
            hurtinjuredrate = (TextView) itemView.findViewById(R.id.hurtinjuredrate);
            hurtinjureddata = (TextView) itemView.findViewById(R.id.hurtinjureddata);
            killdie_indacator_view = (WotIndicatorView) itemView.findViewById(R.id.killdie_indacator_view);
            hurtinjured_indacator_view = (WotIndicatorView) itemView.findViewById(R.id.hurtinjured_indacator_view);
        }
    }

    /**
     * 军团信息
     * 2016年3月30日22:28:13
     */
    class ClanViewHolder extends RecyclerView.ViewHolder {

        private ImageView clanFlag;
        private TextView clanDescription;
        private TextView clanPosition;
        private TextView clanDays;

        public ClanViewHolder(View itemView) {
            super(itemView);
            clanFlag = (ImageView) itemView.findViewById(R.id.clanFlag);
            clanDescription = (TextView) itemView.findViewById(R.id.clanDescription);
            clanPosition = (TextView) itemView.findViewById(R.id.clanPosition);
            clanDays = (TextView) itemView.findViewById(R.id.clanDays);
        }
    }

}
