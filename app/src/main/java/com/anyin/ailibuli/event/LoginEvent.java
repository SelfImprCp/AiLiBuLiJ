package com.anyin.ailibuli.event;

/**
 *  用户登录 的事件
 * Created by Jerry on 2016/6/23.
 */
public class LoginEvent extends BaseEvent {

     private String loginUserNmae ;


    public String getLoginUserNmae() {
        return loginUserNmae;
    }

    public void setLoginUserNmae(String loginUserNmae) {
        this.loginUserNmae = loginUserNmae;
    }

}
