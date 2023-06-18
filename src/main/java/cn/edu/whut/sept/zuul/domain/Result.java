package cn.edu.whut.sept.zuul.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private boolean flag;
    private Object data;
    private String message;

    public static Result  success(Object data){
        Result r = new Result();
        r.setFlag(true);
        r.setData(data);
        return r;
    }

    public static Result fail(String message){
        Result r = new Result();
        r.setMessage(message);
        r.setFlag(false);
        return r;
    }
}
