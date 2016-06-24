package com.anyin.ailibuli.main;


import android.app.Dialog;
import android.content.Context;
import android.view.View;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.api.MyAPI;
import com.anyin.ailibuli.api.MyResponseHandler;
import com.anyin.ailibuli.base.BaseFragment;
import com.anyin.ailibuli.bean.User;
import com.anyin.ailibuli.cache.CacheManager;
import com.anyin.ailibuli.db.MyDbUtil;
import com.anyin.ailibuli.event.LoginEvent;
import com.anyin.ailibuli.ui.dialog.BasicDialog;
import com.anyin.ailibuli.utils.FileUtil;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 规划界面
 *
 * @author Administrator
 */
public class PlanFragment extends BaseFragment implements View.OnClickListener {




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void initView(View view) {
        view.findViewById(R.id.home_new_gide).setOnClickListener(this);
        view.findViewById(R.id.fengxian_test).setOnClickListener(this);

        view.findViewById(R.id.my_test).setOnClickListener(this);



    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            // 测试新手引导界面
            case R.id.home_new_gide:

                UIHelper.showGuiHuaLiCai1s(getActivity());


                break;


            case R.id.fengxian_test:

                UIHelper.fenXianTest1(getActivity());

                break;


            case R.id.my_test:

   UIHelper.showTest(getActivity());
                break;





        }
    }


    public void onEvent(LoginEvent event) {

        LogCp.i(LogCp.CP, PlanFragment.class + "收到用户登录 的事件 " + event.getLoginUserNmae());

    }

}

