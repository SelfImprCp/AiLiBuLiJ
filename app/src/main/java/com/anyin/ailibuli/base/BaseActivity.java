package com.anyin.ailibuli.base;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.app.AppContext;
import com.anyin.ailibuli.app.AppManager;
import com.anyin.ailibuli.event.BaseEvent;
import com.anyin.ailibuli.event.IntentChangeEvent;
import com.anyin.ailibuli.event.LoginEvent;
import com.anyin.ailibuli.utils.InternetUtil;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.UIHelper;


import org.kymjs.kjframe.KJActivity;

import de.greenrobot.event.EventBus;


/**
 * 应用Activity基类
 *
 * @author Administrator
 */
public class BaseActivity extends KJActivity {


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AppManager.getAppManager().addActivity(this);
        ((AppContext) this.getApplication()).getActivityManager().pushActivty(
                this);

        mContext = AppContext.getInstance();

        super.onCreate(savedInstanceState);

        // 检查网络 联接
        if (!InternetUtil.hasInternetConnected()) {
            UIHelper.showToastShort(R.string.tip_network_error);

        }

        // 注册 事件
        EventBus.getDefault().register(this);

        // 发出检查网络 的事件
        IntentChangeEvent event = new IntentChangeEvent();
        EventBus.getDefault().post(event);


        initView();

        initData();
    }


    public void onEvent(LoginEvent event)
    {

    }

// public void onEvent(BaseEvent event)
//{
//
//}




    /**
     * 子类复写，初始化UI
     */
    protected void initView() {
    }


    @Override
    public void setRootView() {

    }

    /**
     * 子类复写，初始化数据
     */
    public void initData() {
    }




    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ((AppContext) this.getApplication()).getActivityManager().popActivity(
                this);

        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }





}
