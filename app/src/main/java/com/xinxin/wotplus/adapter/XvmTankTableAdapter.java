package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xinxin.wotplus.util.CommonUtil.getTankWeight;

/**
 * Created by xinxin on 2016/6/7.
 * 单车数据列表Adapter
 */
public class XvmTankTableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<XvmMainInfo.TanklistEntity> sortedTankTables;
    private Map tanksmap;

    public XvmTankTableAdapter(Context mContext, XvmAllInfo xvmAllInfo) {
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);

        sortedTankTables = xvmAllInfo.getTankTables();
        tanksmap = xvmAllInfo.getTanks();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_xvm_tanktable, parent, false);

        return new TankTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("0.0");
        DecimalFormat df2 = new DecimalFormat("0");

        XvmMainInfo.TanklistEntity ti = sortedTankTables.get(position);
        TankInfo tankInfo = (TankInfo) tanksmap.get(ti.getId().getVehicleTypeCd() + "");

        ((TankTableViewHolder) holder).xvm_tanktable_vehicle_name.setText(tankInfo.getAlias());
        ((TankTableViewHolder) holder).xvm_tanktable_level.setText(tankInfo.getLevel() + "");

        switch (ti.getGunmark()) {
            case 1:
                Glide.with(mContext).load(R.drawable.starlv1).centerCrop().into(((TankTableViewHolder) holder).xvm_tanktable_mark);
                break;
            case 2:
                Glide.with(mContext).load(R.drawable.starlv2).centerCrop().into(((TankTableViewHolder) holder).xvm_tanktable_mark);
                break;
            case 3:
                Glide.with(mContext).load(R.drawable.starlv3).centerCrop().into(((TankTableViewHolder) holder).xvm_tanktable_mark);
                break;
        }

        ((TankTableViewHolder) holder).xvm_tanktable_battle.setText(ti.getBattles() + "");
        ((TankTableViewHolder) holder).xvm_tanktable_wins.setText(df2.format((float) ti.getWins() * 100 / ti.getBattles()) + "%");

        double eff = (ti.getTotalpower() / ti.getBattles() + ti.getMovingpower()) / 2 + ti.getWinpower() / ti.getBattles();
        double wei = getTankWeight(tankInfo);
        ((TankTableViewHolder) holder).xvm_tanktable_effect.setText(df.format((float) eff * 100 / wei) + "");
        ((TankTableViewHolder) holder).xvm_tanktable_kill.setText(df.format((float) ti.getFrags() / ti.getBattles()) + "");
        ((TankTableViewHolder) holder).xvm_tanktable_damage.setText(ti.getDamage() / ti.getBattles() + "");
        ((TankTableViewHolder) holder).xvm_tanktable_light.setText(ti.getAssist() / ti.getBattles() + "");

    }

    @Override
    public int getItemCount() {
        return sortedTankTables.size();
    }

    class TankTableViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xvm_tanktable_vehicle_name)
        TextView xvm_tanktable_vehicle_name;
        @BindView(R.id.xvm_tanktable_mark)
        ImageView xvm_tanktable_mark;
        @BindView(R.id.xvm_tanktable_level)
        TextView xvm_tanktable_level;
        @BindView(R.id.xvm_tanktable_battle)
        TextView xvm_tanktable_battle;
        @BindView(R.id.xvm_tanktable_wins)
        TextView xvm_tanktable_wins;
        @BindView(R.id.xvm_tanktable_effect)
        TextView xvm_tanktable_effect;
        @BindView(R.id.xvm_tanktable_kill)
        TextView xvm_tanktable_kill;
        @BindView(R.id.xvm_tanktable_damage)
        TextView xvm_tanktable_damage;
        @BindView(R.id.xvm_tanktable_light)
        TextView xvm_tanktable_light;

        public TankTableViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
