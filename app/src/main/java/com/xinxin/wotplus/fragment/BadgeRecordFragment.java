package com.xinxin.wotplus.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;

import java.io.InputStream;

/**
 * Created by xinxin on 2016/4/4.
 * 徽章与战绩Fragment
 */
public class BadgeRecordFragment extends BaseFragment implements View.OnClickListener {

    private Woter woter;

    private ImageView class_ace_img, class_1_img, class_2_img, class_3_img;
    private TextView class_ace_num, class_1_num, class_2_num, class_3_num;

    private TextView finghtNum, victoryNum, failureNum, survivingNum, experienceNum, averageExpNum, maxExpNum;
    private TextView destoryNum, findNum, hitRate, killingNum, averageKillingNum, occupiedBaseNum, defendBaseNum;
    private LinearLayout class_ace_layout,class_1_layout,class_2_layout,class_3_layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_badge_record, container, false);

        // 从CharedPreference中获取woter
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("woter", Context.MODE_PRIVATE);
        String woterString = sharedPreferences.getString("woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            Log.d("GsonWoter", woter.toString());
        }

        initView(view);

        initData();

        return view;

    }

    private void initData() {
        // 放在HttpUtil不好用？
        // new HttpUtil.DownloadImageTask(class_ace_img).execute(woter.getBadgeAndRecord().getClassAceImg());

        // 直接使用静态图片 2016年4月17日17:09:13
//        new DownloadImageTask(class_ace_img).execute(woter.getBadgeAndRecord().getClassAceImg());
//        new DownloadImageTask(class_1_img).execute(woter.getBadgeAndRecord().getClass1Img());
//        new DownloadImageTask(class_2_img).execute(woter.getBadgeAndRecord().getClass2Img());
//        new DownloadImageTask(class_3_img).execute(woter.getBadgeAndRecord().getClass3Img());

        class_ace_num.setText(woter.getBadgeAndRecord().getClassAceNum());
        class_1_num.setText(woter.getBadgeAndRecord().getClass1Num());
        class_2_num.setText(woter.getBadgeAndRecord().getClass2Num());
        class_3_num.setText(woter.getBadgeAndRecord().getClass3Num());

        finghtNum.setText(woter.getBadgeAndRecord().getFinghtNum());
        victoryNum.setText(woter.getBadgeAndRecord().getVictoryNum());
        failureNum.setText(woter.getBadgeAndRecord().getFailureNum());
        survivingNum.setText(woter.getBadgeAndRecord().getSurvivingNum());
        experienceNum.setText(woter.getBadgeAndRecord().getExperienceNum());
        averageExpNum.setText(woter.getBadgeAndRecord().getAverageExpNum());
        maxExpNum.setText(woter.getBadgeAndRecord().getMaxExpNum());

        destoryNum.setText(woter.getBadgeAndRecord().getDestoryNum());
        findNum.setText(woter.getBadgeAndRecord().getFindNum());
        hitRate.setText(woter.getBadgeAndRecord().getHitRate());
        killingNum.setText(woter.getBadgeAndRecord().getKillingNum());
        averageKillingNum.setText(woter.getBadgeAndRecord().getAverageKillingNum());
        occupiedBaseNum.setText(woter.getBadgeAndRecord().getOccupiedBaseNum());
        defendBaseNum.setText(woter.getBadgeAndRecord().getDefendBaseNum());

        class_ace_layout.setOnClickListener(this);
        class_1_layout.setOnClickListener(this);
        class_2_layout.setOnClickListener(this);
        class_3_layout.setOnClickListener(this);

    }

    private void initView(View view) {
        class_ace_img = (ImageView) view.findViewById(R.id.class_ace_img);
        class_1_img = (ImageView) view.findViewById(R.id.class_1_img);
        class_2_img = (ImageView) view.findViewById(R.id.class_2_img);
        class_3_img = (ImageView) view.findViewById(R.id.class_3_img);

        class_ace_num = (TextView) view.findViewById(R.id.class_ace_num);
        class_1_num = (TextView) view.findViewById(R.id.class_1_num);
        class_2_num = (TextView) view.findViewById(R.id.class_2_num);
        class_3_num = (TextView) view.findViewById(R.id.class_3_num);

        finghtNum = (TextView) view.findViewById(R.id.finghtNum);
        victoryNum = (TextView) view.findViewById(R.id.victoryNum);
        failureNum = (TextView) view.findViewById(R.id.failureNum);
        survivingNum = (TextView) view.findViewById(R.id.survivingNum);
        experienceNum = (TextView) view.findViewById(R.id.experienceNum);
        averageExpNum = (TextView) view.findViewById(R.id.averageExpNum);
        maxExpNum = (TextView) view.findViewById(R.id.maxExpNum);

        destoryNum = (TextView) view.findViewById(R.id.destoryNum);
        findNum = (TextView) view.findViewById(R.id.findNum);
        hitRate = (TextView) view.findViewById(R.id.hitRate);
        killingNum = (TextView) view.findViewById(R.id.killingNum);
        averageKillingNum = (TextView) view.findViewById(R.id.averageKillingNum);
        occupiedBaseNum = (TextView) view.findViewById(R.id.occupiedBaseNum);
        defendBaseNum = (TextView) view.findViewById(R.id.defendBaseNum);

        class_ace_layout = (LinearLayout) view.findViewById(R.id.class_ace_layout);
        class_1_layout = (LinearLayout) view.findViewById(R.id.class_1_layout);
        class_2_layout = (LinearLayout) view.findViewById(R.id.class_2_layout);
        class_3_layout = (LinearLayout) view.findViewById(R.id.class_3_layout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.class_ace_layout:
                showDesDialog(woter.getBadgeAndRecord().getClassAceDes());
                break;
            case R.id.class_1_layout:
                showDesDialog(woter.getBadgeAndRecord().getClass1Des());
                break;
            case R.id.class_2_layout:
                showDesDialog(woter.getBadgeAndRecord().getClass2Des());
                break;
            case R.id.class_3_layout:
                showDesDialog(woter.getBadgeAndRecord().getClass3Des());
                break;
        }
    }

    private void showDesDialog(String classAceDes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("徽章描述")
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
