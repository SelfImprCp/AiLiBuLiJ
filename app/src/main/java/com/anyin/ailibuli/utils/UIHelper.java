package com.anyin.ailibuli.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.app.AppContext;
import com.anyin.ailibuli.domian.SimpleBackPage;
import com.anyin.ailibuli.ui.BankCardActivity;
import com.anyin.ailibuli.ui.ChangePassActivity;
import com.anyin.ailibuli.ui.FeedBackActivity;
import com.anyin.ailibuli.ui.TestActivity;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing1;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing10;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing2;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing3;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing4;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing5;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing6;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing7;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing8;
import com.anyin.ailibuli.ui.fengxianceping.FengXianCePing9;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCai1Activity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCai2Activity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCai3Activity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCai4Activity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCai5Activity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCai6Activity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCaiCheDaiActivity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCaiDreamActivity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCaiFangDaiActivity;
import com.anyin.ailibuli.ui.LoginActivity;
import com.anyin.ailibuli.ui.RegisterActivity;
import com.anyin.ailibuli.ui.SettingActivity;
import com.anyin.ailibuli.ui.SimpleBackActivity;
import com.anyin.ailibuli.ui.UserInfoActivity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCaiMaiCheActivity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCaiMaiFangActivity;
import com.anyin.ailibuli.ui.guihualicai.GuiHuaLiCaiYangLaoActivity;


/**
 * 界面帮助类
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年10月10日 下午3:33:36
 * 
 */
public class UIHelper {

	private static String lastToast = "";
	private static long lastToastTime;
	public static UIHelper uiHelper;

	public static UIHelper getInstance() {
		if (uiHelper == null)
			uiHelper = new UIHelper();
		return uiHelper;
	}

	/**
	 * 拨打电话
	 *
	 * @param context
	 */
	public static void showTellPhone(Context context, String phone) {
		// Intent intent = new Intent(Intent.ACTION_CALL,
		// Uri.parse("tel:" + phone));
		// context.startActivity(intent);

		Intent intent = new Intent(Intent.ACTION_DIAL,
				Uri.parse("tel:" + phone));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 显示设置网络
	 */

	public static void showSetIntent(Context context) {
		// TODO Auto-generated method stub
		Intent intent = null;
		// 判断手机系统的版本 即API大于10 就是3.0或以上版本
		if (android.os.Build.VERSION.SDK_INT > 10) {
			intent = new Intent(
					android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		} else {
			intent = new Intent();
			ComponentName component = new ComponentName("com.android.settings",
					"com.android.settings.WirelessSettings");
			intent.setComponent(component);
			intent.setAction("android.intent.action.VIEW");
		}
		context.startActivity(intent);
	}




	/**
	 * 显示 关于我们
	 *
	 * @param context
	 */

	public static void showAbout(Context context) {
		LogCp.i(LogCp.CP, UIHelper.class +  " 进入 到uihelper g ，"  );

	//	openActivity(context, AboutMoFox.class);
	}


	/**
	 * 显示 设置
	 * @param context
     */
	public static void showSetting(Context context) {

	 openActivity(context, SettingActivity.class);
	}
	/**
	 * 显示  意见反馈
	 * @param context
	 */
	public static void showFeedback(Context context) {

		openActivity(context, FeedBackActivity.class);
	}

	/**
	 * 显示  用户信息
	 * @param context
	 */
	public static void showUserInfo(Context context) {

		openActivity(context, UserInfoActivity.class);
	}


	/**
	 * 显示  银行卡管理
	 * @param context
	 */
	public static void showBankCard(Context context) {

		openActivity(context, BankCardActivity.class);
	}


	/**
	 * 显示   修改密码
	 * @param context
	 */
	public static void showChangePass(Context context) {

		openActivity(context, ChangePassActivity.class);
	}




	/**
	 * 显示    登录
	 * @param context
	 */
	public static void showLogin(Context context) {

		openActivity(context, LoginActivity.class);
	}


	/**
	 *  显示注册
	 * @param context
	 */
	public static void showRegister(Context context) {

		openActivity(context, RegisterActivity.class);


	}



	/**
	 *  显示规划理财第一个界面
	 * @param context
	 */
	public static void showGuiHuaLiCai1s(Context context) {

		openActivity(context, GuiHuaLiCai1Activity.class);


	}
	/**
	 *  显示规划理财第2个界面
	 * @param context
	 */

	public static void showGuiHuaLiCai2s(Context context) {

		openActivity(context, GuiHuaLiCai2Activity.class);

	}



	/**
	 *  显示规划理财第3个界面
	 * @param context
	 */

	public static void showGuiHuaLiCai3s(Context context) {

		openActivity(context, GuiHuaLiCai3Activity.class);

	}

	/**显示规划理财第4个界面
	 *
	 * @param
     */

	public static void showGuiHuaLiCai4s(  Context context) {

		openActivity(context, GuiHuaLiCai4Activity.class);

	}


	/**
	 *  显示规划理财第5个界面
	 * @param context
     */
	public static void showGuiHuaLiCai5s( Context context  ) {
		openActivity(context, GuiHuaLiCai5Activity.class);
	}

	/**
	 *  显示规划理财第6个界面
	 * @param context
	 */
	public static void showGuiHuaLiCai6s( Context context  ) {
		openActivity(context, GuiHuaLiCai6Activity.class);
	}


	/**
	 *  显示 规划理财买车计划
	 * @param context
     */
	public static void showGuiHuaLiCaiMaiChe(   Context context ) {
	   openActivity(context,GuiHuaLiCaiMaiCheActivity.class);

	}
	/**
	 *  显示 规划理财买房计划
	 * @param context
	 */
	public static void showGuiHuaLiCaiMaiFang(   Context context ) {
		openActivity(context,GuiHuaLiCaiMaiFangActivity.class);

	}


	/**
	 *  显示 规划理财梦想计划
	 * @param context
	 */
	public static void showGuiHuaLiCaiDream(   Context context ) {
		openActivity(context,GuiHuaLiCaiDreamActivity.class);

	}


	/**
	 *  显示 规划理财梦想计划
	 * @param context
	 */
	public static void showGuiHuaLiCaiYangLao(   Context context ) {
		openActivity(context,GuiHuaLiCaiYangLaoActivity.class);

	}














	/***
	 * 风险测试
	 */


	public static void fenXianTest1( Context context  ) {
		openActivity(context, FengXianCePing1.class);
	}

	public static void fenXianTest2( Context context  ) {
		openActivity(context, FengXianCePing2.class);
	}
	public static void fenXianTest3( Context context  ) {
		openActivity(context, FengXianCePing3.class);
	}
	public static void fenXianTest4( Context context  ) {
		openActivity(context, FengXianCePing4.class);
	}
	public static void fenXianTest5( Context context  ) {
		openActivity(context, FengXianCePing5.class);
	}
	public static void fenXianTest6( Context context  ) {
		openActivity(context, FengXianCePing6.class);
	}
	public static void fenXianTest7( Context context  ) {
		openActivity(context, FengXianCePing7.class);
	}
	public static void fenXianTest8( Context context  ) {
		openActivity(context, FengXianCePing8.class);
	}
	public static void fenXianTest9( Context context  ) {
		openActivity(context, FengXianCePing9.class);
	}public static void fenXianTest10( Context context  ) {
		openActivity(context, FengXianCePing10.class);
	}



	/**
	 *  车代
	 * @param context
     */

	public static void showGuiHuaLiCaiCheDai(Context context) {
		openActivity(context, GuiHuaLiCaiCheDaiActivity.class);

	}

	/**
	 *  房子贷款
	 * @param context
     */
	public static void showGuiHuaLiCaiFangDai(Context context) {

		openActivity(context, GuiHuaLiCaiFangDaiActivity.class);

	}




	/**
	 *
	 * @param context
	 */
	public static void showKaiHu(Context context) {

		showSimpleBack(context, SimpleBackPage.KAI_HU);


	}


	/**
	 *   显示我自己的一个测试 页面
	 * @param context
	 */
	public static void showTest(Context context) {

		openActivity(context, TestActivity.class);


	}








	// ===============================以下代码勿改动======================================//

	/**
	 * 通过类名启动Activity
	 *
	 * @param pClass
	 */
	public static void openActivity(Context context, Class<?> pClass) {
		openActivity(context, pClass, null);
	}

	public static void openActivityC(Context context, Class<?> pClass) {
		openActivity(context, pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 *
	 * @param pClass
	 * @param pBundle
	 */
	public static void openActivity(Context context, Class<?> pClass,
			Bundle pBundle) {
		Intent intent = new Intent(context, pClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		context.startActivity(intent);
	}

	/**
	 * 通过Action启动Activity
	 *
	 * @param pAction
	 */
	protected void openActivity(Context cotnext, String pAction) {
		openActivity(cotnext, pAction, null);
	}

	/**
	 * 通过Action启动Activity，并且含有Bundle数据
	 *
	 * @param pAction
	 * @param pBundle
	 */
	protected void openActivity(Context cotnext, String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		cotnext.startActivity(intent);
	}


	/**
	 *
	 * @param context
	 * @param page
	 */
	public static void showSimpleBack(Context context, SimpleBackPage page) {
		Intent intent = new Intent(context, SimpleBackActivity.class);
		intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
		context.startActivity(intent);
	}

	/**
	 *
	 * @param context
	 * @param page
	 * @param args
	 */

	public static void showSimpleBack(Context context, SimpleBackPage page,
									  Bundle args) {
		Intent intent = new Intent(context, SimpleBackActivity.class);
		intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
		intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
		context.startActivity(intent);
	}



	// ===============================显示Toast======================================//
	/**
	 *
	 * @param
	 */

	public static void showToast(int message) {
		showToast(message, Toast.LENGTH_LONG, 0);
	}

	public static void showToast(String message) {
		showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
	}

	public static void showToast(int message, int icon) {
		showToast(message, Toast.LENGTH_LONG, icon);
	}

	public static void showToast(String message, int icon) {
		showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
	}

	public static void showToastShort(int message) {
		showToast(message, Toast.LENGTH_SHORT, 0);
	}

	public static void showToastShort(String message) {
		showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
	}

	public static void showToastShort(int message, Object... args) {
		showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM, args);
	}

	public static void showToast(int message, int duration, int icon) {
		showToast(message, duration, icon, Gravity.BOTTOM);
	}

	public static void showToast(int message, int duration, int icon,
			int gravity) {
		showToast( AppContext.getInstance().getString(message), duration, icon,
				gravity);
	}

	public static void showToast(int message, int duration, int icon,
			int gravity, Object... args) {
		showToast( AppContext.getInstance().getString(message, args), duration,
				icon, gravity);
	}

	public static void showToast(String message, int duration, int icon,
			int gravity) {
		if (message != null && !message.equalsIgnoreCase("")) {
			long time = System.currentTimeMillis();
			if (!message.equalsIgnoreCase(lastToast)
					|| Math.abs(time - lastToastTime) > 2000) {
				View view = LayoutInflater.from( AppContext.getInstance()).inflate(
						R.layout.view_toast, null);
				((TextView) view.findViewById(R.id.title_tv)).setText(message);
				if (icon != 0) {
					((ImageView) view.findViewById(R.id.icon_iv))
							.setImageResource(icon);
					((ImageView) view.findViewById(R.id.icon_iv))
							.setVisibility(View.VISIBLE);
				}
				Toast toast = new Toast( AppContext.getInstance());
				toast.setView(view);
				if (gravity == Gravity.CENTER) {
					toast.setGravity(gravity, 0, 0);
				} else {
					toast.setGravity(gravity, 0, 35);
				}

				toast.setDuration(duration);
				toast.show();
				lastToast = message;
				lastToastTime = System.currentTimeMillis();
			}
		}
	}



}
