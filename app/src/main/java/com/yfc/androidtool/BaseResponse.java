package com.yfc.androidtool;

import java.io.Serializable;


public class BaseResponse implements Serializable {

    /**
     * flag : false
     * code : 20001
     * message : 登录失败
     * data : null
     */

    public boolean flag = false;
    public int code = 0;
    public String message = " ";
}
