package io.github.bootystar.mybatisplus.generator.core;


/**
 * 通用返回类型
 * @author booty
 *
 */
public class Result<T> {
    public static final Integer SUCCESS = 1;
    public static final Integer FAILURE = 2;
    public static final Integer ERROR = 3;

    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failure(String msg) {
        Result<T> result = new Result<>();
        result.setCode(FAILURE);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> failure(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(FAILURE);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(ERROR);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
