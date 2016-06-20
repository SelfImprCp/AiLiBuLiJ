package com.anyin.ailibuli.ui;

import android.view.View;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TimerTextView;
import com.anyin.ailibuli.custom.TitleBarView;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/20.
 */
public class SettingActivity extends BaseActivity {
    @BindView( id = R.id.settting_title)
    TitleBarView settting_title;

    @Override
    public void setRootView() {
      setContentView(R.layout.setting_activity);

    }

    @Override
    protected void initView() {
        super.initView();
       settting_title.setTitleStr("设置");
        settting_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   finish();
            }
        });

    }
}
