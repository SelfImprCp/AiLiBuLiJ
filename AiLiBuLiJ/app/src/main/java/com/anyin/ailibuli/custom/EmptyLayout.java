package com.anyin.ailibuli.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.utils.InternetUtil;


public class EmptyLayout extends LinearLayout implements
		android.view.View.OnClickListener {// , ISkinUIObserver {

	public static final int HIDE_LAYOUT = 4;// 隐藏
	public static final int NETWORK_ERROR = 1;// 网络错误
	public static final int NETWORK_LOADING = 2;// 加载中
	public static final int NODATA = 3;// 没有数据
	public static final int NODATA_ENABLE_CLICK = 5;// 单击重新加载
	public static final int NO_LOGIN = 6;

	private ProgressBar animProgress;
	private boolean clickEnable = true;
	private final Context context;
	public ImageView img;
	private android.view.View.OnClickListener listener;
	private int mErrorState;
	private RelativeLayout mLayout;
	private RelativeLayout error_nodata_layout;
	private String strNoDataContent = "";
	private int imgResource = 0;
	private TextView tv;

	public EmptyLayout(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public EmptyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	private void init() {
		View view = View.inflate(context, R.layout.view_error_layout, null);
		img = (ImageView) view.findViewById(R.id.img_error_layout);
		tv = (TextView) view.findViewById(R.id.tv_error_layout);

		error_nodata_layout = (RelativeLayout) view
				.findViewById(R.id.error_nodata_layout);
		mLayout = (RelativeLayout) view.findViewById(R.id.pageerrLayout);

		animProgress = (ProgressBar) view.findViewById(R.id.animProgress);
		setBackgroundColor(-1);
		setOnClickListener(this);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (clickEnable) {
					// setErrorType(NETWORK_LOADING);
					if (listener != null)
						listener.onClick(v);
				}
			}
		});
		addView(view);
		changeErrorLayoutBgMode(context);
	}

	public void changeErrorLayoutBgMode(Context context1) {
		// mLayout.setBackgroundColor(SkinsUtil.getColor(context1,
		// "bgcolor01"));
		// tv.setTextColor(SkinsUtil.getColor(context1, "textcolor05"));
	}

	public void dismiss() {
		mErrorState = HIDE_LAYOUT;
		setVisibility(View.GONE);
	}

	public int getErrorState() {
		return mErrorState;
	}

	public boolean isLoadError() {
		return mErrorState == NETWORK_ERROR;
	}

	public boolean isLoading() {
		return mErrorState == NETWORK_LOADING;
	}

	@Override
	public void onClick(View v) {
		if (clickEnable) {
			// setErrorType(NETWORK_LOADING);
			if (listener != null)
				listener.onClick(v);
		}
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		// MyApplication.getInstance().getAtSkinObserable().registered(this);
		onSkinChanged();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		// MyApplication.getInstance().getAtSkinObserable().unregistered(this);
	}

	public void onSkinChanged() {
		// mLayout.setBackgroundColor(SkinsUtil
		// .getColor(getContext(), "bgcolor01"));
		// tv.setTextColor(SkinsUtil.getColor(getContext(), "textcolor05"));
	}

	public void setDayNight(boolean flag) {
	}

	public void setErrorMessage(String msg) {
		tv.setText(msg);
	}

	public void setErrorType(int i) {
		setVisibility(View.VISIBLE);
		switch (i) {
		case NETWORK_ERROR:
			mErrorState = NETWORK_ERROR;
			// img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"pagefailed_bg"));
			if (InternetUtil.hasInternetConnected()) {
				tv.setText(R.string.error_view_load_error_click_to_refresh);
				img.setBackgroundResource(R.drawable.pagefailed_bg);
			} else {
				tv.setText(R.string.error_view_network_error_click_to_refresh);
				img.setBackgroundResource(R.drawable.page_icon_network);
			}
			img.setVisibility(View.VISIBLE);
			animProgress.setVisibility(View.GONE);
			clickEnable = true;
			break;
		case NETWORK_LOADING:
			mErrorState = NETWORK_LOADING;
			// animProgress.setBackgroundDrawable(SkinsUtil.getDrawable(context,"loadingpage_bg"));
			animProgress.setVisibility(View.VISIBLE);
			img.setVisibility(View.GONE);
			tv.setText(R.string.error_view_loading);
			clickEnable = false;
			break;
		case NODATA:
			mErrorState = NODATA;
			// img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"page_icon_empty"));
			img.setBackgroundResource(R.drawable.page_icon_empty);
			img.setVisibility(View.VISIBLE);
			animProgress.setVisibility(View.GONE);
			setTvNoDataContent();
			clickEnable = true;
			break;
		case HIDE_LAYOUT:
			setVisibility(View.GONE);
			break;
		case NODATA_ENABLE_CLICK:
			mErrorState = NODATA_ENABLE_CLICK;
			img.setBackgroundResource(R.drawable.page_icon_empty);
			// img.setBackgroundDrawable(SkinsUtil.getDrawable(context,"page_icon_empty"));
			img.setVisibility(View.VISIBLE);
			animProgress.setVisibility(View.GONE);
			setTvNoDataContent();
			clickEnable = true;
			break;
		default:
			break;
		}

		try {

			if (imgResource != 0) {
				img.setBackgroundResource(imgResource);
			} else {
				img.setBackgroundResource(R.drawable.page_icon_empty);
			}
		} catch (Exception e) {
		}
	}

	public void setNoDataContent(String noDataContent) {
		strNoDataContent = noDataContent;
	}

	public void setOnLayoutClickListener(View.OnClickListener listener) {
		this.listener = listener;
	}

	public void setTvNoDataContent() {
		if (!strNoDataContent.equals(""))
			tv.setText(strNoDataContent);
		else
			tv.setText(R.string.error_view_no_data);
	}

	public void setNoDataImg(int imId) {
		imgResource = imId;
	}

	/**
	 * 新添设置背景
	 * 
	 * @author 火蚁 2015-1-27 下午2:14:00
	 * 
	 */
	// public void setErrorImag() {
	//
	// }

	@Override
	public void setVisibility(int visibility) {
		if (visibility == View.GONE)
			mErrorState = HIDE_LAYOUT;
		super.setVisibility(visibility);
	}
}
