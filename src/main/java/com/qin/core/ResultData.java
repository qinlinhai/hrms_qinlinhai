package com.qin.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultData implements Serializable {
    private Type code; //状态吗
    private String msg; //错误消息
//    private Object data; //数据信息

 /*   public class Data{
        Data success;
        Data warn;
        Data error;
    }*/

    public ResultData(Type code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //内部的枚举类型：以常量的形式，见名知意的方式告诉大家变量的作用；枚举的值一般都是特定的值（7天），上中下晚上
    //枚举本质也是一个类型：反编译能看到，地位等同于类
    public enum Type {
        SUCCESS(200),  //Type类型的实例, 1、先定义构造方法2 ，然后根据构造方法进行实例化
        WARN(300),
        ERROR(500);

        //私有字段
        private int value;

        //构造方法
        Type(int code) {
            this.value=code;
        }
    }

    /**
     * 返回一个默认的成功消息
     * @return
     */
    public static ResultData success() {
        return new ResultData(Type.SUCCESS,"处理成功");
    }

    /**
     * 返回一个成功的消息
     * @param msg
     * @return
     */
    public static ResultData success(String msg) {
        return new ResultData(Type.SUCCESS,msg);
    }


    /**
     * 返回一个指定的错误消息
     * @param msg
     * @return
     */
    public static ResultData error(String msg) {
        return new ResultData(Type.ERROR,msg);
    }

}
