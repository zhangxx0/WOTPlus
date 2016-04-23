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

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
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
        if ("b-armory-wrapper__china".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_china);
        } else if ("b-armory-wrapper__germany".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_germany);
        } else if ("b-armory-wrapper__ussr".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_ussr);
        } else if ("b-armory-wrapper__france".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_france);
        } else if ("b-armory-wrapper__usa".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_usa);
        } else if ("b-armory-wrapper__uk".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_uk);
        } else if ("b-armory-wrapper__japan".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_japan);
        } else if ("b-armory-wrapper__czech".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_czech);
        }

        ((TankShortInfo) holder).tankLevel.setText(tank.getTankLevel());
        ((TankShortInfo) holder).tankName.setText(tank.getTankName());
        ((TankShortInfo) holder).tankFightNum.setText(tank.getTankFightNum());
        ((TankShortInfo) holder).tankWinRate.setText(tank.getTankWinRate());

//        new HttpUtil.DownloadImageTask(((TankShortInfo) holder).tankIcon).execute(tank.getTankIcon());
//        new HttpUtil.DownloadImageTask(((TankShortInfo) holder).tankBadge).execute(tank.getTankBadge());
        Glide.with(context).load(tank.getTankIcon()).into(((TankShortInfo) holder).tankIcon);
        Glide.with(context).load(tank.getTankBadge()).into(((TankShortInfo) holder).tankBadge);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
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
