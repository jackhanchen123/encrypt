package com.study.hanchen;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 1.明文
 * 2.提供原始秘钥：长度64位，8个字节
 * base64解决乱码 base64.png
 *
 */
public class DESDemo {
    public static void main(String[] args) throws Exception {

        String clearText="hanchen"; //明文

        String originKey ="12345678";//秘钥

        String cipherText =desEncrpt(clearText,originKey);

        System.out.println(cipherText);
    }

    private static String desEncrpt(String clearText, String originKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //1.实例化des加密算法工具类
        Cipher cipher =Cipher.getInstance("des");
        //int opmode 加密/解密模式
        //Key key 对原始秘钥处理之后的秘钥
        Key key=getKey(originKey);
        //2.对工具类对象初始化
        cipher.init(Cipher.ENCRYPT_MODE,  key);

        //3.用加密工具类对象cipher开始加密-》密文
        byte[] bytes = cipher.doFinal(clearText.getBytes());
//        return  new String(bytes); //gbk编码导致乱码
        return Base64.getEncoder().encodeToString(bytes);

    }

    private static Key getKey(String originKey) {

        SecretKeySpec key =new SecretKeySpec(originKey.getBytes(),"des");
        return key;

    }


}
