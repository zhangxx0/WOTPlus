package com.xinxin.wotplus.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.WoterAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.listener.HidingScrollListener;
import com.xinxin.wotplus.model.ClanInfo;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.model.XvmUserInfo;
import com.xinxin.wotplus.util.CommonUtil;
import com.xinxin.wotplus.util.Constant;
import com.xinxin.wotplus.util.JsoupHtmlUtil;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Date;

/**
 * Created by xinxin on 2016/3/25.
 * <p/>
 * 主页面Fragment
 */
public class MainFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private WoterAdapter woterAdapter;

    private DeathWheelProgressDialog deathWheelProgressDialog;
    public static final String QUERY_FLAG_KEY = "queryFlag";
    private String queryFlag = "";

    Woter woter = new Woter();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case 1:
                    try {
                        woterAdapter = new WoterAdapter(getActivity(), woter);
                        mRecyclerView.setAdapter(woterAdapter);
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
            Log.d("bundle", bundle.toString());
            if (bundle.containsKey(QUERY_FLAG_KEY)) {
                queryFlag = getArguments().getString(QUERY_FLAG_KEY);
            }
            Log.d("queryFlag", queryFlag);

            // FloatingActionButton
            final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "自定义dialog查找玩家信息", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
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

            /**
             * 这个地方应该用什么来延时呢？
             * 由于加载和分析html需要一定的时间，所以这个地方应该是加一个延时的；
             * 至于用什么来实现，则不大清楚；
             * 应该再加一个加载的动画效果；
             * 最好是能知道数据获取完毕的时间、标识等以更快的加载页面
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    // 模拟耗时方法
    private void spandTimeMethod() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // 尝试获取页面信息
    private void getData() {

        // 添加限制，如果SharedPreference中存在了woter信息，则不再从网络中获取
        // 由查找页面进入需要一个flag，查找页面进入时不管存不存在SharedPreference都需要从网络获取；
        Log.d("getData queryFlag", queryFlag);
        if ("".equals(queryFlag)) {
            // 从CharedPreference中获取woter
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("woter", Context.MODE_PRIVATE);
            String woterString = sharedPreferences.getString("woterString", "");
            Gson gson = new Gson();
            if (!TextUtils.isEmpty(woterString)) {
                woter = gson.fromJson(woterString, Woter.class);
                Log.d("GsonWoter", woter.toString());
            }
            Log.d("getData", "1111");
            handler.sendEmptyMessage(1);

        } else {

            Log.d("getData", "2222");
            getDataFromWeb();

        }

    }

    // 从网络中获取数据
    private void getDataFromWeb() {

        // （1）使用HttpURLConnection 报403错误

        // (2)使用Volley
        //  使用mActivity做参数时报错：修改为getActivity
        // java.lang.NullPointerException: Attempt to invoke virtual method 'java.io.File android.content.Context.getCacheDir()' on a null object reference
        final RequestQueue mQueue = Volley.newRequestQueue(getActivity());

        // 第一个请求 请求XVM
        // 拼接请求串
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("queryinfo", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String region = sharedPreferences.getString("region", "");
        Constant.XVM_USER_JSON_URL = Constant.XVM_USER_JSON_BASE_URL + CommonUtil.urlEncodeUTF8(name) +
                "&area=" + region;
        Log.d("XVM_USER_JSON_URL", Constant.XVM_USER_JSON_URL);

        JsonObjectRequest firstjsonObjectRequest = new JsonObjectRequest(Constant.XVM_USER_JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());

                        Gson gson = new Gson();
                        final XvmUserInfo xvmUserInfo = gson.fromJson(response.toString(), XvmUserInfo.class);

                        // 保存woterId
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("woterId", Context.MODE_PRIVATE).edit();
                        editor.putString("woterId", xvmUserInfo.getPlayer().getAid());
                        editor.commit();

                        Constant.WOTER_URL = Constant.WOTER_BASE_URL + xvmUserInfo.getPlayer().getAid() + "-" +
                                CommonUtil.urlEncodeUTF8(xvmUserInfo.getPlayer().getUsername()) + "/";

                        // 第二个请求
                        // 这里的URL需要在第一次请求之后获得，因此初始加载的是空的，会报Bad url错误；
                        // 还是得级联，但是级联实在是太不美观了；2016年3月31日23:17:15
                        final StringRequest stringRequest = new StringRequest(Constant.WOTER_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(final String response) {
                                        Log.d("TAG", String.valueOf(response.length()));

                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                jsoupHtml(response);

                                                // 第三个请求
                                                // 获取军团信息的json
                                                String clan_url = Constant.CLAN_URL_BASE + xvmUserInfo.getPlayer().getAid() + "&time_token=" + new Date().getTime();
                                                Log.d("clan", clan_url);
                                                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(clan_url, null,
                                                        new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                Gson gson = new Gson();
                                                                ClanInfo clanInfo = gson.fromJson(response.toString(), ClanInfo.class);

                                                                handleClaninfo(clanInfo);
                                                                /**
                                                                 * 请教一个Android问题：
                                                                 * 加载页面之前先要获取数据，使用Volley访问网络，返回数据后还要用jsoup处理（比较耗时），这俩方法顺序进行（先获取后解析，最后获得想要的数据），然后更新ui
                                                                 * 于是使用了 handler.sendEmptyMessage(1); 和 ProgressDialog
                                                                 * 但是，这个耗时的操作却不耗时，加载框直接一闪而过，从而获取不到更新ui所需要的数据，
                                                                 * 这个该怎么让他耗时呢？
                                                                 */
                                                                // 执行耗时的方法之后发送消给handler
                                                                handler.sendEmptyMessage(1);

                                                            }
                                                        }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Log.e("TAG3", error.getMessage(), error);
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
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("TAG2", error.getMessage(), error);
                            }
                        });
                        mQueue.add(stringRequest);

//                        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                10000,
//                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG1", error.getMessage(), error);
            }

        });

        mQueue.add(firstjsonObjectRequest);
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
    private Woter jsoupHtml(String html) {

        // 使用返回的html字符串
        Document doc = Jsoup.parse(html);

        woter = JsoupHtmlUtil.handleWotPage(doc);

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
