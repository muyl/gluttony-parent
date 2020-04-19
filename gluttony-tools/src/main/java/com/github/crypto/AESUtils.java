package com.github.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author 拓仲 on 2020/4/19
 */
public class AESUtils {
    /**
     * 生成密钥
     */
    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);  //192 256
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * AES 加密
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * AES 解密
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }






    /**
     * 加密:先AES再base64
     *
     * @param plainText 解密字符串
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText, String passphrase) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(passphrase));
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    /**
     * 解密:先base64再AES
     *
     * @param cipherText 解密字符串
     * @return
     * @throws Exception
     */
    public static String decrypt(String cipherText, String passphrase) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(passphrase));
        byte[] plain64text = Base64.getDecoder().decode(cipherText);
        byte[] plaintext = cipher.doFinal(plain64text);
        return new String(plaintext, StandardCharsets.UTF_8);
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(String password) {
        try {
            byte[] bytesOfMessage = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = null;
            md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(bytesOfMessage);
            return new SecretKeySpec(bytes, "AES");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }


    public static void main(String[] args) throws Exception {
        String result = AESUtils.encrypt("11222", "");
        System.out.println("11222加密后结果：" + result + ",密钥：" + "");
        System.out.println(AESUtils.decrypt(result, ""));
        System.out.println("============");
        String result1 = AESUtils.encrypt("11222", "111");
        System.out.println("11222加密后结果：" + result1 + ",密钥：" + "111");
        System.out.println(AESUtils.decrypt(result1, "111"));
    }
}
