package com.anyin.ailibuli.custom;


import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * 自定义ScrollView
 * 
 * @author wzz
 * 
 */
public class CustomScrollView extends ScrollView {
	private ListView mLsitView;
	private GestureDetector mGestureDetector;

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(context, new YScrollDetector());
		setFadingEdgeLength(0);
	}

	/**
	 * 覆写onInterceptTouchEvent方法，点击操作发生在ListView的区域的时候,
	 * 返回false让ScrollView的onTouchEvent接收不到MotionEvent，而是把Event传到下一级的控件中
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (mLsitView != null && checkArea(mLsitView, ev)) {
			return false;
		}

		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}

	/**
	 * 测试view是否在点击范围内
	 * 
	 * @param
	 * @param v
	 * @return
	 */
	private boolean checkArea(View v, MotionEvent event) {
		float x = event.getRawX();
		float y = event.getRawY();
		int[] locate = new int[2];
		v.getLocationOnScreen(locate);
		int l = locate[0];
		int r = l + v.getWidth();
		int t = locate[1];
		int b = t + v.getHeight();
		if (l < x && x < r && t < y && y < b) {
			return true;
		}
		return false;
	}

	public ListView getListView() {
		return mLsitView;
	}

	public void setListView(ListView listView) {
		this.mLsitView = listView;
	}

	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if (Math.abs(distanceY) > Math.abs(distanceX)) {
				return true;
			}
			return false;
		}
	}

}
