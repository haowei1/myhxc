package com.hdy.myhxc.entity;

/**
 * @author m760384371
 * @date 2019/8/23
 */
public class ResultData {

    private int code = 0;
    private int count;
    private String msg;
    private Object data;

    public ResultData(int code, int count, String msg, Object data) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.data = data;
    }

    public ResultData() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", count=" + count +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
