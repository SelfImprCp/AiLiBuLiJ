package com.anyin.ailibuli.api;

import android.app.Dialog;

import com.anyin.ailibuli.ui.dialog.BasicDialog;
import com.anyin.ailibuli.utils.LogCp;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.kymjs.kjframe.http.HttpCallBack;


/**
 * 统一的数据处理
 *
 * @author Administrator
 */

public abstract class MyResponseHandler extends HttpCallBack {

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);
        dataSuccess(t);
    }


    @Override
    public void onFailure(int errorNo, String strMsg) {
        super.onFailure(errorNo, strMsg);
        dataFailuer(errorNo, strMsg);
    }

    @Override
    public void onFinish() {
        super.onFinish();
        dataFinish();
    }

    /**
     * 数据加载成功后调用
     *
     * @param res
     */
    public abstract void dataSuccess(String res)
    ;

    /**
     * @param
     */
    public abstract void dataFinish();

    /**
     * @param
     */
    public abstract void dataFailuer(int errorNo, String strMsg);
}
