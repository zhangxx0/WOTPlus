package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.AchieveMentAdapter;
import com.xinxin.wotplus.adapter.ViewPagerAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.Achievements;
import com.xinxin.wotplus.model.kongzhong.Statistics;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.StatisticsJsonToStatisticsMapper;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import java.io.IOException;

import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/4.
 * 统计Fragment
 */
public class StatisticsFragment extends BaseFragment {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private DeathWheelProgressDialog deathWheelProgressDialog;

    private Woter woter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
        deathWheelProgressDialog.show();

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new BadgeRecordFragment(), "徽章与战绩");
        adapter.addFragment(new TypeCountryFragment(), "战斗坦克分布");
        viewPager.setAdapter(adapter);

        deathWheelProgressDialog.dismiss();

    }

}
