package com.anyin.ailibuli.db;

import android.content.Context;

import org.kymjs.kjframe.KJDB;

/**
 * 数据库的工具类
 * Created by Administrator on 2016/5/21.
 */


public class MyDbUtil {




    public KJDB getKJDB(Context context) {
        KJDB kjdb = KJDB.create(context);
        return kjdb;
    }


}
