package hello.cipher.lea.ecb;

import hello.cipher.lea.crypto.BlockCipher;
import hello.cipher.lea.crypto.BlockCipherMode;
import hello.cipher.lea.crypto.padding.PKCS5Padding;
import hello.cipher.lea.crypto.symm.LEA;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptBench {

    private static String KEY = "rYz7AQ22NWHneBg3";
    private static BlockCipherMode cipher = new LEA.ECB();
    private static final int ITERATIONS = 1000000;

    private static String encrypt(byte[] dataBytes) {
        byte[] encryptedData = cipher.doFinal(dataBytes);
        String result = Base64.getEncoder().encodeToString(encryptedData);
        cipher.reset();
        return result;
    }

    public static void main(String[] args) {
        String data = "01012341234";

        cipher.init(BlockCipher.Mode.ENCRYPT, KEY.getBytes(StandardCharsets.UTF_8));
        cipher.setPadding(new PKCS5Padding(16));

        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        long encryptionStartTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            encrypt(dataBytes);
        }

        long encryptionEndTime = System.nanoTime();
        long encryptionTime = encryptionEndTime - encryptionStartTime;

        System.out.println("encrypt --> " + encrypt(dataBytes));
        System.out.println("encryptionTime --> " + (encryptionTime / ITERATIONS) + "ms");
    }
}
