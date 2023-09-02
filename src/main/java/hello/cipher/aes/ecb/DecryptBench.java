package hello.cipher.aes.ecb;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DecryptBench {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String KEY = "rYz7AQ22NWHneBg3";
    private static final int ITERATIONS = 1000000;

    public static String decrypt(Cipher cipher, byte[] encryptedBytes) throws Exception {
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String encryptedText = "Hxy80QyxySnR+PQzne/e9w==";

        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

        long startTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            decrypt(cipher, encryptedBytes);
        }

        long endTime = System.nanoTime();
        long decryptionTime = endTime - startTime;

        System.out.println("decrypt --> " + decrypt(cipher, encryptedBytes));
        System.out.println("decryptionTime --> " + (decryptionTime / ITERATIONS) + "ms");
    }
}
