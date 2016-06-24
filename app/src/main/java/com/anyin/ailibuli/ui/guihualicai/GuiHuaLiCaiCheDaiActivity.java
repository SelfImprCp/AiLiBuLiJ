package com.anyin.ailibuli.ui.guihualicai;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/21.
 */
public class GuiHuaLiCaiCheDaiActivity extends BaseActivity {


    @BindView(id = R.id.guihualicai2_chedai_title)
    private TitleBarView guihualicai2_chedai_title;


    @BindView(id = R.id.guihualicai2_chedai_finish, click = true)
    private Button guihualicai2_chedai_finish;


    @BindView(id = R.id.guihualicai2_chedai_shifen, click = true)
    private LinearLayout guihualicai2_chedai_shifen;


    @BindView(id = R.id.guihualicai2_chedai_foufen, click = true)
    private LinearLayout guihualicai2_chedai_foufen;


    @BindView(id = R.id.guihualicai2_chedai_shifen_img)
    private ImageView guihualicai2_chedai_shifen_img;

    @BindView(id = R.id.guihualicai2_chedai_foufen_img)
    private ImageView guihualicai2_chedai_foufen_img;


    //  是否分期
    // 0：分，1：不分
    private int fenInt = 0;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_guihualicaichedai);
    }


    @Override
    protected void initView() {
        super.initView();
        guihualicai2_chedai_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guihualicai2_chedai_title.setTitleStr("车贷");


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.guihualicai2_chedai_shifen:


                guihualicai2_chedai_shifen_img.setImageResource(R.drawable.btn_radio_on);
                guihualicai2_chedai_foufen_img.setImageResource(R.drawable.btn_radio_off);
                fenInt = 0;
                break;
            case R.id.guihualicai2_chedai_foufen:
                guihualicai2_chedai_foufen_img.setImageResource(R.drawable.btn_radio_on);
                guihualicai2_chedai_shifen_img.setImageResource(R.drawable.btn_radio_off);
                fenInt = 1;
                break;

            case R.id.guihualicai2_chedai_finish:
                finish();
                break;

        }

    }


}
