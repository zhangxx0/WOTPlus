package com.xinxin.wotplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.TanksByTypeAdapter;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.Tank;
import com.xinxin.wotplus.model.Woter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinxin on 2016/4/7.
 * 坦克列表Activity
 * 展现各种类型的坦克列表
 */
public class AtyTanks extends BaseActivity {

    /**
     * tank列表类型
     * 0：LT 1：MT 2:HT 3:TD 4:SPG
     */
    public static final String TANKS_TYPE = "0";
    public static final String[] TANKS_TITLE = {"LT", "MT", "HT", "TD", "SPG"};

    private RecyclerView recyclerView;
    private Woter woter;
    private TanksByTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanks);

        Intent intent = getIntent();
        final String tanksType = intent.getStringExtra(TANKS_TYPE);
        Log.d("TANKS_TYPE", tanksType);
        String tanksTitle = TANKS_TITLE[Integer.parseInt(tanksType)];

        final Toolbar tanksToolbar = (Toolbar) findViewById(R.id.tanks_toolbar);
        setSupportActionBar(tanksToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.tanks_collapsing_toolbar);
        collapsingToolbar.setTitle(tanksTitle);

        // 从CharedPreference中获取woter
        SharedPreferences sharedPreferences = getSharedPreferences("woter", Context.MODE_PRIVATE);
        String woterString = sharedPreferences.getString("woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            Log.d("GsonWoter", woter.toString());
        }

        List<Tank> tanksByTypeList = new ArrayList<Tank>();
        switch (Integer.parseInt(tanksType)) {
            case 0:
                tanksByTypeList = woter.getTanks().getLts();
                break;
            case 1:
                tanksByTypeList = woter.getTanks().getMts();
                break;
            case 2:
                tanksByTypeList = woter.getTanks().getHts();
                break;
            case 3:
                tanksByTypeList = woter.getTanks().getTds();
                break;
            case 4:
                tanksByTypeList = woter.getTanks().getSpgs();
                break;
            default:
                break;
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_tanks_bytype);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TanksByTypeAdapter(tanksByTypeList, this);
        recyclerView.setAdapter(adapter);

        loadBackDrop();

    }

    // 加载toobar背景图片
    private void loadBackDrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.tanks_backdrop);
        imageView.setImageResource(R.drawable.lt);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            // 销毁并返回坦克类型列表
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
