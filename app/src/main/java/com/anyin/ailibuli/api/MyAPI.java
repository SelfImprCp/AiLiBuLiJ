package com.anyin.ailibuli.api;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;

/**
 * Created by Jerry on 2016/6/23.
 */
public class MyAPI {

    /**
     * API接口
     * 这是接口类
     *
     * @author Administrator
     */


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
         *
         * @param
         * @param url
         * @param
         */
        public static void serverPost(HttpParams params, String url,
                                      HttpCallBack handler) {


            ApiHttpClient.post(url, params, handler);

        }

        /**
         * get
         *
         * @param url
         * @param handler
         */
        public static void serverGet(String url, HttpCallBack handler) {
            ApiHttpClient.get(url, handler);
        }


        /**
         * 向服务器上传json数据
         *
         * @param
         * @param jsonStr
         */
        public static void postJsonUpServer(String url, String jsonStr,
                                            HttpCallBack handler) {
            ApiHttpClient.postJsonUpServer(url, jsonStr, handler);
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

            String uppwdUrl = "uppassword";

            //   serverPost  (params,uppwdUrl,    handler);
        }


        /**
         * @param UserName
         * @param UserPassword
         * @param handler
         */
        public static void Login(String UserName, String UserPassword,
                                 HttpCallBack handler) {




            HttpParams params = new HttpParams();
            params.put("UserName", UserName);
            params.put("UserPassword", UserPassword);

            String loginUrl = "User/Login";

            serverPost(params, loginUrl, handler);
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

//        RequestParams params = new RequestParams();
//        params.put("page", page);
//        params.put("cityId", cityId);
//        String all_attentin_url = DYNAMIC_URL + "dynamiclist";
//        serverPost(params,all_attentin_url,handler);
//
//

        }



}
