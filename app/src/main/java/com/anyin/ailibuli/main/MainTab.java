package com.anyin.ailibuli.main;


import com.anyin.ailibuli.R;



public enum MainTab {

	HOME(0, R.string.main_tab_home, R.drawable.selector_mine_home,
			PlanFragment.class),

	FOCUS(1, R.string.main_tab_focus, R.drawable.selector_mine_foce,
			YiXuanJiFragment.class),
	SHOPING(2, R.string.main_tab_shopping, R.drawable.selector_mine_shoping,
			ShopFragment.class),



	MY(3, R.string.main_tab_my, R.drawable.selector_mine_my, MyFragment.class);

	private int idx;
	private int resName;
	private int resIcon;
	private Class<?> clz;

	private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
		this.idx = idx;
		this.resName = resName;
		this.resIcon = resIcon;
		this.clz = clz;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getResName() {
		return resName;
	}

	public void setResName(int resName) {
		this.resName = resName;
	}

	public int getResIcon() {
		return resIcon;
	}

	public void setResIcon(int resIcon) {
		this.resIcon = resIcon;
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
}
