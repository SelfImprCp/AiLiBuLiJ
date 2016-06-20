package com.anyin.ailibuli.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;



/**
 * 没有明确分类的工具方法
 * 
 * @author Administrator
 * 
 */
public class Uitl {

	public static Uitl uitl;

	public static Uitl getInstance() {
		if (uitl == null) {
			uitl = new Uitl();
		}

		return uitl;
	}

	/**
	 * 
	 * @param latitudeBuss
	 * @param longitudeBuss
	 * @return
	 */
	public static String getJuLi(double latitudeBuss, double longitudeBuss) {
		// 距离

		// 定位到的用户地址
		/**
		double longitudeUser = AppContext.getInstance().getLongitude();
		double latitudeUser = AppContext.getInstance().getLatitude();

		// LogCp.i(LogCp.CP, Uitl.class + "bussLatLng ;"
		// +longitudeUser + "longitudeUser ;" +"latitudeUser"
		// + latitudeUser);

		if (longitudeUser == 0 || latitudeUser == 0) {
			return "未知";
		}

		LatLng bussLatLng = new LatLng(latitudeBuss, longitudeBuss);
		LatLng userLatLng = new LatLng(latitudeUser, longitudeUser);

		double distAnce = DistanceUtil.getDistance(bussLatLng, userLatLng);

		// LogCp.i(LogCp.CP, Uitl.class + "bussLatLng ;"
		// +bussLatLng + "userLatLng ;" +userLatLng+ " 算出的距离:  "
		// + distAnce);

		String strDistance = "";
		int distAnceInt = (int) distAnce;
		if (distAnceInt < 1000) {

			strDistance = distAnceInt + "m";
		} else {

			strDistance = distAnceInt / 1000.0 + "km";

		}
		 */
		String strDistance = "";
		return strDistance;

	}

	public static int getGridViewHeightByColumns(int columns, int allDataSize) {
		// 返回的grivView 的高度
		int gridViewHeight = 0;
		// 每行item高度
		int itemHeight = 450;

		// 一共有多少列 gridView
		int itemAll = 0;
		if (allDataSize % 2 == 0) {

			itemAll = allDataSize / 2;

			// 被2整除
		} else {
			itemAll = allDataSize / 2 + 1;

		}

		gridViewHeight = itemAll * itemHeight;
		return gridViewHeight;

	}

	/**
	 * 调地图导航
	 */
//	SelectPicPopupWindow menuWindow;
//	// 当前用户所在的地址
//	private String myAddress;
//	// 要前往的地址
//	private String toAddress;
//	// 调此方法的activity
//	private Activity mActivity;
//
//	public void shoMapDaoHang(Context mContext, Activity activity,
//			String mAddress, String tAddress) {
//
//		this.mActivity = activity;
//
//		this.myAddress = mAddress;
//		this.toAddress = tAddress;
//
//		LayoutInflater mInflater = (LayoutInflater) mContext
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View converView = mInflater.inflate(R.layout.item_focus, null);
//
//		menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);
//		menuWindow.showAtLocation(converView.findViewById(R.id.main),
//				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//
//	}

	// 为弹出窗口实现监听类
//	final OnClickListener itemsOnClick = new OnClickListener() {
//
//		@SuppressWarnings("deprecation")
//		public void onClick(View v) {
//			menuWindow.dismiss();
//			switch (v.getId()) {
//			case R.id.btn_baidu:
//
//				// UIHelper.showToast("ssssssssssss"+newAddress);
//				// UIHelper.showToast("ssssssssssss"+focus.getSell_address());
//				try {
//					Intent intent;
//					intent = Intent
//							.getIntent("intent://map/direction?"
//									+ "origin="
//									+ myAddress
//									+ "&"
//									+ "destination="
//									+ toAddress
//									+ "&mode=driving&"
//									+ "region=null"
//									+ "&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//					mActivity.startActivity(intent);
//				} catch (URISyntaxException e) {
//					e.printStackTrace();
//				}
//				break;
//			case R.id.btn_gaode:
//				Intent intent1 = new Intent(
//						"android.intent.action.VIEW",
//						android.net.Uri
//								.parse("androidamap://route?sourceApplication=摩狐&slat=36.2&slon=116.1&sname="
//										+ myAddress
//										+ "&dlat=36.3&dlon=116.2&dname="
//										+ toAddress + "&dev=1&m=0&t=2"));
//				intent1.setPackage("com.autonavi.minimap");
//				mActivity.startActivity(intent1);
//				break;
//			default:
//				break;
//			}
//
//		}
//
//	};
// 
// 

	 
	 
	/**
	  *  
	  */
	public static String contextColor = "#242424";
	public static String oneColor = "#999999";
	public static String twoColor = "#ff1d62";
	public static String blackColor = "#000000";

	public static void textViewShowTwoColor(TextView textView, String oneStr,
			String twoStr, String oneColor, String twoColor) {
		// mholder.item_mfocus_content.setText();
		String focus_content = oneStr + twoStr;

		SpannableStringBuilder builder = new SpannableStringBuilder(
				focus_content.toString());

		// ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
		// ForegroundColorSpan redSpan = new ForegroundColorSpan(oneColor);
		// ForegroundColorSpan whiteSpan = new ForegroundColorSpan(twoColor);
		ForegroundColorSpan redSpan = new ForegroundColorSpan(
				Color.parseColor(oneColor));
		ForegroundColorSpan whiteSpan = new ForegroundColorSpan(
				Color.parseColor(twoColor));

		builder.setSpan(redSpan, 0, oneStr.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		builder.setSpan(whiteSpan, oneStr.length(), focus_content.length(),
				Spannable.SPAN_INCLUSIVE_INCLUSIVE);

		textView.setText(builder);
	}

 
  
 
	 
}
