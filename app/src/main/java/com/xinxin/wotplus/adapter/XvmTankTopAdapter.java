package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.TankInfo;
import com.xinxin.wotplus.model.XvmTankTopAll;
import com.xinxin.wotplus.model.XvmTankTopSingleVO;
import com.xinxin.wotplus.model.XvmTankTopVO;
import com.xinxin.wotplus.util.CommonUtil;
import com.xinxin.wotplus.util.MathExtend;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xinxin on 2016/6/21.
 * 坦克榜单数据适配器
 */
public class XvmTankTopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private Map tanksmap;
    private List<XvmTankTopVO> tankTopVOs;

    public XvmTankTopAdapter(Context mContext, XvmTankTopAll all) {
        this.mContext = mContext;
        this.tanksmap = all.getTankjsmap();
        this.tankTopVOs = all.getTankTopVOs();
        layoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.item_xvm_tanktop, parent, false);

        return new TankTopViewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        XvmTankTopVO topVo = tankTopVOs.get(position);
        XvmTankTopSingleVO top = topVo.getSingleVO();
        TankInfo tank = (TankInfo) tanksmap.get(topVo.getTankid() + "");

        ((TankTopViewholder) holder).xvm_tanktop_vehicle_name.setText(tank.getAlias());
        ((TankTopViewholder) holder).xvm_tanktop_lev.setText(tank.getLevel() + "");

        ((TankTopViewholder) holder).xvm_tanktop_type.setText(CommonUtil.getTankType(tank.getEntype()));
        ((TankTopViewholder) holder).xvm_tanktop_country.setText(CommonUtil.getTankCountry(tank.getEncountry()));

        // 四舍五入的精度保留
        ((TankTopViewholder) holder).xvm_tanktop_wins.setText(MathExtend.round(Double.valueOf(top.getWinrate()), 1) + "%");
        ((TankTopViewholder) holder).xvm_tanktop_wins.setTextColor(mContext.getResources().getColor(CommonUtil.getWRClass(Float.parseFloat(top.getWinrate()))));

        ((TankTopViewholder) holder).xvm_tanktop_effect.setText((int) ((Double.valueOf(top.getDaypower()) + Double.valueOf(top.getWinpower())) * 100 / CommonUtil.getTankWeight(tank)) + "%");
        ((TankTopViewholder) holder).xvm_tanktop_kill.setText(top.getFrags());
        ((TankTopViewholder) holder).xvm_tanktop_damage.setText(top.getDamage().substring(0, top.getDamage().length() - 3));
        ((TankTopViewholder) holder).xvm_tanktop_light.setText(top.getAssist().substring(0, top.getAssist().length() - 3));

    }

    @Override
    public int getItemCount() {
        return tankTopVOs.size();
    }

    class TankTopViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.xvm_tanktop_vehicle_name)
        TextView xvm_tanktop_vehicle_name;
        @BindView(R.id.xvm_tanktop_lev)
        TextView xvm_tanktop_lev;
        @BindView(R.id.xvm_tanktop_type)
        TextView xvm_tanktop_type;
        @BindView(R.id.xvm_tanktop_country)
        TextView xvm_tanktop_country;
        @BindView(R.id.xvm_tanktop_wins)
        TextView xvm_tanktop_wins;
        @BindView(R.id.xvm_tanktop_effect)
        TextView xvm_tanktop_effect;
        @BindView(R.id.xvm_tanktop_kill)
        TextView xvm_tanktop_kill;
        @BindView(R.id.xvm_tanktop_damage)
        TextView xvm_tanktop_damage;
        @BindView(R.id.xvm_tanktop_light)
        TextView xvm_tanktop_light;

        public TankTopViewholder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
