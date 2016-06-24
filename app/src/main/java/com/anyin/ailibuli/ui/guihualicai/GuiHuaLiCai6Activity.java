package com.anyin.ailibuli.ui.guihualicai;

import android.view.View;
import android.widget.Button;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/22.
 */
public class GuiHuaLiCai6Activity extends BaseActivity {


    @BindView(id = R.id.guihualicai6_title)
    private TitleBarView guihualicai6_title;


    @BindView(id = R.id.guihualicai6_next, click = true)
    private Button guihualicai6_next;


    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_guihualicai6);

    }


    @Override
    protected void initView() {
        super.initView();
        guihualicai6_title.setTitleStr("规划理财方案");
        guihualicai6_title.setTitleBackClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            // 下一步
            case R.id.guihualicai6_next:




                break;


        }
    }


}
