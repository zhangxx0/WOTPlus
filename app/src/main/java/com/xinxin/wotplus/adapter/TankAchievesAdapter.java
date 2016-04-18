package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.AchieveTank;
import com.xinxin.wotplus.util.HttpUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by xinxin on 2016/4/9.
 * 坦克成就数据适配器
 */
public class TankAchievesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<AchieveTank.AchievementsEntity> achieveList;
    private Map<String,String> map;

    public TankAchievesAdapter(List<AchieveTank.AchievementsEntity> achieveList, Context context,Map map) {
        this.achieveList = achieveList;
        this.context = context;
        this.map = map;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.activity_tank_item, parent, false);

        return new TankAchieveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AchieveTank.AchievementsEntity achieve = achieveList.get(position);

         ((TankAchieveViewHolder)holder).tank_achieve_name.setText(map.get(achieve.getName().toLowerCase()));
        // 这个方法是不行的，，，R.string后面加的是int。。。你无法获取
//        ((TankAchieveViewHolder)holder).tank_achieve_name.setText(R.string.(achieve.getName().toLowerCase()));
        ((TankAchieveViewHolder)holder).achieve_num_for_tank.setText(String.valueOf(achieve.getCount()));

        // 获取不到勋章的名字和图片；只能获取到标识词，例如：warrior
        // http://ncw.worldoftanks.cn/static/3.34.7/encyclopedia/tankopedia/achievement/warrior.png
        String picUrl = "http://ncw.worldoftanks.cn/static/3.34.7/encyclopedia/tankopedia/achievement/" + achieve.getName().toLowerCase() + ".png";
        new HttpUtil.DownloadImageTask(((TankAchieveViewHolder) holder).tank_achieve_icon).execute(picUrl);


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
