package com.anyin.ailibuli.ui;

import android.view.View;
import android.widget.EditText;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Jerry on 2016/6/20.
 *
 *  意见反馈
 */
public class FeedBackActivity extends BaseActivity {

    @BindView(id = R.id.feedback_title)
    TitleBarView feedback_title;

    @BindView(id = R.id.feedback_exit_content)
    EditText feedback_exit_content;
    @Override
    public void setRootView() {
        super.setRootView();

    setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initView() {
         super.initView();
        feedback_title.setTitleBackClick(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        feedback_title.setTitleStr("意见反馈");
        feedback_title.setMenuTextStr("提交");




        feedback_title.setMenuTextOnClick(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                commitFeedBack();
            }
        });


    }

    /**
     *
     */
    private void commitFeedBack()
     {


         UIHelper.showToast("提交意见反馈" + feedback_exit_content.getText().toString());
     }


}
