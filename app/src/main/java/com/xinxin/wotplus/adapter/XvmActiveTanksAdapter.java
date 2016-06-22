package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.XvmActiveTanks;
import com.xinxin.wotplus.model.XvmMainInfo;

import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by xinxin on 2016/6/22.
 * 活跃战车list Adapter
 */
public class XvmActiveTanksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<XvmMainInfo.TanklistEntity> sortedTanks;
    private Map tanksmap;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return sortedTanks.size();
    }

    class ActiveTanksViewHolder extends RecyclerView.ViewHolder {



        public ActiveTanksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
