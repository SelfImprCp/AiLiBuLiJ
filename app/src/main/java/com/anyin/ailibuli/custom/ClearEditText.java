package com.anyin.ailibuli.custom;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.anyin.ailibuli.R;


public class ClearEditText extends EditText {
	private Drawable mClearDrawable;
	private boolean touchIcon;

	public ClearEditText(Context context, AttributeSet attrs) {
		super(context, attrs);

		mClearDrawable = getCompoundDrawables()[2];
		if (mClearDrawable == null) {
			mClearDrawable = getResources().getDrawable(
					R.drawable.ic_search_clear);
		}

		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
				mClearDrawable.getIntrinsicHeight());

		setClearIconVisible(false);

	}

	public void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;

		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
	}

	public void setClearIcon(boolean touchIcon) {
		if (touchIcon) {
			mClearDrawable = getResources().getDrawable(
					R.drawable.ic_search_clear_pressed);
		} else {
			mClearDrawable = getResources().getDrawable(
					R.drawable.ic_search_clear);
		}
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], mClearDrawable,
				getCompoundDrawables()[3]);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (getCompoundDrawables()[2] == null) {
			return super.onTouchEvent(event);
		}

		boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
				&& (event.getX() < ((getWidth() - getPaddingRight())));
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchIcon = touchable;
			// setClearIcon(touchIcon);
			if (touchIcon) {
				return true;
			}
			break;

		case MotionEvent.ACTION_MOVE:
			if (touchIcon && !touchable) {
				touchIcon = false;
				// setClearIcon(touchIcon);
			} else {
				return true;
			}

			break;

		case MotionEvent.ACTION_UP:
			if (touchIcon) {
				this.setText("");
				return true;
			}
			break;
		default:
			break;
		}

		return super.onTouchEvent(event);
	}

	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		setClearIconVisible(text.length() > 0);
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
	}

}
