package com.anyin.ailibuli.main;



import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseFragment;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.UIHelper;
import com.zhy.autolayout.AutoRelativeLayout;

import org.kymjs.kjframe.ui.BindView;

/**
 * 我的界面
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    // 进入 个人中心
    private AutoRelativeLayout my_enter_info;
    // 修改密码
    private AutoRelativeLayout my_change_password;
    // 修改交易 密码
    private AutoRelativeLayout my_change_order_password;
    //银行卡管理
    private AutoRelativeLayout my_bank_card;
    // 意见反馈
    private AutoRelativeLayout my_feedback;

    private AutoRelativeLayout my_about;

    private Button my_exit;

    // userName
     private TextView my_text_name;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {

        my_enter_info = (AutoRelativeLayout) view.findViewById(R.id.my_enter_info);
        my_enter_info.setOnClickListener(this);

        my_change_password = (AutoRelativeLayout) view.findViewById(R.id.my_change_password);
        my_change_password.setOnClickListener(this);

        my_change_order_password = (AutoRelativeLayout) view.findViewById(R.id.my_change_order_password);
        my_change_order_password.setOnClickListener(this);

        my_bank_card = (AutoRelativeLayout) view.findViewById(R.id.my_bank_card);
        my_bank_card.setOnClickListener(this);

        my_feedback = (AutoRelativeLayout) view.findViewById(R.id.my_feedback);
        my_feedback.setOnClickListener(this);

        my_about = (AutoRelativeLayout) view.findViewById(R.id.my_about);
        my_about.setOnClickListener(this);

        my_exit = (Button) view.findViewById(R.id.my_exit);
        my_exit.setOnClickListener(this);


        my_text_name =  (TextView)view.findViewById(R.id.my_text_name);
        my_text_name.setOnClickListener(this);


    }

    @Override
    public void initData() {

        LogCp.i(LogCp.CP, MyFragment.class + "处理数据");


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_enter_info:
                UIHelper.showUserInfo(getActivity());

                break;

            case R.id.my_change_password:
               UIHelper.showChangePass(getActivity());
                break;


            case R.id.my_change_order_password:
                UIHelper.showToast("修改交易 密码");
                break;


            case R.id.my_bank_card:
                 UIHelper.showBankCard(getActivity());

                break;


            case R.id.my_feedback:
                UIHelper.showFeedback(getActivity());
                break;


            case R.id.my_about:
                UIHelper.showToast("关于我们");

                 UIHelper.showKaiHu(getActivity());

                break;

            case R.id.my_exit:

                 String strLogin = my_exit.getText().toString();
                if(strLogin.equals("登录"))
                {
                    my_exit.setText("退出登录");
                    UIHelper.showLogin(getActivity());
                }else
                {
                    my_exit.setText("登录");

                }


                break;

            //点击用户名,暂时先用来测试登录
            case R.id.my_text_name:

                break;


        }

    }
}