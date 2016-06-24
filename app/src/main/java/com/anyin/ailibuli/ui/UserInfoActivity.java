package com.anyin.ailibuli.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.bean.User;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.event.BaseEvent;
import com.anyin.ailibuli.event.LoginEvent;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.LoginUtil;
import com.anyin.ailibuli.utils.StringUtils;
import com.anyin.ailibuli.utils.UIHelper;


import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/20.
 * 用户信息
 */
public class UserInfoActivity extends BaseActivity {
    @BindView(id = R.id.user_info_titlebar)
    private TitleBarView user_info_titlebar;


    @BindView(id = R.id.user_info_username)
    private EditText user_info_username;


    @BindView(id = R.id.user_info_age)
    private EditText user_info_age;

    // 男性的单击事件
    @BindView(id = R.id.user_info_sex_boy, click = true)
    private LinearLayout user_info_sex_boy;

    @BindView(id = R.id.user_info_sex_girl, click = true)
    private LinearLayout user_info_sex_girl;

    // 男性的图片显示要
    @BindView(id = R.id.user_info_seximg_boy)
    private ImageView user_info_seximg_boy;

    @BindView(id = R.id.user_info_seximg_girl)
    private ImageView user_info_seximg_girl;

    // 未婚的单击事件
    @BindView(id = R.id.user_info_huny_weihun, click = true)
    private LinearLayout user_info_huny_weihun;
    @BindView(id = R.id.user_info_huny_yihun, click = true)
    private LinearLayout user_info_huny_yihun;


    // 未婚的图片显示要
    @BindView(id = R.id.user_info_hunyimg_weihun)
    private ImageView user_info_hunyimg_weihun;
    @BindView(id = R.id.user_info_hunyimg_yihun)
    private ImageView user_info_hunyimg_yihun;


    @BindView(id = R.id.user_info_phone)
    private EditText user_info_phone;


    @BindView(id = R.id.user_info_qq)
    private EditText user_info_qq;


    @BindView(id = R.id.user_info_save, click = true)
    private Button user_info_save;


    // 婚姻状态:1已婚,2:未婚
    private int hunYinInt = 1;


    // 性别状态:1男,2:女
    private int sexInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_user_info);
    }

    @Override
    protected void initView() {
        super.initView();

        user_info_titlebar.setTitleStr("个人信息");
        user_info_titlebar.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @Override
    public void widgetClick(View v) {

        switch (v.getId()) {
            // 保存
            case R.id.user_info_save:
                saveUserInfo();
                break;


            // 未婚
            case R.id.user_info_huny_weihun:

                hunYinInt = 2;
                user_info_hunyimg_yihun.setImageResource(R.drawable.btn_radio_off);
                user_info_hunyimg_weihun.setImageResource(R.drawable.btn_radio_on);

                break;


            // 已婚
            case R.id.user_info_huny_yihun:
                hunYinInt = 1;
                user_info_hunyimg_yihun.setImageResource(R.drawable.btn_radio_on);
                user_info_hunyimg_weihun.setImageResource(R.drawable.btn_radio_off);
                break;


            // 性别女
            case R.id.user_info_sex_girl:
                user_info_seximg_girl.setImageResource(R.drawable.btn_radio_on);
                user_info_seximg_boy.setImageResource(R.drawable.btn_radio_off);
                sexInt = 2;
                break;


            // 性别男
            case R.id.user_info_sex_boy:
                user_info_seximg_girl.setImageResource(R.drawable.btn_radio_off);
                user_info_seximg_boy.setImageResource(R.drawable.btn_radio_on);
                sexInt = 1;
                break;
        }

    }

    /**
     * 保存住处
     */
    private void saveUserInfo() {

        checkInFo();
    }

    /**
     * 检查信息
     */
    private void checkInFo() {


        // 用户名
        String userName = user_info_username.getText().toString();
        if (StringUtils.isEmpty(userName)) {
            UIHelper.showToast("请输入用户名");
            user_info_username.requestFocus();
            return;
        }
        if (userName.length() > 11) {
            UIHelper.showToast("用户名不能超过10位");
            user_info_username.requestFocus();
            return;
        }


        if (userName.length() < 6) {
            UIHelper.showToast("用户名不能少6位");  user_info_username.requestFocus();
            return;
        }
        //
        int age = StringUtils.toInt(user_info_age.getText().toString());
        if (age > 120 || age == 0) {
            user_info_age.requestFocus();
            UIHelper.showToast("请输入正确的年龄");
            return;
        }


        //电话 号码

        if (!LoginUtil.checkLoginName(user_info_phone)) {
            return;
        }

        String strQQ = user_info_qq.getText().toString();


        // 提交 信息

        commitServerInfo()
        ;

    }

    /**
     *
     */
    private void commitServerInfo() {

        String userName = user_info_username.getText().toString();
        int age = StringUtils.toInt(user_info_age.getText().toString());
        String userPhone = user_info_phone.getText().toString();
        String strQQ = user_info_qq.getText().toString();

        UIHelper.showToast("要提交 的信息 "
                + userName + age + userPhone + strQQ + hunYinInt + sexInt);



    }



    public void onEvent(LoginEvent event) {
        LogCp.i(LogCp.CP , UserInfoActivity .class + " 收到事件 了 LoginEvent, cc , " + event.getLoginUserNmae()  );

    }






}
