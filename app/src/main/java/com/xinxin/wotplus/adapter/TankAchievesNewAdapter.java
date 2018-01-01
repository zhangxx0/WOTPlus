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

import java.util.List;

/**
 * Created by xinxin on 2016年6月20日.
 * 坦克成就数据适配器New
 */
public class TankAchievesNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<List<String>> achieveList;

    public TankAchievesNewAdapter(List<List<String>> achieveList, Context context) {
        this.achieveList = achieveList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_tank_item, parent, false);

        return new TankAchieveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<String> achieve = achieveList.get(position);

        ((TankAchieveViewHolder) holder).tank_achieve_name.setText(achieve.get(4));
        ((TankAchieveViewHolder) holder).achieve_num_for_tank.setText(achieve.get(10) + "");

        String picUrl = "http:"+achieve.get(9);

        Glide.with(context).load(picUrl).into(((TankAchieveViewHolder) holder).tank_achieve_icon);

    }

    @Override
    public int getItemCount() {
        return achieveList.size();
    }

    class TankAchieveViewHolder extends RecyclerView.ViewHolder {

        private ImageView tank_achieve_icon;
        private TextView tank_achieve_name, achieve_num_for_tank;

        public TankAchieveViewHolder(View itemView) {
            super(itemView);
            tank_achieve_icon = (ImageView) itemView.findViewById(R.id.tank_achieve_icon);
            tank_achieve_name = (TextView) itemView.findViewById(R.id.tank_achieve_name);
            achieve_num_for_tank = (TextView) itemView.findViewById(R.id.achieve_num_for_tank);
        }
    }
}
