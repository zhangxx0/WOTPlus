package com.xinxin.wotplus.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.xinxin.wotplus.MainActivity;
import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;

/**
 * Created by xinxin on 2016/4/19.
 * 玩家查询对话框Activity
 * 自定义对话框，可以改用对话框的形式显示 Activity
 */
public class AtyQueryDialog extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private EditText nametext_dia;
    private RadioGroup nsregion_dia;
    private RadioButton north_dia, sourth_dia;
    private Button query_dia;

    private static String name_dia, region_dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_query);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();

        query_dia.setOnTouchListener(this);
        query_dia.setOnClickListener(this);
    }

    private void initView() {
        nametext_dia = (EditText) findViewById(R.id.nametext_dia);
        nsregion_dia = (RadioGroup) findViewById(R.id.nsregion_dia);
        north_dia = (RadioButton) findViewById(R.id.north_dia);
        sourth_dia = (RadioButton) findViewById(R.id.sourth_dia);
        query_dia = (Button) findViewById(R.id.query_dia);

        // 默认选中北区
        north_dia.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query_dia:
                Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show();

                name_dia = nametext_dia.getText().toString();
                if (TextUtils.isEmpty(name_dia)) {
                    Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (north_dia.isChecked()) {
                    region_dia = "north";
                } else {
                    region_dia = "sourth";
                }
                // 查询 设置queryFlag为“query”
                MainActivity.mainActionStart(this, name_dia, region_dia);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            query_dia.setBackgroundResource(R.drawable.but_login_bg_guest_tap);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            query_dia.setBackgroundResource(R.drawable.but_login_bg_guest);
        }
        return false;
    }
}

