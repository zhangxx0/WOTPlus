package com.xinxin.wotplus.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;

/**
 * Created by xinxin on 2016/4/4.
 * 徽章与战绩Fragment
 */
public class BadgeRecordFragment extends BaseFragment {

    private Woter woter;

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



        return view;

    }
}
