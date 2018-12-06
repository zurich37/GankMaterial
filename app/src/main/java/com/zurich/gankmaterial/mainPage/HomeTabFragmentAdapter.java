package com.zurich.gankmaterial.mainPage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zurich.gankmaterial.data.source.GankAndroidDataRepository;
import com.zurich.gankmaterial.data.source.GankIOSDataRepository;
import com.zurich.gankmaterial.data.source.GankWebDataRepository;
import com.zurich.gankmaterial.data.source.GankWelfareDataRepository;
import com.zurich.gankmaterial.data.source.remote.GankRemoteDataSource;
import com.zurich.gankmaterial.gankDatas.GankDataFragment;
import com.zurich.gankmaterial.gankDatas.GankDatasPresenter;
import com.zurich.gankmaterial.gankDetail.WelfareDataFragment;

/**
 * tab adapter
 */
public class HomeTabFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"Android", "iOS", "前端", "福利"/*"休息视频", "拓展资源", "福利"*/};

    public HomeTabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GankDataFragment gankDataFragment = (GankDataFragment) GankDataFragment.newInstance();
                new GankDatasPresenter(GankAndroidDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankDataFragment);
                return gankDataFragment;
            case 1:
                GankDataFragment gankIOSDataFragment = (GankDataFragment) GankDataFragment.newInstance();
                new GankDatasPresenter(GankIOSDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankIOSDataFragment);
                return gankIOSDataFragment;
            case 2:
                GankDataFragment gankWebDataFragment = (GankDataFragment) GankDataFragment.newInstance();
                new GankDatasPresenter(GankWebDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankWebDataFragment);
                return gankWebDataFragment;
            case 3:
                WelfareDataFragment gankWelfareDataFragment = (WelfareDataFragment) WelfareDataFragment.newInstance(0);
                new GankDatasPresenter(GankWelfareDataRepository.getInstance(GankRemoteDataSource.getInstance()), gankWelfareDataFragment);
                return gankWelfareDataFragment;
            default:
                break;
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
