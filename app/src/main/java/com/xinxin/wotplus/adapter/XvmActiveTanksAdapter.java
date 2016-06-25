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
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.util.CommonUtil;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xinxin.wotplus.util.CommonUtil.getTankWeight;

/**
 * Created by xinxin on 2016/6/22.
 * 活跃战车list Adapter
 */
public class XvmActiveTanksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<XvmMainInfo.TanklistEntity> sortedTanks;
    private Map tanksmap;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public XvmActiveTanksAdapter(Context mContext, XvmActiveTanks activeTanks) {
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
        this.sortedTanks = activeTanks.getTanklistEntities();
        this.tanksmap = activeTanks.getTanks();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_xvm_active_tank, parent, false);
        return new ActiveTanksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final XvmMainInfo.TanklistEntity ti = sortedTanks.get(position);
        TankInfo tank = (TankInfo) tanksmap.get(ti.getId().getVehicleTypeCd() + "");

        // 上半部分图片信息
        // (1)国旗
        Glide.with(mContext).load(CommonUtil.getTankCountryIcon(tank.getEncountry())).centerCrop().into(((ActiveTanksViewHolder) holder).at_country);
        // (2)坦克
        Glide.with(mContext).load(CommonUtil.getTankIcon(tank.getEncountry(), tank.getName())).centerCrop().into(((ActiveTanksViewHolder) holder).at_tank);
        // (3)等级
        Glide.with(mContext).load(CommonUtil.getTankLevelIcon("level" + tank.getLevel())).centerCrop().into(((ActiveTanksViewHolder) holder).at_level);
        // (4)类型
        Glide.with(mContext).load(CommonUtil.getTankTypeIcon(tank.getEntype())).centerCrop().into(((ActiveTanksViewHolder) holder).at_type);
        // (5)标识
        // 占位图片需要更换为透明的
        int gunmarkDraw = R.drawable.star0;
        if (ti.getGunmark() == 1) {
            gunmarkDraw = R.drawable.starlv1;
        } else if (ti.getGunmark() == 2) {
            gunmarkDraw = R.drawable.starlv2;
        } else if (ti.getGunmark() == 3) {
            gunmarkDraw = R.drawable.starlv3;
        }
        Glide.with(mContext).load(gunmarkDraw).centerCrop().into(((ActiveTanksViewHolder) holder).at_star);
        // (6)特级
        // 占位图片需要更换为透明的
        int m = R.drawable.master0;
        if (ti.getMark4() > 0) {
            m = 4;
        } else if (ti.getMark3() > 0) {
            m = 3;
        } else if (ti.getMark2() > 0) {
            m = 2;
        } else if (ti.getMark1() > 0) {
            m = 1;
        }
        int tankmarkDraw = 0;
        if (m == 1) {
            tankmarkDraw = R.drawable.master1;
        } else if (m == 2) {
            tankmarkDraw = R.drawable.master2;
        } else if (m == 3) {
            tankmarkDraw = R.drawable.master3;
        } else if (m == 4) {
            tankmarkDraw = R.drawable.master4;
        }
        Glide.with(mContext).load(tankmarkDraw).centerCrop().into(((ActiveTanksViewHolder) holder).at_master);


        // 下半部分文本信息
        DecimalFormat df = new DecimalFormat("0.0");

        ((ActiveTanksViewHolder) holder).at_tank_name.setText(tank.getAlias());
        ((ActiveTanksViewHolder) holder).at_tank_battle.setText("场次：" + ti.getBattles());
        float wins = (float) ti.getWins() * 100 / ti.getBattles();
        ((ActiveTanksViewHolder) holder).at_tank_wins.setText(df.format(wins) + "%");
        ((ActiveTanksViewHolder) holder).at_tank_wins.setTextColor(mContext.getResources().getColor(CommonUtil.getWRClass(wins)));
        double eff = (ti.getTotalpower() / ti.getBattles() + ti.getMovingpower()) / 2 + ti.getWinpower() / ti.getBattles();
        double wei = getTankWeight(tank);
        ((ActiveTanksViewHolder) holder).at_tank_effect.setText("效率：" + df.format((float) eff * 100 / wei) + "%");


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return sortedTanks.size();
    }

    class ActiveTanksViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.at_level)
        ImageView at_level;
        @BindView(R.id.at_type)
        ImageView at_type;
        @BindView(R.id.at_country)
        ImageView at_country;
        @BindView(R.id.at_tank)
        ImageView at_tank;
        @BindView(R.id.at_master)
        ImageView at_master;
        @BindView(R.id.at_star)
        ImageView at_star;

        @BindView(R.id.at_tank_name)
        TextView at_tank_name;
        @BindView(R.id.at_tank_battle)
        TextView at_tank_battle;
        @BindView(R.id.at_tank_wins)
        TextView at_tank_wins;
        @BindView(R.id.at_tank_effect)
        TextView at_tank_effect;

        public ActiveTanksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
