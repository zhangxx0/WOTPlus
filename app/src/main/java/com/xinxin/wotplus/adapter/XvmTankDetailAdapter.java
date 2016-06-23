package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.util.CommonUtil;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xinxin on 2016/6/23.
 * 坦克战斗明细中的日战斗列表适配器
 */
public class XvmTankDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    List<XvmMainInfo.DaylistEntity> tankdaylist;
    TankInfo tank;

    public XvmTankDetailAdapter(Context mContext, List<XvmMainInfo.DaylistEntity> tankdaylist, TankInfo tank) {
        this.mContext = mContext;
        this.tankdaylist = tankdaylist;
        this.tank = tank;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_xvm_tankdetail_daylist, parent, false);
        return new TankDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("0.0");

        XvmMainInfo.DaylistEntity d = tankdaylist.get(position);

        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_date.setText(CommonUtil.formatDate(d.getInsert_date()).substring(5));
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_battles.setText(d.getBattles() + "");
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_comp.setText(d.getTeambattles() + "");
        float winrate = (float) d.getWins() * 100 / d.getBattles();
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_wins.setText(df.format(winrate) + "%");
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_wins.setTextColor(mContext.getResources().getColor(CommonUtil.getWRClass(winrate)));
        String eff = (int) (CommonUtil.addDouble(d.getDaypower(), d.getWinpower()) * 100 / CommonUtil.getTankWeight(tank) / d.getBattles()) + "%";
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_effect.setText(eff);
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_kill.setText(df.format((float) d.getFrags() / d.getBattles()));
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_damg.setText(d.getDamage() / d.getBattles() + "");
        ((TankDetailViewHolder) holder).xvm_tankdetail_daylist_assist.setText(d.getAssist() / d.getBattles() + "");


    }

    @Override
    public int getItemCount() {
        return tankdaylist.size();
    }

    class TankDetailViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.xvm_tankdetail_daylist_date)
        TextView xvm_tankdetail_daylist_date;
        @BindView(R.id.xvm_tankdetail_daylist_battles)
        TextView xvm_tankdetail_daylist_battles;
        @BindView(R.id.xvm_tankdetail_daylist_comp)
        TextView xvm_tankdetail_daylist_comp;
        @BindView(R.id.xvm_tankdetail_daylist_wins)
        TextView xvm_tankdetail_daylist_wins;
        @BindView(R.id.xvm_tankdetail_daylist_effect)
        TextView xvm_tankdetail_daylist_effect;
        @BindView(R.id.xvm_tankdetail_daylist_kill)
        TextView xvm_tankdetail_daylist_kill;
        @BindView(R.id.xvm_tankdetail_daylist_damg)
        TextView xvm_tankdetail_daylist_damg;
        @BindView(R.id.xvm_tankdetail_daylist_assist)
        TextView xvm_tankdetail_daylist_assist;

        public TankDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
