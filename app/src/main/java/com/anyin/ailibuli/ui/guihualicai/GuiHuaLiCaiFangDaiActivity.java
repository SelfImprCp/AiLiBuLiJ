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
public class GuiHuaLiCaiFangDaiActivity extends BaseActivity {



    @BindView( id = R.id.guihualicai2_fangdai_title)
    private TitleBarView guihualicai2_fangdai_title;


    @BindView( id = R.id.guihualicai2_fangdai_finish,click = true)
    private Button guihualicai2_fangdai_finish;

    @BindView( id = R.id.guihualicai2_fangdai_shifen,click = true)
    private LinearLayout guihualicai2_fangdai_shifen;


    @BindView( id = R.id.guihualicai2_fangdai_foufen,click = true)
    private LinearLayout guihualicai2_fangdai_foufen;


    @BindView( id = R.id.guihualicai2_fangdai_shifen_img )
    private ImageView guihualicai2_fangdai_shifen_img;

    @BindView( id = R.id.guihualicai2_fangdai_foufen_img )
    private ImageView guihualicai2_fangdai_foufen_img;



    //  是否分期
    // 0：分，1：不分
    private int fenInt = 0;



    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_guihualicaifangdai);
    }

    @Override
    protected void initView() {
        super.initView();
        guihualicai2_fangdai_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guihualicai2_fangdai_title.setTitleStr("房贷");


    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId())
        {
            case R.id.guihualicai2_fangdai_shifen:


                guihualicai2_fangdai_shifen_img.setImageResource(R.drawable.btn_radio_on);
                guihualicai2_fangdai_foufen_img.setImageResource(R.drawable.btn_radio_off);
                fenInt = 0;
                break;
            case R.id.guihualicai2_fangdai_foufen:
                guihualicai2_fangdai_foufen_img.setImageResource(R.drawable.btn_radio_on);
                guihualicai2_fangdai_shifen_img.setImageResource(R.drawable.btn_radio_off);
                fenInt = 1;
                break;


             // 完成
            case R.id.guihualicai2_fangdai_finish:

   finish();

                break;




        }
    }
}

