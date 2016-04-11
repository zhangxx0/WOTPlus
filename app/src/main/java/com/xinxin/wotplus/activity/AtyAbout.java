package com.xinxin.wotplus.activity;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.util.CommonUtil;

/**
 * Created by xinxin on 2016/4/11.
 * 关于页面
 */
public class AtyAbout extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.about_toolbar);
        toolbar.setTitle("关于");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.about_framelayout, new AboutFragment()).commit();

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
     * About Fragment
     */
    public static class AboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

        private final String INTRODUCTION = "introduction";
        private final String CURRENT_VERSION = "current_version";
        private final String SHARE = "share";
        private final String STAR = "Star";
        private final String ENCOURAGE = "encourage";
        private final String BLOG = "blog";
        private final String GITHUB = "github";
        private final String EMAIL = "email";
        private final String CHECK = "check_version";

        private Preference mIntroduction;
        private Preference mVersion;
        private Preference mCheckVersion;
        private Preference mShare;
        private Preference mStar;
        private Preference mEncounrage;
        private Preference mBolg;
        private Preference mGithub;
        private Preference mEmail;

        View view;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = super.onCreateView(inflater, container, savedInstanceState);
            addPreferencesFromResource(R.xml.about);

            mIntroduction = findPreference(INTRODUCTION);
            mVersion = findPreference(CURRENT_VERSION);
            mCheckVersion = findPreference(CHECK);
            mShare = findPreference(SHARE);
            mStar = findPreference(STAR);
            mEncounrage = findPreference(ENCOURAGE);
            mBolg = findPreference(BLOG);
            mGithub = findPreference(GITHUB);
            mEmail = findPreference(EMAIL);

            mIntroduction.setOnPreferenceClickListener(this);
            mVersion.setOnPreferenceClickListener(this);
            mCheckVersion.setOnPreferenceClickListener(this);
            mShare.setOnPreferenceClickListener(this);
            mStar.setOnPreferenceClickListener(this);
            mEncounrage.setOnPreferenceClickListener(this);
            mBolg.setOnPreferenceClickListener(this);
            mGithub.setOnPreferenceClickListener(this);
            mEmail.setOnPreferenceClickListener(this);

            mVersion.setSummary("WOTPlus Version-" + CommonUtil.getVersion(getActivity()));

            return view;

        }

        @Override
        public boolean onPreferenceClick(Preference preference) {

            if (mCheckVersion == preference) {
                // 检查版本更新
                Snackbar.make(getView(), "正在检查。。。", Snackbar.LENGTH_SHORT).show();
                CommonUtil.checkVersion(getActivity(), getView());
            }


            return false;
        }
    }

}
