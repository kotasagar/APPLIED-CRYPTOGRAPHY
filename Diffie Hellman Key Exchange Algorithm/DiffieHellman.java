import java.math.BigInteger;
import java.util.Random;

public class DiffieHellman {

    public static void main(String[] args) {
        // Prime modulus (p) and primitive root (g)
        BigInteger p = new BigInteger("23"); // public prime number
        BigInteger g = new BigInteger("5");  // public primitive root

        // Private keys: chosen randomly by Alice and Bob
        BigInteger a = new BigInteger(10, new Random()); // Alice's private key
        BigInteger b = new BigInteger(10, new Random()); // Bob's private key

        // Public keys
        BigInteger A = g.modPow(a, p); // Alice's public key
        BigInteger B = g.modPow(b, p); // Bob's public key

        // Shared secret calculation
        BigInteger sharedSecretAlice = B.modPow(a, p);
        BigInteger sharedSecretBob = A.modPow(b, p);

        // Display results
        System.out.println("Public parameters:");
        System.out.println("  Prime (p): " + p);
        System.out.println("  Primitive Root (g): " + g);
        
        System.out.println("\nPrivate keys:");
        System.out.println("  Alice's private key (a): " + a);
        System.out.println("  Bob's private key (b): " + b);
        
        System.out.println("\nPublic keys:");
        System.out.println("  Alice's public key (A): " + A);
        System.out.println("  Bob's public key (B): " + B);
        
        System.out.println("\nShared secrets:");
        System.out.println("  Alice's computed secret: " + sharedSecretAlice);
        System.out.println("  Bob's computed secret: " + sharedSecretBob);

        if (sharedSecretAlice.equals(sharedSecretBob)) {
            System.out.println("\nKey exchange successful! Shared secret established.");
        } else {
            System.out.println("\nError: Shared secrets do not match.");
        }
    }
}
