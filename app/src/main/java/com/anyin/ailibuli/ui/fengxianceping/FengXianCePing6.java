package com.anyin.ailibuli.ui.fengxianceping;

import android.view.View;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/22.
 */
public class FengXianCePing6 extends BaseActivity {

    @BindView(id = R.id.fengxian6_title)
    private TitleBarView fengxian6_title;


    @BindView(id = R.id.fenxian6_01, click = true)
    private TextView fenxian6_01;



    @Override
    public void setRootView() {
        super.setRootView();
 setContentView(  R.layout.activity_fengxian6);




    }


    @Override
    protected void initView() {
        super.initView();

        fengxian6_title.setTitleStr("风险测评");
        fengxian6_title.setTitleBackClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {

            case R.id.fenxian6_01:

                UIHelper.fenXianTest7(this);

                break;
        }
    }





}
