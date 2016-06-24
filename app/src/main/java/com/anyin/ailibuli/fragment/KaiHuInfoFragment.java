package com.anyin.ailibuli.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseFragment;


/**
 *  开户个人信息界面
 * Created by Jerry on 2016/6/21.
 */
public class KaiHuInfoFragment extends BaseFragment {


   // protected int getLayoutId() {
 //       return R.layout.fragment_kaihu_info;
   // }


    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kaihu_info, null);
 return view;
    }
    */

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_kaihu_info, container,false);
       return view;
    }
}
