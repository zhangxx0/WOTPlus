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
import com.xinxin.wotplus.model.Tank;

import java.util.List;

/**
 * Created by xinxin on 2016/4/7.
 * 各个分类的坦克列表适配器
 */
public class TanksByTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;

    private List<Tank> tanksByTypeList;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public TanksByTypeAdapter(List<Tank> tanksByTypeList, Context context) {
        this.tanksByTypeList = tanksByTypeList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.activity_tanks_item, parent, false);

        return new TankShortInfo(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Tank tank = tanksByTypeList.get(position);

        // 2016年4月8日01:17:44
        if ("china".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_china);
        } else if ("germany".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_germany);
        } else if ("ussr".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_ussr);
        } else if ("france".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_france);
        } else if ("usa".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_usa);
        } else if ("uk".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_uk);
        } else if ("japan".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_japan);
        } else if ("czech".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_czech);
        }

        ((TankShortInfo) holder).tankLevel.setText(tank.getTankLevel());
        ((TankShortInfo) holder).tankName.setText(tank.getTankName());
        ((TankShortInfo) holder).tankFightNum.setText(tank.getTankFightNum());
        ((TankShortInfo) holder).tankWinRate.setText(tank.getTankWinRate());

//        new HttpUtil.DownloadImageTask(((TankShortInfo) holder).tankIcon).execute(tank.getTankIcon());
//        new HttpUtil.DownloadImageTask(((TankShortInfo) holder).tankBadge).execute(tank.getTankBadge());
        Glide.with(context).load(tank.getTankIcon()).into(((TankShortInfo) holder).tankIcon);
        // 去掉无战斗嘉奖的战车的嘉奖图标，现在展示的该车辆的图片 2016年5月3日22:18:16
        if (tank.getTankBadge().indexOf("vehicle") != -1) {
            // 包含vehicle则使用占位图
        } else {
            String badge = tank.getTankBadge();
            int badgePath ;
            if ("1".equals(badge)) {
                badgePath = R.drawable.master1;
            } else if ("2".equals(badge)) {
                badgePath = R.drawable.master2;
            } else if ("3".equals(badge)) {
                badgePath = R.drawable.master3;
            } else if ("4".equals(badge)) {
                badgePath = R.drawable.master4;
            } else {
                badgePath = R.drawable.master0;
            }
            Glide.with(context).load(badgePath).into(((TankShortInfo) holder).tankBadge);
        }

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return tanksByTypeList.size();
    }

    class TankShortInfo extends RecyclerView.ViewHolder {

        private TextView tankLevel, tankName, tankFightNum, tankWinRate;
        private ImageView tankCountry, tankIcon, tankBadge;

        public TankShortInfo(View itemView) {
            super(itemView);
            tankCountry = (ImageView) itemView.findViewById(R.id.tankCountry);
            tankLevel = (TextView) itemView.findViewById(R.id.tankLevel);
            tankName = (TextView) itemView.findViewById(R.id.tankName);
            tankFightNum = (TextView) itemView.findViewById(R.id.tankFightNum);
            tankWinRate = (TextView) itemView.findViewById(R.id.tankWinRate);
            tankIcon = (ImageView) itemView.findViewById(R.id.tankIcon);
            tankBadge = (ImageView) itemView.findViewById(R.id.tankBadge);
        }
    }


}
