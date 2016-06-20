package com.anyin.ailibuli.api;

import android.content.Context;
import android.util.Log;


import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.UpdateManagerUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import java.io.UnsupportedEncodingException;
import java.util.Locale;

import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;


/**
 *  http 请求的封装,
 */
public class ApiHttpClient {



    // 开发环境 http://deliverydev.mofox.cn/
    //                                                                                                                                             http://crmdev.mofox.cn/
    // http://apidev.mofox.cn/
    // http://businessdev.mofox.cn/
    // 梁爽 11:02:28
    // 测试环境http://deliverytest.mofox.cn:88/
    // http://crmtest.mofox.cn:88/
    // http://apitest.mofox.cn:88/
    // http://businesstest.mofox.cn:88/
    // 梁爽 11:02:56
    // 生产环境 http://delivery.mofox.cn/
    // http://crm.mofox.cn/
    // http://api.mofox.cn/
    // http://business.mofox.cn/

    //http://apidev.mofox.cn/second
    public final static String HOST = "api.mofox.cn";

    public static String IMAGE_URL = "http://"+HOST+"/second/";

    public static String API_URL = "http://"+HOST+"/second/%s";

    public static String PAY_VERSION_URL = "http://"+HOST+"/%s";




    public static final String DELETE = "DELETE";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String ENCODING = "UTF-8";
    public static AsyncHttpClient client;

    // public static final int HTTP_TIMEOUT = 15 * 1000; // 请求超时时间

    public ApiHttpClient() {
    }

    public static AsyncHttpClient getHttpClient() {
        return client;
    }

    public static void cancelAll(Context context) {
        client.cancelRequests(context, true);
    }

    public static void clearUserCookies(Context context) {
        // (new HttpClientCookieStore(context)).a();
    }

    public static void delete(String partUrl, AsyncHttpResponseHandler handler) {
        client.delete(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("DELETE ").append(partUrl).toString());
    }

    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("GET ").append(partUrl).toString());
    }

    public static void get(String partUrl, RequestParams params,
                           AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("GET ").append(partUrl).append("&")
                .append(params).toString());
    }

    public static void getOne(String partUrl, RequestParams params,
                              AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrlOne(partUrl), params, handler);
        log(new StringBuilder("GET ").append(partUrl).append("&")
                .append(params).toString());
    }
    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
        Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }

	/*public static String getAbsoluteApiUrl1(String partUrl) {
		String url = String.format(API_URL1, partUrl);
		Log.d("BASE_CLIENT", "request:" + url);
		return url;
	}*/

    public static String getAbsoluteApiUrlOne(String partUrl) {
        String url = String.format(PAY_VERSION_URL, partUrl);
        Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    public static void getDirect(String url, AsyncHttpResponseHandler handler) {
        client.get(url, handler);
        log(new StringBuilder("GET ").append(url).toString());
    }

    public static void log(String log) {
        Log.d("BaseApi", log);

    }

    public static void post(String partUrl, AsyncHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("POST ").append(partUrl).toString());
    }

    public static void post(String partUrl, RequestParams params,
                            AsyncHttpResponseHandler handler) {
        LogCp.i(LogCp.CP, ApiHttpClient.class + "client 是否为空，" + client);
        client.post(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("POST ").append(partUrl).append("&")
                .append(params).toString());

    }



    public static void postOne(String partUrl,
                               AsyncHttpResponseHandler handler){
        LogCp.i(LogCp.CP, ApiHttpClient.class + "client 是否为空，" + client);
        client.post(getAbsoluteApiUrlOne(partUrl), handler);
        log(new StringBuilder("POST ").append(partUrl).toString());
    }

    public static void postOne(String partUrl, RequestParams params,
                               AsyncHttpResponseHandler handler){
        LogCp.i(LogCp.CP, ApiHttpClient.class + "client 是否为空，" + client);
        client.post(getAbsoluteApiUrlOne(partUrl), params, handler);
        log(new StringBuilder("POST ").append(partUrl).append("&")
                .append(params).toString());
    }


    public static void postJsonUpServer(Context context, String url,
                                        String jsonStr, AsyncHttpResponseHandler responseHandler) {

        ByteArrayEntity entity;
        try {
            entity = new ByteArrayEntity(jsonStr.getBytes("UTF-8"));

            client.post(context, getAbsoluteApiUrl(url), entity,
                    "application/json", responseHandler);
            log(new StringBuilder("POST ").append(getAbsoluteApiUrl(url))
                    .toString());

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 绝对路径访问
     */
    public static void absolute_post(String absolute_url, RequestParams params,
                                     AsyncHttpResponseHandler handler) {
        client.post(absolute_url, params, handler);

    }

    /**
     * 请进度监听
     *
     * @author Tercel
     *
     */
    public interface ProgressListener {
        public void cumulative(long num);

        public void progress(int progress);
    }

    public static void postDirect(String url, RequestParams params,
                                  AsyncHttpResponseHandler handler) {
        client.post(url, params, handler);
        log(new StringBuilder("POST ").append(url).append("&").append(params)
                .toString());
    }

    public static void put(String partUrl, AsyncHttpResponseHandler handler) {
        client.put(getAbsoluteApiUrl(partUrl), handler);
        log(new StringBuilder("PUT ").append(partUrl).toString());
    }

    public static void put(String partUrl, RequestParams params,
                           AsyncHttpResponseHandler handler) {
        client.put(getAbsoluteApiUrl(partUrl), params, handler);
        log(new StringBuilder("PUT ").append(partUrl).append("&")
                .append(params).toString());
    }

    public static void setApiUrl(String apiUrl) {
        API_URL = apiUrl;
    }

    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
        client.addHeader("Accept-Language", Locale.getDefault().toString());
        client.addHeader("Host", HOST);
        client.addHeader("Connection", "Keep-Alive");
        client.addHeader("AppVersion", UpdateManagerUtil.getVersionName());
        client.getHttpClient().getParams()
                .setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

       // setUserAgent(getUserAgent(AppContext.getInstance()));
    }

    public static void setUserAgent(String userAgent) {
        client.setUserAgent(userAgent);
    }

    public static void setCookie(String cookie) {
        client.addHeader("Cookie", cookie);
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
