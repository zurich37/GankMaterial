package com.zurich.gankmaterial.widget;


import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.zurich.gankmaterial.util.DimenUtils;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 通用的ImageView
 * @author weixinfei
 */
public class GankImageView extends AppCompatImageView {

    public GankImageView(Context context) {
        super(context);
    }

    public GankImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GankImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void displayFromUrl(String url) {
        Glide.with(getContext())
                .load(url)
                .bitmapTransform(new CenterCrop(getContext()), new RoundedCornersTransformation(getContext(), DimenUtils.dp2px(getContext(), 4), 0, RoundedCornersTransformation.CornerType.ALL))
                .into(GankImageView.this);
    }

    /**
     * 加载无圆角图片
     *
     * @param url
     */
    public void displayFromUrlWithoutCorner(String url) {
        Glide.with(getContext())
                .load(url)
                .into(GankImageView.this);
    }

    /**
     * 高斯模糊图片
     */
    public void displayFromUrlWithBlur(String url) {
        Glide.with(getContext())
                .load(url)
                .dontAnimate()
                // 设置高斯模糊
                .bitmapTransform(new BlurTransformation(getContext(), 12, 4), new CenterCrop(getContext()))
                .into(GankImageView.this);
    }
}
