import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;

public class DESExample {

    // Encrypts a plaintext string using DES and returns Base64 ciphertext
    public static String encrypt(String plaintext, String key) throws Exception {
        // DES uses 8-byte (64-bit) keys
        byte[] keyBytes = key.getBytes("UTF-8");
        if (keyBytes.length != 8) {
            throw new IllegalArgumentException("DES key must be exactly 8 bytes");
        }

        // Create DES key
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        // Create and init cipher (DES in ECB mode with PKCS5 padding)
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt
        byte[] plaintextBytes = plaintext.getBytes("UTF-8");
        byte[] encryptedBytes = cipher.doFinal(plaintextBytes);

        // Return Base64 encoded ciphertext
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypts a Base64 ciphertext string using DES and returns plaintext
    public static String decrypt(String ciphertextBase64, String key) throws Exception {
        byte[] keyBytes = key.getBytes("UTF-8");
        if (keyBytes.length != 8) {
            throw new IllegalArgumentException("DES key must be exactly 8 bytes");
        }

        // Create DES key
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        // Create and init cipher
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode Base64 and decrypt
        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertextBase64);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, "UTF-8");
    }

    public static void main(String[] args) {
        try {
            // Example plaintext and key
            String plaintext = "HELLO DES TEST";
            String key = "12345678"; // 8-byte key

            System.out.println("Plaintext: " + plaintext);
            System.out.println("Key: " + key);

            // Encrypt
            String ciphertext = encrypt(plaintext, key);
            System.out.println("Ciphertext (Base64): " + ciphertext);

            // Decrypt
            String decrypted = decrypt(ciphertext, key);
            System.out.println("Decrypted: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}