package com.xinxin.wotplus.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.xinxin.wotplus.R;

/**
 * Created by xinxin on 2016年6月5日17:21:55
 * 燃烧之WOT 加载框
 */
public class FireWotProgressDialog extends Dialog {

    private Context context = null;
    private static FireWotProgressDialog deathWheelProgressDialog = null;

    public FireWotProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public FireWotProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static FireWotProgressDialog createDialog(Context context) {
        deathWheelProgressDialog = new FireWotProgressDialog(context, R.style.CustomProgressDialog);
        deathWheelProgressDialog.setContentView(R.layout.firewot_progress_dialog);
        deathWheelProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        deathWheelProgressDialog.setCancelable(false);

        return deathWheelProgressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        if (deathWheelProgressDialog == null) {
            return;
        }

//        ImageView imageView = (ImageView) deathWheelProgressDialog.findViewById(R.id.loadingImageView);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
//        animationDrawable.start();
    }

    public FireWotProgressDialog setTitile(String strTitle) {
        return deathWheelProgressDialog;
    }

    public FireWotProgressDialog setMessage(String strMessage) {
//        TextView tvMsg = (TextView)deathWheelProgressDialog.findViewById(R.id.id_tv_loadingmsg);
//
//        if (tvMsg != null){
//            tvMsg.setText(strMessage);
//        }

        return deathWheelProgressDialog;
    }

}
