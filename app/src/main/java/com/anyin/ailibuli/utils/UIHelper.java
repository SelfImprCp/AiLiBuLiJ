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
import com.anyin.ailibuli.ui.SettingActivity;


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
	 * 显示登录
	 */
	public static void showLogin(Context context) {
	//	openActivity(context, LoginActivity.class);
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
	 *
	 * @param context
     */
	public static void showSetting(Context context) {

	 openActivity(context, SettingActivity.class);
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

//	/**
//	 * 
//	 * @param context
//	 * @param page
//	 */
//	public static void showSimpleBack(Context context, SimpleBackPage page) {
//		Intent intent = new Intent(context, SimpleBackActivity.class);
//		intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
//		context.startActivity(intent);
//	}
//
//	/**
//	 * 
//	 * @param context
//	 * @param page
//	 * @param args
//	 */
//
//	public static void showSimpleBack(Context context, SimpleBackPage page,
//			Bundle args) {
//		Intent intent = new Intent(context, SimpleBackActivity.class);
//		intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
//		intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
//		context.startActivity(intent);
//	}

 

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
