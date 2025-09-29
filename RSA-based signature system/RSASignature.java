import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class RSASignature {
    private BigInteger n;  // modulus
    private BigInteger e;  // public exponent
    private BigInteger d;  // private exponent
    private int bitLength = 1024;

    public RSASignature() throws Exception {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(65537);
        d = e.modInverse(phi);
    }

    // Sign the message by hashing and then encrypting the hash with private key
    public BigInteger sign(byte[] message) throws Exception {
        byte[] hash = sha256(message);
        BigInteger hashInt = new BigInteger(1, hash);
        return hashInt.modPow(d, n);
    }

    // Verify signature by decrypting with public key and comparing hash
    public boolean verify(byte[] message, BigInteger signature) throws Exception {
        byte[] hash = sha256(message);
        BigInteger hashInt = new BigInteger(1, hash);

        BigInteger decryptedHash = signature.modPow(e, n);
        return hashInt.equals(decryptedHash);
    }

    // SHA-256 hashing
    private byte[] sha256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    }

    public static void main(String[] args) throws Exception {
        RSASignature rsa = new RSASignature();

        String message = "This is a message to sign";
        System.out.println("Original message: " + message);

        BigInteger signature = rsa.sign(message.getBytes());
        System.out.println("Signature: " + signature.toString(16));  // hex output

        boolean isValid = rsa.verify(message.getBytes(), signature);
        System.out.println("Signature valid? " + isValid);

        // Test verification with tampered message
        String fakeMessage = "This is a tampered message";
        boolean isFakeValid = rsa.verify(fakeMessage.getBytes(), signature);
        System.out.println("Signature valid for tampered message? " + isFakeValid);
    }
}
