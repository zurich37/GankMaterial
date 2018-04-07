package com.zurich.gankmaterial.webPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseActivity;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.widget.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by weixinfei on 2017/10/9.
 */

public class GankDataDetailActivity extends BaseActivity {
    public static final String EXTRA_GANK_DATA = "extra_gank_data";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nestScroll_gank_detail)
    NestedScrollView nestScrollGankDetail;
    private GankData gankData;
    private ProgressWebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        ButterKnife.bind(this);

        if (!handleIntent(getIntent())) {
            return;
        }

        initToolbar();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");//设置分享内容的类型
            intent.putExtra(Intent.EXTRA_SUBJECT, gankData.desc);//添加分享内容标题
            intent.putExtra(Intent.EXTRA_TEXT, gankData.url);//添加分享内容
            intent = Intent.createChooser(intent, "分享到...");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean handleIntent(Intent intent) {
        gankData = (GankData) intent.getSerializableExtra(EXTRA_GANK_DATA);
        return gankData != null;
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitle(gankData.desc);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
    }

    private void initView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWebView = new ProgressWebView(getApplicationContext());
        mWebView.setLayoutParams(params);
        nestScrollGankDetail.addView(mWebView);
        mWebView.loadUrl(gankData.url);
    }

    @Override
    protected void onDestroy() {
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
        nestScrollGankDetail.removeAllViews();
        super.onDestroy();
    }

    public static void launchActivity(Activity activity, GankData gankData) {
        Intent intent = new Intent(activity, GankDataDetailActivity.class);
        intent.putExtra(EXTRA_GANK_DATA, gankData);
        activity.startActivity(intent);
    }
}
