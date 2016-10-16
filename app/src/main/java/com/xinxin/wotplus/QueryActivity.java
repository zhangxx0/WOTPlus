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
import com.xinxin.wotplus.util.HttpUtil;
import com.xinxin.wotplus.util.PreferenceUtils;

/**
 * Created by xinxin on 2016/3/19.
 * 昵称查询页面
 */
public class QueryActivity extends BaseActivity implements View.OnClickListener {

    private EditText nametext;
    private RadioGroup nsregion;
    private RadioButton north,sourth;
    private Button query;

    /**
     * 大区标识
     */
    public static final String REGION_NORTH = "north";
    public static final String REGION_SOUTH = "south";

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
        // 缓存上一次查询的玩家昵称
        String sname = PreferenceUtils.getCustomPrefString(QueryActivity.this, "queryinfo", "name", "");
        String sregion = PreferenceUtils.getCustomPrefString(QueryActivity.this, "queryinfo", "region", "");

        if (!TextUtils.isEmpty(sname)) {
            nametext.setText(sname);
        }
        if (!TextUtils.isEmpty(sregion)) {
            if (REGION_NORTH.equals(sregion)) {
                north.setChecked(true);
            } else if (REGION_SOUTH.equals(sregion)) {
                sourth.setChecked(true);
            }
        } else {
            // 默认选中北区
            north.setChecked(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:

                name = nametext.getText().toString();
                // 昵称不能为空
                if (TextUtils.isEmpty(name)) {
                    Snackbar.make(v, "请输入昵称", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                // 昵称长度不得少于 4个字数
                if (name.length() < 4) {
                    Snackbar.make(v, "昵称长度不得少于4个字数", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                if (north.isChecked()) {
                    region = REGION_NORTH;
                } else {
                    region = REGION_SOUTH;
                }
                // 查询 设置queryFlag为“query”
                if (!HttpUtil.isNetworkAvailable()) {
                    Snackbar.make(v, "网络不可用！", Snackbar.LENGTH_LONG).show();
                } else {
                    MainActivity.mainActionStart(this, name, region);
                    finish();
                }
                break;
            default:
                break;
        }
    }

}
