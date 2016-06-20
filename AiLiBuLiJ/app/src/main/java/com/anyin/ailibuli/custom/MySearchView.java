package com.anyin.ailibuli.custom;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.anyin.ailibuli.R;

import org.kymjs.kjframe.ui.BindView;


/**
 *  自定义搜索框
 * 
 * @author baoyong
 *
 */

public class MySearchView extends FrameLayout {
	@BindView(id = R.id.editText_search)
	private EditText clearEditText;
	@BindView(id = R.id.search_button_cancel)
	private TextView textView_cancel;

	private OnFocusChangeListener mFocusChangeListener;

	
	public MySearchView(Context context) {
		super(context);
		init(context);
	}

	public MySearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.view_search, this);

		if (isInEditMode()) {
			return;
		}

//		ViewUtils.inject(this);
		clearEditText.addTextChangedListener(new EditChangedListener());

		clearEditText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
			
				
				
				
			}
		});

	

	}
	
	
	 /**
	  * 设置 一健清除
	  * @param onClickListener
	  */
	public void setCanOnClick(OnClickListener onClickListener)
	{
		textView_cancel.setOnClickListener(onClickListener);
	}

	public void setOnFocusChangeListener(OnFocusChangeListener l) {
		mFocusChangeListener = l;
	}

	public void addTextChangedListener(TextWatcher watcher) {
		clearEditText.addTextChangedListener(watcher);
	}

	public CharSequence getText() {
		return clearEditText.getText();
	}

	public void setHintText(CharSequence str )
	{
		
		clearEditText.setHint(str);
	}
	
	public void setText(CharSequence text) {
		clearEditText.setText(text);
	}

	
	/**
	 * 输入监听
	 * @author Administrator
	 *
	 */
    class EditChangedListener implements TextWatcher {  
       
   
        @Override  
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {  
           
        }  
   
        @Override  
        public void onTextChanged(CharSequence s, int start, int before, int count) {  
        	if (clearEditText.getText().length()>0) {
				textView_cancel.setVisibility(View.VISIBLE);
			} else {
				textView_cancel.setVisibility(View.GONE);
			}
   
        }  
   
        @Override  
        public void afterTextChanged(Editable s) {  
        }
    };  
}
