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
public class FengXianCePing9 extends BaseActivity {




    @BindView(id = R.id.fengxian9_title)
    private TitleBarView fengxian9_title;



    @BindView(id = R.id.fenxian9_01, click = true)
    private TextView fenxian9_01;






    @Override
    public void setRootView() {
        super.setRootView();
 setContentView(  R.layout.activity_fengxian9);

    }


    @Override
    protected void initView() {
        super.initView();

        fengxian9_title.setTitleStr("风险测评");
        fengxian9_title.setTitleBackClick(new View.OnClickListener() {

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

            case R.id.fenxian9_01:

                UIHelper.fenXianTest10(this);

                break;
        }
    }


}
