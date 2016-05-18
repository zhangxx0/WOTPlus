package com.xinxin.wotplus.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Subscription;

/**
 * Created by xinxin on 2016/3/25.
 * BaseFragment
 */
public class BaseFragment extends Fragment {

    protected Subscription subscription;

    // 这个好像没啥用唉？
    // 只在一处起了作用？
    protected Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
        unsubscribe();
    }

    private void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
