package com.anyin.ailibuli.bean;


import org.kymjs.kjframe.database.annotate.Id;

/**
 * 用户信息
 *
 * @author Administrator
 */


public class User extends Entity  {

    /**
     * 每个实体中都 要有的属性，id ,kjDB,往数据库里存的时候拿不到父类的
     */

    public int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    // 手机号
    private String mobile;

    // 头像
    private String avatar;

    // 昵称
    private String nickname;


    // 性别,1=男，2=女
    private int gender;



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
