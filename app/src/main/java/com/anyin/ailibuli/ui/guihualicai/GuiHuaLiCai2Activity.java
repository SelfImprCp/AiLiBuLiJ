package com.anyin.ailibuli.ui.guihualicai;

import android.view.View;
import android.widget.Button;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;
import com.zhy.autolayout.AutoLinearLayout;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/21.
 */
public class GuiHuaLiCai2Activity extends BaseActivity {


    @BindView(id = R.id.guihualicai2_next, click = true)
    private Button guihualicai2_next;


    @BindView(id = R.id.guihualicai2_title)
    private TitleBarView guihualicai2_title;

    @BindView(id = R.id.guihualicai2_fangdai, click = true)
    private AutoLinearLayout guihualicai2_fangdai;


    @BindView(id = R.id.guihualicai2_chedais, click = true)
    private AutoLinearLayout guihualicai2_chedais;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_guihualicai2);
    }


    @Override
    protected void initView() {
        super.initView();

        guihualicai2_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guihualicai2_title.setTitleStr("规划理财方案");
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            // 下一步
            case R.id.guihualicai2_next:

                UIHelper.showGuiHuaLiCai3s(this);
                break;

            case R.id.guihualicai2_fangdai:


                UIHelper.showGuiHuaLiCaiFangDai(this);

                break;


            case R.id.guihualicai2_chedais:

                UIHelper.showGuiHuaLiCaiCheDai(this);


                break;


        }
    }
}
