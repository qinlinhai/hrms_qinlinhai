package com.qin.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class PassWord {
    private static String algorithmName="MD5";//加密算法的名字
    private static int hashIterations=1024;//迭代次数

    /**
     *
     * @param oldPassword 原始密码
     * @param salt 加密的盐值
     * @return 加密后的密码
     */
    public static String encPassword(String oldPassword,Object salt) {
        SimpleHash simpleHash=new SimpleHash(algorithmName,oldPassword,salt,hashIterations);
        String str = simpleHash.toBase64();
//        String str = simpleHash.toHex();
        return str;
//        System.out.println(simpleHash.toString());
    }

    public static void main(String[] args) {
        String s = encPassword("123456", "张麻子");
        System.out.println(s);
    }
}
