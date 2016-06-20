package com.anyin.ailibuli.banner;

/**
 * 描述：广告信息</br>
 */
public class ADInfo {

	String id = "";
	String url = "";
	String content = "";
	String type = "";
	String jumpUrl = "";
	String title = "";
	String imgUrl;
	// 是否要分享，1分享，0不分享
		private String shareAble;
		private String shareTitle;
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShareAble() {
		return shareAble;
	}

	public void setShareAble(String shareAble) {
		this.shareAble = shareAble;
	}

	private String shareSubtitle;

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getShareSubtitle() {
		return shareSubtitle;
	}

	public void setShareSubtitle(String shareSubtitle) {
		this.shareSubtitle = shareSubtitle;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
