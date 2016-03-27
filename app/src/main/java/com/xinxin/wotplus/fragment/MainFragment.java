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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.WoterAdapter;
import com.xinxin.wotplus.base.BaseFragment;
import com.xinxin.wotplus.model.ClanInfo;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.JsoupHtmlUtil;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by xinxin on 2016/3/25.
 * <p/>
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

        // 军团信息URL
        final String clanUrl = "http://ncw.worldoftanks.cn/zh-cn/community/clans/show_clan_block/?spa_id=1509154099&time_token=1459046879981";

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

        // 获取军团信息的json
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(clanUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Log.d("TAG", response.toString());

                        Gson gson = new Gson();
                        ClanInfo clanInfo = gson.fromJson(response.toString(), ClanInfo.class);
                        
                        handleClaninfo(clanInfo);

                        // Log.d("json", clanInfo.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });

        mQueue.add(stringRequest);
        mQueue.add(jsonObjectRequest);


    }

    /**
     * 处理军团信息
     * @param clanInfo
     */
    private void handleClaninfo(ClanInfo clanInfo) {

        Log.d("claninfo", "111");
        Log.d("claninfo", clanInfo.getData().toString());
        // Log.d("claninfo", clanInfo.getData().getClan_block().toString());
        // System.out.println(clanInfo.getData().getClan_block().toString());

        jsoupClanInfo(clanInfo.getData().getClan_block().toString());

    }

    /**
     * 使用jsoup处理军团信息
     * @param s
     */
    private void jsoupClanInfo(String s) {
        Document doc = Jsoup.parse(s);
        Element link = doc.select("img").first();

//        String clanDescription = link.attr("alt");
//        String clanImgSrc = link.attr("src");

        Element clanPosition = doc.select(".number").first();
        Element clanDays = doc.select(".number").last();

//        String clanPositionS = clanPosition.text();
//        String clanDaysS = clanDays.text();

        // 赋值给woter，但是这个地方要考虑赋值的顺序，应该是在获取解析的主页面信息之后，再设置这几个军团信息；
        woter.setClanDescription(link.attr("alt"));
        woter.setClanImgSrc(link.attr("src"));
        woter.setClanPosition(clanPosition.text());
        woter.setClanDays(clanDays.text());

    }

    /**
     * 解析获取到的文件
      */
    private void jsoupHtml(String html) {

        // 使用返回的html字符串
        Document doc = Jsoup.parse(html);

        woter = JsoupHtmlUtil.handleWotPage(doc);


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
