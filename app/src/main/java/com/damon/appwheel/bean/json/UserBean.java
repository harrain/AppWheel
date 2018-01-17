package com.damon.appwheel.bean.json;

/**
 * Created by stephen on 2017/7/25.
 */

public class UserBean {

    int code;
    String message;

    private String userName;
    private String nick;
    private String password;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

