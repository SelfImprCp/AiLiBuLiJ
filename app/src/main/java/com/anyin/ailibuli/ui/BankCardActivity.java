package com.anyin.ailibuli.ui;

import android.view.View;
import android.widget.Button;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/20.
 */
public class BankCardActivity extends BaseActivity {


     @BindView(id = R.id.bank_card_title)
     TitleBarView bank_card_title;

    @BindView(id = R.id.bank_card_add,click = true)
    Button bank_card_add;





    @Override
    public void setRootView() {
        super.setRootView();

     setContentView(R.layout.activity_bankcard);
    }


    @Override
    protected void initView() {
        super.initView();

        bank_card_title.setTitleBackClick(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bank_card_title.setTitleStr("银行卡管理");

    }

    @Override
    public void widgetClick(View v) {
         switch (v.getId())
         {
             case R.id.bank_card_add:

                 UIHelper.showToast("添加银行卡");
                 break;

         }

    }
}
