package hello.cipher.aes.ecb;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Exam {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String KEY = "rYz7AQ22NWHneBg3";

    private static Cipher getCipher(int mode) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(mode, keySpec);
        return cipher;
    }

    public static String encrypt(String originalText) throws Exception {
        byte[] plainTextBytes = originalText.getBytes();
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
        byte[] encryptedBytes = cipher.doFinal(plainTextBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String originalText = "01012341234";
        System.out.println("originalText --> " + originalText);
        String encryptedText = encrypt(originalText);
        System.out.println("encryptedText --> " + encryptedText);
        String decryptedText = decrypt(encryptedText);
        System.out.println("decryptedText --> " + decryptedText);
    }
}
