package com.anyin.ailibuli.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.anyin.ailibuli.event.IntentChangeEvent;
import com.anyin.ailibuli.utils.LogCp;

import org.greenrobot.eventbus.EventBus;


/**
 * 监听 网络 变化的广播
 * 
 * @author Administrator
 * 
 */
public class IntentStateService extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// Toast.makeText(context, intent.getAction(), 1).show();
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobileInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiInfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo activeInfo = manager.getActiveNetworkInfo();
		// 如果无网络连接activeInfo为null

		//发出检查网络 的事件 
		IntentChangeEvent event = new IntentChangeEvent();
		EventBus.getDefault().post(event);
		
		LogCp.i(LogCp.CP, IntentStateService.class + " 网络 变化了 ，" + activeInfo);

		// Toast.makeText(context,
		// "mobile:"+mobileInfo.isConnected()+"\n"+"wifi:"+wifiInfo.isConnected()
		// +"\n"+"active:"+activeInfo.getTypeName(), 1).show();
	}

}
