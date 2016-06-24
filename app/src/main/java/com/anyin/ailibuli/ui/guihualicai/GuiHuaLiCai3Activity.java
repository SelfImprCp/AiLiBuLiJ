package com.anyin.ailibuli.ui.guihualicai;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/21.
 */
public class GuiHuaLiCai3Activity extends BaseActivity {


    @BindView(id = R.id.guihualicai3_title)
    private TitleBarView guihualicai3_title;


    @BindView(id = R.id.guihualicai3_next, click = true)
    private Button guihualicai3_next;


    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_guihualicai3);

    }


    @Override
    protected void initView() {
        super.initView();
        guihualicai3_title.setTitleStr("规划理财方案");
        guihualicai3_title.setTitleBackClick(new View.OnClickListener() {

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
            case R.id.guihualicai3_next:


                UIHelper.showGuiHuaLiCai4s(this);

                break;


        }
    }


}
