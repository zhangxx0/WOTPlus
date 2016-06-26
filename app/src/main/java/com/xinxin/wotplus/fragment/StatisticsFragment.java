package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.ViewPagerAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

/**
 * Created by xinxin on 2016/4/4.
 * 统计Fragment
 */
public class StatisticsFragment extends BaseFragment {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private DeathWheelProgressDialog deathWheelProgressDialog;

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
        adapter.addFragment(new TypeCountryFragment(), "类型与国家");
        viewPager.setAdapter(adapter);

        deathWheelProgressDialog.dismiss();

    }

}
