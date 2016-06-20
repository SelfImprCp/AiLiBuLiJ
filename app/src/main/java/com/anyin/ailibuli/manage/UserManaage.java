package com.anyin.ailibuli.manage;

import android.content.Context;

import com.anyin.ailibuli.api.ApiHttpClient;
import com.anyin.ailibuli.app.AppConfig;
import com.anyin.ailibuli.app.AppContext;
import com.anyin.ailibuli.bean.User;
import com.anyin.ailibuli.res.UserRes;
import com.anyin.ailibuli.utils.SharePreferencesUitl;
import com.loopj.android.http.AsyncHttpClient;

import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.http.HttpConfig;

import java.util.List;

import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.client.protocol.ClientContext;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.protocol.HttpContext;


/**
 * 当前登录用户管理 工具类
 *
 * @author Administrator
 */

public class UserManaage {
    // 当前 登录用户
    private static User loginUser = null;

    public static UserManaage userManage;

    public static UserManaage getUserManaage() {
        if (userManage == null) {
            userManage = new UserManaage();
        }
        return userManage;

    }

    /**
     * 用户登录成功后的处理 包括 1：保存用户信息 2：保存 ，设置cookie 3：开启友盟推送 4:发出登录成功的事件
     */
    public void userLoginSuccessProcess(Context context, UserRes userRes) {

        AsyncHttpClient client = ApiHttpClient.getHttpClient();
        HttpContext httpContext = client.getHttpContext();
        CookieStore cookies = (CookieStore) httpContext
                .getAttribute(ClientContext.COOKIE_STORE);
        if (cookies != null) {
            String tmpcookies = "";
            for (Cookie c : cookies.getCookies()) {

                tmpcookies += (c.getName() + "=" + c.getValue()) + ";";
            }
//            LogCp.i(LogCp.CP, UserManaage.class + "登录成功后的 cookie:"
//                    + tmpcookies);
            // 保存cookie
            UserManaage.getUserManaage().saveCookie(context, tmpcookies);

            // 给http请求设置 cookie
            ApiHttpClient.setCookie(UserManaage.getUserManaage().getCookie(
                    AppContext.getInstance()));

            HttpConfig.sCookie = tmpcookies;
        }

        // 保存登录信息
       // UserManaage.getUserManaage().saveUserInfo(context, userRes.getMember());


        // 绑定推送

     //   PushMessageBind pushMessage = new PushMessageBind(context);
       // pushMessage.initPushMsg();

        // 发出用户登录成功的事件


        //    UserChangeEvent eventPost = new UserChangeEvent();

        //    EventBus.getDefault().post(eventPost);

    }

    /**
     * 用户注销
     * <p/>
     * 用户退出的唯 一处理点，
     */
    public void logout(Context cxt) {


         /**
        // 移除推送
        PushMessageBind bind = new PushMessageBind(cxt);
        bind.removeAliasTask();

        UIHelper.showToast("退出成功");

        UserChangeEvent event = new UserChangeEvent();
        event.setUserState(UserChangeEvent.EXIT);
        EventBus.getDefault().post(event);

        cleanCookie(cxt);

        KJDB kjdb = KJDB.create(cxt);
        kjdb.deleteByWhere(User.class, "");

        loginUser = null;

  */
    }


    /**
     * 用户是否登录
     *
     * @param cxt
     * @return
     */
    boolean isLogin = false;

    public boolean isLogin(Context cxt) {
        if (null != getLoginUser(cxt))
            return true;
        else
            return false;
        //
        // boolean isLogin = false;

        // MoFoxApi.isLogin(new AsyncHttpResponseHandler() {
        //
        // @Override
        // public void onSuccess(int arg0, String arg1) {
        // // TODO Auto-generated method stub
        // super.onSuccess(arg0, arg1);
        // LogCp.i(LogCp.CP, UserManaage.class
        // + " 用户是否登录 了，， " + arg1);
        //
        // Response loginRes = GsonUtil.jsonStrToBean(arg1, Response.class);
        //
        // if(loginRes.getIsLogin() == Response.SUCCESS)
        // {
        // isLogin = true;
        // }else
        // {
        // isLogin = false;
        // }
        //
        // }
        //
        // public void onFailure(Throwable arg0) {
        // };
        //
        // @Override
        // public void onFinish() {
        // // TODO Auto-generated method stub
        // super.onFinish();
        // }
        // });
        //
        // return isLogin;

    }

    /**
     * 保存登录用户信息
     */
    public void saveUserInfo(Context cxt, final User user) {

        KJDB kjdb = KJDB.create(cxt);
        kjdb.deleteByWhere(User.class, "");

        loginUser = user;
        kjdb.save(user);

    }

    /**
     * 调用 处要进行null 判断
     *
     * @return
     */
    public User getLoginUser(Context cxt) {

        if (loginUser != null) {
            return loginUser;
        }
        KJDB kjdb = KJDB.create(cxt);
        List<User> datas = kjdb.findAll(User.class);

        if (datas != null && datas.size() > 0) {
            loginUser = datas.get(0);
        }
        return loginUser;

    }


    /**
     * 清除保存的 cookie
     */
    public void cleanCookie(Context cxt) {
        SharePreferencesUitl.getSharePreferencesUitl(cxt).removeProperty(
                AppConfig.CONF_COOKIE);
    }

    /**
     * 取得 cookie
     *
     * @param appContext
     * @return
     */
    public String getCookie(AppContext appContext) {
        String appCookie = "";
        SharePreferencesUitl uitl = SharePreferencesUitl
                .getSharePreferencesUitl(appContext);
        appCookie = uitl.getProperty(AppConfig.CONF_COOKIE);
        return appCookie;
    }

    /**
     * 保存cookie
     *
     * @param context
     * @param cookie
     */
    public void saveCookie(Context context, String cookie) {
        SharePreferencesUitl.getSharePreferencesUitl(context).setProperty(
                AppConfig.CONF_COOKIE, cookie);
    }









}
