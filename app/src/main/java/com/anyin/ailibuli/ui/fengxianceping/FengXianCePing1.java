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
public class FengXianCePing1 extends BaseActivity {

    @BindView(id = R.id.fenxian1_jijing, click = true)
    private TextView fenxian1_jijing;


    @BindView(id = R.id.fengxian1_title)
    private TitleBarView fengxian1_title;



    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_fengxian1);

    }


    @Override
    protected void initView() {
        super.initView();

        fengxian1_title.setTitleStr("风险测评");
        fengxian1_title.setTitleBackClick(new View.OnClickListener() {

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

            case R.id.fenxian1_jijing:

                UIHelper.fenXianTest2(this);

                break;
        }
    }
}