package com.anyin.ailibuli.ui.guihualicai;

import android.media.Image;
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
public class GuiHuaLiCai1Activity extends BaseActivity {



    @BindView( id  = R.id.guihualicai1_title)
    private TitleBarView guihualicai1_title;



    @BindView( id  = R.id.guihualicai1_username)
    private EditText guihualicai1_username;

    @BindView( id  = R.id.guihualicai1_age)
    private EditText guihualicai1_age;


    @BindView( id  = R.id.guihualicai1_next,click =  true)
    private Button guihualicai1_next;


    @BindView( id  = R.id.guihualicai1_sex_boy,click =  true)
    private LinearLayout guihualicai1_sex_boy;


    @BindView( id  = R.id.guihualicai1_seximg_boy )
    private ImageView guihualicai1_seximg_boy;




    @BindView( id  = R.id.guihualicai1_sex_girl,click =  true)
    private LinearLayout guihualicai1_sex_girl;


    @BindView( id  = R.id.guihualicai1_seximg_girl )
    private ImageView guihualicai1_seximg_girl;





    // 性别 0:男 1:女.
     private int intSex = 0;




    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_guihualicai1);

    }


    @Override
    protected void initView() {
        super.initView();
        guihualicai1_title .setTitleStr("规划理财方案");
        guihualicai1_title.setTitleBackClick(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId())
        {
             // 下一步
            case R.id.guihualicai1_next:


                 if( checkInfo())
                 {
                      UIHelper.showGuiHuaLiCai2s(this);

                 };

                 break;


            // 性别女
            case R.id.guihualicai1_sex_girl:
                guihualicai1_seximg_girl.setImageResource(R.drawable.btn_radio_on);
                guihualicai1_seximg_boy.setImageResource(R.drawable.btn_radio_off);
                intSex = 2;
                break;


            // 性别男
            case R.id.guihualicai1_sex_boy:
                guihualicai1_seximg_girl.setImageResource(R.drawable.btn_radio_off);
                guihualicai1_seximg_boy.setImageResource(R.drawable.btn_radio_on);
                intSex = 1;
                break;





        }
    }

    /**
     *  检查信息是否合格
     */
    private boolean checkInfo() {

         String strName  = guihualicai1_username.getText().toString();

        String strAge  = guihualicai1_age.getText().toString();



          // 出需求后再做检查信息


        return true;



    }




}
