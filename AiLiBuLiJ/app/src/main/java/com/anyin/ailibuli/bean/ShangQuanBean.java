package com.anyin.ailibuli.bean;

/**
 * 商圈主页的bean
 * 
 * @author Administrator
 * 
 */
public class ShangQuanBean extends Entity {
	//


	private String shopId;
	private String shopName;
	private String shopLogoUrl;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLogoUrl() {
		return shopLogoUrl;
	}

	public void setShopLogoUrl(String shopLogoUrl) {
		this.shopLogoUrl = shopLogoUrl;
	}
}
