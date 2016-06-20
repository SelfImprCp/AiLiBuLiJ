package com.anyin.ailibuli.event;


import com.anyin.ailibuli.utils.InternetUtil;

/**
 *  网络 状态改变的事件 ，包括连接，断开
 * @author Administrator
 *
 */
public class IntentChangeEvent  extends BaseEvent{


   private boolean intentLine = false;

   public IntentChangeEvent() {
       intentLine =  InternetUtil.hasInternetConnected();
   }

   public boolean getHasInternet()
   {
       return intentLine;
   }

}
