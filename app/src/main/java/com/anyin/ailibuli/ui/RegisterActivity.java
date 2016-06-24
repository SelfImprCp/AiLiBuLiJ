package com.anyin.ailibuli.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.LoginUtil;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;
import org.w3c.dom.Text;

/**
 * Created by Jerry on 2016/6/21.
 * 用户注册
 */
public class RegisterActivity extends BaseActivity {


    @BindView(id = R.id.register_title)
    private TitleBarView register_title;


    @BindView(id = R.id.register_input_name)
    private EditText register_input_name;

    @BindView(id = R.id.register_input_password)
    private EditText register_input_password;


    @BindView(id = R.id.register_input_yanzhenma)
    private EditText register_input_yanzhenma;


    @BindView(id = R.id.reginster_get_yanzhengma, click = true)
    private TextView reginster_get_yanzhengma;


    @BindView(id = R.id.register_btn, click = true)
    private Button register_btn;

    @BindView(id = R.id.register_login, click = true)
    private TextView register_login;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        super.initView();
        register_title.setTitleStr("注册");
        register_title.setTitleBackClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            // 拿验证码
            case R.id.reginster_get_yanzhengma:


                break;
            // 注册
            case R.id.register_btn:

                //先检查手机号和密码
                boolean isLogin = LoginUtil.checkLogin(register_input_name, register_input_password);
                UIHelper.showToast(" yes login " + isLogin);


                break;


            case R.id.register_login:

                UIHelper.showLogin(this);
                break;


        }
    }
}
