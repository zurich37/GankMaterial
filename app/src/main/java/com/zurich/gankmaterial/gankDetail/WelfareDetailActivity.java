package com.zurich.gankmaterial.gankDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zurich.gankmaterial.GankConstans;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseActivity;
import com.zurich.gankmaterial.data.GankData;
import com.zurich.gankmaterial.util.ImageUtil;

import java.io.File;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 福利详情页
 *
 * @author weixinfei
 * @date 16/5/30
 */
public class WelfareDetailActivity extends BaseActivity {

    public static final String EXTRA_WELFARE_DATA = "EXTRA_WELFARE_DATA";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ivMeizhi)
    ImageView ivMeizhi;
    private String desc;
    private String url;
    private PhotoViewAttacher attacher;
    private Bitmap bitmap;

    @Override
    protected boolean parseIntent() {
        Intent intent = getIntent();
        GankData gankData = (GankData) intent.getSerializableExtra(EXTRA_WELFARE_DATA);
        if (gankData != null) {
            desc = gankData.desc;
            url = gankData.url;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_welfare_detail);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        if (!TextUtils.isEmpty(url)) {
            attacher = new PhotoViewAttacher(ivMeizhi);
            Glide.with(this)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(new SimpleTarget<Bitmap>() {

                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            ivMeizhi.setImageBitmap(resource);
                            attacher.update();
                            bitmap = resource;
                        }
                    });
        }
    }

    @Override
    protected void setUpToolbar(int titleResId, int menuId, int mode) {
        mToolbar.setTitle(desc);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welfare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_share:
                //图片名称处理
                String[] strings = url.substring(url.lastIndexOf("/") + 1).split("\\.");
                String fileName = strings[0] + "." + strings[1];

                Intent intent = new Intent(Intent.ACTION_SEND);
                File file = new File(GankConstans.WELFARE_DIR + fileName);
                if (!file.exists()) {
                    ImageUtil.saveImage(this, url, bitmap, ivMeizhi, "save", false);
                }
                Uri uri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(this, getPackageName()+".fileprovider", file);
                } else {
                    uri = Uri.fromFile(file);
                }
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(intent);
                break;
            case R.id.action_save:
                ImageUtil.saveImage(this, url, bitmap, ivMeizhi, "save", true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void launchActivity(FragmentActivity activity, GankData gankData) {
        Intent intent = new Intent(activity, WelfareDetailActivity.class);
        intent.putExtra(EXTRA_WELFARE_DATA, gankData);
        activity.startActivity(intent);
    }
}
