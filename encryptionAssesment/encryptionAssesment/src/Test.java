import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author Joel Parfitt - 23020948
 */

public class Test {

    public static void main(String[] args) throws Exception {

        final String secretKey = "ssshhhhhhhhhhh!!!!";
        final String secretKey2 = "ssshhhhhhhhhhh!!!!";

        String originalString = "howtodoinjava.com";
        String encryptedString = AES.encrypt(originalString, secretKey);
        String decryptedString = AES.decrypt(encryptedString, secretKey2);

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);

    }}
