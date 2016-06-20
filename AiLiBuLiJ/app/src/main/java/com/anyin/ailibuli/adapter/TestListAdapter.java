package com.anyin.ailibuli.adapter;

import android.content.Context;

import android.view.View;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.bean.ShangQuanBean;
import com.anyin.ailibuli.utils.UIHelper;


/**
 * Created by Jerry on 2016/6/17.
 */
public class TestListAdapter extends ListBaseAdapter<ShangQuanBean> {

    public TestListAdapter(Context context) {
        mContext = context;


    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_list_xiaoxi;
    }

    @Override
    public void convert(ViewHolder helper, final ShangQuanBean item) {

        TextView textView = helper.getView(R.id.xiaoxi_context);
        textView.setText(item.getShopName());

        TextView xiaoxi_time = helper.getView(R.id.xiaoxi_time);
        xiaoxi_time.setText(item.getShopId());


        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showToast(" 列表的单击事件," + item.getShopId());
            }
        });

    }

}
