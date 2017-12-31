package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.AchieveNew;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.Achievements;

import java.util.List;

import it.gmariotti.recyclerview.adapter.ScaleInAnimatorAdapter;

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

        Achievements achievements = mWoter.getAchievements();
        int getNums = achievements.getData().getData().get(position).getEarned_achievements();
        int totalNums = achievements.getData().getData().get(position).getAvailable_achievements();

        String sub_title = achievements.getData().getData().get(position).getMark()
                + " " + getNums + "/" + totalNums + "";
        // 特殊勋章 只展示获取个数
        if (position == 6) {
            sub_title = achievements.getData().getData().get(position).getMark() + " " + getNums;
        }
        ((BaseViewHolder) holder).achieve_sub_title.setText(sub_title);

        subadapter = new AchieveAdapter(mWoter.getAchievements().getData().getData().get(position).getAchievements(), mContext);

        // sub RecyclerView 的处理
        GridLayoutManager gm = new GridLayoutManager(mContext, 4);
        ((BaseViewHolder) holder).recyclerview_achieve_sub.setLayoutManager(gm);
        // RecyclerView 动画
        ScaleInAnimatorAdapter animatorAdapter = new ScaleInAnimatorAdapter(subadapter, ((BaseViewHolder) holder).recyclerview_achieve_sub);
        ((BaseViewHolder) holder).recyclerview_achieve_sub.setAdapter(animatorAdapter);


        // 点击事件
        subadapter.setOnItemClickLitener(new AchieveAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position, String description) {
                // 成就详细信息的展示用什么呢?待寻找 2016年4月3日15:48:07
                showDesDialog(description);
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


    /**
     * 去除unique成就
     *
     * @param position
     */
    private void removeUniqueAchieve(int position) {
        /*List<AchieveNew.AchievementsEntity> achievementsEntities = mWoter.getNewAchievements().get(position).getAchievements();

        // 倒叙遍历
        for (int i = achievementsEntities.size() - 1; i >= 0; i--) {

            if ("uniqueAchievements".equals(achievementsEntities.get(i).getBlock())) {
                // 不能在这里直接remove,使用倒叙遍历时可以直接remove
                // http://787141854-qq-com.iteye.com/blog/1485914
                achievementsEntities.remove(i);
            }
        }

        mWoter.getNewAchievements().get(position).setAchievements(achievementsEntities);*/
    }

    private void showDesDialog(String classAceDes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("成就描述")
                .setMessage(classAceDes)
                .setPositiveButton("了解", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
