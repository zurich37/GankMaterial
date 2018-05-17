package com.zurich.gankmaterial.mainPage;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.zurich.gankmaterial.GankConstans;
import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.base.BaseActivity;
import com.zurich.gankmaterial.base.BaseFragment;
import com.zurich.gankmaterial.splash.SplashActivity;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseFragment.OnFragmentOpenDrawerListener {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected boolean parseIntent() {
        return true;
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_main, 0, BaseActivity.MODE_NONE);
    }

    @Override
    protected void setUpView() {
        loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        navigationView.setNavigationItemSelectedListener(this);
        setUpMenu(R.menu.main);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(GankConstans.KEY_HOME_ACTION, GankConstans.ACTION_BACK_TO_HOME);
        switch (action) {
            case GankConstans.ACTION_KICK_OUT:
                protectApp();
                break;
            default:
                break;
        }
    }

    @Override
    protected void protectApp() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressedSupport() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressedSupport();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOpenDrawer() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }
}
