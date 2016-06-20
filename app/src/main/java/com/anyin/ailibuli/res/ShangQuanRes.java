package com.anyin.ailibuli.res;

import com.anyin.ailibuli.bean.ShangQuanBean;

import java.util.List;



/**
 *  主页商圈返回
 * @author Administrator
 *
 */
public class ShangQuanRes extends Response{

    private List<ShangQuanBean> result;

   public List<ShangQuanBean> getResult() {
       return result;
   }

   public void setResult(List<ShangQuanBean> result) {
       this.result = result;
   }


}
