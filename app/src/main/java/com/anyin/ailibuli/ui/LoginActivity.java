package com.anyin.ailibuli.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.event.LoginEvent;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.LoginUtil;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import de.greenrobot.event.EventBus;

/**
 * Created by Jerry on 2016/6/21.
 * <p/>
 * 登录界面
 */
public class LoginActivity extends BaseActivity {


    @BindView(id = R.id.login_title)
    private TitleBarView login_title;

    @BindView(id = R.id.login_input_name)
    private EditText login_input_name;

    @BindView(id = R.id.login_input_password)
    private EditText login_input_password;

    @BindView(id = R.id.login_btn, click = true)
    private Button login_btn;

    @BindView(id = R.id.find_password, click = true)
    private TextView find_password;


    @BindView(id = R.id.registered_user, click = true)
    private TextView registered_user;


    @Override
    public void setRootView() {

        setContentView(R.layout.activity_login);

    }

    @Override
    protected void initView() {
        super.initView();

        login_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_title.setTitleStr("登录");


    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            //登录
            case R.id.login_btn:
                commitLogin();
                break;
            //找回密码
            case R.id.find_password:
                findPassWord();
                break;
            //注册
            case R.id.registered_user:
                userRegister();
                break;


        }
    }

    /**
     *
     */

    private void userRegister() {

         UIHelper.showRegister(this);

    }

    /**
     *
     */
    private void findPassWord() {
         UIHelper.showChangePass(this);

    }

    /**
     *
     */
    private void commitLogin() {


        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setLoginUserNmae(" 小李");
        EventBus.getDefault().post(loginEvent);

        LogCp.i(LogCp.CP,LoginActivity.class + "发出事件 "  );






         if (LoginUtil.checkLogin(login_input_name, login_input_password)) {

            String strUserName = login_input_name.getText().toString();
            String strUserPwd = login_input_password.getText().toString();
            UIHelper.showToast("可以去登录了 " +strUserName + strUserPwd );

        }


    }



}
