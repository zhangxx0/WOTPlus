package com.xinxin.wotplus.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.AchieveMentAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;

/**
 * Created by xinxin on 2016/4/3.
 * 成就Fragment
 */
public class AchieveMentFragment extends BaseFragment {

    private RecyclerView recyclerview_achieve;
    private AchieveMentAdapter adapter;
    private Woter woter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_achieve, container, false);

        recyclerview_achieve = (RecyclerView) view.findViewById(R.id.recyclerview_achieve);
        LinearLayoutManager lm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        recyclerview_achieve.setLayoutManager(lm);
        recyclerview_achieve.setItemAnimator(new DefaultItemAnimator());

        // 从CharedPreference中获取woter
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("woter", Context.MODE_PRIVATE);
        String woterString = sharedPreferences.getString("woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            Log.d("GsonWoter", woter.toString());
        }

        adapter = new AchieveMentAdapter(getActivity(),woter);
        recyclerview_achieve.setAdapter(adapter);

        return view;
    }
}
