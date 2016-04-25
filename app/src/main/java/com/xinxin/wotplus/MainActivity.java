package com.xinxin.wotplus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xinxin.wotplus.activity.AtyAbout;
import com.xinxin.wotplus.activity.AtySetting;
import com.xinxin.wotplus.fragment.AchieveMentFragment;
import com.xinxin.wotplus.fragment.GradeFragment;
import com.xinxin.wotplus.fragment.MainFragment;
import com.xinxin.wotplus.fragment.StatisticsFragment;
import com.xinxin.wotplus.fragment.TanksFragment;
import com.xinxin.wotplus.util.PreferenceUtils;

/**
 * 2016年3月19日15:50:20 by zhang.xx
 * 主页
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    private TextView header_text;

    public static final String queryFlag = "query";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // 传递queryFlag,查询页进入传递字符串“query”
        Bundle bundle = new Bundle();
        bundle.putString(MainFragment.QUERY_FLAG_KEY, queryFlag);
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                replace(R.id.fl_content, mainFragment, "latest").
                commit();

    }


    private void initView() {

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 设置打开Navigatioin的图标
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        // ?
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Navigation
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        if (mNavigationView != null) {
            setupDrawerContent(mNavigationView);
        }
        // Navigation的header控件操作
        // 这个地方不知道用的对不对，自己琢磨的
        View view = mNavigationView.getHeaderView(0);

        String name = getIntent().getStringExtra("name");
        String region = getIntent().getStringExtra("region");

        // 放入SharePreference
        PreferenceUtils.putCustomPrefString(this, "queryinfo", "name", name);
        PreferenceUtils.putCustomPrefString(this, "queryinfo", "region", region);

        header_text = (TextView) view.findViewById(R.id.id_header);
        header_text.setText(name);

        // FloatingActionButton
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "FloatingActionButton clicked", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    // 侧滑菜单点击事件
    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        // 改变页面标题，标明导航状态
                        // 设置和关于两个页面时打开新的页面，不需要修改标题；
                        mDrawerLayout.closeDrawers();
                        // 这个地方，点击不同的按钮，应该跳到不同的页面上去；
                        //  或者是用Fragment将主页的内容展示部分替换掉；
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:

                                setTitle(menuItem.getTitle());

                                // 传递queryFlag,侧滑菜单传递空字符串
                                Bundle bundle = new Bundle();
                                bundle.putString(MainFragment.QUERY_FLAG_KEY, "");
                                MainFragment mainFragment = new MainFragment();
                                mainFragment.setArguments(bundle);

                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                                        replace(R.id.fl_content, mainFragment, "main").
                                        commit();

                                break;
                            case R.id.nav_achieve:
                                setTitle(menuItem.getTitle());
                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                                        replace(R.id.fl_content, new AchieveMentFragment(), "achieve").
                                        commit();
                                break;
                            case R.id.nav_count:
                                setTitle(menuItem.getTitle());
                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                                        replace(R.id.fl_content, new StatisticsFragment(), "statistics").
                                        commit();
                                break;
                            case R.id.nav_level:
                                setTitle(menuItem.getTitle());
                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                                        replace(R.id.fl_content, new GradeFragment()).
                                        commit();
                                break;
                            case R.id.nav_vehicle:
                                setTitle(menuItem.getTitle());
                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                                        replace(R.id.fl_content, new TanksFragment(), "tanks").
                                        commit();
                                break;

                            case R.id.nav_settings:
                                Intent intent0 = new Intent(MainActivity.this, AtySetting.class);
                                intent0.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent0);
                                break;
                            case R.id.nav_about:
                                Intent intent1 = new Intent(MainActivity.this, AtyAbout.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent1);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
    }

    // action启动方法
    public static void mainActionStart(Context c, String name, String region) {
        Intent intent = new Intent(c, MainActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("region", region);
        c.startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//
//        // getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        // ic_menu点击事件
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
