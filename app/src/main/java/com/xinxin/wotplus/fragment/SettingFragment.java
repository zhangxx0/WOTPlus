package com.xinxin.wotplus.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;

import com.xinxin.wotplus.R;

/**
 * Created by xinxin on 2016/4/10.
 * 设置Fragment
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference mClearCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        mClearCache = findPreference("clear_cache");
        // setSummary

        mClearCache.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (mClearCache == preference) {
            Snackbar.make(getView(), "缓存已清除", Snackbar.LENGTH_SHORT).show();
        }
        return false;
    }
}
