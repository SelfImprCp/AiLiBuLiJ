package com.anyin.ailibuli.ui;

import android.view.View;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;
import com.anyin.ailibuli.utils.UpdateManagerUtil;

import org.kymjs.kjframe.ui.BindView;


/**
 * 关于我们，
 *
 * @author Administrator
 */

public class AboutMoFox extends BaseActivity {


    @BindView(id = R.id.about_text_version)
    private TextView about_text_version;

    @BindView(id = R.id.activity_about_titlebar)
    private TitleBarView activity_about_titlebar;


    @Override
    protected void initView() {
        super.initView();
        //设置标题
        activity_about_titlebar.setTitleStr("关于我们");
        activity_about_titlebar.setMenuTextStr("分享");
        activity_about_titlebar.setMenuTextOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showToast("分享一下");
            }
        });
//		activity_about_titlebar.setTitleBarMenuImg(R.drawable.fitting_check_attention_red);
        //
//		//返回
        activity_about_titlebar.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });


        String strVersion = UpdateManagerUtil.getVersionName();

        about_text_version.setText("当前版本:V" + strVersion);
    }




}
