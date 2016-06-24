package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.DaylistEntityForRecent;
import com.xinxin.wotplus.util.CommonUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xinxin on 2016年6月5日16:19:55
 * 30日数据Adapter
 */
public class XvmThirtyDaylistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;

    List<DaylistEntityForRecent> daylist = new ArrayList();
    List<String> daylistdate = new ArrayList();

    public XvmThirtyDaylistAdapter(Context mContext, Map map) {
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;

        // 将map数据分解到两个list中
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            daylistdate.add(key);
            daylist.add((DaylistEntityForRecent) map.get(key));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_xvm_thirty_record, parent, false);

        return new ThirtyDayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("0.0");

        // 时间
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_date.setText(daylistdate.get(position).substring(5));
        // 场次
        DaylistEntityForRecent day = daylist.get(position);
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_battles.setText(day.getBattles() + "");

        // 组队
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_comp.setText(day.getTeambattles() + "");

        // 组队胜率
        /*float teamwinrate = (float) day.getTeamwins() * 100 / day.getBattles();
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_compwins.setText(df.format(teamwinrate) + "%");
        // 组队胜率的颜色设置
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_compwins.setTextColor(mContext.getResources().getColor(CommonUtil.getWRClass(teamwinrate)));*/


        // 胜率
        float winrate = (float) day.getWins() * 100 / day.getBattles();
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_wins.setText(df.format(winrate) + "%");
        // 胜率的颜色设置
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_wins.setTextColor(mContext.getResources().getColor(CommonUtil.getWRClass(winrate)));

        // 效率
        // ((ThirtyDayListViewHolder) holder).xvm_daylist_effect.setText((day.getDaypower() + day.getWinpower()) * 100 / day.getWeight());
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_effect.setText((int) (CommonUtil.addDouble(day.getDaypower(), day.getWinpower()) * 100 / day.getWeight()) + "%");
        // 击杀
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_kill.setText(df.format((float) day.getFrags() / day.getBattles()));
        // 伤害
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_damg.setText(day.getDamage() / day.getBattles() + "");
        // 助攻
        ((ThirtyDayListViewHolder) holder).xvm_30_daylist_assist.setText(day.getAssist() / day.getBattles() + "");

    }

    @Override
    public int getItemCount() {
        return daylist.size();
    }

    class ThirtyDayListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.xvm_30_daylist_date)
        TextView xvm_30_daylist_date;
        @BindView(R.id.xvm_30_daylist_battles)
        TextView xvm_30_daylist_battles;
        @BindView(R.id.xvm_30_daylist_comp)
        TextView xvm_30_daylist_comp;
        /*@BindView(R.id.xvm_30_daylist_compwins)
        TextView xvm_30_daylist_compwins;*/
        @BindView(R.id.xvm_30_daylist_wins)
        TextView xvm_30_daylist_wins;
        @BindView(R.id.xvm_30_daylist_effect)
        TextView xvm_30_daylist_effect;
        @BindView(R.id.xvm_30_daylist_kill)
        TextView xvm_30_daylist_kill;
        @BindView(R.id.xvm_30_daylist_damg)
        TextView xvm_30_daylist_damg;
        @BindView(R.id.xvm_30_daylist_assist)
        TextView xvm_30_daylist_assist;

        public ThirtyDayListViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            try {
                ButterKnife.bind(this, itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
