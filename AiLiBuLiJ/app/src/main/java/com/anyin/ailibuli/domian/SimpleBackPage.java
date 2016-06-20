package com.anyin.ailibuli.domian;


/**
 * 返回页的基本信息注册 (其实就是将原本会在Manifest中注册的Activity转成Fragment在这里注册)
 * 
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 */
public enum SimpleBackPage {

//	MY_HELP(2, IssueFragment.class),
//
//	MY_DELIVERY(5, DeliveryOrderFragment.class), ORDER_COUPONS(6,
//			OrderSelectCoupons.class), My_STOREUNDONE(7,
//			TakeOrderFragment.class), MY_COUPONS(8,
//			MyCouponsViewPagerFragment.class), MY_ADDRESS(
//
//	9, AddressmanageFragment.class), TRY_ORDER(10, TryOrderFragment.class),
//
// MAIN_FOCUS_COMMENT(14,
//			MyFocusComment.class),
//
//	DETAIL_COMMENT(17, GoodsCommentFragment.class), MORE_FASHION(18,
//			FashionAdviceFragment.class),
//			FASHION_ADVICE(22,
//			FashionAdviceFragment.class),
//
//	ORDER_COMMENT(28, DeliveryOrderComment.class),
//
//	MOFANSHI_SHOW(30, MoFanShiFragment.class), MY_COLLECT(32,
//			CollectViewPagerFragment.class), MY_RETRO(34,
//			FeedbackFragment.class), My_RETURNORDER(36,
//			ReturnOrderListFragment.class), MINGPINGUANG(37,
//			MingPinGuanFragment.class), SHNAGQUANDETIAL(38,
//			ShangQuanDeital.class), SHNAGQUANYOUHUIQUAN(39,
//			ShangQuanYouHuiQuanFragment.class), SHANGQUANSHOPTYPE(40,
//			ShangQuanShopTypeFragment.class),
//
//	SHOPDETIAL(41, ShopDetialFragment.class),
//	
//	SHOP_FENDIAN(42, FenDianShopFragment.class),
//	
	
	
	

	;

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
