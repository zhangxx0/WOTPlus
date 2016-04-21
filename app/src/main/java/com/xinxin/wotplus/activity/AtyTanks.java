package com.xinxin.wotplus.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.adapter.TanksByTypeAdapter;
import com.xinxin.wotplus.base.SwipeBackBaseActivity;
import com.xinxin.wotplus.model.Tank;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.util.Constant;
import com.xinxin.wotplus.widget.RevealBackgroundView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinxin on 2016/4/7.
 * 坦克列表Activity
 * 展现各种类型的坦克列表
 */
public class AtyTanks extends SwipeBackBaseActivity implements RevealBackgroundView.OnStateChangeListener {

    /**
     * tank列表类型
     * 0：LT 1：MT 2:HT 3:TD 4:SPG
     */
    public static final String TANKS_TYPE = "0";
    public static final String[] TANKS_TITLE = {"LT", "MT", "HT", "TD", "SPG"};

    private RevealBackgroundView vRevealBackground;

    private CoordinatorLayout tanksMainContent;
    private AppBarLayout mAppBarLayout;
    private RecyclerView recyclerView;
    private Woter woter;
    private TanksByTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanks);

        tanksMainContent = (CoordinatorLayout) findViewById(R.id.tanks_main_content);
//        tanksMainContent.setVisibility(View.INVISIBLE);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.tanks_appbar);
        mAppBarLayout.setVisibility(View.INVISIBLE);

        vRevealBackground = (RevealBackgroundView) findViewById(R.id.revealBackgroundView);


        Intent intent = getIntent();
        final String tanksType = intent.getStringExtra(TANKS_TYPE);
        Log.d("TANKS_TYPE", tanksType);
        final String tanksTitle = TANKS_TITLE[Integer.parseInt(tanksType)];

        final Toolbar tanksToolbar = (Toolbar) findViewById(R.id.tanks_toolbar);
        setSupportActionBar(tanksToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.tanks_collapsing_toolbar);
        collapsingToolbar.setTitle(tanksTitle);

        final ImageView imageView = (ImageView) findViewById(R.id.tanks_backdrop);

        // 从CharedPreference中获取woter
        SharedPreferences sharedPreferences = getSharedPreferences("woter", Context.MODE_PRIVATE);
        String woterString = sharedPreferences.getString("woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            Log.d("GsonWoter", woter.toString());
            // 提取勋章的ID与名称对照字段
            // <string name=""></string> 此方法无效
//            Achievements achievements = woter.getAchievements();
//            List<Achieve> warheroList = achievements.getWarheroList();
//            for (Achieve achieve : warheroList) {
//                System.out.println("<string name=\"" + achieve.getAchivementId() + "\">" + achieve.getAchivementName() + "</string>");
//            }


        }

        List<Tank> tanksByTypeList = new ArrayList<Tank>();
        switch (Integer.parseInt(tanksType)) {
            case 0:
                tanksByTypeList = woter.getTanks().getLts();
                imageView.setImageResource(R.drawable.lt);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                break;
            case 1:
                tanksByTypeList = woter.getTanks().getMts();
                imageView.setImageResource(R.drawable.mt);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                break;
            case 2:
                tanksByTypeList = woter.getTanks().getHts();
                imageView.setImageResource(R.drawable.ht);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                break;
            case 3:
                tanksByTypeList = woter.getTanks().getTds();
                imageView.setImageResource(R.drawable.td);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                break;
            case 4:
                tanksByTypeList = woter.getTanks().getSpgs();
                imageView.setImageResource(R.drawable.spg);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                break;
            default:
                break;
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_tanks_bytype);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TanksByTypeAdapter(tanksByTypeList, this);
        recyclerView.setVisibility(View.INVISIBLE);

        final List<Tank> finalTanksByTypeList = tanksByTypeList;
        adapter.setOnItemClickLitener(new TanksByTypeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Tank tank = finalTanksByTypeList.get(position);

                Intent intent = new Intent(AtyTanks.this, AtyTank.class);
                if (tank != null) {
                    intent.putExtra(AtyTank.TANK_TITLE, tank.getTankName());
                    intent.putExtra(AtyTank.TANK_ID, tank.getTankId());
                }
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(adapter);

        // loadBackDrop();
        setupRevealBackground(savedInstanceState);

    }

    // 加载toobar背景图片
    private void loadBackDrop() {


    }

    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(Constant.START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    Log.d("location",startingLocation.toString());
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
        }
    }

    @Override
    public void onStateChange(int state) {
        if (RevealBackgroundView.STATE_FINISHED == state) {
//            tanksMainContent.setVisibility(View.VISIBLE);
            mAppBarLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
//            setStatusBarColor(Color.TRANSPARENT);
        }

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
