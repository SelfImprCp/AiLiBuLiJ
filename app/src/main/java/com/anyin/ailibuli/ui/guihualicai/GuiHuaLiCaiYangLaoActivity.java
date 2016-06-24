package com.anyin.ailibuli.ui.guihualicai;

import android.view.View;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/22.
 */
public class GuiHuaLiCaiYangLaoActivity  extends BaseActivity {



    @BindView(id = R.id.guihualicai_yanglao_title)
    private TitleBarView guihualicai_yanglao_title;

    @Override
    public void setRootView() {
        super.setRootView();

     setContentView(R.layout.activity_guihualicai_yanglao);
    }


    @Override
    protected void initView() {
        super.initView();

        guihualicai_yanglao_title.setTitleStr("养老计划");
        guihualicai_yanglao_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

