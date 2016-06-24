package com.anyin.ailibuli.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/20.
 * <p>
 * 修改密码
 */
public class ChangePassActivity extends BaseActivity {


    @BindView(id = R.id.chgpass_title)
    private TitleBarView chgpass_title;


     @BindView(id = R.id.my_change_password_save,click =  true)
     private Button my_change_password_save;

    @BindView(id = R.id.chgpass_get_yanzhengma,click =  true)
    private TextView chgpass_get_yanzhengma;






    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_change_pass);
    }

    @Override
    protected void initView() {

        chgpass_title.setTitleStr("修改密码");
        chgpass_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void widgetClick(View v) {

     switch (v.getId())
     {



          // 完成
         case R.id.my_change_password_save:


             break;




         //  猎取验证码
         case R.id.chgpass_get_yanzhengma:

             UIHelper.showToast("拿验证码 ");
             break;



     }

    }
}
