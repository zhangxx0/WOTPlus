package com.xinxin.wotplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.fragment.TankAchievesAdapter;
import com.xinxin.wotplus.model.Achieve;
import com.xinxin.wotplus.model.AchieveTank;
import com.xinxin.wotplus.model.Achievements;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.Constant;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xinxin on 2016/4/8.
 * 坦克详细战绩页面
 */
public class AtyTank extends BaseActivity {

    public static final String TANK_TITLE = "tanktitle";
    public static final String TANK_ID = "tankid";

    private RecyclerView recyclerView;
    private TextView tankdestroy, tankexp, tankmaxexp, tankhitrate, tankhitnum, tankdesdeadrate, tankhitrecirate, tankperdestroy, tankperhitnum;

    private TankAchievesAdapter adapter;
    private Woter woter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank);

        Intent intent = getIntent();
        String tankTitle = intent.getStringExtra(TANK_TITLE);
        String tankId = intent.getStringExtra(TANK_ID);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tank_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(tankTitle);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_tank_achieves);
        GridLayoutManager gm = new GridLayoutManager(this, 2);
        gm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gm);

        RequestQueue mQueue = Volley.newRequestQueue(this);
        // 拼接战车战绩查询URL
        SharedPreferences sharedPreferences = getSharedPreferences("woterId", Context.MODE_PRIVATE);
        String woterId = sharedPreferences.getString("woterId", "");
        String tankUrl = Constant.TANK_ACHIEVE_URL_BASE + woterId + "/vehicle_details/?vehicle_cd=" + tankId;

        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, tankUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson gson = new Gson();
                            AchieveTank achieveTank = gson.fromJson(response.toString(), AchieveTank.class);

                            if (achieveTank != null) {
                                List<AchieveTank.AchievementsEntity> achieveList = achieveTank.getAchievements();

                                // 从CharedPreference中获取woter
                                SharedPreferences sharedPreferences = getSharedPreferences("woter", Context.MODE_PRIVATE);
                                String woterString = sharedPreferences.getString("woterString", "");
                                Gson gson2 = new Gson();
                                Map<String, String> map = new HashMap<String, String>();
                                if (!TextUtils.isEmpty(woterString)) {
                                    woter = gson2.fromJson(woterString, Woter.class);
                                    // 提取勋章的ID与名称对照字段
                                    Achievements achievements = woter.getAchievements();
                                    List<Achieve> warheroList = achievements.getWarheroList();
                                    for (Achieve achieve : warheroList) {
                                        map.put(achieve.getAchivementId(), achieve.getAchivementName());
                                    }
                                    List<Achieve> honorList = achievements.getHonorList();
                                    for (Achieve achieve : honorList) {
                                        map.put(achieve.getAchivementId(), achieve.getAchivementName());
                                    }
                                    List<Achieve> epicList = achievements.getEpicList();
                                    for (Achieve achieve : epicList) {
                                        map.put(achieve.getAchivementId(), achieve.getAchivementName());
                                    }
                                    List<Achieve> teamList = achievements.getTeamList();
                                    for (Achieve achieve : teamList) {
                                        map.put(achieve.getAchivementId(), achieve.getAchivementName());
                                    }
                                    List<Achieve> commemorateList = achievements.getCommemorateList();
                                    for (Achieve achieve : commemorateList) {
                                        map.put(achieve.getAchivementId(), achieve.getAchivementName());
                                    }
                                    List<Achieve> stageList = achievements.getStageList();
                                    for (Achieve achieve : stageList) {
                                        map.put(achieve.getAchivementId(), achieve.getAchivementName());
                                    }
                                    List<Achieve> otherList = achievements.getOtherList();
                                    for (Achieve achieve : otherList) {
                                        map.put(achieve.getAchivementId(), achieve.getAchivementName());
                                    }
                                }

                                adapter = new TankAchievesAdapter(achieveList, AtyTank.this, map);
                                recyclerView.setAdapter(adapter);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonObjRequest", "onErrorResponse, error=" + error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                // 注释的header中的某一个会导致返回不了json的错误；具体哪一个，，，
//                headers.put("Accept", "application/json, text/javascript, */*; q=0.01");
//                headers.put("Accept-Encoding", "gzip, deflate, sdch");
//                headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//                headers.put("Cache-Control", "max-age=0");
//                headers.put("Connection", "keep-alive");
//                headers.put("Host", "ncw.worldoftanks.cn");
                headers.put("X-Requested-With", "XMLHttpRequest");

                return headers;
            }

        };

        mQueue.add(jsonObjRequest);
        mQueue.start();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            // 销毁并返回坦克列表
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
