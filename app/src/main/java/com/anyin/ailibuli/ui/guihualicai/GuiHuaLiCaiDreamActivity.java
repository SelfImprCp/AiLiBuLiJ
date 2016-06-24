package com.anyin.ailibuli.ui.guihualicai;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.zhy.autolayout.AutoLinearLayout;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/22.
 */
public class GuiHuaLiCaiDreamActivity extends BaseActivity {


    @BindView(id = R.id.guihualicai_dream_title)
    private TitleBarView guihualicai_dream_title;


    @BindView(id = R.id.guihualicai_dream_add, click = true)
    private Button guihualicai_dream_add;

    @BindView(id = R.id.guihualicai_dream_save, click = true)
    private Button guihualicai_dream_save;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_guihualicai_dream);

    }

    @Override
    protected void initView() {
        super.initView();

        guihualicai_dream_title.setTitleStr("梦想计划");
        guihualicai_dream_title.setTitleBackClick(new View.OnClickListener() {
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


            case R.id.guihualicai_dream_save:


                break;


            case R.id.guihualicai_dream_add:

                break;


        }


    }
}
