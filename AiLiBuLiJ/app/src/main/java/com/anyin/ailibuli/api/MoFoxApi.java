package com.anyin.ailibuli.api;


import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


/**
 * API接口
 * 这是接口类
 *
 * @author Administrator
 */
public class MoFoxApi {



//    private static MoFoxApi instance;
//
//    /**
//     * 单一实例
//     */
//    public static MoFoxApi getMoFoxApi() {
//        if (instance == null) {
//            instance = new MoFoxApi();
//        }
//        return instance;
//    }

    // ====================================访问方式================================================

    /**
     * 访问服务器
     * post
     * @param
     * @param url
     * @param
     */
    public static void serverPost(RequestParams params, String url,
                            AsyncHttpResponseHandler handler) {


        ApiHttpClient.post(url, params, handler);

    }

    /**
     * get
     * @param url
     * @param handler
     */
    public static void serverGet(String url, MyResponseHandler handler) {
        ApiHttpClient.get(url, handler);
    }


     /**
    * 向服务器上传json数据
      *
     * @param context
     * @param jsonStr
      */
     public static void postJsonUpServer(Context context, String url, String jsonStr,
                                  MyResponseHandler handler) {
        ApiHttpClient.postJsonUpServer(context, url, jsonStr, handler);
    }

    // =====================================接口======================================

    /**
     * @param oldPwd
     * @param newPwd
     * @param handler
     */
    public static void chanPWD(String oldPwd, String newPwd,
                               AsyncHttpResponseHandler handler) {
        RequestParams params = new RequestParams();
        params.put("oldPwd", oldPwd);
        params.put("newPwd", newPwd);

        String uppwdUrl =     "uppassword";

        serverPost  (params,uppwdUrl,    handler);
    }





    /**
     * 动态广场
     *
     * @param page
     * @param cityId
     * @param handler
     */
    private static String DYNAMIC_URL = "dynamic/";

    public static void getMainFocusList(String page, String cityId,
                                        AsyncHttpResponseHandler handler)

    {

        RequestParams params = new RequestParams();
        params.put("page", page);
        params.put("cityId", cityId);
        String all_attentin_url = DYNAMIC_URL + "dynamiclist";
        serverPost(params,all_attentin_url,handler);
    }



}
