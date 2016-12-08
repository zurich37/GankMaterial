package com.zurich.gankmaterial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zurich.gankmaterial.R;
import com.zurich.gankmaterial.adapter.HomeTabFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 主页面Fragment
 * Created by weixinfei on 2016/11/25.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;

    public static SupportFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initToolbarNav(toolbar, true);

        /*tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_all));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_android));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_iOS));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_fuli));*/

        viewPager.setAdapter(new HomeTabFragmentAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

}
