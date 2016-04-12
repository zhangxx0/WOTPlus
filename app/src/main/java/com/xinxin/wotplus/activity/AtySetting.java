package com.xinxin.wotplus.activity;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
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

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);

            mClearCache = findPreference("clear_cache");
            updateCache();
            mClearCache.setOnPreferenceClickListener(this);
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
            }
            return false;
        }
    }
}
