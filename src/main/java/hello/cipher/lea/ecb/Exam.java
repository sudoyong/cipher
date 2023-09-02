package hello.cipher.lea.ecb;

import hello.cipher.lea.crypto.BlockCipher;
import hello.cipher.lea.crypto.BlockCipherMode;
import hello.cipher.lea.crypto.padding.PKCS5Padding;
import hello.cipher.lea.crypto.symm.LEA;

import java.nio.charset.StandardCharsets;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

public class Exam {

    private static String KEY = "rYz7AQ22NWHneBg3";
    private static BlockCipherMode cipher = new LEA.ECB();

    private static String encrypt(String data) {
        cipher.init(BlockCipher.Mode.ENCRYPT, KEY.getBytes(StandardCharsets.UTF_8));
        cipher.setPadding(new PKCS5Padding(16));

        byte[] dataBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        String result = getEncoder().encodeToString(dataBytes);
        cipher.reset();

        return result;
    }

    private static String decrypt(String encryptData) {
        cipher.init(BlockCipher.Mode.DECRYPT, KEY.getBytes(StandardCharsets.UTF_8));
        cipher.setPadding(new PKCS5Padding(16));

        byte[] dataBytes = getDecoder().decode(encryptData);
        byte[] decryptedData = cipher.doFinal(dataBytes);

        String result = new String(decryptedData, StandardCharsets.UTF_8);
        cipher.reset();

        return result;
    }

    public static void main(String[] args) {
        String data = "01012341234";
        System.out.println("data --> " + data);
        String encryptData = encrypt(data);
        System.out.println("encryptData --> " + encryptData);
        String decryptData = decrypt(encryptData);
        System.out.println("decryptData --> " + decryptData);
    }
}
