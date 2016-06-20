package com.anyin.ailibuli.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.base.BaseActivity;
import com.anyin.ailibuli.main.MainActivity;


/**
 * 应用 程序启动页
 * 
 * @author Administrator /**
 * 
 * @{# SplashActivity.java Create on 2013-5-2 下午9:10:01
 * 
 *     class desc: 启动画面 (1)判断是否是首次加载应用--采取读取SharedPreferences的方法
 *     (2)是，则进入GuideActivity；否，则进入MainActivity (3)3s后执行(2)操作
 * 
 *     <p>
 *     Copyright: Copyright(c) 2013
 *     </p>
 * @Version 1.0
 * @Author <a href="mailto:gaolei_xj@163.com">Leo</a>
 * 
 * 
 */



public class AppStart extends BaseActivity {

	// 定位有关
//	public LocationClient mLocationClient = null;
//	public BDLocationListener myListener = new MyLocationListener();

	boolean isFirstIn = false;

	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	// 延迟3秒
	private static final long SPLASH_DELAY_MILLIS = 3000;

	private static final String SHAREDPREFERENCES_NAME = "first_pref";
	/**
	 * Handler:跳转到不同界面
	 */
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHome();
				break;
			case GO_GUIDE:
				goGuide();
				break;
			}
			super.handleMessage(msg);
		}
	};

	/**
	 * 去首页
	 */
	private void goHome() {

		Intent jumpIntent = new Intent();
		jumpIntent.setClass(this, MainActivity.class);
		startActivity(jumpIntent);
		finish();
	}

	/**
	 * 去引导页
	 */

	private void goGuide() {
		Intent jumpIntent = new Intent();
		jumpIntent.setClass(this, GuideActivity.class);
		startActivity(jumpIntent);
		finish();

	}


	@Override
	protected void initView() {
		super.initView();

		init();


		// 定位初始化
		 /**
		mLocationClient = new LocationClient(this); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		option.setAddrType("all");
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		  */

	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
 /**
		PushMessageBind pushMessage = new PushMessageBind(AppStart.this);
		pushMessage.initPushMsg();
  */
	}

	/**
	  * 
	  */

	private void init() {
		// 读取SharedPreferences中需要的数据
		// 使用SharedPreferences来记录程序的使用次数
		SharedPreferences preferences = getSharedPreferences(
				SHAREDPREFERENCES_NAME, MODE_PRIVATE);

		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		isFirstIn = preferences.getBoolean("isFirstIn", true);

		// 设成false ,从启动图一直进
		// 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
		if (!isFirstIn) {
			// 使用Handler的postDelayed方法，3秒后执行跳转到MainActivity
			mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
		}

	}

	/**
	 * 定位SDK监听函数
	 */
	 /**
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null)
				return;

			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());

			Intent intent = new Intent("com.loaction.getAddress");

			if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果

				sb.append("\nspeed : ");
				sb.append(location.getSpeed());// 单位：公里每小时
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\nheight : ");
				sb.append(location.getAltitude());// 单位：米
				sb.append("\ndirection : ");
				sb.append(location.getDirection());// 单位度
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\ndescribe : ");
				sb.append("gps定位成功");

				intent.putExtra("address", location.getAddrStr());
				sendBroadcast(intent);

			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
				sb.append("\ndescribe : ");
				sb.append("网络定位成功");
				intent.putExtra("address", location.getAddrStr());
				sendBroadcast(intent);
			} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
				sb.append("\ndescribe : ");
				sb.append("离线定位成功，离线定位结果也是有效的");
				intent.putExtra("address", location.getAddrStr());
				sendBroadcast(intent);
			} else if (location.getLocType() == BDLocation.TypeServerError) {
				sb.append("\ndescribe : ");
				sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
			} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
				sb.append("\ndescribe : ");
				sb.append("网络不同导致定位失败，请检查网络是否通畅");
			} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
				sb.append("\ndescribe : ");
				sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原"
						+ "因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
			}

			AppContext.getInstance().setLatitude(location.getLatitude());
			AppContext.getInstance().setLongitude(location.getLongitude());

			AppContext.getInstance().setLocationCity(location.getCity());

			LogCp.i(LogCp.CP, AppStart.class + "百度定位到的数据 ，" + sb.toString());

		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
	   */

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
	//	mLocationClient.stop();

		super.onDestroy();
	}

}
