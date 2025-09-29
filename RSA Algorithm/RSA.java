import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger n;  // modulus
    private BigInteger e;  // public exponent
    private BigInteger d;  // private exponent
    private int bitLength = 1024;  // key size

    public RSA() {
        SecureRandom random = new SecureRandom();
        // Generate two large random primes p and q
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent e (commonly 65537)
        e = BigInteger.valueOf(65537);

        // Compute private exponent d
        d = e.modInverse(phi);
    }

    // Encrypt message (as BigInteger)
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decrypt ciphertext (as BigInteger)
    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();

        String text = "Hello RSA!";
        System.out.println("Original Text: " + text);

        // Convert string to BigInteger
        BigInteger message = new BigInteger(text.getBytes());

        // Encrypt
        BigInteger ciphertext = rsa.encrypt(message);
        System.out.println("Encrypted Text: " + ciphertext);

        // Decrypt
        BigInteger decrypted = rsa.decrypt(ciphertext);

        // Convert BigInteger back to string
        String decryptedText = new String(decrypted.toByteArray());
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
