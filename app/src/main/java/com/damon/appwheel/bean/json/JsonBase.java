package com.damon.appwheel.bean.json;

/**
 * Created by data on 2017/9/28.
 */

public class JsonBase {

    //子类里面用gsonformat会跳过code和message。很智能，只生成result的bean或list

    /**
     * code : 200
     * message : 请求成功
     * result : [{"name":"黄瓜","model":"3","count":"10"},{"name":"香蕉","model":"1","count":"1"},{"name":"黄瓜","model":"5","count":"50"},{"name":"西红柿","model":"2","count":"30"}]
     */

    public int code;
    public String message;
    //result 可能是json、jsonArray ，所以成员变量可能生成的是ResultBean，或者List<ResultBean>
    //子类去覆写 result的成员变量及getter、setter

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
