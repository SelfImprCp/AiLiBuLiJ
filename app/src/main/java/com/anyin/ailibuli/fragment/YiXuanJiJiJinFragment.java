package com.anyin.ailibuli.fragment;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseFragment;
import com.anyin.ailibuli.event.BaseEvent;
import com.anyin.ailibuli.event.LoginEvent;
import com.anyin.ailibuli.utils.LogCp;

/**
 * Created by Jerry on 2016/6/21.
 *  基金挑选
 *
 */
public class YiXuanJiJiJinFragment  extends BaseFragment{

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yixuanji_jijin;
    }


    public void onEvent(LoginEvent event) {
        super.onEvent(event);

        LogCp.i(LogCp.CP , YiXuanJiJiJinFragment .class + "  cc 收到事件 了");

    }
}
