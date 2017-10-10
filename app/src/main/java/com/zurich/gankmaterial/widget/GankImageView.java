package com.zurich.gankmaterial.widget;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.zurich.gankmaterial.util.DimenUtils;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GankImageView extends android.support.v7.widget.AppCompatImageView {

    public static final int IMAGE_TYPE_VIDEO_LIST = 0x01;
    public static final int IMAGE_TYPE_VIDEO_LAST_PLAY = 0x02;

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
