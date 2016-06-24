package com.anyin.ailibuli.main;


import android.support.v4.app.Fragment;
import android.view.View;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseFragment;
import com.anyin.ailibuli.bean.HeaderBeanV2;
import com.anyin.ailibuli.custom.TitleBarView;
import com.anyin.ailibuli.fragment.FragmentHeaderTab;
import com.anyin.ailibuli.fragment.YiXuanJiDiyFragment;
import com.anyin.ailibuli.fragment.YiXuanJiGeXinFragment;
import com.anyin.ailibuli.fragment.YiXuanJiJiJinFragment;
import com.anyin.ailibuli.interf.IFragmentCreate;

/**
 * 关注
 * <p>
 * author Administrator
 */

public class YiXuanJiFragment extends BaseFragment implements View.OnClickListener, IFragmentCreate {



       private TitleBarView main_yixuanji_title;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yixuanji;
    }


    @Override
    public void initView(View view) {
        super.initView(view);


        Fragment f = initFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, f)
                .commit();



        main_yixuanji_title = (TitleBarView)view.findViewById(R.id.main_yixuanji_title);
        main_yixuanji_title.setTitleStr("易选基");
        main_yixuanji_title.setTitleBackIsShow(View.GONE);

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {


        }
    }


    @Override
    public Fragment initFragment() {


        HeaderBeanV2 hSVip = new HeaderBeanV2("自定义收益率", "SVipFragment", new YiXuanJiDiyFragment());
        HeaderBeanV2 hVip = new HeaderBeanV2("个性推荐", "VipFragment", new YiXuanJiGeXinFragment());
        HeaderBeanV2 hCar = new HeaderBeanV2("基金挑选", "CarFragment", new YiXuanJiJiJinFragment());
        return FragmentHeaderTab.newInstance(hSVip, hVip, hCar);

    }


}
