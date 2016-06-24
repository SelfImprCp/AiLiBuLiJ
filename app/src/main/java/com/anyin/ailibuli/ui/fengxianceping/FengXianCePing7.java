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
public class FengXianCePing7 extends BaseActivity {


    @BindView(id = R.id.fengxian7_title)
    private TitleBarView fengxian7_title;

    @BindView(id = R.id.fenxian7_01, click = true)
    private TextView fenxian7_01;





    @Override
    public void setRootView() {
        super.setRootView();
 setContentView(  R.layout.activity_fengxian7);

    }

    @Override
    protected void initView() {
        super.initView();

        fengxian7_title.setTitleStr("风险测评");
        fengxian7_title.setTitleBackClick(new View.OnClickListener() {

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

            case R.id.fenxian7_01:

                UIHelper.fenXianTest8(this);

                break;
        }
    }

}
