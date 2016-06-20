
package com.anyin.ailibuli.base;



import android.app.ProgressDialog;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.app.AppContext;
import com.anyin.ailibuli.event.BaseEvent;
import com.anyin.ailibuli.interf.BaseFragmentInterface;
import com.anyin.ailibuli.ui.dialog.DialogControl;
import com.anyin.ailibuli.utils.InternetUtil;
import com.anyin.ailibuli.utils.UIHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.kymjs.kjframe.ui.SupportFragment;


/**
 * fragment 基类
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public class BaseFragment extends Fragment implements
        BaseFragmentInterface {

    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;

    protected LayoutInflater mInflater;

    public AppContext getApplication() {
        return (AppContext) getActivity().getApplication();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        // 注册 事件
        EventBus.getDefault().register(this);

        View view = inflateView(getLayoutId());
         //加载界面
        initView(view);
         // 处理数据
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }



    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }


    /**
     * @param event
     */
    @Subscribe
    public void onEvent(BaseEvent event) {
    }



    /**
     *  子类复写这个方法,设置当前界面的布局
     * @return
     */
    protected int getLayoutId() {
        return 0;
    }

     /**
       子类直接复写这个方法,view,就是当前界面的view
      */
    @Override
    public void initView(View view) {

    }

    /**
     *  子类直接复写这个方法,view, 对当前界面的数据进行处理
     */
    @Override
    public void initData() {

    }

}
