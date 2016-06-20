package com.anyin.ailibuli.ui;/*
package cn.mofox.client.ui;

import org.kymjs.kjframe.ui.BindView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.mofox.client.R;
import cn.mofox.client.base.BaseActivity;
import cn.mofox.client.utils.LogCp;
import cn.mofox.client.utils.UpdateManagerUtil;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

*/
/**
 * 统一的URL界面 显示 ，传入一个ULR，去显示界面的数据 ，
 * 
 * @author Administrator
 * 
 *//*




public class WebViewActivity extends BaseActivity implements OnClickListener {

	// 返回
	@BindView(id = R.id.titlebar_img_back, click = true)
	private ImageView titlebar_img_back;
	// 分享
	@BindView(id = R.id.titlebar_img_menu, click = true)
	private ImageView titlebar_img_menu;
	// 收藏
	@BindView(id = R.id.titlebar_img_menu_col, click = true)
	private ImageView titlebar_img_menu_col;

	// 标题
	@BindView(id = R.id.titlebar_text_title)
	private TextView titlebar_text_title;
	// 网页
	@BindView(id = R.id.shop_webView)
	private WebView mWebView;

 
	private String load_url;
 
	@Override
	public void setRootView() {

		setContentView(R.layout.activity_webview);

	}

	@Override
	public void initWidget() {
		fullUI();

	}

	@Override
	public void initData() {

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	*/
/**
	 * 适配UI
	 *//*


	private void fullUI()

	{

		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		webSettings.setDefaultTextEncodingName("utf-8");

		webSettings.setBuiltInZoomControls(true);
		webSettings.setDisplayZoomControls(false);
		webSettings.setSupportZoom(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setDatabaseEnabled(true);

		// 全屏显示
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);

		mWebView.addJavascriptInterface(getHtmlObject(), "");

		// 修改UA
		String ua = webSettings.getUserAgentString();
		LogCp.i(LogCp.CP, WebViewActivity.class + "  获取到的 UA ：  " + ua);
		String version = UpdateManagerUtil.getVersionName();
		String mUA = ua + " mofox/" + version;
		LogCp.i(LogCp.CP, WebViewActivity.class + "   回味无穷的UA：  " + mUA);

		webSettings.setUserAgentString(mUA);
		mWebView.loadUrl(load_url);

		// mWebView.loadUrl("javascript:window.local_obj.shareinfo(document.getElementById(\"share\").getAttribute(\"shareinfo\"));");

	}

	@Override
	public void widgetClick(View v) {
		super.widgetClick(v);
		switch (v.getId()) {
		case R.id.titlebar_img_back:
			finish();
			break;

		// 分享出去，。，

		case R.id.titlebar_img_menu:

			break;

		// 收藏
		case R.id.titlebar_img_menu_col:

			break;

		default:
			break;
		}
	}

	private Object getHtmlObject() {
		Object insertObj = new Object() {
			public String HtmlcallJava() {
				return "Html call Java";
			}

			public String sendCommand(final String param) {

				LogCp.i(LogCp.CP, WebViewActivity.class
						+ "  从html中json 传来的数据 ----》 " + param);

				return "Html call Java : " + param;
			}

		};

		return insertObj;
	}

	// 首先在您的Activity中添加如下成员变量
	final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		*/
/** 使用SSO授权必须添加如下代码 *//*

		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
				requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
}
*/
