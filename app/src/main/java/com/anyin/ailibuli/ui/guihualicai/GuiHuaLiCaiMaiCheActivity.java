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
 *
 *  买车计划
 */
public class GuiHuaLiCaiMaiCheActivity  extends BaseActivity
{



     @BindView(id  = R.id.guihualicai_maiche_title)
      private TitleBarView guihualicai_maiche_title;



   // 要分期付款，显示分期付款要选择的项
    @BindView( id  = R.id.guihualicai_maiche_fenqi )
    private AutoLinearLayout guihualicai_maiche_fenqi;



    @BindView( id  = R.id.guihualicai_maiche_shifenqi,click =  true)
    private LinearLayout guihualicai_maiche_shifenqi;


    @BindView( id  = R.id.guihualicai_seximg_shifenqi )
    private ImageView guihualicai_seximg_shifenqi;




    @BindView( id  = R.id.guihualicai_maiche_bufenqi,click =  true)
    private LinearLayout guihualicai_maiche_bufenqi;


    @BindView( id  = R.id.guihualicai_seximg_bufenqi )
    private ImageView guihualicai_seximg_bufenqi;


    @BindView( id  = R.id.guihualicai_maiche_benxi,click =  true)
    private LinearLayout guihualicai_maiche_benxi;

    @BindView( id  = R.id.guihualicai_maiche_benjin,click =  true)
    private LinearLayout guihualicai_maiche_benjin;


    @BindView( id  = R.id.guihualicai_seximg_benxi )
    private ImageView guihualicai_seximg_benxi;

    @BindView( id  = R.id.guihualicai_seximg_benjin )
    private ImageView guihualicai_seximg_benjin;




    @BindView( id  = R.id.guihualicai_maiche_save ,click = true)
    private ImageView guihualicai_maiche_save;




    @BindView( id  = R.id.guihualicai_maiche_shoufu_select ,click = true)
    private ImageView guihualicai_maiche_shoufu_select;








  // 是否分期：0分期，1： 不分期
   private int fengQIInt = 0;



    @Override
    public void setRootView() {
        super.setRootView();
     setContentView(R.layout.activity_guihualicai_maiche);
    }

    @Override
    protected void initView() {
        super.initView();

        guihualicai_maiche_title.setTitleStr("买车计划");
        guihualicai_maiche_title.setTitleBackClick(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               finish();
            }
        });





    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

     switch (v.getId())
     {




         //  分期
         case R.id.guihualicai_maiche_shifenqi:
             guihualicai_seximg_shifenqi.setImageResource(R.drawable.btn_radio_on);
             guihualicai_seximg_bufenqi.setImageResource(R.drawable.btn_radio_off);
             fengQIInt = 0;
             guihualicai_maiche_fenqi.setVisibility(View.VISIBLE);
             break;


         //  不分期
         case R.id.guihualicai_maiche_bufenqi:
             guihualicai_seximg_shifenqi.setImageResource(R.drawable.btn_radio_off);
             guihualicai_seximg_bufenqi.setImageResource(R.drawable.btn_radio_on);
             fengQIInt = 1;
             guihualicai_maiche_fenqi.setVisibility(View.GONE);
             break;


          // 本息
         case R.id.guihualicai_maiche_benxi:

             guihualicai_seximg_benxi.setImageResource(R.drawable.btn_radio_on);
             guihualicai_seximg_benjin.setImageResource(R.drawable.btn_radio_off);
             break;

         case R.id.guihualicai_maiche_benjin:

             guihualicai_seximg_benxi.setImageResource(R.drawable.btn_radio_off);
             guihualicai_seximg_benjin.setImageResource(R.drawable.btn_radio_on);
             break;




          // 保存
         case R.id.guihualicai_maiche_save:

             finish();


             break;


          // 选择首付比例
         case R.id.guihualicai_maiche_shoufu_select:


             break;

     }
    }
}
