package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.Grade;

/**
 * Created by xinxin on 2016/4/4.
 * 等级数据适配器
 */
public class GradeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private Grade grade;

    private static final int[] gradeImgs = {R.drawable.ic_stat_rating, R.drawable.ic_stat_battles, R.drawable.ic_stat_destroyed
            , R.drawable.ic_stat_dmg_caused,R.drawable.ic_stat_max_exp,R.drawable.ic_stat_wins,
            R.drawable.ic_stat_hit_ratio,R.drawable.ic_stat_avg_exp};

    private static final String[] gradeNames = {"个人排名", "参加战斗的数量", "击毁车辆", "造成的总伤害",
            "单次获得最大经验", "战胜率", "命中率", "场均经验"};
    private static String[] gradeDatas = {};
    private static String[] gradeRankings = {};


    public GradeAdapter(Context mContext, Grade grade) {
        this.mContext = mContext;
        this.grade = grade;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fragment_grade_item, parent, false);

        Grade.UserRatingEntity userRatingEntity = grade.getUser_rating().get(0);
        gradeDatas = new String[]{String.valueOf(userRatingEntity.getGlobal_rating()), String.valueOf(userRatingEntity.getBattles_count()),
                String.valueOf(userRatingEntity.getFrags_count()), String.valueOf(userRatingEntity.getDamage_dealt()),
                String.valueOf(userRatingEntity.getXp_max()), userRatingEntity.getWins_ratio() + "%",
                userRatingEntity.getHits_ratio() + "%", userRatingEntity.getXp_avg() + ""};
        gradeRankings = new String[]{userRatingEntity.getGlobal_rating_rank() + "", userRatingEntity.getBattles_count_rank() + "",
                userRatingEntity.getFrags_count_rank() + "", userRatingEntity.getDamage_dealt_rank() + "",
                userRatingEntity.getXp_max_rank() + "", userRatingEntity.getWins_ratio_rank() + "",
                userRatingEntity.getHits_ratio_rank() + "", userRatingEntity.getXp_avg_rank() + ""};

        return new GradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((GradeViewHolder) holder).grade_img.setImageResource(gradeImgs[position]);
        ((GradeViewHolder) holder).grade_name.setText(gradeNames[position]);
        ((GradeViewHolder) holder).grade_data.setText(gradeDatas[position]);
        ((GradeViewHolder) holder).grade_ranking.setText(gradeRankings[position]);

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class GradeViewHolder extends RecyclerView.ViewHolder {

        private TextView grade_name, grade_data, grade_ranking;
        private ImageView grade_img;

        public GradeViewHolder(View itemView) {
            super(itemView);
            grade_img = (ImageView) itemView.findViewById(R.id.grade_img);
            grade_name = (TextView) itemView.findViewById(R.id.grade_name);
            grade_data = (TextView) itemView.findViewById(R.id.grade_data);
            grade_ranking = (TextView) itemView.findViewById(R.id.grade_ranking);
        }
    }

}
