package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.WoterAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.JsoupHtmlUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by xinxin on 2016/3/25.
 *
 * 主页面Fragment
 */
public class MainFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private WoterAdapter woterAdapter;

    Woter woter = new Woter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        try {

            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
            // 设置RecyclerView的布局管理；
            LinearLayoutManager lm = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(lm);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            // 测试
//        List<String> mData = new ArrayList<>();
//        for (int i = 'A'; i <= 'z'; i++) {
//            mData.add("" + (char) i);
//        }
//        SimpleRecAdapter simpleRecAdapter = new SimpleRecAdapter(getActivity(), mData);
//        mRecyclerView.setAdapter(simpleRecAdapter);

            getData();

            woterAdapter = new WoterAdapter(getActivity(), woter);
            mRecyclerView.setAdapter(woterAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }




        return view;
    }

    // 尝试获取页面信息
    private void getData() {
        String add = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/1509154099-%E5%BA%B7%E6%81%A9%E9%A5%AD_/";
        String add2 = "http://www.baidu.com";
        String add3 = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/search/?name=%E5%BA%B7%E6%81%A9%E9%A5%AD_&name_gt=";
        String add4 = "http://ncw.worldoftanks.cn/zh-cn/community/accounts/#wot&at_search=%E5%BA%B7%E6%81%A9%E9%A5%AD_";

        // （1）使用HttpURLConnection
//        HttpUtil.sendHttpRequest(add4, new HttpCallbackListener() {
//            @Override
//            public void onFinish(String response) {
//                System.out.println(response);
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });

        // (2)使用Volley
        // 使用mActivity做参数时报错：修改为getActivity
        // java.lang.NullPointerException: Attempt to invoke virtual method 'java.io.File android.content.Context.getCacheDir()' on a null object reference
        RequestQueue mQueue = Volley.newRequestQueue(getActivity());

        StringRequest stringRequest = new StringRequest(add,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", String.valueOf(response.length()));

                        // 写入文件；
//                        FileOutputStream out = null;
//                        BufferedWriter writer = null;
//                        try {
//                            out = openFileOutput("html1", Context.MODE_PRIVATE);
//                            writer = new BufferedWriter(new OutputStreamWriter(out));
//                            writer.write(response);
//                            Log.d("TAG", "保存成功！");

                        jsoupHtml(response);

//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } finally {
//                            if (writer != null) {
//                                try {
//                                    writer.close();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        mQueue.add(stringRequest);


    }

    // 解析获取到的文件
    private void jsoupHtml(String html) {

        // 使用返回的html字符串
        Document doc = Jsoup.parse(html);

        JsoupHtmlUtil.handleWotPage(doc);


        // 使用保存的file
        //获得文件对象
//        File file = new File(html1);
//
//        System.out.println("file:" + file.toString());
//
//        //获得文档对象
//        try {
//            // java.io.FileNotFoundException: html1: open failed: ENOENT (No such file or directory)
//            Document doc = Jsoup.parse(file, "UTF-8");
//
//            Log.d("Tag:", doc.toString());
//
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
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println(doc.toString());
//                }
//            }).start();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
