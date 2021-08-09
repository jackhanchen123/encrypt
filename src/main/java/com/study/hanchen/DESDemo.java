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

        String clearText="hanchen"; //明文plainText
        String originKey ="12345678";//秘钥key
        //DES算法
        String cipherText =desEncrpt(clearText,originKey);

        System.out.println(cipherText);

        //解密
        String plainText=  desDecrypt(cipherText,originKey);
        System.out.println(plainText);
    }

    /**
     * 解密
     * @param cipherText
     * @param originKey
     * @return
     */
    private static String desDecrypt(String cipherText, String originKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher des = Cipher.getInstance("des");
        Key key = getKey(originKey);
        des.init(Cipher.DECRYPT_MODE,key);
        byte[] decode = Base64.getDecoder().decode(cipherText);
        byte[] bytes = des.doFinal(decode);
        return new String(bytes);
    }

    /**
     * 都是对byte进行一些数学运算
     * @param clearText
     * @param originKey
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private static String desEncrpt(String clearText, String originKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //1.Cipher--->>>实例化des加密算法工具类//Algorithm算法
        Cipher cipher =Cipher.getInstance("des");
        //原始秘钥转换成key
        Key key=getKey(originKey);
        //2.对工具类对象初始化
        // int opmode 加密/解密模式
        // Key key 对原始秘钥处理之后的秘钥
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
