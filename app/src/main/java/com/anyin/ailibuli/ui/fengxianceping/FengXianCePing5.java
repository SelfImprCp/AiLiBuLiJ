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
public class FengXianCePing5 extends BaseActivity {




    @BindView(id = R.id.fengxian5_title)
    private TitleBarView fengxian5_title;


    @BindView(id = R.id.fenxian5_01, click = true)
    private TextView fenxian5_01;







    @Override
    public void setRootView() {
        super.setRootView();
 setContentView(  R.layout.activity_fengxian5);

    }

    @Override
    protected void initView() {
        super.initView();

        fengxian5_title.setTitleStr("风险测评");
        fengxian5_title.setTitleBackClick(new View.OnClickListener() {

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

            case R.id.fenxian5_01:

                UIHelper.fenXianTest6(this);

                break;
        }
    }

}
