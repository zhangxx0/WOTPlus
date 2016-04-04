package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.Woter;

/**
 * Created by xinxin on 2016/4/3.
 * 成就页面数据适配器
 */
public class AchieveMentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private Woter mWoter;

    private AchieveAdapter subadapter;

    public AchieveMentAdapter(Context mContext, Woter mWoter) {
        this.mContext = mContext;
        this.mWoter = mWoter;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.fragment_achieve_item, parent, false);

        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (position) {
            case 0:
                ((BaseViewHolder) holder).achieve_sub_title.setText(mWoter.getAchievements().getWarHeroNum());
                subadapter = new AchieveAdapter(mWoter.getAchievements().getWarheroList(), mContext);
                break;
            case 1:
                ((BaseViewHolder) holder).achieve_sub_title.setText(mWoter.getAchievements().getHonorNum());
                subadapter = new AchieveAdapter(mWoter.getAchievements().getHonorList(), mContext);
                break;
            case 2:
                ((BaseViewHolder) holder).achieve_sub_title.setText(mWoter.getAchievements().getEpicNum());
                subadapter = new AchieveAdapter(mWoter.getAchievements().getEpicList(), mContext);
                break;
            case 3:
                ((BaseViewHolder) holder).achieve_sub_title.setText(mWoter.getAchievements().getTeamNum());
                subadapter = new AchieveAdapter(mWoter.getAchievements().getTeamList(), mContext);
                break;
            case 4:
                ((BaseViewHolder) holder).achieve_sub_title.setText(mWoter.getAchievements().getCommemorateNum());
                subadapter = new AchieveAdapter(mWoter.getAchievements().getCommemorateList(), mContext);
                break;
            case 5:
                ((BaseViewHolder) holder).achieve_sub_title.setText(mWoter.getAchievements().getStageNum());
                subadapter = new AchieveAdapter(mWoter.getAchievements().getStageList(), mContext);
                break;
            case 6:
                ((BaseViewHolder) holder).achieve_sub_title.setText(mWoter.getAchievements().getOtherNum());
                subadapter = new AchieveAdapter(mWoter.getAchievements().getOtherList(), mContext);
                break;
        }

        // sub RecyclerView 的处理
        GridLayoutManager gm = new GridLayoutManager(mContext, 4);
        ((BaseViewHolder) holder).recyclerview_achieve_sub.setLayoutManager(gm);
        ((BaseViewHolder) holder).recyclerview_achieve_sub.setAdapter(subadapter);
        // 点击事件
        subadapter.setOnItemClickLitener(new AchieveAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position, String description) {
                // 成就详细信息的展示用什么呢?待寻找 2016年4月3日15:48:07
                Toast.makeText(mContext, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    /**
     * 子View的ViewHolder
     */
    class BaseViewHolder extends RecyclerView.ViewHolder {

        private TextView achieve_sub_title;
        private RecyclerView recyclerview_achieve_sub;

        public BaseViewHolder(View itemView) {
            super(itemView);
            achieve_sub_title = (TextView) itemView.findViewById(R.id.achieve_sub_title);
            recyclerview_achieve_sub = (RecyclerView) itemView.findViewById(R.id.recyclerview_achieve_sub);
        }
    }
}
