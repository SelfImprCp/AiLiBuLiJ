package com.anyin.ailibuli.main;





import com.anyin.ailibuli.adapter.ListBaseAdapter;
import com.anyin.ailibuli.adapter.TestListAdapter;


import com.anyin.ailibuli.api.MyAPI;
import com.anyin.ailibuli.base.BaseListFragment;
import com.anyin.ailibuli.bean.ShangQuanBean;
import com.anyin.ailibuli.res.ShangQuanRes;
import com.anyin.ailibuli.utils.GsonUtil;
import com.anyin.ailibuli.utils.LogCp;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Administrator
 *
 */
public class ListTestFragment extends BaseListFragment<ShangQuanBean> {

    private static final String CACHE_KEY_PREFIX = "tweetslist_";



    @Override
    protected ListBaseAdapter<ShangQuanBean> getListAdapter() {
        return new TestListAdapter(getActivity());
    }

    @Override
    protected String getCacheKeyPrefix() {
        return  CACHE_KEY_PREFIX;
    }

    @Override
    protected List<ShangQuanBean> parseList(String is) throws Exception {


        ShangQuanRes res = GsonUtil.jsonStrToBean(is,ShangQuanRes.class);


        return res.getResult();
    }

    @Override
    protected void requestData(boolean refresh) {
        super.requestData(refresh);
    }

    @Override
    protected void sendRequestData() {


        LogCp.i(LogCp.CP ,ListTestFragment.class + " 当前加的是第几页 ：" + mCurrentPage);
        MyAPI.getMainFocusList(mCurrentPage + "",  "76" + "", mHandler);


    }

    @Override
    protected List<ShangQuanBean> readList(Serializable seri) {
        return  (List<ShangQuanBean>) seri;
    }
}

