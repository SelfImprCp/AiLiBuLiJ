package com.anyin.ailibuli.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.base.BaseFragment;
import com.anyin.ailibuli.domian.SimpleBackPage;
import com.anyin.ailibuli.utils.LogCp;

import org.kymjs.kjframe.ui.SupportFragment;

import java.lang.ref.WeakReference;


/**
 * 应用二级界面
 * 
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 * 
 */
public class SimpleBackActivity extends BaseActivity {
	public static String TAG = SimpleBackActivity.class.getSimpleName();
	public static String CONTENT_KEY = "sba_content_key";
	public static String DATA_KEY = "sba_datat_key";
	public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
	public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";
	private BaseFragment currentFragment;
	public static WeakReference<Fragment> mFragment;
	protected int mPageValue = -1;

	public static SimpleBackActivity activity = new SimpleBackActivity();

	@Override
	public void setRootView() {
		setContentView(R.layout.aty_simple_back);
		initView();
		int value = getIntent().getIntExtra(CONTENT_KEY, -1);
		if (value != -1) {
			try {
				currentFragment = (BaseFragment) SimpleBackPage
						.getPageByValueClass(value).newInstance();
				changeFragment(currentFragment);
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			}
		}

		if (mPageValue == -1) {
			mPageValue = getIntent().getIntExtra(BUNDLE_KEY_PAGE, 0);
		}
		initFromIntent(mPageValue, getIntent());

	}

	/**
	 * 初始化控件
	 */
	public void initView() {

		/*
		 * mRlTitleBar = (RelativeLayout) findViewById(R.id.titlebar); mImgBack
		 * = (ImageView) findViewById(R.id.titlebar_img_back); mTvTitle =
		 * (TextView) findViewById(R.id.titlebar_text_title); mImgMenu =
		 * (ImageView) findViewById(R.id.titlebar_img_menu);
		 * mImgBack.setOnClickListener(this); mImgMenu.setOnClickListener(this);
		 */
		LogCp.i(LogCp.CP, SimpleBackActivity.class + "调用 initView ");

	}

	@Override
	public void onBackPressed() {
		if (mFragment != null && mFragment.get() != null
				&& mFragment.get() instanceof BaseFragment) {
			BaseFragment bf = (BaseFragment) mFragment.get();
			if (!bf.onBackPressed()) {
				super.onBackPressed();
			}
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 设置标题
	 */
	/*
	 * public void setTitle(String title) { mTvTitle.setText(title); }
	 *//**
	 * 设置右边控件的icon
	 */
	/*
	 * public void setMImageMenuIcon(int resId) {
	 * mImgMenu.setImageResource(resId); }
	 */

	/*	*//**
	 * 设置右边控件是否显示
	 */
	/*
	 * public void setMenuIsShow(boolean isShow) { if (isShow) {
	 * mImgMenu.setVisibility(View.VISIBLE); } else {
	 * mImgMenu.setVisibility(View.GONE); } }
	 */

	protected void initFromIntent(int pageValue, Intent data) {
		if (data == null) {
			throw new RuntimeException(
					"you must provide a page info to display");
		}
		SimpleBackPage page = SimpleBackPage.getPageByValue(pageValue);
		if (page == null) {
			throw new IllegalArgumentException("can not find page by value:"
					+ pageValue);
		}

		// setActionBarTitle(page.getTitle());
		/*
		 * if (page.getBackShow()) { mImgBack.setOnClickListener(this);
		 * 
		 * } else { mImgBack.setVisibility(View.GONE); } if
		 * (page.getRightShow()) {
		 * mImgMenu.setOnClickListener(page.getRightClick()); } else {
		 * mImgMenu.setVisibility(View.GONE); }
		 */
		try {
			Fragment fragment = (Fragment) page.getClazz().newInstance();

			Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);
			if (args != null) {
				fragment.setArguments(args);
			}

			FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
			trans.replace(R.id.main_content, fragment, TAG);
			trans.addToBackStack(null);
			trans.commitAllowingStateLoss();

			mFragment = new WeakReference<Fragment>(fragment);

		} catch (Exception e) {
			e.printStackTrace();
			 throw new IllegalArgumentException(
			 		"generate fragment error. by value:" + pageValue);
		}
	}

	public void detach() {

	}

	/*
	 * public void setActionBarTitle(int resId) { if (resId != 0) {
	 * setActionBarTitle(getString(resId)); } }
	 */

	/*
	 * public void setActionBarTitle(String title) { if
	 * (!StringUtils.isEmpty(title)) { // title = getString(R.string.app_name);
	 * // LogCp.i(LogCp.CP, SimpleBackActivity.class + "设置的标题：" + title);
	 * setTitle(title); }
	 * 
	 * }
	 */

	public Bundle getBundleData() {
		return getIntent().getBundleExtra(DATA_KEY);
	}

	public void changeFragment(SupportFragment targetFragment) {
		// super.changeFragment(R.id.main_content, targetFragment);
		// changeFragment(R.id.main_content,currentFragment);
		super.changeFragment(R.id.main_content, targetFragment);
	}

}
