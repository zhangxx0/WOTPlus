package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseFragment;

/**
 * Created by xinxin on 2016/5/22.
 * XVM Fragment
 */
public class XvmFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xvm,container,false);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();



        return view;
    }
}
