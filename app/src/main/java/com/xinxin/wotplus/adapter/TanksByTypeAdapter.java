package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tanksByTypeList.size();
    }

    class TankShortInfo extends RecyclerView.ViewHolder {

        private TextView tankCountryAndLevel,tankName,tankFightNum,tankWinRate;
        private ImageView tankIcon,tankBadge;

        public TankShortInfo(View itemView) {
            super(itemView);
            tankCountryAndLevel = (TextView) itemView.findViewById(R.id.tankCountryAndLevel);
            tankName = (TextView) itemView.findViewById(R.id.tankName);
            tankFightNum = (TextView) itemView.findViewById(R.id.tankFightNum);
            tankWinRate = (TextView) itemView.findViewById(R.id.tankWinRate);
            tankIcon = (ImageView) itemView.findViewById(R.id.tankIcon);
            tankBadge = (ImageView) itemView.findViewById(R.id.tankBadge);
        }
    }
}
