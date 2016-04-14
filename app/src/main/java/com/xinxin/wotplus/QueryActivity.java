package com.xinxin.wotplus;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xinxin.wotplus.base.BaseActivity;

/**
 * Created by xinxin on 2016/3/19.
 * 昵称查询页面
 */
public class QueryActivity extends BaseActivity implements View.OnClickListener {

    private EditText nametext;
    private RadioGroup nsregion;
    private RadioButton north,sourth;
    private Button query;

    private static String name, region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 主要在于画页面
        setContentView(R.layout.activity_query);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();

        query.setOnClickListener(this);

    }

    private void initView() {
        nametext = (EditText) findViewById(R.id.nametext);
        nsregion = (RadioGroup) findViewById(R.id.nsregion);
        north = (RadioButton) findViewById(R.id.north);
        sourth = (RadioButton) findViewById(R.id.sourth);
        query = (Button) findViewById(R.id.query);

        // 默认选中北区
        north.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                name = nametext.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Snackbar.make(v, "请输入昵称", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                if (north.isChecked()) {
                    region = "north";
                } else {
                    region = "sourth";
                }
                // 查询
                MainActivity.mainActionStart(this, name, region);
                break;
            default:
                break;
        }
    }
}
