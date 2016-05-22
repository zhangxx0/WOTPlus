package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.XvmMainInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xinxin on 2016/5/23.
 * 近日数据Adapter
 */
public class XvmDaylistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    List<XvmMainInfo.DaylistEntity> daylist;
    List<String> daylistdate;

    public XvmDaylistAdapter(Context mContext, List<XvmMainInfo.DaylistEntity> daylist,List<String> daylistdate) {
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.daylist = daylist;
        this.daylistdate =daylistdate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_xvm_daylist, parent, false);
        Log.d("daylist", String.valueOf(daylist.size()));
        return new DayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DayListViewHolder) holder).xvm_daylist_date.setText(daylistdate.get(position));

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
