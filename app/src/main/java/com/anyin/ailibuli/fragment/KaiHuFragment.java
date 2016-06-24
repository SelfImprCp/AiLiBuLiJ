package com.anyin.ailibuli.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;


import com.anyin.ailibuli.R;
import com.anyin.ailibuli.adapter.ViewPageFragmentAdapter;

import com.anyin.ailibuli.base.BaseListFragment;
import com.anyin.ailibuli.base.BaseViewPagerFragment;

import com.anyin.ailibuli.interf.OnTabReselectListener;


/**
 * Created by Jerry on 2016/6/21.
 * <p>
 * 开户
 */
public class KaiHuFragment extends BaseViewPagerFragment implements
        OnTabReselectListener {




   /*
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_kaihu);
    }
  */
 /*
    @Override
    protected void initView() {
        super.initView();

        kaihu_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        kaihu_title.setTitleStr("开户");

        kaihu_title.setMenuTextStr("下一步");
        kaihu_title.setMenuTextOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
*/


    // }


    @Override
    public void onTabReselect() {
        try {
            int currentIndex = mViewPager.getCurrentItem();
            Fragment currentFragment = getChildFragmentManager().getFragments()
                    .get(currentIndex);
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.onTabReselect();
            }
        } catch (NullPointerException e) {
        }
    }

    public void setInitTitleView() {
        mTvTitle.setText("开户");
        mImgBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        mRlTitleBar.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {

        String[] title = getResources().getStringArray(R.array.kaihu_array);


        adapter.addTab(title[0], "info", KaiHuInfoFragment.class,
               null);

        adapter.addTab(title[1], "bank", KaiHuBankFragment.class,
                null);

        adapter.addTab(title[2], "finish", KaiHuFinishFragment.class,
                null);


    }


    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, newType);
        return bundle;
    }




}
