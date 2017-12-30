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
        if (position == TYPE_FOR) {
            return TYPE_FOR;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        // 可以在这个地方判断军团信息是否存在，控制返回的视图数量；
        if (mWoter.getEnterClanFlag().equals("0")) {
            return 3;
        } else {
            return 4;
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
            View view = layoutInflater.inflate(R.layout.item_main_charts, parent, false);
            return new ChartsViewHolder(view);
        }
        if (viewType == TYPE_FOR) {
            View view = layoutInflater.inflate(R.layout.item_main_clan, parent, false);
            return new ClanViewHolder(view);
        }
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

            int days = (int) ((new Date().getTime() - new Date(creatLong * 1000).getTime()) / (1000 * 60 * 60 * 24));

            ((AccountViewHolder) holder).fightDays.setText("您已经在坦克世界中战斗了：" + String.valueOf(days) + "天");
        }

        if (holder instanceof MainInfoViewHolder) {

            ((MainInfoViewHolder) holder).personWin.setText(mWoter.getPersonWin());
            ((MainInfoViewHolder) holder).personFight.setText(mWoter.getPersonFight());
            ((MainInfoViewHolder) holder).personRanking.setText(mWoter.getPersonRanking());
            ((MainInfoViewHolder) holder).personExp.setText(mWoter.getPersonExp());
            ((MainInfoViewHolder) holder).personDmg.setText(mWoter.getPersonDmg());

        }

        if (holder instanceof ChartsViewHolder) {

            ((ChartsViewHolder) holder).killdie_indacator_view.setmDesiredAngle(mWoter.getKillDeathRate()!=null ? Float.valueOf(mWoter.getKillDeathRate()):0);
            ((ChartsViewHolder) holder).killdie_indacator_view.animateAngle();
            ((ChartsViewHolder) holder).hurtinjured_indacator_view.setmDesiredAngle(mWoter.getDmgRecRate()!=null ? Float.valueOf(mWoter.getDmgRecRate()):0);
            ((ChartsViewHolder) holder).hurtinjured_indacator_view.animateAngle();

            ((ChartsViewHolder) holder).killdierate.setText(mWoter.getKillDeathRate());
            ((ChartsViewHolder) holder).killdiedata.setText(mWoter.getKillDeathNum());
            ((ChartsViewHolder) holder).hurtinjuredrate.setText(mWoter.getDmgRecRate());
            ((ChartsViewHolder) holder).hurtinjureddata.setText(mWoter.getDmgRecNum());

            /*try {

                PieData mpieData = getKillDieData(4, 100);
                ((ChartsViewHolder) holder).killdiechart.setHoleRadius(60f);  //半径
                ((ChartsViewHolder) holder).killdiechart.setTransparentCircleRadius(64f);
                ((ChartsViewHolder) holder).killdiechart.setCenterText(mWoter.getKillDeathRate());
                ((ChartsViewHolder) holder).killdiechart.setDrawHoleEnabled(true);
                ((ChartsViewHolder) holder).killdiechart.setRotationAngle(90);
                ((ChartsViewHolder) holder).killdiechart.setRotationEnabled(true);
                ((ChartsViewHolder) holder).killdiechart.setUsePercentValues(false);
                ((ChartsViewHolder) holder).killdiechart.setDescription(""); // 设置为空
                ((ChartsViewHolder) holder).killdiechart.setData(mpieData);
                Legend mLegend = ((ChartsViewHolder) holder).killdiechart.getLegend();  //设置比例图
                mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);  //最右边显示
                // mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
                mLegend.setXEntrySpace(7f);
                mLegend.setYEntrySpace(5f);

                PieData mpieData2 = getHurtRecData(4, 100);
                ((ChartsViewHolder) holder).hurtinjuredchart.setHoleRadius(60f);  //半径
                ((ChartsViewHolder) holder).hurtinjuredchart.setTransparentCircleRadius(64f);
                ((ChartsViewHolder) holder).hurtinjuredchart.setCenterText(mWoter.getDmgRecRate());
                ((ChartsViewHolder) holder).hurtinjuredchart.setDrawHoleEnabled(true);
                ((ChartsViewHolder) holder).hurtinjuredchart.setRotationAngle(90);
                ((ChartsViewHolder) holder).hurtinjuredchart.setRotationEnabled(true);
                ((ChartsViewHolder) holder).hurtinjuredchart.setUsePercentValues(false);
                ((ChartsViewHolder) holder).hurtinjuredchart.setDescription(""); // 设置为空
                ((ChartsViewHolder) holder).hurtinjuredchart.setData(mpieData2);
                Legend mLegend2 = ((ChartsViewHolder) holder).hurtinjuredchart.getLegend();  //设置比例图
                mLegend2.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);  //最右边显示
                mLegend2.setXEntrySpace(7f);
                mLegend2.setYEntrySpace(5f);

            } catch (Exception e) {
                e.printStackTrace();
            }*/

        }

        if (holder instanceof ClanViewHolder) {

            // 使用自定义的HttpURLConnection无效，，，不知道怎么加thread；
//            bitmap[0] = HttpUtil.getHttpBitmap(mWoter.getClanImgSrc());
//            ((ClanViewHolder) holder).clanFlag.setImageBitmap(bitmap[0]);

            // 使用AsyncTask实现简单的图片获取；
            // http://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
            // 有空研究下http://blog.csdn.net/guolin_blog/article/details/17482165
//            new DownloadImageTask(((ClanViewHolder) holder).clanFlag).execute(mWoter.getClanImgSrc());
            Glide.with(mContext).load(mWoter.getClanImgSrc()).into(((ClanViewHolder) holder).clanFlag);

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

        public AccountViewHolder(View itemView) {
            super(itemView);
            woterName = (TextView) itemView.findViewById(R.id.woterName);
            createAccount = (TextView) itemView.findViewById(R.id.createAccount);
            fightDays = (TextView) itemView.findViewById(R.id.fightDays);

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

        public MainInfoViewHolder(View itemView) {
            super(itemView);
            personWin = (TextView) itemView.findViewById(R.id.personWin);
            personFight = (TextView) itemView.findViewById(R.id.personFight);
            personRanking = (TextView) itemView.findViewById(R.id.personRanking);
            personExp = (TextView) itemView.findViewById(R.id.personExp);
            personDmg = (TextView) itemView.findViewById(R.id.personDmg);
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


    /**
     * 获取击杀死亡率数据
     *
     * @param count
     * @param range
     * @return PieData
     */
    private PieData getKillDieData(int count, float range) {

        String[] killdieNums = mWoter.getKillDeathNum().split("/");

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        xValues.add("击杀");
        xValues.add("死亡");

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        /**
         * 使用比值
         */
        // 计算比值 24,936 13,595
        float quarterly1 = Float.parseFloat(killdieNums[0].toString().replace(",", ""));
        float quarterly2 = Float.parseFloat(killdieNums[1].toString().replace(",", ""));

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "击杀／死亡率"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float px = 2 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

    /**
     * 获取伤害收到数据
     *
     * @param i
     * @param i1
     * @return PieData
     */
    private PieData getHurtRecData(int i, int i1) {

        String[] hurtRecNums = mWoter.getDmgRecNum().split("/");

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容
        xValues.add("伤害");
        xValues.add("收到");

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据
        // 饼图数据
        float quarterly1 = Float.parseFloat(hurtRecNums[0].toString().replace(",", ""));
        float quarterly2 = Float.parseFloat(hurtRecNums[1].toString().replace(",", ""));

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "伤害原因／收到"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float px = 2 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }

}
