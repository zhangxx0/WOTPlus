package com.xinxin.wotplus.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinxin.wotplus.R;
import com.xinxin.wotplus.model.Tank;
import com.xinxin.wotplus.util.HttpUtil;

import java.io.InputStream;
import java.util.List;

/**
 * Created by xinxin on 2016/4/7.
 * 各个分类的坦克列表适配器
 */
public class TanksByTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;

    private List<Tank> tanksByTypeList;

    public TanksByTypeAdapter(List<Tank> tanksByTypeList, Context context) {
        this.tanksByTypeList = tanksByTypeList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.activity_tanks_item, parent, false);

        return new TankShortInfo(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Tank tank = tanksByTypeList.get(position);

        // 2016年4月8日01:17:44
        if ("b-armory-wrapper__china".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_china);
        } else if ("b-armory-wrapper__germany".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_germany);
        } else if ("b-armory-wrapper__ussr".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_ussr);
        } else if ("b-armory-wrapper__france".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_france);
        } else if ("b-armory-wrapper__usa".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_usa);
        } else if ("b-armory-wrapper__uk".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_uk);
        } else if ("b-armory-wrapper__japan".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_japan);
        } else if ("b-armory-wrapper__czech".equals(tank.getTankCountry())) {
            ((TankShortInfo) holder).tankCountry.setImageResource(R.drawable.ch_czech);
        }

        ((TankShortInfo) holder).tankLevel.setText(tank.getTankLevel());
        ((TankShortInfo) holder).tankName.setText(tank.getTankName());
        ((TankShortInfo) holder).tankFightNum.setText(tank.getTankFightNum());
        ((TankShortInfo) holder).tankWinRate.setText(tank.getTankWinRate());

        new HttpUtil.DownloadImageTask(((TankShortInfo) holder).tankIcon).execute(tank.getTankIcon());
        new HttpUtil.DownloadImageTask(((TankShortInfo) holder).tankBadge).execute(tank.getTankBadge());

    }

    @Override
    public int getItemCount() {
        return tanksByTypeList.size();
    }

    class TankShortInfo extends RecyclerView.ViewHolder {

        private TextView tankLevel, tankName, tankFightNum, tankWinRate;
        private ImageView tankCountry, tankIcon, tankBadge;

        public TankShortInfo(View itemView) {
            super(itemView);
            tankCountry = (ImageView) itemView.findViewById(R.id.tankCountry);
            tankLevel = (TextView) itemView.findViewById(R.id.tankLevel);
            tankName = (TextView) itemView.findViewById(R.id.tankName);
            tankFightNum = (TextView) itemView.findViewById(R.id.tankFightNum);
            tankWinRate = (TextView) itemView.findViewById(R.id.tankWinRate);
            tankIcon = (ImageView) itemView.findViewById(R.id.tankIcon);
            tankBadge = (ImageView) itemView.findViewById(R.id.tankBadge);
        }
    }

    /**
     * 获取图片
     */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();

                // 怎么下载原图?现在的情况是缩小了
                BitmapFactory.Options options = new BitmapFactory.Options();
                // 如果我们把它设为true，那么BitmapFactory.decodeFile(String path, Options opt)并不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你，这样就不会占用太多的内存，也就不会那么频繁的发生OOM了
                // options.inJustDecodeBounds = true;
                // options.inSampleSize = 1;

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
}
