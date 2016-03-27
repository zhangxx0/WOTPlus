package com.xinxin.wotplus.util;

/**
 * Created by xinxin on 2016/2/17.
 *
 * 利用java的回调机制
 */
public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
