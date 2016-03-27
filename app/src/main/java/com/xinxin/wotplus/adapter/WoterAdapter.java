package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.Woter;

/**
 * Created by xinxin on 2016/3/24.
 * 主页的数据适配器
 */
public class WoterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LayoutInflater layoutInflater;
    private Woter mWoter;

    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;

    public WoterAdapter(Context context, Woter woter) {
        this.mContext = context;
        this.mWoter = woter;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_ONE) {
            return TYPE_ONE;
        }
        if (position == TYPE_TWO) {
            return TYPE_TWO;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = layoutInflater.inflate(R.layout.item_main_top_0, parent, false);
            return new AccountViewHolder(view);
        }
        if (viewType == TYPE_TWO) {
            View view = layoutInflater.inflate(R.layout.item_main_top, parent, false);
            return new MainInfoViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class AccountViewHolder extends RecyclerView.ViewHolder {


        public AccountViewHolder(View itemView) {
            super(itemView);

        }
    }

    class MainInfoViewHolder extends RecyclerView.ViewHolder {

        public MainInfoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
