package com.anyin.ailibuli.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseFragment;

/**
 *  开户银行卡界面
 * Created by Jerry on 2016/6/21.
 */
public class KaiHuFinishFragment extends BaseFragment {


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_kaihu_finish, container,false);
        return view;
    }
     /*
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kaihu_finish;
    }
    */
}
