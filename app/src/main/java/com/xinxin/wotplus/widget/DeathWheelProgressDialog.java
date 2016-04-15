package com.xinxin.wotplus.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.xinxin.wotplus.R;

/**
 * Created by xinxin on 2016/4/16.
 * 死亡之轮 加载框
 */
public class DeathWheelProgressDialog extends Dialog {

    private Context context = null;
    private static DeathWheelProgressDialog deathWheelProgressDialog = null;

    public DeathWheelProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public DeathWheelProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static DeathWheelProgressDialog createDialog(Context context) {
        deathWheelProgressDialog = new DeathWheelProgressDialog(context, R.style.CustomProgressDialog);
        deathWheelProgressDialog.setContentView(R.layout.death_wheel_progress_dialog);
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

    public DeathWheelProgressDialog setTitile(String strTitle) {
        return deathWheelProgressDialog;
    }

    public DeathWheelProgressDialog setMessage(String strMessage) {
//        TextView tvMsg = (TextView)deathWheelProgressDialog.findViewById(R.id.id_tv_loadingmsg);
//
//        if (tvMsg != null){
//            tvMsg.setText(strMessage);
//        }

        return deathWheelProgressDialog;
    }

}
