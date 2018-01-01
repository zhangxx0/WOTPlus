package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.TanksType;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.Statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinxin on 2016/4/6.
 * 坦克类型列表数据适配器
 */
public class TanksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private Woter woter;
    private int[] tanksTypesImgs = {R.drawable.filter_ic_light, R.drawable.filter_ic_medium, R.drawable.filter_ic_heavy,
            R.drawable.filter_ic_td, R.drawable.filter_ic_sau};

    private int[] tanksTypesImgs0 = {R.drawable.filter_ic_light2, R.drawable.filter_ic_medium2, R.drawable.filter_ic_heavy2,
            R.drawable.filter_ic_td2, R.drawable.filter_ic_sau2};

    private Statistics statistics = new Statistics();

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public TanksAdapter(Context mContext, Woter woter) {
        this.mContext = mContext;
        this.woter = woter;
        layoutInflater = LayoutInflater.from(mContext);
        statistics = woter.getStatistics();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_tanks_item, parent, false);
        return new TanksTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        // 先设置点击事件，避免后面的return导致点击事件未设置；2018年1月1日19:57:08
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
                    // int pos = holder.getLayoutPosition();
                    // mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

        Statistics.DataBean.TypesBean types = statistics.getData().getTypes();
        switch (position) {
            case 0:
                ((TanksTypeViewHolder) holder).tanksTypeNameAndNum.setText("轻型坦克");
                ((TanksTypeViewHolder) holder).tanksTypeFightNum.setText(String.valueOf(types.getLightTank().getBattles_count()));
                ((TanksTypeViewHolder) holder).tanksTypeWinRating.setText(String.valueOf(types.getLightTank().getWins_count_percent()));
                ((TanksTypeViewHolder) holder).tanksTypeBadgeNum.setText(String.valueOf(types.getLightTank().getMaster_count()));
                ((TanksTypeViewHolder) holder).tanksTypeImg.setImageResource(tanksTypesImgs[position]);
                return;
            case 1:
                ((TanksTypeViewHolder) holder).tanksTypeNameAndNum.setText("中型坦克");
                ((TanksTypeViewHolder) holder).tanksTypeFightNum.setText(String.valueOf(types.getMediumTank().getBattles_count()));
                ((TanksTypeViewHolder) holder).tanksTypeWinRating.setText(String.valueOf(types.getMediumTank().getWins_count_percent()));
                ((TanksTypeViewHolder) holder).tanksTypeBadgeNum.setText(String.valueOf(types.getMediumTank().getMaster_count()));
                ((TanksTypeViewHolder) holder).tanksTypeImg.setImageResource(tanksTypesImgs[position]);
                return;
            case 2:
                ((TanksTypeViewHolder) holder).tanksTypeNameAndNum.setText("重型坦克");
                ((TanksTypeViewHolder) holder).tanksTypeFightNum.setText(String.valueOf(types.getHeavyTank().getBattles_count()));
                ((TanksTypeViewHolder) holder).tanksTypeWinRating.setText(String.valueOf(types.getHeavyTank().getWins_count_percent()));
                ((TanksTypeViewHolder) holder).tanksTypeBadgeNum.setText(String.valueOf(types.getHeavyTank().getMaster_count()));
                ((TanksTypeViewHolder) holder).tanksTypeImg.setImageResource(tanksTypesImgs[position]);
                return;
            case 3:
                ((TanksTypeViewHolder) holder).tanksTypeNameAndNum.setText("坦克歼击车");
                ((TanksTypeViewHolder) holder).tanksTypeFightNum.setText(String.valueOf(types.getATSPG().getBattles_count()));
                ((TanksTypeViewHolder) holder).tanksTypeWinRating.setText(String.valueOf(types.getATSPG().getWins_count_percent()));
                ((TanksTypeViewHolder) holder).tanksTypeBadgeNum.setText(String.valueOf(types.getATSPG().getMaster_count()));
                ((TanksTypeViewHolder) holder).tanksTypeImg.setImageResource(tanksTypesImgs[position]);
                return;
            case 4:
                ((TanksTypeViewHolder) holder).tanksTypeNameAndNum.setText("火炮");
                ((TanksTypeViewHolder) holder).tanksTypeFightNum.setText(String.valueOf(types.getSPG().getBattles_count()));
                ((TanksTypeViewHolder) holder).tanksTypeWinRating.setText(String.valueOf(types.getSPG().getWins_count_percent()));
                ((TanksTypeViewHolder) holder).tanksTypeBadgeNum.setText(String.valueOf(types.getSPG().getMaster_count()));
                ((TanksTypeViewHolder) holder).tanksTypeImg.setImageResource(tanksTypesImgs[position]);
                return;
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class TanksTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tanksTypeNameAndNum, tanksTypeFightNum, tanksTypeWinRating, tanksTypeBadgeNum;
        private ImageView tanksTypeImg;

        public TanksTypeViewHolder(View itemView) {
            super(itemView);
            tanksTypeNameAndNum = (TextView) itemView.findViewById(R.id.tanksTypeNameAndNum);
            tanksTypeFightNum = (TextView) itemView.findViewById(R.id.tanksTypeFightNum);
            tanksTypeWinRating = (TextView) itemView.findViewById(R.id.tanksTypeWinRating);
            tanksTypeBadgeNum = (TextView) itemView.findViewById(R.id.tanksTypeBadgeNum);
            tanksTypeImg = (ImageView) itemView.findViewById(R.id.tanksTypeImg);
        }
    }
}
