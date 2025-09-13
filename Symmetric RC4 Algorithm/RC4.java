import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class RC4 {

    // Encrypt or Decrypt using RC4
    public static String rc4(String input, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("RC4");  // RC4 is also known as ARCFOUR
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "RC4");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] outputBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(outputBytes);
    }

    public static String rc4Decrypt(String input, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("RC4");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "RC4");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String key = "secretkey"; // RC4 key can be up to 256 bits (32 bytes)
        String originalText = "Hello RC4 Encryption!";
        System.out.println("Original Text: " + originalText);

        String encryptedText = rc4(originalText, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = rc4Decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
