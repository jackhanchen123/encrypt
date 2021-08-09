package com.study.hanchen;


import java.nio.charset.StandardCharsets;

/**
 * 凯撒密码加密  统一向右移动进行加密 同一把key加解密--*--->>>>对称加密算法（DES AES算法）
 * <p>
 * encrypt 加密
 * decrypt 解密
 */
public class CaesarDemo {

    public static void main(String[] args) {
        //1.明文
        String plainText = "i love you";
        //2.加密规则将字母向右移动3位
        int key = 3;
        //3.加密
        String cipher = encrypt(plainText, key);
        System.out.println(cipher);

        //4.解密
        String decrypt=decrypt(cipher,key);
        System.out.println(decrypt);
    }

    /**
     * 解密
     * @param cipher
     * @param key
     * @return
     */
    private static String decrypt(String cipher, int key) {
        char[] chars = cipher.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i]= (char) (chars[i]-3);
        }
        return String.valueOf(chars);
    }

    /**
     * 加密方法
     *
     * @param plainText
     * @param key
     * @return
     */
    private static String encrypt(String plainText, int key) {
//        byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
//        for (byte b:bytes){
//            b=(byte) (b+key);
//        }
//
//        return bytes.toString();

 //string char byte 关系以及转换

        char[] chars = plainText.toCharArray();
//        for (char c : chars) {
//            c = (char) (c + key);
//        }
        for (int i = 0; i < chars.length; i++) {
            //ASCII码表：a(97)  b(98)  可直接加
            chars[i]= (char) (chars[i]+key);
        }
        return new String(chars);
    }




}
