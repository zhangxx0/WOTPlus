package com.xinxin.wotplus.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.xinxin.wotplus.model.ClanInfo;
import com.xinxin.wotplus.model.ClanInfoUsed;
import com.xinxin.wotplus.model.KongzhongUserInfo;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.HttpUtil;
import com.xinxin.wotplus.util.JsoupHtmlUtil;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.ClanInfoToWoterMapper;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Date;

import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/3/25.
 * <p/>
 * 主页面Fragment
 * 保留第一版的代码，无实际作用
 */
public class MainFragment_copy extends BaseFragment {

    private RecyclerView mRecyclerView;
    private WoterAdapter woterAdapter;

    private DeathWheelProgressDialog deathWheelProgressDialog;
    public static final String QUERY_FLAG_KEY = "queryFlag";
    private String queryFlag = "";
    private String clanUrl = "";
    private String accountId = "";
    private String region = "";

    Woter woter = new Woter();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 1:
                    try {
                        woterAdapter = new WoterAdapter(getActivity(), woter);
                        // 添加载入动画
                        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(woterAdapter, mRecyclerView);

                        mRecyclerView.setAdapter(animatorAdapter);
                        deathWheelProgressDialog.dismiss();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };

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
            handler.sendEmptyMessage(1);

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
            Snackbar.make(getView(), "获取军团信息出错！", Snackbar.LENGTH_LONG).show();
            backToQuery();
        }

        @Override
        public void onNext(ClanInfoUsed c) {
            Log.d("clan",c.toString());

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

            w.setEnterClanFlag("0");

            if (!TextUtils.isEmpty(clanUrl)) {
                w.setEnterClanFlag("1");

                woter = w;

                String timeToken = new Date().getTime() + "";
                subscription = Network.getClanInfoApi(region)
                        .getClanInfo(accountId,timeToken)
                        .map(ClanInfoToWoterMapper.getInstance())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(clanObserver);
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

        /*subscription = Network.getUseInfoApi(region)
                .getUserInfo(name, name_gt)
                .flatMap(new Func1<KongzhongUserInfo, Observable<ResponseBody>>() {
                    @Override
                    public Observable<ResponseBody> call(KongzhongUserInfo kongzhongUserInfo) {
                        clanUrl = kongzhongUserInfo.getResponse().get(0).getClan_url();
                        accountId = kongzhongUserInfo.getResponse().get(0).getAccount_id();
                        return kongzhongUserInfo.getResponse().size() == 0
                                ? Observable.<ResponseBody>error(new Exception("getUserInfoError"))
                                : Network.getRecordApi(region)
                                .getHtml2(kongzhongUserInfo.getResponse().get(0).getAccount_profile());
                    }
                })
                .map(HtmlToWoterMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);*/



    }

    /**
     * 从网络中获取数据
     */
    private void getDataFromWeb() {

        // （1）使用HttpURLConnection 报403错误，应该是一些请求头的设置错误；

        // (2)使用Volley
        //  使用mActivity做参数时报错：修改为getActivity
        // java.lang.NullPointerException: Attempt to invoke virtual method 'java.io.File android.content.Context.getCacheDir()' on a null object reference
//        final RequestQueue mQueue = Volley.newRequestQueue(getActivity());
//
//        // 第一个请求 请求官网获取用户信息
//        // 拼接请求串
//        String name = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "name", "");
//        final String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");
//
//        if (QueryActivity.REGION_NORTH.equals(region)) {
//            Constant.USER_JSON_URL = Constant.USER_JSON_BASE_URL_NORTH + CommonUtil.urlEncodeUTF8(name) + "&name_gt=";
//        } else {
//            Constant.USER_JSON_URL = Constant.USER_JSON_BASE_URL_SOUTH + CommonUtil.urlEncodeUTF8(name) + "&name_gt=";
//        }
//
//
//        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, Constant.USER_JSON_URL, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//
//                        Gson gson = new Gson();
//                        //final XvmUserInfo xvmUserInfo = gson.fromJson(response.toString(), XvmUserInfo.class);
//                        final KongzhongUserInfo userInfo = gson.fromJson(response.toString(), KongzhongUserInfo.class);
//
//                        if (userInfo.getResponse().size() == 0) {
//                            Snackbar.make(getView(), "玩家信息不存在！", Snackbar.LENGTH_LONG).show();
//                            backToQuery();
//                        } else {
//                            // 保存woterId
//                            PreferenceUtils.putCustomPrefString(getActivity(), "woterId", "woterId", userInfo.getResponse().get(0).getAccount_id());
//
//                            if (QueryActivity.REGION_NORTH.equals(region)) {
//                                Constant.WOTER_URL = Constant.WOTER_BASE_URL_NORTH + userInfo.getResponse().get(0).getAccount_id() + "-" +
//                                        CommonUtil.urlEncodeUTF8(userInfo.getResponse().get(0).getAccount_name()) + "/";
//                            } else if (QueryActivity.REGION_SOUTH.equals(region)) {
//                                Constant.WOTER_URL = Constant.WOTER_BASE_URL_SOUTH + userInfo.getResponse().get(0).getAccount_id() + "-" +
//                                        CommonUtil.urlEncodeUTF8(userInfo.getResponse().get(0).getAccount_name()) + "/";
//                            }
//
//                            // 第二个请求
//                            // 这里的URL需要在第一次请求之后获得，因此初始加载的是空的，会报Bad url错误；
//                            // 还是得级联，但是级联实在是太不美观了；2016年3月31日23:17:15
//                            final StringRequest stringRequest = new StringRequest(Constant.WOTER_URL,
//                                    new Response.Listener<String>() {
//                                        @Override
//                                        public void onResponse(final String response) {
//
//                                            new Thread(new Runnable() {
//                                                @Override
//                                                public void run() {
//
//                                                    jsoupHtml(response, region);
//
//                                                    // 第三个请求
//                                                    // 获取军团信息的json
//                                                    String clan_url;
//                                                    if (QueryActivity.REGION_NORTH.equals(region)) {
//                                                        clan_url = Constant.CLAN_URL_BASE_NORTH + userInfo.getResponse().get(0).getAccount_id() + "&time_token=" + new Date().getTime();
//                                                    } else {
//                                                        clan_url = Constant.CLAN_URL_BASE_SOUTH + userInfo.getResponse().get(0).getAccount_id() + "&time_token=" + new Date().getTime();
//                                                    }
//                                                    Log.d("clan_url", clan_url);
//                                                    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(clan_url, null,
//                                                            new Response.Listener<JSONObject>() {
//                                                                @Override
//                                                                public void onResponse(JSONObject response) {
//                                                                    Gson gson = new Gson();
//                                                                    ClanInfo clanInfo = gson.fromJson(response.toString(), ClanInfo.class);
//
//                                                                    handleClaninfo(clanInfo);
//
//                                                                    // 执行耗时的方法之后发送消给handler
//                                                                    handler.sendEmptyMessage(1);
//
//                                                                }
//                                                            },
//                                                            new Response.ErrorListener() {
//                                                                @Override
//                                                                public void onErrorResponse(VolleyError error) {
//                                                                    Log.e("TAG3", error.getMessage(), error);
//                                                                    Snackbar.make(getView(), "获取军团信息出错！", Snackbar.LENGTH_LONG).show();
//                                                                    backToQuery();
//                                                                }
//                                                            });
//
//
//                                                    // 此处判断是否要请求军团信息，设置EnterClanFlag
//                                                    // 从官网获取的userInfo中没有ClanId
//                                                    // 从http://north.warsaga.cn/clans/2083/?utm_campaign=wot-portal&utm_medium=link中截取
//                                                    String clanUrl = userInfo.getResponse().get(0).getClan_url();
//
//                                                    if (TextUtils.isEmpty(clanUrl)) {
//                                                        woter.setEnterClanFlag("0");
//                                                        handler.sendEmptyMessage(1);
//                                                    } else {
//                                                        woter.setEnterClanFlag("1");
//                                                        mQueue.add(jsonObjectRequest);
//                                                    }
//                                                }
//                                            }).start();
//
//                                        }
//                                    },
//                                    new Response.ErrorListener() {
//                                        @Override
//                                        public void onErrorResponse(VolleyError error) {
//                                            Log.e("TAG2", error.getMessage(), error);
//                                            Snackbar.make(getView(), "获取战绩页面出错！", Snackbar.LENGTH_LONG).show();
//                                            backToQuery();
//                                        }
//                                    });
//                            mQueue.add(stringRequest);
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("TAG1", error.getMessage(), error);
//                Snackbar.make(getView(), "获取userinfo出错！", Snackbar.LENGTH_LONG).show();
//                backToQuery();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//
//                headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
//                headers.put("X-Requested-With", "XMLHttpRequest");
//
//                return headers;
//            }
//        };
//
//        mQueue.add(jsonObjRequest);

        // 使用XVM获取用户信息
        /**

         Constant.XVM_USER_JSON_URL = Constant.XVM_USER_JSON_BASE_URL + CommonUtil.urlEncodeUTF8(name) + "&area=" + region;

         JsonObjectRequest firstjsonObjectRequest = new JsonObjectRequest(Constant.XVM_USER_JSON_URL, null,
         new Response.Listener<JSONObject>() {
        @Override public void onResponse(JSONObject response) {

        Gson gson = new Gson();
        final XvmUserInfo xvmUserInfo = gson.fromJson(response.toString(), XvmUserInfo.class);

        if (TextUtils.isEmpty(xvmUserInfo.getPlayer().getAid())) {
        Snackbar.make(getView(), "玩家信息不存在！", Snackbar.LENGTH_LONG).show();
        backToQuery();
        } else {
        // 保存woterId
        PreferenceUtils.putCustomPrefString(getActivity(), "woterId", "woterId", xvmUserInfo.getPlayer().getAid());

        if (QueryActivity.REGION_NORTH.equals(region)) {
        Constant.WOTER_URL = Constant.WOTER_BASE_URL_NORTH + xvmUserInfo.getPlayer().getAid() + "-" +
        CommonUtil.urlEncodeUTF8(xvmUserInfo.getPlayer().getUsername()) + "/";
        } else if (QueryActivity.REGION_SOUTH.equals(region)) {
        Constant.WOTER_URL = Constant.WOTER_BASE_URL_SOUTH + xvmUserInfo.getPlayer().getAid() + "-" +
        CommonUtil.urlEncodeUTF8(xvmUserInfo.getPlayer().getUsername()) + "/";
        }

        // 第二个请求
        // 这里的URL需要在第一次请求之后获得，因此初始加载的是空的，会报Bad url错误；
        // 还是得级联，但是级联实在是太不美观了；2016年3月31日23:17:15
        final StringRequest stringRequest = new StringRequest(Constant.WOTER_URL,
        new Response.Listener<String>() {
        @Override public void onResponse(final String response) {

        new Thread(new Runnable() {
        @Override public void run() {

        jsoupHtml(response, region);

        // 第三个请求
        // 获取军团信息的json
        String clan_url;
        if (QueryActivity.REGION_NORTH.equals(region)) {
        clan_url = Constant.CLAN_URL_BASE_NORTH + xvmUserInfo.getPlayer().getAid() + "&time_token=" + new Date().getTime();
        } else {
        clan_url = Constant.CLAN_URL_BASE_SOUTH + xvmUserInfo.getPlayer().getAid() + "&time_token=" + new Date().getTime();
        }
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(clan_url, null,
        new Response.Listener<JSONObject>() {
        @Override public void onResponse(JSONObject response) {
        Gson gson = new Gson();
        ClanInfo clanInfo = gson.fromJson(response.toString(), ClanInfo.class);

        handleClaninfo(clanInfo);

        //                                                                     请教一个Android问题：
        //                                                                     加载页面之前先要获取数据，使用Volley访问网络，返回数据后还要用jsoup处理（比较耗时），这俩方法顺序进行（先获取后解析，最后获得想要的数据），然后更新ui
        //                                                                     于是使用了 handler.sendEmptyMessage(1); 和 ProgressDialog
        //                                                                     但是，这个耗时的操作却不耗时，加载框直接一闪而过，从而获取不到更新ui所需要的数据，
        //                                                                     这个该怎么让他耗时呢？

        // 执行耗时的方法之后发送消给handler
        handler.sendEmptyMessage(1);

        }
        },
        new Response.ErrorListener() {
        @Override public void onErrorResponse(VolleyError error) {
        Log.e("TAG3", error.getMessage(), error);
        Snackbar.make(getView(), "获取军团信息出错！", Snackbar.LENGTH_LONG).show();
        backToQuery();
        }
        });


        // 此处判断是否要请求军团信息，设置EnterClanFlag
        if ("0".equals(xvmUserInfo.getPlayer().getClanid())) {
        woter.setEnterClanFlag("0");
        handler.sendEmptyMessage(1);
        } else {
        woter.setEnterClanFlag("1");
        mQueue.add(jsonObjectRequest);
        }
        }
        }).start();

        }
        },
        new Response.ErrorListener() {
        @Override public void onErrorResponse(VolleyError error) {
        Log.e("TAG2", error.getMessage(), error);
        Snackbar.make(getView(), "获取战绩页面出错！", Snackbar.LENGTH_LONG).show();
        backToQuery();
        }
        });
        mQueue.add(stringRequest);
        }

        }
        },
         new Response.ErrorListener() {
        @Override public void onErrorResponse(VolleyError error) {
        Log.e("TAG1", error.getMessage(), error);
        Snackbar.make(getView(), "获取xvminfo出错！", Snackbar.LENGTH_LONG).show();
        backToQuery();
        }

        });

         mQueue.add(firstjsonObjectRequest);
         */
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

    /**
     * 处理军团信息
     *
     * @param clanInfo
     */
    private void handleClaninfo(ClanInfo clanInfo) {

        Log.d("claninfo", clanInfo.getData().toString());

        jsoupClanInfo(clanInfo.getData().getClan_block().toString());

    }

    /**
     * 使用jsoup处理军团信息
     *
     * @param s
     */
    private void jsoupClanInfo(String s) {
        Document doc = Jsoup.parse(s);
        Element link = doc.select("img").first();

        Element clanPosition = doc.select(".number").first();
        Element clanDays = doc.select(".number").last();

        // 赋值给woter，但是这个地方要考虑赋值的顺序，应该是在获取解析的主页面信息之后，再设置这几个军团信息；
        woter.setClanDescription(link.attr("alt"));
        woter.setClanImgSrc(link.attr("src"));
        woter.setClanPosition(clanPosition.text());
        woter.setClanDays(clanDays.text());

    }

    /**
     * 解析获取到的文件
     */
    private Woter jsoupHtml(String html, String region) {

        // 使用返回的html字符串
        Document doc = Jsoup.parse(html);

        woter = JsoupHtmlUtil.handleWotPage(doc, region);

        return woter;

        // 使用保存的file获得文件对象
//        File file = new File(html1);
//
//        //获得文档对象
//        try {
//            // java.io.FileNotFoundException: html1: open failed: ENOENT (No such file or directory)
//            Document doc = Jsoup.parse(file, "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 使用jsoup直接获取
//        final String add = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/";
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Document doc = null;
//                    try {
//                        doc = Jsoup.connect(add).get();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


}
