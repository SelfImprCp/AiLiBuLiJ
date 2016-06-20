package com.anyin.ailibuli.app;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * 应用程序配置类：用于保存用户相关信息及设置
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 下午5:29:00
 * 
 */
public class AppConfig {



	private Context mContext;
	private static AppConfig appConfig;

	public static AppConfig getAppConfig(Context context) {
		if (appConfig == null) {
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}




	public final static String CONF_COOKIE = "cookie";

	public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";

	// 检查更新
	public static final String KEY_CHECK_UPDATE = "KEY_CHECK_UPDATE";

	public static final int PAGE_SIZE = 10;// 默认分页大小



	// AppID：wx8edd04533dbf6f90
	// AppSecret：b03bd25d57678f55c314627b837d8d5f

	public static final String WEICHAT_APPID = "wx8edd04533dbf6f90";
	public static final String WEICHAT_SECRET = "b03bd25d57678f55c314627b837d8d5f1277794801";
	public static final String WEICHAT_APPKEY = "677b1ef7f0884ad4404695b10f828b0f";

	public static final String QQ_APPID = "1104789433";
	public static final String QQ_APPKEY = "8edd3cc7ca8dcc15082d6fe75969601b";
	// public static final String QQ_APPKEY = "xvGZLso3FunCEqDs";

	// 默认存放图片的路径
	public final static String DEFAULT_SAVE_IMAGE_PATH = Environment
			.getExternalStorageDirectory() +

	File.separator + "AiLiBuLi" + File.separator + "ailibuliimg" + File.separator;

	// 默认存放文件下载的路径
	public final static String DEFAULT_SAVE_FILE_PATH =

	Environment.getExternalStorageDirectory() + File.separator + "AiLiBuLi"
			+ File.separator + "download" + File.separator;


}
