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
 * Created by Jerry on 2016/6/22.
 */
public class GuiHuaLiCai4Activity extends BaseActivity {


    @BindView(id = R.id.guihualicai4_title)
    private TitleBarView guihualicai4_title;

    @BindView(id = R.id.guihualicai4_next, click = true)
    private Button guihualicai4_next;

    @BindView(id = R.id.guihualicai4_mengxiang,click = true)
    private AutoLinearLayout guihualicai4_mengxiang;

    @BindView(id = R.id.guihualicai4_maiche,click = true)
    private AutoLinearLayout guihualicai4_maiche;
    @BindView(id = R.id.guihualicai4_maifang,click = true)
    private AutoLinearLayout guihualicai4_maifang;


    @BindView(id = R.id.guihualicai4_zinujiaoyu,click = true)
    private AutoLinearLayout guihualicai4_zinujiaoyu;
    @BindView(id = R.id.guihualicai4_yanglao,click = true)
    private AutoLinearLayout guihualicai4_yanglao;








    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_guihualicai4);

    }


    @Override
    protected void initView() {
        super.initView();
        guihualicai4_title.setTitleStr("规划理财方案");
        guihualicai4_title.setTitleBackClick(new View.OnClickListener() {

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
            case R.id.guihualicai4_next:


                UIHelper.showGuiHuaLiCai5s(this);

                break;


            case R.id.guihualicai4_zinujiaoyu:

                break;

            case R.id.guihualicai4_maiche:

                UIHelper.showGuiHuaLiCaiMaiChe(this);

                break;
            case R.id.guihualicai4_maifang:
                UIHelper.showGuiHuaLiCaiMaiFang(this);

                break;
            case R.id.guihualicai4_mengxiang:
                 UIHelper.showGuiHuaLiCaiDream(this);

                break;

            case R.id.guihualicai4_yanglao:
                UIHelper.showGuiHuaLiCaiYangLao(this);

                break;





        }
    }


}
