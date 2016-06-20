package com.anyin.ailibuli.utils;

import android.widget.EditText;

import com.anyin.ailibuli.R;


/**
 * Created by lin on 6/15/2016.
 *  登录工具类,
 *
 *
 */
public class LoginUtil {



    /**
     *  是否可以去登录,
     *
     *
     * @param
     */
    private boolean checkLogin(EditText uMobile, EditText uPassWord) {

        if (!InternetUtil.hasInternetConnected()) {
            UIHelper.showToastShort(R.string.tip_network_error);
            return false;
        }

        if (!checkLoginName(uMobile)) {

            return false;
        }

        if (!checkLoginPass(uPassWord)) {

            return false;
        }
        return true;
    }









    /**
     * 传入EditTExt 不能为null
     * 如果合法,返回true
     * 如果不合法,会有提示,并获取焦点
     * 验证手机号
     * @param mEtName
     * @return
     */



    private boolean checkLoginName(EditText mEtName  ) {

         String str = mEtName.getText().toString();
        if (StringUtils.isEmpty(str)) {
            UIHelper.showToastShort(R.string.please_name);

            mEtName.requestFocus();
            return false;
        } else {

            if (StringUtils.isPhoneValid(str)) {
                return true;
            } else {
                UIHelper.showToastShort(R.string.please_correct_phone);
                mEtName.requestFocus();
                return false;
            }



        }

    }



    /**
     * 验证密码
     *
     *  根据不同业务需要写判断
     * @param
     * @return
     */

    private boolean checkLoginPass(EditText mEtName) {
         String str = mEtName.getText().toString();

        if (StringUtils.isEmpty(str)) {
            UIHelper.showToastShort(R.string.please_password);
            mEtName.requestFocus();
            return false;
        } else {

            return true;

        }

    }




}
