package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.TanksType;
import com.xinxin.wotplus.model.Woter;

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

    List<TanksType> tanksTypes = new ArrayList<TanksType>();


    public TanksAdapter(Context mContext, Woter woter) {
        this.mContext = mContext;
        this.woter = woter;
        layoutInflater = LayoutInflater.from(mContext);
        tanksTypes = woter.getTanks().getTanksTypes();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_tanks_item, parent, false);
        return new TanksTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TanksTypeViewHolder) holder).tanksTypeNameAndNum.setText(tanksTypes.get(position).getTanksTypeName() +" "+ tanksTypes.get(position).getTanksTypeNum());
        ((TanksTypeViewHolder) holder).tanksTypeFightNum.setText(tanksTypes.get(position).getTanksTypeFightNum());
        ((TanksTypeViewHolder) holder).tanksTypeWinRating.setText(tanksTypes.get(position).getTanksTypeWinRating());
        ((TanksTypeViewHolder) holder).tanksTypeBadgeNum.setText(tanksTypes.get(position).getTanksTypeBadgeNum());


    }

    @Override
    public int getItemCount() {
        return tanksTypes.size();
    }

    class TanksTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tanksTypeNameAndNum, tanksTypeFightNum, tanksTypeWinRating, tanksTypeBadgeNum;

        public TanksTypeViewHolder(View itemView) {
            super(itemView);
            tanksTypeNameAndNum = (TextView) itemView.findViewById(R.id.tanksTypeNameAndNum);
            tanksTypeFightNum = (TextView) itemView.findViewById(R.id.tanksTypeFightNum);
            tanksTypeWinRating = (TextView) itemView.findViewById(R.id.tanksTypeWinRating);
            tanksTypeBadgeNum = (TextView) itemView.findViewById(R.id.tanksTypeBadgeNum);
        }
    }
}
