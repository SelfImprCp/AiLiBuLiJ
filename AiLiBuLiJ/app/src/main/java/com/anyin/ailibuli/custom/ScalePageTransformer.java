package com.anyin.ailibuli.custom;
 

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 动画
 * 
 * @author wuzz
 * 
 */
public class ScalePageTransformer implements ViewPager.PageTransformer {

	public static final float MAX_SCALE = 1.0f;
	
	//public static final float MIN_SCALE = 418 / 472.0f;
	
 public static final float MIN_SCALE =418 / 420.0f;
	
	public static final float MAX_ALPHA = 0f; 
	public static final float MIN_ALPHA = 0.45f;
                   
	@Override
	public void transformPage(View page, float position) {

		if (position < -1) {
			position = -1;
		} else if (position > 1) {
			position = 1;
		}

		float tempScale = position < 0 ? 1 + position : 1 - position;

		float slope = (MAX_SCALE - MIN_SCALE) / 1;
		// 一个公式
		float scaleValue = MIN_SCALE + tempScale * slope;
		page.setScaleX(scaleValue);
		page.setScaleY(scaleValue);

		page.setAlpha(MIN_ALPHA + (scaleValue - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.BASE) {
			page.getParent().requestLayout();
		}
	}
}
