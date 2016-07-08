package com.xinxin.wotplus.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import com.xinxin.wotplus.R;


/**
 * Created by xinxin on 2016/7/5.
 * 2016年7月5日11:27:04
 * 尝试编写WOT仪表盘控件
 */
public class WotIndicatorView extends View {

    private Context mContext;
    private Bitmap mBackgroundBitmap;
    private Bitmap mPointerBitmap;
    private Paint mPaint;

    private final Point mRotationPoint = new Point();
    private float mCurrentAngle = -36.0F;
    private float mDesiredAngle;

    private static Interpolator sInterpolator = new OvershootInterpolator(0.1F);
    private float anglesweep = 72.0F;

    public WotIndicatorView(Context context) {
        super(context);
    }

    public WotIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        mPaint = new Paint();
        // mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);

        mBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.radial_graph);
        mPointerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.radial_arrow);
    }

    /**
     * 计算停止角度
     */
    public void setmDesiredAngle(float paramFloat) {
        this.mDesiredAngle = calculateAngleForKoef(paramFloat);
        for (float f = this.mDesiredAngle; ; f = -36.0F) {
            this.mCurrentAngle = f;
            return;
        }
    }

    public float calculateAngleForKoef(float paramFloat) {
        float resultFloat = 0;

        if (paramFloat < 1.5) {
            resultFloat = -36.0F * ((1.5F - paramFloat) / 1.5F);
        } else if (paramFloat == 1.5) {
            resultFloat = 0.0F;
        } else if (paramFloat > 1.5) {
            resultFloat = 36.0F * ((paramFloat - 1.5F) / 1.5F);
        }

        return resultFloat;
    }

    /**
     * 指针移动的属性动画
     */
    public void animateAngle() {
        ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this,
                "angle",
                new float[]{mCurrentAngle, mDesiredAngle});
        // 差值器
        localObjectAnimator.setInterpolator(sInterpolator);
        localObjectAnimator.setDuration((int) Math.abs((mDesiredAngle - mCurrentAngle)
                * 2000.0F / anglesweep));
        localObjectAnimator.start();
    }

    @Override
    protected void onMeasure(int paramInt1, int paramInt2) {

        int i = MeasureSpec.getSize(paramInt1);
        this.mRotationPoint.x = (i / 2);
        this.mRotationPoint.y = pxFromDp(214.0F);

        for (; ; ) {
            setMeasuredDimension(MeasureSpec.getSize(paramInt1), this.mBackgroundBitmap.getHeight() / 3 * 2);
            return;
        }
    }

    // 创建你自己想要大小的 bitmap
    public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newWidth = w;
            int newHeight = h;
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
                    height, matrix, true);
            return resizedBitmap;
        } else {
            return null;
        }
    }

    @Override
    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);

        // 测量的宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        // 图片太大，需要缩小展示
        // 宽度：744 / 1.5 ; 高度：198 / 1.5;
        // paramCanvas.drawBitmap(this.mBackgroundBitmap, this.mRotationPoint.x - this.mBackgroundBitmap.getWidth() / 2, 0.0F, this.mPaint);
        paramCanvas.drawBitmap(resizeBitmap(mBackgroundBitmap, 496, 132), this.mRotationPoint.x - 496 / 2, 0.0F, this.mPaint);

        float f = Math.min(36.0F, Math.max(this.mCurrentAngle, -36.0F));
        int i = paramCanvas.save();
        paramCanvas.rotate(f, this.mRotationPoint.x, this.mRotationPoint.y);

        // paramCanvas.drawBitmap(this.mPointerBitmap, this.mRotationPoint.x - this.mPointerBitmap.getWidth() / 2, 0.0F, this.mPaint);
        paramCanvas.drawBitmap(resizeBitmap(mPointerBitmap, 22 / 2, 105 / 2), this.mRotationPoint.x - 22 / 2 / 2, 0.0F, this.mPaint);
        paramCanvas.restoreToCount(i);
    }

    public void setAngle(float paramFloat) {
        this.mCurrentAngle = paramFloat;
        invalidate();
    }

    /* 普通的onMeasure方法
     @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("meaure", String.valueOf(measureWidth(widthMeasureSpec)));
        Log.d("meaure", String.valueOf(measureHeight(heightMeasureSpec)));

        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }*/

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    protected int pxFromDp(float paramFloat) {
        return (int) (getContext().getResources().getDisplayMetrics().density * paramFloat);
    }
}
