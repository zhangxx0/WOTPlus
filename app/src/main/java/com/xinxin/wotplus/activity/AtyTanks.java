package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import com.xinxin.wotplus.fragment.TypeCountryFragment;
import com.xinxin.wotplus.model.Tank;
import com.xinxin.wotplus.model.Tanks;
import com.xinxin.wotplus.model.Woter;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.Constant;
import com.xinxin.wotplus.util.PreferenceUtils;
import com.xinxin.wotplus.util.mapper.TankTypeJsonCorrectAndToVoMapper;
import com.xinxin.wotplus.util.mapper.TanksJsonCorrectAndToVoMapper;
import com.xinxin.wotplus.widget.DeathWheelProgressDialog;
import com.xinxin.wotplus.widget.RevealBackgroundView;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.recyclerview.adapter.SlideInRightAnimatorAdapter;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private DeathWheelProgressDialog deathWheelProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanks);

        tanksMainContent = (CoordinatorLayout) findViewById(R.id.tanks_main_content);
//        tanksMainContent.setVisibility(View.INVISIBLE);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.tanks_appbar);
        // mAppBarLayout.setVisibility(View.INVISIBLE);

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
        String woterString = PreferenceUtils.getCustomPrefString(this, "woter", "woterString", "");
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(woterString)) {
            woter = gson.fromJson(woterString, Woter.class);
            Log.d("GsonWoter", woter.toString());
        }

        /**
         * 由于数据变为动态获取，所以在此重新获取各类型的list
         * 2016年10月15日18:15:00 modified by zhang.xx
         */
        Observer<Tanks> observer = new Observer<Tanks>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(tanksMainContent, "获取坦克战绩信息出错！", Snackbar.LENGTH_LONG).show();
                deathWheelProgressDialog.dismiss();
            }

            @Override
            public void onNext(Tanks tanks) {
                // 处理数据
                woter.setTanks(tanks);

                deathWheelProgressDialog.dismiss();

                List<Tank> tanksByTypeList = new ArrayList<Tank>();
                // 这个统一放在lts里面，原先的数据结构有些多余 2016年10月15日18:44:42
                tanksByTypeList = woter.getTanks().getLts();

                switch (Integer.parseInt(tanksType)) {
                    case 0:
//                tanksByTypeList = woter.getTanks().getLts();
                        imageView.setImageResource(R.drawable.lt);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                        break;
                    case 1:
//                tanksByTypeList = woter.getTanks().getMts();
                        imageView.setImageResource(R.drawable.mt);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                        break;
                    case 2:
//                tanksByTypeList = woter.getTanks().getHts();
                        imageView.setImageResource(R.drawable.ht);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                        break;
                    case 3:
//                tanksByTypeList = woter.getTanks().getTds();
                        imageView.setImageResource(R.drawable.td);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                        break;
                    case 4:
//                tanksByTypeList = woter.getTanks().getSpgs();
                        imageView.setImageResource(R.drawable.spg);
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        // Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
                        break;
                    default:
                        break;
                }

                adapter = new TanksByTypeAdapter(tanksByTypeList, AtyTanks.this);
                recyclerView.setVisibility(View.INVISIBLE);

                final List<Tank> finalTanksByTypeList = tanksByTypeList;
                adapter.setOnItemClickLitener(new TanksByTypeAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Tank tank = finalTanksByTypeList.get(position);

                        // 全屏扩散
                        int[] startingLocation = new int[2];
                        view.getLocationOnScreen(startingLocation);
                        startingLocation[0] += view.getWidth() / 2;

                        Intent intent = new Intent(AtyTanks.this, AtyTank.class);
                        intent.putExtra(Constant.START_LOCATION, startingLocation);
                        if (tank != null) {
                            intent.putExtra(AtyTank.TANK_TITLE, tank.getTankName());
                            intent.putExtra(AtyTank.TANK_ID, tank.getTankId());
                        }
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                // RecyclerView 动画
                SlideInRightAnimatorAdapter animatorAdapter = new SlideInRightAnimatorAdapter(adapter, recyclerView);
                recyclerView.setAdapter(animatorAdapter);


            }
        };
        deathWheelProgressDialog = DeathWheelProgressDialog.createDialog(this);
        deathWheelProgressDialog.show();
        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");
        String region = PreferenceUtils.getCustomPrefString(this, "queryinfo", "region", "");
        String vehicle_type = "";
        switch (Integer.parseInt(tanksType)) {
            case 0:
                vehicle_type = "lightTank";
                break;
            case 1:
                vehicle_type = "mediumTank";
                break;
            case 2:
                vehicle_type = "heavyTank";
                break;
            case 3:
                vehicle_type = "AT-SPG";
                break;
            case 4:
                vehicle_type = "SPG";
                break;
            default:
                vehicle_type = "lightTank";
                break;
        }
        Network.getTanksNewInfo(region)
                .getTanksNewInfo(woterId, TypeCountryFragment.LANG, vehicle_type)
                .map(TanksJsonCorrectAndToVoMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_tanks_bytype);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                    Log.d("location", startingLocation.toString());
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
