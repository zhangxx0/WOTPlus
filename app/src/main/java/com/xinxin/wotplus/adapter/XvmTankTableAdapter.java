package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmAllInfo;
import com.xinxin.wotplus.model.XvmMainInfo;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        XvmMainInfo.TanklistEntity tanklistEntity = sortedTankTables.get(position);
        TankInfo tankInfo = (TankInfo) tanksmap.get(tanklistEntity.getId().getVehicleTypeCd()+"");

        ((TankTableViewHolder)holder).xvm_tanktabke_vehicle_name.setText(tankInfo.getAlias());
        ((TankTableViewHolder)holder).xvm_tanktabke_level.setText(tankInfo.getLevel()+"");
    }

    @Override
    public int getItemCount() {
        return sortedTankTables.size();
    }

    class TankTableViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xvm_tanktabke_vehicle_name)
        TextView xvm_tanktabke_vehicle_name;
        @BindView(R.id.xvm_tanktabke_level)
        TextView xvm_tanktabke_level;

        public TankTableViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}