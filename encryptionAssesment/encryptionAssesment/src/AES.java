import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    // data declarations
    private static byte[] k;
    private static SecretKeySpec sKey;

    /**
     * SecretKey generator method ----
     * Instantiate key gen - specify AES alogrithm from .crypto class
     * Initializes this key generator for a certain keysize of 'i'.
     * instantiate SecretKey variable and assign generated key
     * Return Key value
     * end of method ----
     */

    public static void setKey(String myKey) {
        MessageDigest sha1 = null;
        try {
            k = myKey.getBytes("UTF-8");
            sha1 = MessageDigest.getInstance("SHA-1");
            k = sha1.digest(k);
            k = Arrays.copyOf(k, 16);
            sKey = new SecretKeySpec(k, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /*
     * Encrypt String Method ----
     * Instantiate cipher variable - pass algorithm string
     * Initialize cipher variable in encryption mode - pass secret key and initialization vector
     * Instantiate byte array - encode cipherText
     * return value 
     * end of method ----
     */

     // instantiate encryption method
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}