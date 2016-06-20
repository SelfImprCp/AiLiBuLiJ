package com.anyin.ailibuli.custom;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.anyin.ailibuli.R;


public class ImageCombination extends RelativeLayout {

	private ImageView mImageView;
	private CircleImageView cImageView;
	private OnItemClickListener mOnClickListener = null;
	private Bitmap mBitmap;

	public ImageCombination(Context context) {
		super(context);
		initView(context);
	}

	public ImageCombination(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public ImageCombination(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	private void initView(Context context) {
		View.inflate(context, R.layout.match_league_round_item, this);
		mImageView = (ImageView) findViewById(R.id.activity_index_gallery_item);
		cImageView = (CircleImageView) findViewById(R.id.activity_index_gallery_head);
	}

	public void setMImageResource(int resId) {
		mImageView.setImageResource(resId);
	}

	public void setCircleImageResource(int resId) {
		cImageView.setImageResource(resId);

	}
	public void setImageBitmap(Bitmap createReflectedImage) {
		this.mBitmap=createReflectedImage;
		
		
	}
	

	// ���õײ�ͼƬ�Ĵ�С
	public void setMImageSize(int width, int height) {
		LayoutParams layoutParams = new LayoutParams(width, height);
		mImageView.setLayoutParams(layoutParams);
	}

	// ����Բͼ�Ĵ�С
	public void setCircleImageSize(int width, int height) {
		LayoutParams layoutParams = new LayoutParams(width, height);
		cImageView.setLayoutParams(layoutParams);
	}

	/*
	 * ���õ������
	 */
	public void onClickListener(OnItemClickListener listener) {
		this.mOnClickListener = listener;
		mImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mOnClickListener.onImageClick();

			}
		});

	}

	public interface OnItemClickListener {
		public void onImageClick();
	}

	


}
