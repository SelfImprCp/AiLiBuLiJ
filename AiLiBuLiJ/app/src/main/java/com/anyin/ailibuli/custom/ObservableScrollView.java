package com.anyin.ailibuli.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

//自定义ScrollView
public class ObservableScrollView extends ScrollView {

	public ObservableScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mCallbacks != null) {
			mCallbacks.onScrollChanged(t);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mCallbacks != null) {
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				mCallbacks.onDownMotionEvent();
				break;

			case MotionEvent.ACTION_UP:
				break;
			case MotionEvent.ACTION_CANCEL:
				mCallbacks.onUpOrCancelMotionEvent();
				break;
			}
		}
		return super.onTouchEvent(ev);
	}

	
	@Override
	protected int computeVerticalScrollRange() {
		return super.computeVerticalScrollRange();
	}

	public static interface Callbacks {
		public void onScrollChanged(int scrollY);

		public void onDownMotionEvent();

		public void onUpOrCancelMotionEvent();
	}

	private Callbacks mCallbacks;

	public void setCallbacks(Callbacks listener) {
		mCallbacks = listener;
	}

	
}
