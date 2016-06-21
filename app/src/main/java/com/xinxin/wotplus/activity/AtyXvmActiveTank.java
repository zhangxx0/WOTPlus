package com.xinxin.wotplus.activity;

import android.os.Bundle;
import android.util.Log;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.base.BaseActivity;
import com.xinxin.wotplus.model.XvmMainInfo;
import com.xinxin.wotplus.network.Network;
import com.xinxin.wotplus.util.PreferenceUtils;

import java.util.List;

import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/6/22.
 * 活跃坦克列表
 */
public class AtyXvmActiveTank extends BaseActivity {


    Observer<List<XvmMainInfo.DaylistEntity>> activeTankObserver = new Observer<List<XvmMainInfo.DaylistEntity>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.d("xxx", e.getMessage());
        }

        @Override
        public void onNext(List<XvmMainInfo.DaylistEntity> daylistEntities) {
            Log.d("zzz", String.valueOf(daylistEntities.size()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xvm_active_tank);
        ButterKnife.bind(this);

        String woterId = PreferenceUtils.getCustomPrefString(this, "woterId", "woterId", "");

        Network.getXvmInfo()
                .getXvmThirtyDay(woterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activeTankObserver);

    }
}
