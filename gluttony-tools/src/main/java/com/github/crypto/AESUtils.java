package com.github.crypto;

import javax.crypto.Cipher;
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
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(bytesOfMessage);
            return new SecretKeySpec(bytes, "AES");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }
}
