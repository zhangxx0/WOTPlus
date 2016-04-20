package com.xinxin.wotplus.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.util.CommonUtil;
import com.xinxin.wotplus.util.DataClearManager;

/**
 * Created by xinxin on 2016/4/10.
 * 设置Activity
 */
public class AtySetting extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.setting_toolbar);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.setting_framelayout, new SettingFragment()).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // 销毁并返回
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置Fragment
     */
    public static class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

        private Preference mClearCache;
        private Preference feedback;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);

            mClearCache = findPreference("clear_cache");
            updateCache();
            mClearCache.setOnPreferenceClickListener(this);

            feedback = findPreference("feedback");
            feedback.setOnPreferenceClickListener(this);

        }

        /**
         * 更新缓存大小的展示
         */
        void updateCache() {
            mClearCache.setSummary(DataClearManager.getApplicationDataSize(getActivity()));
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (mClearCache == preference) {
                DataClearManager.cleanApplicationData(getActivity());
                updateCache();
                Snackbar.make(getView(), "缓存已清除", Snackbar.LENGTH_SHORT).show();
            } else if (preference.equals(feedback)) {
                // 发送邮件
                String model = android.os.Build.MODEL; // 型号
                String brand = android.os.Build.BRAND; // 品牌
                String version = android.os.Build.VERSION.RELEASE; // 系统版本
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:zxx377241804@gmail.com"));
                data.putExtra(Intent.EXTRA_SUBJECT, "WOTPlus安卓客户端反馈");
                data.putExtra(Intent.EXTRA_TEXT, "\n\n\n技术信息:\n" + "WOTPlus Version-" + CommonUtil.getVersion(getActivity()) + "\n" + brand+" "+model + "\n" + version);
                startActivity(data);
            }
            return false;
        }
    }
}
