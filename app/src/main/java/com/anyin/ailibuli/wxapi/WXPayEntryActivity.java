
 /**
package com.anyin.ailibuli.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.mofox.client.R;
import cn.mofox.client.app.AppConfig;
import cn.mofox.client.utils.LogCp;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_result);

		api = WXAPIFactory.createWXAPI(this, AppConfig.WEICHAT_APPID);

		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	/**
	 * 微信支付回调

	@Override
	public void onResp(BaseResp resp) {

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

			LogCp.i(LogCp.CP, WXPayEntryActivity.class + "微信支付返回 回调 ， 。。   "
					+ resp.errCode + ",," + resp.errStr + resp.transaction);
			if (resp.errCode == 0) {

				// MoFoxApi.orderpayBack(SelectPay.payRes.getOrder_pay_no(),"2",
				// orderPayBackhandler);
				//

				// 支付成功,跳转到订单详情，是等待发货状态
				// DeliveryOrder deliver = new DeliveryOrder();
				// deliver.setOrderId(SelectPay.payRes.getOrderID());
				//
				// deliver.setStatus(2);
				//
				// Bundle bundle = new Bundle();
				// bundle.putSerializable(DeliverDetailActivity.DEL_KEY,
				// deliver);
				// UIHelper.shwoDeliverDetail(WXPayEntryActivity.this, bundle);
				//

			}
			finish();
		}
	}

	/**
	 * 支付成功后，调用 服务器接口 客户端SDK支付完，务必调用此接口，以便服务器锁定订单,避免多次支付。

	private AsyncHttpResponseHandler orderPayBackhandler = new AsyncHttpResponseHandler() {
		public void onSuccess(String arg0) {
		};

		public void onFailure(Throwable arg0) {
		};

		public void onFinish() {
		};
	};

}
 */