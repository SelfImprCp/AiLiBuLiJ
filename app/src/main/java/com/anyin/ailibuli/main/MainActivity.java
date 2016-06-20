package com.anyin.ailibuli.main;

 import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


 import android.support.v4.app.Fragment;
 import android.support.v4.app.FragmentActivity;
 import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;


import com.anyin.ailibuli.R;
import com.anyin.ailibuli.app.AppContext;
import com.anyin.ailibuli.custom.MyFragmentTabHost;
import com.anyin.ailibuli.interf.OnTabReselectListener;
import com.anyin.ailibuli.utils.DoubleClickExitHelper;

 import com.anyin.ailibuli.utils.LogCp;
 import com.anyin.ailibuli.utils.SharePreferencesUitl;
 import com.anyin.ailibuli.utils.StringUtils;
 import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.view.LayoutInflater.from;

/**
 * 应用主界面
 *
 * @author Administrator
 */
public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener,
        View.OnTouchListener {

    public static MyFragmentTabHost mTabHost;
    // 双击退出
    private DoubleClickExitHelper mDoubleClickExit;
    public static String TCurrent = "value";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
 //   private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      //  client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void initView() {
        mDoubleClickExit = new DoubleClickExitHelper(this);

        mTabHost = (MyFragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        if (Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        initTabs();

        int current = getIntent().getIntExtra(TCurrent, 0);

        mTabHost.setCurrentTab(current);


        // 保存用户
//        User user = new User();
//        user.setNickname("小林");
//        user.setMobile("13560255894");
//
//        UserManaage.getUserManaage().saveUserInfo(MainActivity.this,
//                user);
//
//
//        User dbUser = UserManaage.getUserManaage().getLoginUser(MainActivity.this);
//
//        LogCp.i(LogCp.CP, MainActivity.class + "从数据库中查出来的登录用户" + dbUser );

    }


    /**
     * 初始化tab
     */
    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));

            View indicator = from(getApplicationContext())
                    .inflate(R.layout.tab_indicator, null);

            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(
                    mainTab.getResIcon());

            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
                    null);

            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

            @Override
            public View createTabContent(String tag) {
                return new View(MainActivity.this);
            }
        });
        mTabHost.addTab(tab, mainTab.getClz(), null);

        mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
    }

    /**
     * 检查 版本更新
     */
    //checkUpdate();

}


    /**
     * 监听返回--是否退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogCp.i(LogCp.CP,MainActivity.class + " 单击的值  dd "
                //+ StringUtils.toBool(SharePreferencesUitl.getSharePreferencesUitl(MainActivity.this).loadData(DoubleClickExitHelper.KEY_DOUBLE_CLICK_EXIT
                //    ))
        );
        if (keyCode == KeyEvent.KEYCODE_BACK) {



            // 是否退出应用
            // if (StringUtils.toBool(SharePreferencesUitl.getSharePreferencesUitl(MainActivity.this).loadData(DoubleClickExitHelper.KEY_DOUBLE_CLICK_EXIT
            //      ))) {

            // mPushAgent.disable(mUnregisterCallback);

                return mDoubleClickExit.onKeyDown(keyCode, event);

            //}
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                // if(i==3&&!AppContext.getInstance().isLogin())
                // {
                // noLogin();
                //
                // }

                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }

        supportInvalidateOptionsMenu();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouchEvent(event);
        boolean consumed = false;
        // use getTabHost().getCurrentTabView to decide if the current tab is
        // touched again
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && v.equals(mTabHost.getCurrentTabView())) {
            // use getTabHost().getCurrentView() to get a handle to the view
            // which is displayed in the tab - and to get this views context
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.onTabReselect();
                consumed = true;
            }
        }
        return consumed;
    }

    private android.support.v4.app.Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(
                mTabHost.getCurrentTabTag());
    }




}
