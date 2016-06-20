package com.anyin.ailibuli.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

 /**
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.ShareType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.utils.OauthHelper;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

   */


//import com.umeng.soexample.share_auth.ShareActivity;

/**
 * 分享工具类， 分享到WeChat ，QQ，等 ，用 友盟分享
 * 
 * 
 */
public class ShareUtil {

	public static ShareUtil shareUtih;

	public static final String SHARE_URL = "share_url";
	public static final String SHARE_CONTENT = "share_content";

	public static final String SHARE_JUMP_URL = "share_jump_url";
	public static final String SHARE_TITLE = "share_title";

	// 分享中显示 的图片
	private String share_url;
	private String shareContent = "【mofox客户端】一款开启实体店线上逛街购物新体验的APP";
	// 下载界面的url ,点击跳转的url
	private String share_jump_url;
	// 分享的标题，
	private String share_title = "";

	public static ShareUtil getInstance() {
		if (shareUtih == null)
			shareUtih = new ShareUtil();
		return shareUtih;
	}

	/**
	 * 调起分享 先调用 起来，后期再加分享的文字，图片等内容 bundle 里的是要分享的内容
	 * 
	 * @param context
	 */

	public void showShare(final Activity acti, final Context context,
			Bundle bundle) {
		/*
		 * if (TextUtils.isEmpty(getShareContent()) ||
		 * TextUtils.isEmpty(getShareUrl())) {
		 * AppContext.showToast("内容加载失败..."); return; }
		 */

		if (bundle != null) {
			share_url = bundle.getString(SHARE_URL);

			shareContent = bundle.getString(SHARE_CONTENT);
			LogCp.i(LogCp.CP, ShareUtil.class + "收到的分享内容 ：  " + shareContent);

			share_jump_url = bundle.getString(SHARE_JUMP_URL);

			share_title = bundle.getString(SHARE_TITLE);
		}

		 /*
		final ShareDialog dialog = new ShareDialog(context);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setTitle(R.string.share_to);
		dialog.setOnPlatformClickListener(new OnSharePlatformClick() {
			@Override
			public void onPlatformClick(int id) {
				switch (id) {
				case R.id.ly_share_weichat_circle:
					shareToWeiChatCircle(context);
					break;
				case R.id.ly_share_weichat:
					shareToWeiChat(context);
					break;
				case R.id.ly_share_sina_weibo:
					shareToSinaWeibo(context);
					break;
				case R.id.ly_share_qq:
					shareToQQ(SHARE_MEDIA.QQ, acti);
					break;
				case R.id.ly_share_copy_link:

					shareToQQZone(SHARE_MEDIA.QZONE, acti);
					// TDevice.copyTextToBoard(getShareUrl());
					break;
				case R.id.ly_share_more_option:
					TDevice.showSystemShareOption(context, "测试分享",
							"http://pic4.nipic.com/20090827/3095621_083213047918_2.jpg");
					break;

				default:
					break;
				}
				dialog.dismiss();
			}
		});
		dialog.show();
		*/
	}

	 /*
	private void shareContent(SHARE_MEDIA media, Context cotnext) {
		mController.setShareContent(getShareContent() + getShareUrl());
		mController.directShare(cotnext, media, null);
	}
	*/

	private String getShareContent() {

		if (StringUtils.isEmpty(shareContent)) {
			return " MoFox ";
		} else

		{
			return shareContent;

		}

		// return "MoFox";
	}

	private String getShareUrl() {
		return "www.baidu.com";
	}

	// ///////////////////////////Sina///////////////////////////////////

	private void shareToSinaWeibo(final Context cotnext) {

		 /**
		UMImage img;
		if (!StringUtils.isEmpty(share_url)) {
			img = new UMImage(cotnext, share_url);
		} else {
			img = new UMImage(cotnext, R.drawable.ic_launcher);
		}

		// 设置新浪微博SSO handler
		SinaSsoHandler sinaSsoHandler = new SinaSsoHandler();
		sinaSsoHandler.setTargetUrl(getJumpUrl());
		mController.setShareType(ShareType.SHAKE);
		mController.setShareContent(getShareContent() + " " + getJumpUrl());
		mController.setShareImage(getShareImg(cotnext));
		  */
		/**
		 * 
		 * 
		 * 
		 * 分享图片暂时只支持png格式 以下代码
		 * 
		 * 
		 * 
		 */
		// mController.setShareImage(new UMImage(cotnext,
		// "http://www.umeng.com/images/pic/social/chart_1.png"));

		 /**
		mController.getConfig().setSsoHandler(sinaSsoHandler);

		if (OauthHelper.isAuthenticated(cotnext, SHARE_MEDIA.SINA)) {
			mController.directShare(cotnext, SHARE_MEDIA.SINA, null);
		} else {
			mController.doOauthVerify(cotnext, SHARE_MEDIA.SINA,
					new SocializeListeners.UMAuthListener() {

						@Override
						public void onStart(SHARE_MEDIA arg0) {
						}

						@Override
						public void onError(SocializeException arg0,
								SHARE_MEDIA arg1) {
						}

						@Override
						public void onComplete(Bundle arg0, SHARE_MEDIA arg1) {
							mController.directShare(cotnext, SHARE_MEDIA.SINA,
									null);
						}

						@Override
						public void onCancel(SHARE_MEDIA arg0) {
						}
					});
		}
		  */

		// // 设置新浪微博SSO handler
		// mController.getConfig().setSsoHandler(new SinaSsoHandler());
		// if (OauthHelper.isAuthenticated(cotnext, SHARE_MEDIA.SINA)) {
		// shareContent(SHARE_MEDIA.SINA, cotnext);
		// } else {
		// mController.doOauthVerify(cotnext, SHARE_MEDIA.SINA,
		// new UMAuthListener() {
		//
		// @Override
		// public void onStart(SHARE_MEDIA arg0) {
		// }
		//
		// @Override
		// public void onError(SocializeException arg0,
		// SHARE_MEDIA arg1) {
		// }
		//
		// @Override
		// public void onComplete(Bundle arg0, SHARE_MEDIA arg1) {
		// shareContent(SHARE_MEDIA.SINA, cotnext);
		// }
		//
		// @Override
		// public void onCancel(SHARE_MEDIA arg0) {
		// }
		// });
		// }
	}

	// ///////////////////////////////////////////////////////////////
	// 发到朋友圈
	@SuppressWarnings("deprecation")
	private void shareToWeiChatCircle(Context cotnext) {
		 /**
		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(cotnext,
				AppConfig.WEICHAT_APPID, AppConfig.WEICHAT_SECRET);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
		// 设置微信朋友圈分享内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareContent(getShareContent());
		// 设置朋友圈title "MoFox 摩狐"
		//
		circleMedia.setTitle(share_title);
		circleMedia.setShareImage(getShareImg(cotnext));
		// 跳转
		circleMedia.setTargetUrl(getJumpUrl());

		mController.setShareMedia(circleMedia);
		mController.postShare(cotnext, SHARE_MEDIA.WEIXIN_CIRCLE, null);
	 */
		   }

	@SuppressWarnings("deprecation")
	private void shareToWeiChat(Context cotnext) {
		 /**
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(cotnext,
				AppConfig.WEICHAT_APPID, AppConfig.WEICHAT_SECRET);
		wxHandler.addToSocialSDK();
		// 设置微信好友分享内容
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		// 设置分享文字
		weixinContent.setShareContent(getShareContent());
		// 设置title
		weixinContent.setTitle(share_title);
		// 设置分享内容跳转URL
		weixinContent.setTargetUrl(getJumpUrl());
		// 设置分享图片
		weixinContent.setShareImage(getShareImg(cotnext));
		mController.setShareMedia(weixinContent);
		mController.postShare(cotnext, SHARE_MEDIA.WEIXIN, null);
   */
	}

	/**
	 * 
	 * @param media
	 * @param con
	 */

	 /**
	protected void shareToQQ(SHARE_MEDIA media, Activity con) {
		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(con,
				AppConfig.QQ_APPID, AppConfig.QQ_APPKEY);
		qqSsoHandler.setTargetUrl(getJumpUrl());
		qqSsoHandler.setTitle(share_title);
		qqSsoHandler.addToSocialSDK();
		mController.setShareContent(getShareContent());
		mController.setShareImage(getShareImg(con));
		mController.postShare(con, media, null);
	}
  */
	/**
	 * 
	 * @param media
	 * @param con
	 */
	 /**
	protected void shareToQQZone(SHARE_MEDIA media, Activity con) {

		//
		// QZoneShareContent qzone = new QZoneShareContent();
		// //设置分享文字
		// qzone.setShareContent(getShareContent());
		// //设置点击消息的跳转URL
		// qzone.setTargetUrl(getJumpUrl());
		// //设置分享内容的标题
		// qzone.setTitle(share_title);
		// //设置分享图片
		// qzone.setShareImage(getShareImg(con));
		// mController.setShareMedia(qzone);

		QZoneSsoHandler qqSsoHandler = new QZoneSsoHandler(con,
				AppConfig.QQ_APPID, AppConfig.QQ_APPKEY);
		// 参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.

		// qqSsoHandler.setTargetUrl(getJumpUrl());
		// qqSsoHandler.
		// qqSsoHandler.setTitle(share_title);
		//

		qqSsoHandler.addToSocialSDK();

		QZoneShareContent qzone = new QZoneShareContent();
		// 设置分享文字
		qzone.setShareContent(getShareContent());
		// 设置点击消息的跳转URL
		qzone.setTargetUrl(getJumpUrl());
		// 设置分享内容的标题
		qzone.setTitle(share_title);
		// 设置分享图片
		qzone.setShareImage(getShareImg(con));
		mController.setShareMedia(qzone);

		// mController.setShareContent(getShareContent());
		//
		// mController.setShareImage(getShareImg(con));
		mController.postShare(con, media, null);

	}

	// 点击分享跳转的url
	private String getJumpUrl() {
		// 如里有跳转的界面 ，就到跳转的界面 ， 如果 没有，就到下载 的界面
		if (!StringUtils.isEmpty(share_jump_url)) {
			return share_jump_url;
		} else {
			return "http://www.super.cn/m.php";
		}

	}

	protected UMImage getShareImg(Context context) {
		UMImage img;
		if (!StringUtils.isEmpty(share_url)) {
			img = new UMImage(context, share_url);

		} else {
			img = new UMImage(context, R.drawable.ic_launcher);

		}

		return img;
	}

	// 首先在您的Activity中添加如下成员变量
	final UMSocialService mController = UMServiceFactory
			.getUMSocialService("com.umeng.share");

	private void shareTest(Context context) {
		// 设置分享内容
		mController
				.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
		// 设置分享图片, 参数2为图片的url地址
		mController.setShareMedia(new UMImage(context,
				"http://www.umeng.com/images/pic/banner_module_social.png"));
		// 设置分享图片，参数2为本地图片的资源引用
		// mController.setShareMedia(new UMImage(getActivity(),
		// R.drawable.icon));
		// 设置分享图片，参数2为本地图片的路径(绝对路径)
		// mController.setShareMedia(new UMImage(getActivity(),
		// BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

		// 设置分享音乐
		// UMusic uMusic = new
		// UMusic("http://sns.whalecloud.com/test_music.mp3");
		// uMusic.setAuthor("GuGu");
		// uMusic.setTitle("天籁之音");
		// 设置音乐缩略图
		// uMusic.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		// mController.setShareMedia(uMusic);

		// 设置分享视频
		// UMVideo umVideo = new UMVideo(
		// "http://v.youku.com/v_show/id_XNTE5ODAwMDM2.html?f=19001023");
		// 设置视频缩略图
		// umVideo.setThumb("http://www.umeng.com/images/pic/banner_module_social.png");
		// umVideo.setTitle("友盟社会化分享!");
		// mController.setShareMedia(umVideo);

	}
	   */

}
