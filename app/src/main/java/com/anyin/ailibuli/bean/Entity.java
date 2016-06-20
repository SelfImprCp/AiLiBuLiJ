package com.anyin.ailibuli.bean;



import java.io.Serializable;


/**
 * 实体类
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */

public abstract class Entity implements Serializable {


    public int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
