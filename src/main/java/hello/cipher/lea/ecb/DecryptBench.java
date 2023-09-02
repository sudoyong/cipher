package hello.cipher.lea.ecb;

import hello.cipher.lea.crypto.BlockCipher;
import hello.cipher.lea.crypto.BlockCipherMode;
import hello.cipher.lea.crypto.padding.PKCS5Padding;
import hello.cipher.lea.crypto.symm.LEA;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DecryptBench {

    private static String KEY = "rYz7AQ22NWHneBg3";
    private static BlockCipherMode cipher = new LEA.ECB();
    private static final int ITERATIONS = 1000000;

    private static String decrypt(byte[] encryptedData) {
        byte[] decryptedData = cipher.doFinal(encryptedData);
        String result = new String(decryptedData, StandardCharsets.UTF_8);
        cipher.reset();
        return result;
    }

    public static void main(String[] args) {
        String encryptedData = "ymfMU+ILWOLJDONt0TA6tw==";

        cipher.init(BlockCipher.Mode.DECRYPT, KEY.getBytes(StandardCharsets.UTF_8));
        cipher.setPadding(new PKCS5Padding(16));

        byte[] dataBytes = Base64.getDecoder().decode(encryptedData);

        long decryptionStartTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            decrypt(dataBytes);
        }

        long decryptionEndTime = System.nanoTime();
        long decryptionTime = decryptionEndTime - decryptionStartTime;

        System.out.println("decrypt --> " + decrypt(dataBytes));
        System.out.println("decryptionTime --> " + (decryptionTime / ITERATIONS) + "ms");
    }
}
