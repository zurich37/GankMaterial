package com.zurich.gankmaterial.mainPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zurich.gankmaterial.data.source.LocalAppInfoRepository;
import com.zurich.gankmaterial.data.source.remote.GankRemoteDataSource;
import com.zurich.gankmaterial.data.source.GankDataRepository;
import com.zurich.gankmaterial.data.source.remote.LocalAppInfoRemoteDataSource;
import com.zurich.gankmaterial.gankDatas.GankDataFragment;
import com.zurich.gankmaterial.localApps.LocalAppInfoDataFragment;
import com.zurich.gankmaterial.gankDatas.AndroidDataPresenter;
import com.zurich.gankmaterial.localApps.LocalAppInfoDataPresenter;
import com.zurich.gankmaterial.util.schedulers.ImmediateSchedulerProvider;

/**
 * tab adapter
 */
public class HomeTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"Android", "iOS", /*"休息视频", "拓展资源", "前端", "福利"*/};
    private GankDataFragment gankDataFragment;

    public HomeTabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                gankDataFragment = (GankDataFragment) GankDataFragment.newInstance(0);
                AndroidDataPresenter androidDataPresenter = new AndroidDataPresenter(GankDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankDataFragment);
                return gankDataFragment;
            case 1:
                LocalAppInfoDataFragment localAppInfoDataFragment = LocalAppInfoDataFragment.newInstance(new Bundle());
                ImmediateSchedulerProvider schedulerProvider = new ImmediateSchedulerProvider();
                new LocalAppInfoDataPresenter(LocalAppInfoRepository.getInstance(LocalAppInfoRemoteDataSource.getInstance()), localAppInfoDataFragment, schedulerProvider);
                return localAppInfoDataFragment;
            case 2:
                GankDataFragment gankDataFragment2 = (GankDataFragment) GankDataFragment.newInstance(0);
                new AndroidDataPresenter(GankDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankDataFragment2);
            case 3:
                GankDataFragment gankDataFragment3 = (GankDataFragment) GankDataFragment.newInstance(0);
                new AndroidDataPresenter(GankDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankDataFragment3);
            case 4:
                GankDataFragment gankDataFragment4 = (GankDataFragment) GankDataFragment.newInstance(0);
                new AndroidDataPresenter(GankDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankDataFragment4);
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
