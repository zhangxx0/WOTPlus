package com.xinxin.wotplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.xinxin.wotplus.base.BaseActivity;

/**
 * Created by xinxin on 2016/3/19.
 * 启动页
 */
public class BeginActivity extends BaseActivity {

    private ImageView iv_begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        // 有下面这句话才能实现启动图放大的效果
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        iv_begin = (ImageView) findViewById(R.id.iv_begin);

        initImage();

    }

    // 加载图片
    private void initImage() {
        // 图片放大效果
        // 是否可以多弄几个图片，进行随机的展示

        iv_begin.setImageResource(R.mipmap.begin);

        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(2000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_begin.startAnimation(scaleAnim);

    }

    private void startActivity() {
        Intent intent = new Intent(BeginActivity.this, QueryActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }
}
