package com.zurich.gankmaterial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zurich.gankmaterial.data.source.remote.AndroidRemoteDataSource;
import com.zurich.gankmaterial.data.source.GankDataRepository;
import com.zurich.gankmaterial.fragment.AndroidDataFragment;
import com.zurich.gankmaterial.presenter.AndroidDataPresenter;

/**
 * tab adapter
 */
public class HomeTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"Android"/*, "iOS", "休息视频", "拓展资源", "前端", "福利"*/};
    private AndroidDataFragment androidDataFragment;

    public HomeTabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                androidDataFragment = (AndroidDataFragment) AndroidDataFragment.newInstance(0);
                AndroidDataPresenter androidDataPresenter = new AndroidDataPresenter(GankDataRepository.getInstance(AndroidRemoteDataSource.getInstance()), androidDataFragment);
                return androidDataFragment;
            case 1:
                AndroidDataFragment androidDataFragment1 = (AndroidDataFragment) AndroidDataFragment.newInstance(0);
                new AndroidDataPresenter(GankDataRepository.getInstance(AndroidRemoteDataSource.getInstance()), androidDataFragment1);
                return androidDataFragment1;
            case 2:
                AndroidDataFragment androidDataFragment2 = (AndroidDataFragment) AndroidDataFragment.newInstance(0);
                new AndroidDataPresenter(GankDataRepository.getInstance(AndroidRemoteDataSource.getInstance()), androidDataFragment2);
            case 3:
                AndroidDataFragment androidDataFragment3 = (AndroidDataFragment) AndroidDataFragment.newInstance(0);
                new AndroidDataPresenter(GankDataRepository.getInstance(AndroidRemoteDataSource.getInstance()), androidDataFragment3);
            case 4:
                AndroidDataFragment androidDataFragment4 = (AndroidDataFragment) AndroidDataFragment.newInstance(0);
                new AndroidDataPresenter(GankDataRepository.getInstance(AndroidRemoteDataSource.getInstance()), androidDataFragment4);
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
