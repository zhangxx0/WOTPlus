package com.xinxin.wotplus.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.xinxin.wotplus.MyApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xinxin on 2016/2/17.
 * <p/>
 * 网络请求工具类
 */
public class HttpUtil {

    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {

        if (!isNetworkAvailable()) {
            // 使用MyApplication获取context；
            Toast.makeText(MyApplication.getContext(), "network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(30000);
                    connection.setReadTimeout(30000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36");
//                    connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//                    connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
//                    connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
//                    connection.setRequestProperty("Cache-Control", "max-age=0");
//                    connection.setRequestProperty("Connection", "keep-alive");
//                    connection.setRequestProperty("Host", "ncw.worldoftanks.cn");
//                    connection.setRequestProperty("Referer", "http://ncw.worldoftanks.cn/zh-cn/community/accounts/");
//                    connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
//                    connection.setRequestProperty("Cookie", "cm.internal.bs_id=5f7d64f7-0667-4060-0fa1-bed7ceae9744; hllang=zh-cn; csrftoken_portal_cn1=Gt7EcsZaX39YoRjofEw3wS5FcGJEul9o; map_visited=visited; session_id_cn2=1his7bgyu1mgssijvdf7qqt4vt4c9525; session_id_cn1=fxtlw64cm9884rxuowr5h9fsfcapgw30; newbie-lifetime=1453518950815-1458658317659; authentication_confirmation_expires_at=1458658318; csrftoken_portal_cn1=Gt7EcsZaX39YoRjofEw3wS5FcGJEul9o; _ga=GA1.3.23560097.1453518953");


                    Log.d("httputil", String.valueOf(connection.getResponseCode()));

//                    InputStream in = connection.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getResponseCode() / 100 == 2 ? connection.getInputStream() : connection.getErrorStream()));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // return response.toString();
                    // 回调onFinish()方法
                    if (listener != null) {
                        // 在子线程中运行，不能进行UI操作；
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // return e.getMessage();
                    // 在子线程中运行，不能进行UI操作；
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    /**
     * 获取网络图片
     * xinxin
     * 2016年3月30日23:32:12
     *
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {

        URL myFileURL;
        Bitmap bitmap = null;
        try {
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 获取图片
     */
    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


    // 判断网络是否可用，以下三个方法同
    //  来源：https://github.com/Freelander/Android_Data/blob/master/fake_land/NetworkUtils.java
    public static boolean isNetworkAvailable() {

        Context context = MyApplication.getContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (NetworkInfo aNetworkInfo : networkInfo) {
//                    System.out.println(i + "===状态===" + networkInfo[i].getState());
//                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (aNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断WIFI是否打开
     *
     * @param context
     * @return
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 判断是否是3G网络
     *
     * @param context
     * @return
     */
    public static boolean is3rd(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 判断是wifi还是3g网络
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

}
