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
 * Created by xinxin on 2016/5/23.
 * 近日数据Adapter
 */
public class XvmDaylistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;

    List<DaylistEntityForRecent> daylist = new ArrayList();
    List<String> daylistdate = new ArrayList();

    public XvmDaylistAdapter(Context mContext, Map map) {
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;

        daylistdate.add(0,"日期1");
        daylist.add(new DaylistEntityForRecent());

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
        View view = layoutInflater.inflate(R.layout.item_xvm_daylist, parent, false);

        return new DayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("0.0");

        if (position > 0) {
            // 时间
            ((DayListViewHolder) holder).xvm_daylist_date.setText(daylistdate.get(position).substring(5));
            // 场次
            DaylistEntityForRecent day = daylist.get(position);
            ((DayListViewHolder) holder).xvm_daylist_battles.setText(day.getBattles() + "");
            // 胜率
            float winrate = (float) day.getWins() * 100 / day.getBattles();
            ((DayListViewHolder) holder).xvm_daylist_wins.setText(df.format(winrate) + "%");
            // 胜率的颜色设置
            ((DayListViewHolder) holder).xvm_daylist_wins.setTextColor(mContext.getResources().getColor(CommonUtil.getWRClass(winrate)));

            // 效率
            // ((DayListViewHolder) holder).xvm_daylist_effect.setText((day.getDaypower() + day.getWinpower()) * 100 / day.getWeight());
            ((DayListViewHolder) holder).xvm_daylist_effect.setText((int) (CommonUtil.addDouble(day.getDaypower(), day.getWinpower()) * 100 / day.getWeight()) + "%");
            // 击杀
            ((DayListViewHolder) holder).xvm_daylist_kill.setText(df.format((float) day.getFrags() / day.getBattles()));
            // 伤害
            ((DayListViewHolder) holder).xvm_daylist_hit.setText(day.getDamage() / day.getBattles() + "");
            // 助攻
            ((DayListViewHolder) holder).xvm_daylist_assist.setText(day.getAssist() / day.getBattles() + "");
        }

    }

    @Override
    public int getItemCount() {
        return daylist.size();
    }

    class DayListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.xvm_daylist_date)
        TextView xvm_daylist_date;
        @BindView(R.id.xvm_daylist_battles)
        TextView xvm_daylist_battles;
        @BindView(R.id.xvm_daylist_wins)
        TextView xvm_daylist_wins;
        @BindView(R.id.xvm_daylist_effect)
        TextView xvm_daylist_effect;
        @BindView(R.id.xvm_daylist_kill)
        TextView xvm_daylist_kill;
        @BindView(R.id.xvm_daylist_hit)
        TextView xvm_daylist_hit;
        @BindView(R.id.xvm_daylist_assist)
        TextView xvm_daylist_assist;

        public DayListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
