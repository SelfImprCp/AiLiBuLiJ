package com.anyin.ailibuli.domian;


import com.anyin.ailibuli.fragment.KaiHuFragment;

/**
 * 返回页的基本信息注册 (其实就是将原本会在Manifest中注册的Activity转成Fragment在这里注册)
 * 
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 */
public enum SimpleBackPage {

	KAI_HU(1, KaiHuFragment.class),;


	
	


	private Class<?> clazz;
	private int value;

	private SimpleBackPage(int value, Class<?> cls) {
		this.clazz = cls;
		this.value = value;

	}

	public int getValue() {
		return value;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public static Class<?> getPageByValueClass(int val) {
		for (SimpleBackPage p : values()) {
			if (p.getValue() == val)
				return p.getClass();
		}
		return null;
	}

	public static SimpleBackPage getPageByValue(int val) {
		for (SimpleBackPage p : values()) {
			if (p.getValue() == val)
				return p;
		}
		return null;
	}

}
