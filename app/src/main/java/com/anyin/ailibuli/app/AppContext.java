package com.anyin.ailibuli.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Notification;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Build;


import com.anyin.ailibuli.R;
import com.anyin.ailibuli.api.ApiHttpClient;

import com.anyin.ailibuli.manage.UserManaage;
import com.anyin.ailibuli.utils.ActivityManagerUtil;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.SharePreferencesUitl;
import com.anyin.ailibuli.utils.StringUtils;
import com.loopj.android.http.AsyncHttpClient;

import com.loopj.android.http.PersistentCookieStore;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;



import java.io.File;
import java.util.UUID;
import java.util.concurrent.Executor;


public class AppContext extends Application {

	private static AppContext context;
	private static ActivityManagerUtil activityManager = null;
	private Executor excutor = null;
	public static String APPCONFIG = "";
	private Notification mNotification;
	public static final int NOTIFY_ID = 0x912;// 通知ID


	 //最后一次刷新时间 ，，

	private static String LAST_REFRESH_TIME = "last_refresh_time.pref";

	// 经纬度
	public double latitude = 0;
	public double longitude = 0;
	public String locationCity = "";


	public String getLocationCity() {
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}



	//private PushAgent mPushAgent;

	@Override
	public void onCreate() {
		super.onCreate();
		activityManager = ActivityManagerUtil.getInstance();
		context = this;






		initHttp();

		// 百度地图
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		//SDKInitializer.initialize(this);
		// 友盟推送
		initUMPush();

		// 配置图片
		configImageLoader();

		 //初始化DB
//		MyDbManage.getMyDbManage().initDB();


	}

	/**
	 * 配置ImageLoder
	 */
	private void configImageLoader() {
		// 初始化ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.default_100_100)
				// 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.default_100_100)
				// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.default_100_100)
				// 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)
				// 设置下载的图片是否缓存在SD卡中
				.bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.EXACTLY)
				// .displayer(new RoundedBitmapDisplayer(50)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		File cacheDir = StorageUtils.getOwnCacheDirectory(
				getApplicationContext(), AppConfig.DEFAULT_SAVE_IMAGE_PATH);

		LogCp.i(LogCp.CP, AppContext.class + "  存放图片 的路径 ，， " + cacheDir);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				AppContext.this)
				.defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCache(new UnlimitedDiscCache(cacheDir))
				// 自定义缓存路径
				.imageDownloader(
						new BaseImageDownloader(getApplicationContext(),
								10 * 1000, 30 * 1000))
				// .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
				// 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

	public ActivityManagerUtil getActivityManager() {
		return activityManager;
	}

	public Executor getExcutor() {
		return excutor;
	}

	/**
	 * 友盟推送消息回调
	 */
	private void initUMPush() {

		// 使用自定义的NotificationHandler，来结合友盟统计处理消息通知
		// 参考http://bbs.umeng.com/thread-11112-1-1.html
		// CustomNotificationHandler notificationClickHandler = new
		// CustomNotificationHandler();

		// 友盟推送

		 /**
		mPushAgent = PushAgent.getInstance(this);
		mPushAgent.setDebugMode(true);
		// 友盟推送

		UmengMessageHandler messageHandler = new UmengMessageHandler() {
			@Override
			public void dealWithCustomMessage(Context arg0, final UMessage msg) {
				new Handler().post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 对自定义消息的处理方式，点击或者忽略
						boolean isClickOrDismissed = true;
						if (isClickOrDismissed) {
							// 自定义消息的点击统计
							UTrack.getInstance(getApplicationContext())
									.trackMsgClick(msg);
						} else {
							// 自定义消息的忽略统计
							UTrack.getInstance(getApplicationContext())
									.trackMsgDismissed(msg);
						}
						// UIHelper.showToast("友盟推送的自定义消息 " + msg.custom);

						// 设置 弹出通知

						int icon = R.drawable.ic_launcher;
						CharSequence tickerText = "摩狐";
						long when = System.currentTimeMillis();

						NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
								context);
						// 定义NotificationManager
						String ns = Context.NOTIFICATION_SERVICE;
						NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
						// 定义通知栏展现的内容信息

						mNotification = new Notification(icon, tickerText, when);
						mNotification.flags = Notification.FLAG_AUTO_CANCEL;
						// 设置默认声音
						mNotification.defaults |= Notification.DEFAULT_SOUND;
						// 设定震动(需加VIBRATE权限)
						// mNotification.defaults |=
						// Notification.DEFAULT_VIBRATE;
						mNotification.contentView = null;

						LogCp.i(LogCp.CP, AppContext.class + "  收到友盟的自定义消息 "
								+ msg.custom + " msg.extra;;" + msg.extra);

						// ********************** 处理业务
						// ****************************

					}
				});
			}

		};

		mPushAgent.setMessageHandler(messageHandler);
		  */
	}

	/**
	 * 获得当前app运行的AppContext
	 * 
	 * @return
	 */
	public static AppContext getInstance() {
		return context;
	}

	public static void exit() {
		activityManager.finishAllActivity();
	}


	/**
	 * 获取App唯一标识
	 * 
	 * @return
	 */
	public String getAppId() {

		SharePreferencesUitl uitl = SharePreferencesUitl
				.getSharePreferencesUitl(this);

		String uniqueID = uitl.getProperty(AppConfig.CONF_APP_UNIQUEID);
		if (StringUtils.isEmpty(uniqueID)) {
			uniqueID = UUID.randomUUID().toString();
			uitl.setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
		}
		return uniqueID;
	}

	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	/**
	 *
	 */
	private void initHttp() {
		// 初始化网络请求
		AsyncHttpClient client = new AsyncHttpClient();
		PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
		client.setCookieStore(myCookieStore);
		ApiHttpClient.setHttpClient(client);

		LogCp.i(LogCp.CP, AppContext.class + "   初始化http 请求时设置 的cookie : "
				+ UserManaage.getUserManaage().getCookie(this));

		ApiHttpClient.setCookie(UserManaage.getUserManaage().getCookie(this));

		// Bitmap缓存地址
		// BitmapConfig.CACHEPATH = "MoFox/imagecache";
	}
	/**
	 * 清除 cookie
	 */
	public void cleanCookie() {
		SharePreferencesUitl.getSharePreferencesUitl(this).removeProperty(
				AppConfig.CONF_COOKIE);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
