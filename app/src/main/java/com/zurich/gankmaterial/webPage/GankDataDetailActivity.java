package com.zurich.gankmaterial.webPage;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseActivity;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.widget.ProgressWebView;

import butterknife.BindView;

/**
 * Created by weixinfei on 2017/10/9.
 */

public class GankDataDetailActivity extends BaseActivity {
    public static final String EXTRA_GANK_DATA = "extra_gank_data";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nestScroll_gank_detail)
    FrameLayout contentLayout;
    private GankData gankData;
    private ProgressWebView mWebView;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_gank_detail, 0, BaseActivity.MODE_NONE);
    }

    @Override
    protected void setUpView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new ProgressWebView(getApplicationContext());
        mWebView.setLayoutParams(params);
        contentLayout.addView(mWebView);
    }

    @Override
    protected void setUpData() {
        mWebView.loadUrl(gankData.url);
    }

    @Override
    protected void setUpToolbar(int titleResId, int menuId, int mode) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setTitle(gankData.desc);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        setUpMenu(R.menu.menu_detail);
    }

    @Override
    protected boolean parseIntent() {
        gankData = (GankData) getIntent().getSerializableExtra(EXTRA_GANK_DATA);
        return gankData != null;
    }

    @Override
    protected void onDestroy() {
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
        contentLayout.removeAllViews();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, gankData.desc);
            intent.putExtra(Intent.EXTRA_TEXT, gankData.url);
            intent = Intent.createChooser(intent, "分享到...");
            startActivity(intent);
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void launchActivity(Activity activity, GankData gankData) {
        Intent intent = new Intent(activity, GankDataDetailActivity.class);
        intent.putExtra(EXTRA_GANK_DATA, gankData);
        activity.startActivity(intent);
    }
}
