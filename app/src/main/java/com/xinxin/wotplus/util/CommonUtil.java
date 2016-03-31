package com.xinxin.wotplus.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by xinxin on 2016/3/31.
 * 普通工具类
 */
public class CommonUtil {

    /**
     * utf编码
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
