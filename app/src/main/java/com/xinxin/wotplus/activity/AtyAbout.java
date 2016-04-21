package com.xinxin.wotplus.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.xinxin.wotplus.base.SwipeBackBaseActivity;
import com.xinxin.wotplus.util.CommonUtil;

/**
 * Created by xinxin on 2016/4/11.
 * 关于页面
 */
public class AtyAbout extends SwipeBackBaseActivity {


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

        // 应用介绍
        private final String INTRODUCTION = "introduction";
        // 当前版本
        private final String CURRENT_VERSION = "current_version";
        // 检查更新
        private final String CHECK = "check_version";
        // 推荐
        private final String SHARE = "share";
        // 给个Star
        private final String STAR = "Star";
        // 打赏
        private final String ENCOURAGE = "encourage";
        // 关于作者
        private final String BLOG = "blog";
        private final String GITHUB = "github";
        private final String EMAIL = "email";

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

            // 设置当前版本
            mVersion.setSummary("WOTPlus Version-" + CommonUtil.getVersion(getActivity()));

            return view;

        }

        @Override
        public boolean onPreferenceClick(Preference preference) {

            if (preference.equals(mIntroduction)) {
                // 应用介绍：跳转到github readme
                Uri uri = Uri.parse(getString(R.string.readme));
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                getActivity().startActivity(intent);
            } else if (preference.equals(mCheckVersion)) {
                // 检查版本更新
                Snackbar.make(getView(), "正在检查。。。", Snackbar.LENGTH_SHORT).show();
                CommonUtil.checkVersion(getActivity(), getView());
            } else if (preference.equals(mShare)) {
                // 分享推荐
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_txt));
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_app)));
            } else if (preference.equals(mStar)) {
                // 点赞
                new AlertDialog.Builder(getActivity()).setTitle("Star")
                        .setMessage("去项目地址给作者个Star，鼓励下作者")
                        .setNegativeButton("复制", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copyToClipboard(getView(), getString(R.string.app_html));
                            }
                        })
                        .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri uri = Uri.parse(getString(R.string.app_html));
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(uri);
                                getActivity().startActivity(intent);
                            }
                        })
                        .show();
            } else if (preference.equals(mEncounrage)) {
                // 打赏
                new AlertDialog.Builder(getActivity()).setTitle("请作者冲个高级账号？")
                        .setMessage("点击按钮后，作者支付宝账号将会复制到剪切板，" + "你就可以使用支付宝转账给作者了")
                        .setPositiveButton("打赏", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copyToClipboard(getView(), getString(R.string.alipay));
                            }
                        }).show();
            } else if (preference.equals(mBolg)) {
                copyToClipboard(getView(), mBolg.getSummary().toString());
            } else if (preference.equals(mGithub)) {
                copyToClipboard(getView(), mGithub.getSummary().toString());
            } else if (preference.equals(mEmail)) {
                copyToClipboard(getView(), mEmail.getSummary().toString());
            }

            return false;
        }

        // 复制黏贴板
        private void copyToClipboard(View view, String info) {
            ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("msg", info);
            manager.setPrimaryClip(clipData);
            Snackbar.make(view, "已经复制到剪切板", Snackbar.LENGTH_SHORT).show();
        }
    }


}
