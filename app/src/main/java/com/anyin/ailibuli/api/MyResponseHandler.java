package com.anyin.ailibuli.api;

import android.app.Dialog;

import com.anyin.ailibuli.ui.dialog.BasicDialog;
import com.anyin.ailibuli.utils.LogCp;
import com.loopj.android.http.AsyncHttpResponseHandler;



/**
 * 统一的数据处理
 * 
 * @author Administrator
 * 
 */

public abstract class MyResponseHandler extends AsyncHttpResponseHandler {

	// 加载框
	private Dialog mDialog;

	// 是否显示服务器返回的提示
	// private boolean mShowMessage;

	// 转化类
	// private Response mRes;

	public MyResponseHandler(Dialog dialog) {
		// TODO Auto-generated constructor stub
		// this.mRes = res;
		this.mDialog = dialog;
		// this.mShowMessage = showMessage;
	}

	public void onSuccess(String arg0) {

		LogCp.i(LogCp.CP, MyResponseHandler.class + " 请求来的数据 -------> " + arg0);

		// mRes = GsonUtil.jsonStrToBean(arg0, mRes.getClass());
		// if (mRes.getCode() == Response.SUCCESS) {
		//

		dataSuccess(arg0);

		// } else {
		// UIHelper.showToast(mRes.getMsg());
		// }
		//
		// if (mShowMessage)
		// UIHelper.showToast(mRes.getMsg());

	}

	public void onFailure(Throwable arg0) {
		arg0.printStackTrace();
		dataFailuer(arg0);
	};

	public void onFinish() {

		BasicDialog.hideDialog(mDialog);
		dataFinish();

	};

	/**
	 * 数据加载成功后调用
	 * 
	 * @param res
	 */
	public abstract void dataSuccess(String res);

	/**
	 * 
	 * @param
	 */
	public void dataFinish() {
	};

	/**
	 * 
	 * @param arg0
	 */
	public void dataFailuer(Throwable arg0) {

	}
}
