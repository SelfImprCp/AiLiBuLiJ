package com.anyin.ailibuli.api;

import android.content.Context;
import android.util.Log;


import com.anyin.ailibuli.manage.UserManaage;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.UpdateManagerUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;


import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;


/**
 * http 请求的封装,
 */
public class ApiHttpClient {


    // https://www.ailibuli.cn/api/User/Login


    public final static String HOST = "www.ailibuli.cn";


    public static String API_URL = "https://" + HOST + "/api/%s";



    public static KJHttp client;

    public ApiHttpClient() {
    }



    /**
     *
     */
    public static void initHttp() {
        // 初始化网络请求
        client   = new KJHttp( );




    }





    public static KJHttp getHttpClient() {
        return client;
    }

    /**
     *
     * @param partUrl
     * @param handler
     */

    public static void get(String partUrl, HttpCallBack handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);


        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求方式：GET 无参"

        );

        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );

    }

    /**
     *
     * @param partUrl
     * @param params
     * @param handler
     */

    public static void get(String partUrl, HttpParams params,
                           HttpCallBack handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);


        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求方式：GET 有参"

        );
        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求参数 ："
                + params.getUrlParams().toString()

        );
        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );

    }

    /**
     * @param partUrl
     * @param params
     * @param handler
     */
    public static void post(String partUrl, HttpParams params,
                            HttpCallBack handler) {

        client.post(getAbsoluteApiUrl(partUrl)  , params, handler);

        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求方式：POST"

        );
        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求参数 ："
                + params.getUrlParams().toString()

        );
        LogCp.d(LogCp.CP, ApiHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl)

        );



    }



    /**
     * post 向服务器提交 json
     *
     * @param url
     * @param jsonStr
     * @param responseHandler
     */
    public static void postJsonUpServer(String url,
                                        String jsonStr, HttpCallBack responseHandler) {


        HttpParams params = new HttpParams();


        //这里传递json字符串，(JSONObject可以调用toString方法转换)
        LogCp.i(LogCp.CP, ApiHttpClient.class + "上传的 json :" + jsonStr);
        params.putJsonParams(jsonStr);


        client.jsonPost(url, params, responseHandler);


    }



    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
      //  Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }


    public static String getApiUrl() {
        return API_URL;
    }



    public static void setHttpClient(KJHttp c) {
        client = c;
//        client.
//        client.addHeader("Accept-Language", Locale.getDefault().toString());
//        client.addHeader("Host", HOST);
//        client.addHeader("Connection", "Keep-Alive");
//        client.addHeader("AppVersion", UpdateManagerUtil.getVersionName());
//        client.getHttpClient().getParams()
//                .setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

        // setUserAgent(getUserAgent(AppContext.getInstance()));
    }




    /**
     * 获得请求的服务端数据的userAgent
     *
     * @param appContext
     * @return
     */
//    public static String getUserAgent(AppContext appContext) {
//        StringBuilder ua = new StringBuilder("MoFox.NET");
//        ua.append('/' + TDevice.getPackageInfo(appContext).versionName + '_'
//                + TDevice.getPackageInfo(appContext).versionCode);// app版本信息
//        ua.append("/Android");// 手机系统平台
//        ua.append("/" + android.os.Build.VERSION.RELEASE);// 手机系统版本
//        ua.append("/" + android.os.Build.MODEL); // 手机型号
//        ua.append("/" + appContext.getAppId());// 客户端唯一标识
//        return ua.toString();
//    }


}
