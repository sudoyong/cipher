package hello.cipher.aes.ecb;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptBench {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String KEY = "rYz7AQ22NWHneBg3";
    private static final int ITERATIONS = 1000000;

    public static String encrypt(Cipher cipher, byte[] originBytes) throws Exception {
        byte[] encryptedBytes = cipher.doFinal(originBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String originalText = "01012341234";
        byte[] originBytes = originalText.getBytes();

        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        long startTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            encrypt(cipher, originBytes);
        }

        long endTime = System.nanoTime();
        long encryptionTime = endTime - startTime;

        System.out.println("encrypt --> " + encrypt(cipher, originBytes));
        System.out.println("encryptionTime --> " + (encryptionTime / ITERATIONS) + "ms");
    }
}
