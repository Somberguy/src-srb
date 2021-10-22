package com.tanbo.srb.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanbo
 * @create 2021-10-18-11:41
 */
@Data
public class R {

    private Integer code;
    private String message;
    private Map<String,Object> result = new HashMap<>();

    private R(){

    }

    public static R OK(){

        R r = new R();

        r.setCode(ResponseEnum.SUCCESS.getCode());

        r.setMessage(ResponseEnum.SUCCESS.getMessage());

        return r;
    }

    public static R error(){

        R r = new R();

        r.setCode(ResponseEnum.FAIL.getCode());

        r.setMessage(ResponseEnum.FAIL.getMessage());

        return r;
    }

    public static R setResult(ResponseEnum responseEnum){

        R r = new R();

        r.setCode(responseEnum.getCode());

        r.setMessage(responseEnum.getMessage());

        return r;
    }

    public R setData(String desc,Object data){

        this.result.put(desc,data);

        return this;
    }

    public R resetMessage(String message){

        this.setMessage(message);

        return this;
    }

    public R resetCode(Integer code){

        this.setCode(code);

        return this;
    }

    public R resetResult(Map<String,Object> map){

        this.result = map;

        return this;
    }
}
