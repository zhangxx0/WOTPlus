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

/**
 * Created by xinxin on 2016/4/3.
 * 成就子列表数据适配器
 */
public class AchieveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;

    // private List<Achieve> achieveList;
    private AchieveNew achieveNew;

    // 通过adapter中自己去提供回调
    public interface OnItemClickLitener {
        void onItemClick(View view, int position, String description);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public AchieveAdapter(AchieveNew achieveNew, Context mContext) {
        this.achieveNew = achieveNew;
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

        // final Achieve achieve = achieveList.get(position);
        final AchieveNew.AchievementsEntity achieve = achieveNew.getAchievements().get(position);

        // 效果是实现了，这个地方对图片的处理是否不妥？
        // 有什么缺点?缓存什么的怎么弄？自己写还是使用框架? 2016年4月3日15:20:31
//        new DownloadImageTask(((AchieveViewHolder) holder).achieve_icon).execute(achieve.getAchivementImg());
        String icon = achieve.getIcons().getBig();
        if ("".equals(icon) || icon == null) {
            icon = achieve.getIcons().getDefaultX();
        }
        icon = "http:" + icon;

        Glide.with(mContext).load(icon).centerCrop().into(((AchieveViewHolder) holder).achieve_icon);
        ((AchieveViewHolder) holder).achieve_name.setText(achieve.getLocalization().getMark());
        ((AchieveViewHolder) holder).achieve_num.setText(achieve.getNums());

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    // 成就的描述信息
                    String description = achieve.getLocalization().getDescription() +"\n"+ achieve.getLocalization().getCondition();
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
        return achieveNew.getAchievements().size();
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
