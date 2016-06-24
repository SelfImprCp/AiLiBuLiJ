package com.anyin.ailibuli.ui.guihualicai;

import android.view.View;
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
public class GuiHuaLiCaiMaiFangActivity  extends BaseActivity {


    @BindView(id  = R.id.guihualicai_maifang_title)
    private TitleBarView guihualicai_maifang_title;




    // 要分期付款，显示分期付款要选择的项
    @BindView( id  = R.id.guihualicai_maifang_fenqi )
    private AutoLinearLayout guihualicai_maifang_fenqi;




    @BindView( id  = R.id.guihualicai_maifang_shifenqi,click =  true)
    private LinearLayout guihualicai_maifang_shifenqi;


    @BindView( id  = R.id.guihualicai_fangimg_shifenqi )
    private ImageView guihualicai_fangimg_shifenqi;




    @BindView( id  = R.id.guihualicai_maifang_bufenqi,click =  true)
    private LinearLayout guihualicai_maifang_bufenqi;


    @BindView( id  = R.id.guihualicai_fangimg_bufenqi )
    private ImageView guihualicai_fangimg_bufenqi;


    @Override
    public void setRootView() {
        super.setRootView();

    setContentView(R.layout.activity_guihualicai_maifang);

    }

    @Override
    protected void initView() {
        super.initView();

     guihualicai_maifang_title.setTitleStr("买房计划");
        guihualicai_maifang_title.setTitleBackClick(new View.OnClickListener(){
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
          case R.id.guihualicai_maifang_bufenqi:

              guihualicai_fangimg_bufenqi.setImageResource(R.drawable.btn_radio_on);
              guihualicai_fangimg_shifenqi.setImageResource(R.drawable.btn_radio_off);
              guihualicai_maifang_fenqi.setVisibility(View.GONE);
              break;

          case R.id.guihualicai_maifang_shifenqi:
              guihualicai_fangimg_bufenqi.setImageResource(R.drawable.btn_radio_off);
              guihualicai_fangimg_shifenqi.setImageResource(R.drawable.btn_radio_on);

              guihualicai_maifang_fenqi.setVisibility(View.VISIBLE);

              break;



      }




    }
}
