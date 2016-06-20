package com.anyin.ailibuli.res;


import com.anyin.ailibuli.bean.User;

/**
 *  用户登录 返回
 * @author Administrator
 *
 */
public  class UserRes extends Response{

    private User member;

   public User getMember() {
       return member;
   }

   public void setMember(User member) {
       this.member = member;
   }





}
