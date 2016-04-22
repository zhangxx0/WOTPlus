package com.xinxin.wotplus.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.activity.AtyTanks;
import com.xinxin.wotplus.adapter.TanksAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.Constant;
import com.xinxin.wotplus.util.PreferenceUtils;

/**
 * Created by xinxin on 2016/4/6.
 * 战车Fragment
 */
public class TanksFragment extends BaseFragment {

    private RecyclerView recyclerview_tanks;
    private TanksAdapter tanksAdapter;
    private Woter woter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tanks, container, false);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();

        recyclerview_tanks = (RecyclerView) view.findViewById(R.id.recyclerview_tanks);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerview_tanks.setLayoutManager(lm);
        recyclerview_tanks.setItemAnimator(new DefaultItemAnimator());

        // 从CharedPreference中获取woter
        String woterString = PreferenceUtils.getCustomPrefString(getActivity(), "woter", "woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            // Log.d("GsonWoter", woter.toString());
        }

        tanksAdapter = new TanksAdapter(getActivity(), woter);
        // 点击事件
        tanksAdapter.setOnItemClickLitener(new TanksAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Context context = view.getContext();
                // 全屏扩散
                int[] startingLocation = new int[2];
                view.getLocationOnScreen(startingLocation);
                startingLocation[0] += view.getWidth() / 2;

                Intent intent = new Intent(context, AtyTanks.class);
                intent.putExtra(Constant.START_LOCATION, startingLocation);
                intent.putExtra(AtyTanks.TANKS_TYPE, String.valueOf(position));

                context.startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        recyclerview_tanks.setAdapter(tanksAdapter);

        return view;
    }
}
