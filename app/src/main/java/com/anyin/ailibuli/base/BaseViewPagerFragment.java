package com.anyin.ailibuli.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.adapter.ViewPageFragmentAdapter;
import com.anyin.ailibuli.custom.EmptyLayout;
import com.anyin.ailibuli.custom.PagerSlidingTabStrip;


/**
 * 带有导航条的基类
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年11月6日 下午4:59:50
 * 
 */
public abstract class BaseViewPagerFragment extends BaseFragment {

    protected PagerSlidingTabStrip mTabStrip;
    protected ViewPager mViewPager;
    protected ViewPageFragmentAdapter mTabsAdapter;
    protected EmptyLayout mErrorLayout;



    public ImageView mImgBack;
    public TextView mTvTitle;

    public ImageView mImgMenu;
    public TextView mTvMenu;
    public RelativeLayout mRlTitleBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpage_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTabStrip = (PagerSlidingTabStrip) view
                .findViewById(R.id.pager_tabstrip);

        mViewPager = (ViewPager) view.findViewById(R.id.pager);

        mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);


        mRlTitleBar = (RelativeLayout) view.findViewById(R.id.title);
        mImgBack = (ImageView) view.findViewById(R.id.titlebar_img_back);
        mTvTitle = (TextView) view.findViewById(R.id.titlebar_text_title);
        mImgMenu = (ImageView) view.findViewById(R.id.titlebar_img_menu);
        mTvMenu = (TextView) view.findViewById(R.id.titlebar_tv_menu);



        mImgBack.setOnClickListener(this);

        setInitTitleView();



        mTabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(),
                mTabStrip, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
        // if (savedInstanceState != null) {
        // int pos = savedInstanceState.getInt("position");
        // mViewPager.setCurrentItem(pos, true);
        // }
    }
    
    protected void setScreenPageLimit() {
    }

    // @Override
    // public void onSaveInstanceState(Bundle outState) {
    // //No call for super(). Bug on API Level > 11.
    // if (outState != null && mViewPager != null) {
    // outState.putInt("position", mViewPager.getCurrentItem());
    // }
    // //super.onSaveInstanceState(outState);
    // }



    @Override
    protected void widgetClick(View v) {
        // TODO Auto-generated method stub
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.titlebar_img_back:
                getActivity().finish();
                break;

            default:
                break;
        }
    }

    protected abstract void setInitTitleView()  ;
    protected abstract void onSetupTabAdapter(ViewPageFragmentAdapter adapter);
}