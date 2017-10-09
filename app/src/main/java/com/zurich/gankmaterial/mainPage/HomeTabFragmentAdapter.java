package com.zurich.gankmaterial.mainPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zurich.gankmaterial.data.source.GankAndroidDataRepository;
import com.zurich.gankmaterial.data.source.GankIOSDataRepository;
import com.zurich.gankmaterial.data.source.GankWebDataRepository;
import com.zurich.gankmaterial.data.source.remote.GankRemoteDataSource;
import com.zurich.gankmaterial.gankDatas.GankDataFragment;
import com.zurich.gankmaterial.gankDatas.GankDatasPresenter;

/**
 * tab adapter
 */
public class HomeTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"Android", "iOS", "前端"/*"休息视频", "拓展资源", "福利"*/};
    private GankDataFragment gankDataFragment;

    public HomeTabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                gankDataFragment = (GankDataFragment) GankDataFragment.newInstance(0);
                new GankDatasPresenter(GankAndroidDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankDataFragment);
                return gankDataFragment;
            case 1:
                GankDataFragment gankIOSDataFragment = (GankDataFragment) GankDataFragment.newInstance(0);
                new GankDatasPresenter(GankIOSDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankIOSDataFragment);
                return gankIOSDataFragment;
            case 2:
                GankDataFragment gankWebDataFragment = (GankDataFragment) GankDataFragment.newInstance(0);
                new GankDatasPresenter(GankWebDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankWebDataFragment);
                return gankWebDataFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
