package com.zurich.gankmaterial.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zurich.gankmaterial.R;

/**
 * 各页面提示view
 * Created by weixinfei on 16/5/10.
 */
public class HintView extends FrameLayout {

    private ViewStub loadingViewStub;
    private ViewGroup loadingRootView;
    private ViewStub emptyViewStub;
    private ViewGroup emptyRootView;
    private ViewStub errorViewStub;
    private ViewGroup errorRootView;
    private ProgressBar loadingPbView;

    public static final int DEFAULT = 0;
    public static final int LOADING = 1;
    public static final int FAILURE = 2;
    public static final int EMPTY = 3;
    private int currentStatus = DEFAULT;
    private static final int MINIMUM_DURATION = 2000;
    private long loadingTime;

    private Runnable delayAnimationHiddenRunnable;

    private TextView emptyMessageTextView;
    private TextView errorMessageTextView;

    private HintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public HintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HintView(Context context) {
        super(context);
        init();
    }

    private void init() {
        // 添加加载中子页面
        loadingViewStub = new ViewStub(getContext(), R.layout.widget_loading);
        addView(loadingViewStub);

        // 添加页面空子页面
        emptyViewStub = new ViewStub(getContext(), R.layout.widget_empty);
        addView(emptyViewStub);

        // 添加加载失败子页面
        errorViewStub = new ViewStub(getContext(), R.layout.widget_error);
        addView(errorViewStub);


        setVisibility(GONE);
    }

    /**
     * 显示加载中页面，默认提示信息为“加载中…”
     */
    public LoadingBuilder loading() {
        currentStatus = LOADING;
        return new LoadingBuilder(this);
    }

    public boolean isShowing(){
        return currentStatus != DEFAULT;
    }

    /**
     * 显示页面空页面
     *
     * @param message
     * @return
     */
    public EmptyBuilder empty(String message) {
        currentStatus = EMPTY;
        return new EmptyBuilder(this, message);
    }

    public static class EmptyBuilder {
        private HintView hintView;
        private String message;

        private EmptyBuilder(HintView hintView, String message) {
            this.hintView = hintView;
            this.message = message;
        }

        /**
         * 设置提示消息
         */
        public EmptyBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 显示
         */
        public void show() {
            hintView.tryCancelDelayedHidden();

            if (hintView.emptyViewStub != null) {
                View rootView = hintView.emptyViewStub.inflate();
                hintView.emptyViewStub = null;
                hintView.emptyMessageTextView = (TextView) hintView.findViewById(R.id.empty_hint);
                hintView.emptyRootView = (ViewGroup) rootView;
            }

            // 设置提示文本以及按钮点击事件
            hintView.emptyMessageTextView.setText(message);

            // 隐藏掉其它的
            if (hintView.loadingRootView != null) {
                hintView.loadingRootView.setVisibility(View.GONE);
            }
            if (hintView.errorRootView != null) {
                hintView.errorRootView.setVisibility(View.GONE);
            }

            // 显示出来
            hintView.emptyRootView.setVisibility(View.VISIBLE);
            hintView.setVisibility(VISIBLE);
            hintView.setClickable(true);
        }
    }


    public static class LoadingBuilder {
        private HintView hintView;
        private String message;
        private String tips;

        private LoadingBuilder(HintView hintView) {
            this.hintView = hintView;
            this.message = "加载中…";
        }

        /**
         * 设置提示消息
         */
        public LoadingBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置小提示
         */
        public LoadingBuilder tips(String tips) {
            this.tips = tips;
            return this;
        }

        /**
         * 显示
         */
        public void show() {
            // 如果是第一次执行此方法就始化加载中部分的View
            if (hintView.loadingViewStub != null) {
                View rootView = hintView.loadingViewStub.inflate();
                hintView.loadingViewStub = null;

                hintView.loadingPbView = (ProgressBar) hintView.findViewById(R.id.pb_loading);

                hintView.loadingRootView = (ViewGroup) rootView;
            }
            hintView.loadingRootView.setVisibility(View.VISIBLE);
            hintView.setVisibility(VISIBLE);
            hintView.setClickable(true);
            hintView.loadingTime = System.currentTimeMillis();
        }
    }

    /**
     * 显示页面空页面
     *
     * @param message
     * @return
     */
    public EmptyBuilder error(String message) {
        currentStatus = FAILURE;
        return new EmptyBuilder(this, message);
    }

    public static class ErrorBuilder {
        private HintView hintView;
        private String message;

        private ErrorBuilder(HintView hintView, String message) {
            this.hintView = hintView;
            this.message = message;
        }

        /**
         * 设置提示消息
         */
        public ErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 显示
         */
        public void show() {
            hintView.tryCancelDelayedHidden();

            if (hintView.errorViewStub != null) {
                View rootView = hintView.errorViewStub.inflate();
                hintView.errorViewStub = null;

                hintView.errorMessageTextView = hintView.findViewById(R.id.error_hint);

                hintView.errorRootView = (ViewGroup) rootView;
            }

            // 设置提示文本以及按钮点击事件
            hintView.errorMessageTextView.setText(message);

            // 隐藏掉其它的
            if (hintView.loadingRootView != null) {
                hintView.loadingRootView.setVisibility(View.GONE);
            }
            if (hintView.emptyRootView != null) {
                hintView.emptyRootView.setVisibility(View.GONE);
            }

            // 显示出来
            hintView.errorRootView.setVisibility(View.VISIBLE);
            hintView.setVisibility(VISIBLE);
            hintView.setClickable(true);
        }
    }

    /**
     * 隐藏提示视图，默认通过渐隐动画慢慢的隐藏
     */
    public void hidden() {
        if (currentStatus == DEFAULT)
            return;
        // 如果从loading开始到现在的时间小于小于MINIMUM_DURATION就再等一会儿
        long currentTime = System.currentTimeMillis();
        if (currentTime - loadingTime >= MINIMUM_DURATION) {
            animationHidden();
        }else {
            if (delayAnimationHiddenRunnable == null) {
                delayAnimationHiddenRunnable = new Runnable() {
                    @Override
                    public void run() {
                        animationHidden();
                    }
                };
            }
            postDelayed(delayAnimationHiddenRunnable, MINIMUM_DURATION
                    - (currentTime - loadingTime));
        }
    }

    /**
     * 动画隐藏
     */
    private void animationHidden() {
        setClickable(false);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(400);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (loadingRootView != null && loadingRootView.getVisibility() != View.GONE) {
                    loadingRootView.setVisibility(View.GONE);
                }
                if (emptyRootView != null && emptyRootView.getVisibility() != View.GONE) {
                    emptyRootView.setVisibility(View.GONE);
                }
                if (getVisibility() != View.GONE) {
                    setVisibility(View.GONE);
                }
            }
        });
        startAnimation(alphaAnimation);
    }

    /**
     * 尝试取消延迟隐藏的设定
     */
    private void tryCancelDelayedHidden() {
        if (delayAnimationHiddenRunnable != null) {
            removeCallbacks(delayAnimationHiddenRunnable);
        }
    }
}
