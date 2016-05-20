package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.GradeAdapter;
import com.xinxin.wotplus.base.BaseFragment;

/**
 * Created by xinxin on 2016/4/4.
 * 等级Fragment
 * 2016年5月21日01:06:02
 * 保留第一版代码，无实际意义
 */
public class GradeFragment_copy extends BaseFragment {

    private RecyclerView recyclerview_grade;
    private GradeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grade, container, false);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();

        recyclerview_grade = (RecyclerView) view.findViewById(R.id.recyclerview_grade);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerview_grade.setLayoutManager(lm);
        recyclerview_grade.setItemAnimator(new DefaultItemAnimator());

//        RequestQueue mQueue = Volley.newRequestQueue(getActivity());
//
//        // 拼接等级查询URL
//        String woterId = PreferenceUtils.getCustomPrefString(getActivity(), "woterId", "woterId", "");
//
//        // 区分南北区
//        String region = PreferenceUtils.getCustomPrefString(getActivity(), "queryinfo", "region", "");
//        String gradeUrl;
//        if (QueryActivity.REGION_NORTH.equals(region)) {
//            gradeUrl = Constant.GRADE_URL_BASE_NORTH + woterId + "-/account_ratings/";
//        } else {
//            gradeUrl = Constant.GRADE_URL_BASE_SOUTH + woterId + "-/account_ratings/";
//        }
//
//        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, gradeUrl, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Gson gson = new Gson();
//                            Grade grade = gson.fromJson(response.toString(), Grade.class);
//
//                            adapter = new GradeAdapter(getActivity(), grade);
//                            // RecyclerView 动画
//                            SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(adapter, recyclerview_grade);
//                            recyclerview_grade.setAdapter(animatorAdapter);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.e("统计", "onErrorResponse, error=" + error);
//            }
//        }) {
//            //  getParams方法好像没起作用？里面的log也没有打印；
//            //  https://github.com/mcxiaoke/android-volley/issues/82
////            @Override
////            public Map<String, String> getParams() {
////                Map<String, String> params = new HashMap<String, String>();
////                params.put("timerange", "all");
////                params.put("group", "all");
////                Log.d("统计", params.toString());
////                return params;
////            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//
//                headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
//                headers.put("Accept-Encoding", "gzip, deflate, sdch");
//                headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//                headers.put("Cache-Control", "max-age=0");
//                headers.put("Connection", "keep-alive");
//                headers.put("X-Requested-With", "XMLHttpRequest");
//
//                Log.d("统计", "headers=" + headers);
//                return headers;
//            }
//        };
//        mQueue.add(jsonObjRequest);
//        mQueue.start();

        return view;
    }
}