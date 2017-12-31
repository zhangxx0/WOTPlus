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
import com.xinxin.wotplus.model.AchieveNew;
import com.xinxin.wotplus.model.kongzhong.Achievements;

import java.util.List;

/**
 * Created by xinxin on 2016/4/3.
 * 成就子列表数据适配器
 */
public class AchieveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;

    private List<List<String>> achievements;

    // 通过adapter中自己去提供回调
    public interface OnItemClickLitener {
        void onItemClick(View view, int position, String description);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public AchieveAdapter(List<List<String>> achList, Context mContext) {
        this.achievements = achList;
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    public AchieveAdapter(Context mContext) {
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.fragment_achieve_item_sub, parent, false);

        return new AchieveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final List<List<String>> achieve = achievements;

        System.out.println("achieve:" + achieve);

        String icon = achieve.get(position).get(9);
        if ("".equals(icon) || icon == null) {
            icon = achieve.get(position).get(8);
        }
        icon = "http:" + icon;

        Glide.with(mContext).load(icon).centerCrop().into(((AchieveViewHolder) holder).achieve_icon);
        ((AchieveViewHolder) holder).achieve_name.setText(achieve.get(position).get(4));
        ((AchieveViewHolder) holder).achieve_num.setText(achieve.get(position).get(10) != null ? achieve.get(position).get(10) : "0");

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    // 成就的描述信息
                    String description = achieve.get(5).get(1) +"\n"+ achieve.get(6).get(1);
                    mOnItemClickLitener.onItemClick(holder.itemView, pos, description);
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
        return achievements.size();
    }

    /**
     * 单个成就
     */
    class AchieveViewHolder extends RecyclerView.ViewHolder {

        private ImageView achieve_icon;
        private TextView achieve_name;
        private TextView achieve_num;

        public AchieveViewHolder(View itemView) {
            super(itemView);
            achieve_icon = (ImageView) itemView.findViewById(R.id.achieve_icon);
            achieve_name = (TextView) itemView.findViewById(R.id.achieve_name);
            achieve_num = (TextView) itemView.findViewById(R.id.achieve_num);
        }
    }
}
