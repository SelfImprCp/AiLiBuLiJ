package com.anyin.ailibuli.bean;

import android.support.v4.app.Fragment;

/**
 * Created by Jerry on 2016/6/24.
 */
public class HeaderBeanV2 {

     public String text;
     public String tag;
     public Fragment content;

  public HeaderBeanV2( String text,String tag,Fragment content)
  {
       this.text = text;
      this.tag  = tag;
      this.content = content;




  }


}
