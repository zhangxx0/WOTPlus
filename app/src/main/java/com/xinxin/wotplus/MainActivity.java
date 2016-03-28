package com.xinxin.wotplus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xinxin.wotplus.fragment.MainFragment;

/**
 * 2016年3月19日15:50:20 by zhang.xx
 * 主页
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;

    private TextView header_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                replace(R.id.fl_content, new MainFragment(), "latest").
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
        header_text = (TextView) view.findViewById(R.id.id_header);
        header_text.setText(name);

        // FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "FloatingActionButton clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    // 侧滑菜单点击事件
    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        setTitle(menuItem.getTitle()); // 改变页面标题，标明导航状态
//                        currentNavigationId = menuItem.getItemId();
                        mDrawerLayout.closeDrawers();
                        // 这个地方，点击不同的按钮，应该跳到不同的页面上去；
                        //  或者是用不用的Fragment将主页的内容展示部分替换掉；
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                getSupportFragmentManager().beginTransaction().
                                        setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left).
                                        replace(R.id.fl_content, new MainFragment(), "main").
                                        commit();
                                Snackbar.make(navigationView, "Home", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_achieve:
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
