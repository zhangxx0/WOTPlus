package com.xinxin.wotplus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.google.gson.Gson;
import com.xinxin.wotplus.QueryActivity;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.activity.AtyQueryDialog;
import com.xinxin.wotplus.adapter.WoterAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.listener.HidingScrollListener;
import com.xinxin.wotplus.model.ClanInfoUsed;
import com.xinxin.wotplus.model.KongzhongUserInfo;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.kongzhong.UserSummary;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.HttpUtil;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.ClanInfoToWoterMapper;
import com.xinxin.wotplus.util.mapper.HtmlToWoterMapper;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import java.util.Date;

import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/3/25.
 * <p/>
 * 主页面Fragment
 */
public class MainFragment extends BaseFragment {

    private static final String BATTLE_TYPE = "random";

    private RecyclerView mRecyclerView;
    private WoterAdapter woterAdapter;

    private DeathWheelProgressDialog deathWheelProgressDialog;
    public static final String QUERY_FLAG_KEY = "queryFlag";
    private String queryFlag = "";
    private String clanUrl = "";
    private String accountId = "";
    private String region = "";

    Woter woter = new Woter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        try {
            // 获取queryFlag
            Bundle bundle = getArguments();
            if (bundle.containsKey(QUERY_FLAG_KEY)) {
                queryFlag = getArguments().getString(QUERY_FLAG_KEY);
            }

            // FloatingActionButton
            final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 弹出战绩查询对话框
                    Intent intent = new Intent(getActivity(), AtyQueryDialog.class);
                    startActivity(intent);

                }
            });

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            final int fabBottomMargin = lp.bottomMargin;

            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            // 设置RecyclerView的布局管理；
            LinearLayoutManager lm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(lm);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.addOnScrollListener(new HidingScrollListener() {
                @Override
                public void onHide() {
                    fab.animate()
                            .translationY(fab.getHeight() + fabBottomMargin)
                            .setInterpolator(new AccelerateInterpolator(2))
                            .start();
                }


                @Override
                public void onShow() {
                    fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                }
            });

            /* 显示ProgressDialog */
            // progressDialog = ProgressDialog.show(getActivity(), "加载中", "请稍候。。。");
            deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(getActivity());
            deathWheelProgressDialog.show();

            // 获取数据
            getData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    // 尝试获取页面信息
    private void getData() {

        // 添加限制，如果SharedPreference中存在了woter信息，则不再从网络中获取
        // 由查找页面进入需要一个flag，查找页面进入时不管存不存在SharedPreference都需要从网络获取；
        if ("".equals(queryFlag)) {
            // 从CharedPreference中获取woter
            String woterString = PreferenceUtils.getCustomPrefString(getActivity(), "woter", "woterString", "");
            Gson gson = new Gson();
            if (!TextUtils.isEmpty(woterString)) {
                woter = gson.fromJson(woterString, Woter.class);
            }
            woterAdapter = new WoterAdapter(getActivity(), woter);
            // 添加载入动画
            AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(woterAdapter, mRecyclerView);

            mRecyclerView.setAdapter(animatorAdapter);
            deathWheelProgressDialog.dismiss();

        } else {
            if (!HttpUtil.isNetworkAvailable()) {
                Snackbar.make(getView(), "网络不可用！", Snackbar.LENGTH_LONG).show();
            } else {
                getDataFromWeb2();
            }
        }

    }

    Observer<KongzhongUserInfo> userObserver = new Observer<KongzhongUserInfo>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("TAG1", e.getMessage(), e);
            Snackbar.make(getView(), "Rx获取userinfo出错！", Snackbar.LENGTH_LONG).show();
            backToQuery();
        }

        @Override
        public void onNext(KongzhongUserInfo kongzhongUserInfo) {
            deathWheelProgressDialog.dismiss();
            Log.d("成功", kongzhongUserInfo.toString());
        }
    };

    Observer<ClanInfoUsed> clanObserver = new Observer<ClanInfoUsed>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("666", e.getMessage(), e);
            Snackbar.make(getView(), "获取军团信息出错！", Snackbar.LENGTH_LONG).show();
            backToQuery();
        }

        @Override
        public void onNext(ClanInfoUsed c) {
            Log.d("clan", c.toString());

            woter.setClanDescription(c.getClanDescription());
            woter.setClanImgSrc(c.getClanImgSrc());
            woter.setClanPosition(c.getClanPosition());
            woter.setClanDays(c.getClanDays());

            woterAdapter = new WoterAdapter(getActivity(), woter);
            // 添加载入动画
            AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(woterAdapter, mRecyclerView);

            mRecyclerView.setAdapter(animatorAdapter);
            deathWheelProgressDialog.dismiss();
        }
    };

    Observer<Woter> observer = new Observer<Woter>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

            if ("getUserInfoError".equals(e.getMessage())) {
                Snackbar.make(getView(), "玩家信息不存在！", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(getView(), "Rx其他错误！", Snackbar.LENGTH_LONG).show();
                Log.e("eeee", e.getMessage(), e);
            }

            backToQuery();
        }

        @Override
        public void onNext(Woter w) {

            if (!TextUtils.isEmpty(clanUrl)) {
                w.setEnterClanFlag("1");

                woter = w;

                String timeToken = new Date().getTime() + "";
                subscription = Network.getClanInfoApi(region)
                        .getClanInfo(accountId, timeToken)
                        .map(ClanInfoToWoterMapper.getInstance())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(clanObserver);
            } else {
                w.setEnterClanFlag("0");

                woter = w;

                woterAdapter = new WoterAdapter(getActivity(), woter);
                // 添加载入动画
                AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(woterAdapter, mRecyclerView);

                mRecyclerView.setAdapter(animatorAdapter);
                deathWheelProgressDialog.dismiss();
            }

        }
    };

    /**
     * 从网络中获取数据，使用Rxjava和Retrofit
     * 2016年5月17日21:22:28
     */
    private void getDataFromWeb2() {

        // （1）请求官网玩家信息
        String name = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "name", "");
        region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");
        String name_gt = "";

        subscription = Network.getUseInfoApi(region)
                .getUserInfo(name, name_gt)
                .flatMap(new Func1<KongzhongUserInfo, Observable<Woter>>() {
                    @Override
                    public Observable<Woter> call(KongzhongUserInfo kongzhongUserInfo) {

                        clanUrl = kongzhongUserInfo.getResponse().get(0).getClan_url();
                        accountId = kongzhongUserInfo.getResponse().get(0).getAccount_id();
                        int userSize = kongzhongUserInfo.getResponse().size();
                        String account_profile = kongzhongUserInfo.getResponse().get(0).getAccount_profile();

                        // 保存woterId
                        PreferenceUtils.putCustomPrefString(getActivity(), "woterId", "woterId", accountId);
                        return userSize == 0
                                ? Observable.<Woter>error(new Exception("getUserInfoError"))
                                : Observable.zip(
                                    Network.getRecordApi(region).getHtml2(account_profile),
                                    Network.getKongzhongNewApi(region).getUserSummary(accountId, BATTLE_TYPE),
                                    new Func2<ResponseBody, UserSummary, Woter>() {
                                        @Override
                                        public Woter call(ResponseBody responseBody, UserSummary userSummary) {
                                            woter.setResponseBody(responseBody);
                                            woter.setUserSummary(userSummary);
                                            return woter;
                                        }
                                    });
                    }
                })
                .map(HtmlToWoterMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }


    /**
     * 返回查询页面
     */
    public void backToQuery() {
        // 跳转回查询页面，延时1s
        new Thread(new Runnable() {
            public void run() {
                spandTimeMethod();
                Intent intent = new Intent(getActivity(), QueryActivity.class);
                startActivity(intent);
            }
        }).start();
    }

    // 模拟耗时方法
    private void spandTimeMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}