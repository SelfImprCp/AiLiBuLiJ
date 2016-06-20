/**package com.anyin.ailibuli.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.umeng.common.message.UmengMessageDeviceConfig;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.IUmengUnregisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

import org.json.JSONException;


/**
 * 推送消息绑定类
 * 
 * @author Administrator
 * 

public class PushMessageBind {

	private PushAgent mPushAgent;

	private Context mContext;

	private final String ALIAS_TYPE = "mofox";

	public PushMessageBind(Context context) {
		this.mContext = context;
	}


	public void initPushMsg() {
		mPushAgent = PushAgent.getInstance(mContext);
		// mPushAgent.setPushCheck(true); //默认不检查集成配置文件
		// mPushAgent.setLocalNotificationIntervalLimit(false);
		// //默认本地通知间隔最少是10分钟

		// sdk开启通知声音
		mPushAgent
				.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
		// sdk关闭通知声音
		// mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
		// 通知声音由服务端控制
		// mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);

		// mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
		// mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);

		// 应用程序启动统计
		// 参考集成文档的1.5.1.2
		// http://dev.umeng.com/push/android/integration#1_5_1
		mPushAgent.onAppStart();

		LogCp.i(LogCp.CP,
				PushMessageBind.class + "是否绑定" + mPushAgent.isEnabled());

		LogCp.i(LogCp.CP,
				PushMessageBind.class + "是否注册" + mPushAgent.isRegistered());
		// mPushAgent.disable(mUnregisterCallback);

		// 开启推送并设置注册的回调处理
		// if(!mPushAgent.isEnabled()||!mPushAgent.isRegistered())
		// {
		mPushAgent.enable(mRegisterCallback);

		// }

		PushAgent.sendSoTimeout(mContext, 600); // 设置护保进程间隔时间

	}

	Handler handler = new Handler();

	// 此处是注册的回调处理
	// 参考集成文档的1.7.10
	// http://dev.umeng.com/push/android/integration#1_7_10
	public IUmengRegisterCallback mRegisterCallback = new IUmengRegisterCallback() {

		@Override
		public void onRegistered(String registrationId) {
			// TODO Auto-generated method stub
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					try {
						updateStatus();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		}
	};
	// 此处是注销的回调处理
	// 参考集成文档的1.7.10
	// http://dev.umeng.com/push/android/integration#1_7_10
	public IUmengUnregisterCallback mUnregisterCallback = new IUmengUnregisterCallback() {

		@Override
		public void onUnregistered(String registrationId) {
			// TODO Auto-generated method stub
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					try {
						updateStatus();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		}
	};

	private void updateStatus() throws JSONException {

		// UIHelper.showToast("调用消息推送回调");

		String pkgName = mContext.getPackageName();
		String info = String.format(
				"enabled:%s  isRegistered:%s  DeviceToken:%s "
						+ "SdkVersion:%s AppVersionCode:%s AppVersionName:%s",
				mPushAgent.isEnabled(), mPushAgent.isRegistered(),
				mPushAgent.getRegistrationId(), MsgConstant.SDK_VERSION,
				UmengMessageDeviceConfig.getAppVersionCode(mContext),
				UmengMessageDeviceConfig.getAppVersionName(mContext));
		LogCp.i(LogCp.CP, PushMessageBind.class + "应用包名：" + pkgName + "\n"
				+ info);

		User user = UserManaage.getUserManaage().getLoginUser(mContext);
		if (null != user) {
			int loginUID = user.getId();

			if (0 != loginUID)

			{

				LogCp.i(LogCp.CP, PushMessageBind.class + " 设置 绑定 ，， ：");

				// new RemoveAliasTask(loginUID+"","mofox").execute();

				// mPushAgent.removeAlias(loginUID +"","mofox");
				new AddAliasTask(loginUID + "", ALIAS_TYPE).execute();

				// mPushAgent.addExclusiveAlias(loginUID, "mofox");

			}
		}
		String device_token = UmengRegistrar.getRegistrationId(mContext);

		LogCp.i(LogCp.CP, PushMessageBind.class + "device to ,,,, ："
				+ device_token + " udi :" + ",," + mPushAgent.isEnabled());

		// copyToClipBoard();

		LogCp.i(LogCp.CP,
				PushMessageBind.class
						+ "updateStatus:"
						+ String.format("enabled:%s  isRegistered:%s",
								mPushAgent.isEnabled(),
								mPushAgent.isRegistered()));

	}

	class AddAliasTask extends AsyncTask<Void, Void, Boolean> {

		String alias;
		String aliasType;

		public AddAliasTask(String aliasString, String aliasTypeString) {
			// TODO Auto-generated constructor stub
			this.alias = aliasString;
			this.aliasType = aliasTypeString;
		}

		protected Boolean doInBackground(Void... params) {
			try {
				return mPushAgent.addAlias(alias, aliasType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (Boolean.TRUE.equals(result))
				LogCp.i(LogCp.CP, "alias was set successfully.");

			// edAlias.setText("");
			// updateInfo("Add Alias:" + (result?"Success":"Fail"));
		}

	}

	/**
	 * 移除推送
	 * 
	 * @author Administrator
	 * 


	public void removeAliasTask() {

		// 退出了，
		User mInfo = UserManaage.getUserManaage().getLoginUser(mContext);

		if (mInfo != null) {
			int loginUID = mInfo.getId();

			if (0 != loginUID)

			{

				new RemoveAliasTask(loginUID + "", ALIAS_TYPE).execute();

			}
		}

	}

	class RemoveAliasTask extends AsyncTask<Void, Void, Boolean> {

		String alias;
		String aliasType;

		public RemoveAliasTask(String aliasString, String aliasTypeString) {
			// TODO Auto-generated constructor stub
			this.alias = aliasString;
			this.aliasType = aliasTypeString;
		}

		protected Boolean doInBackground(Void... params) {
			try {
				return mPushAgent.removeAlias(alias, aliasType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (Boolean.TRUE.equals(result))
				LogCp.i(LogCp.CP, "alias was set successfully.");

			// edAlias.setText("");
			// updateInfo("Add Alias:" + (result?"Success":"Fail"));
		}

	}


	 */
	// private void copyToClipBoard() {
	// if (Build.VERSION.SDK_INT < 11)
	// return;
	// String deviceToken = mPushAgent.getRegistrationId();
	// if (!TextUtils.isEmpty(deviceToken)) {
	// ClipboardManager clipboard = (ClipboardManager)
	// getSystemService(CLIPBOARD_SERVICE);
	// clipboard.setText(deviceToken);
	// }
	// }


