package com.qifenqian.bms.common.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultData extends HashMap<String, Object> implements Serializable {
    public ResultData() {
        put("code", 200);
    }

    public static ResultData error() {
        return error(500, "系统繁忙");
    }

    public static ResultData error(String msg) {
        return error(500, msg);
    }

    public static ResultData error(int code, String msg) {
        ResultData resultData = new ResultData();
        resultData.put("code", code);
        resultData.put("msg", msg);
        return resultData;
    }

    public static ResultData error(int code, String msg,Object object) {
        ResultData resultData = new ResultData();
        resultData.put("code", code);
        resultData.put("msg", msg);
        resultData.put("data", object);
        return resultData;
    }

    public static ResultData success( String msg,Object object) {
        ResultData resultData = new ResultData();
        resultData.put("code", 200);
        resultData.put("msg", msg);
        resultData.put("data", object);
        return resultData;
    }

    public static ResultData success(int code, String msg) {
        ResultData resultData = new ResultData();
        resultData.put("code", code);
        resultData.put("msg", msg);
        return resultData;
    }

    public static ResultData success(String msg) {
        return success(200, msg);
    }

    public static ResultData success(Object obj) {
        ResultData resultData = new ResultData();
        resultData.put("code", 200);
        resultData.put("msg", "操作成功");
//        resultData.put("msg", "");
        resultData.put("data", obj);
        return resultData;
    }

    public static ResultData success(Map<String, Object> map) {
        ResultData resultData = new ResultData();
        resultData.putAll(map);
        return resultData;
    }

    public static ResultData success() {
        ResultData resultData = new ResultData();
        resultData.put("msg", "操作成功");
        resultData.put("code", 200);
        return resultData;
    }

    public ResultData put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Object get(Object key) {
        return super.get(key);
    }
}
