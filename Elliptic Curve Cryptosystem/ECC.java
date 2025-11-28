import java.math.BigInteger;
import java.security.SecureRandom;

public class ECC {

    // Simple elliptic curve over a prime field: y^2 = x^3 + ax + b (mod p)
    // Toy parameters (NOT secure!)
    private static final BigInteger P = new BigInteger("751");                 // prime modulus
    private static final BigInteger A = P.subtract(BigInteger.ONE);            // a = -1 mod p  => 750
    private static final BigInteger B = BigInteger.ONE;                        // b = 1

    // Base point G on the curve (chosen to be valid for this toy curve)
    private static final BigInteger Gx = BigInteger.ZERO;
    private static final BigInteger Gy = BigInteger.ONE;

    private static final SecureRandom random = new SecureRandom();

    // Point on the curve
    public static class ECPoint {
        public final BigInteger x;
        public final BigInteger y;
        public final boolean infinity;

        public ECPoint(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
            this.infinity = false;
        }

        private ECPoint(boolean infinity) {
            this.x = BigInteger.ZERO;
            this.y = BigInteger.ZERO;
            this.infinity = infinity;
        }

        public static ECPoint infinity() {
            return new ECPoint(true);
        }

        @Override
        public String toString() {
            if (infinity) return "Point(INFINITY)";
            return "Point(" + x + ", " + y + ")";
        }
    }

    // Modular reduction [0, p-1]
    private static BigInteger mod(BigInteger x) {
        x = x.mod(P);
        if (x.signum() < 0) {
            x = x.add(P);
        }
        return x;
    }

    // Modular inverse using Extended Euclidean Algorithm
    private static BigInteger modInverse(BigInteger k) {
        k = mod(k);
        if (k.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Inverse of zero does not exist");
        }
        return k.modInverse(P);
    }

    // Elliptic curve point addition
    public static ECPoint add(ECPoint P1, ECPoint P2) {
        if (P1.infinity) return P2;
        if (P2.infinity) return P1;

        // P1 = (x1, y1), P2 = (x2, y2)
        BigInteger x1 = P1.x;
        BigInteger y1 = P1.y;
        BigInteger x2 = P2.x;
        BigInteger y2 = P2.y;

        // If x1 == x2 and y1 == -y2 -> point at infinity
        if (x1.equals(x2)) {
            BigInteger y2Neg = mod(y2.negate());
            if (y1.equals(y2Neg)) {
                return ECPoint.infinity();
            }
            // else it's point doubling
            return doublePoint(P1);
        }

        // P1 != P2: lambda = (y2 - y1) / (x2 - x1)
        BigInteger lambda = mod(y2.subtract(y1)).multiply(modInverse(x2.subtract(x1)));
        lambda = mod(lambda);

        BigInteger x3 = mod(lambda.multiply(lambda).subtract(x1).subtract(x2));
        BigInteger y3 = mod(lambda.multiply(x1.subtract(x3)).subtract(y1));

        return new ECPoint(x3, y3);
    }

    // Elliptic curve point doubling
    public static ECPoint doublePoint(ECPoint P1) {
        if (P1.infinity) return P1;

        BigInteger x1 = P1.x;
        BigInteger y1 = P1.y;

        if (y1.equals(BigInteger.ZERO)) {
            // Tangent is vertical -> point at infinity
            return ECPoint.infinity();
        }

        // lambda = (3*x1^2 + a) / (2*y1)
        BigInteger threeX2 = x1.multiply(x1).multiply(BigInteger.valueOf(3));
        BigInteger numerator = mod(threeX2.add(A));
        BigInteger denominator = modInverse(y1.multiply(BigInteger.TWO));
        BigInteger lambda = mod(numerator.multiply(denominator));

        BigInteger x3 = mod(lambda.multiply(lambda).subtract(x1.multiply(BigInteger.TWO)));
        BigInteger y3 = mod(lambda.multiply(x1.subtract(x3)).subtract(y1));

        return new ECPoint(x3, y3);
    }

    // Scalar multiplication: k * P using double-and-add
    public static ECPoint scalarMultiply(BigInteger k, ECPoint P) {
        ECPoint result = ECPoint.infinity();
        ECPoint addend = P;

        BigInteger n = k;

        while (n.signum() > 0) {
            if (n.testBit(0)) {  // if the lowest bit is 1
                result = add(result, addend);
            }
            addend = doublePoint(addend);
            n = n.shiftRight(1);
        }

        return result;
    }

    // Generate a random private key in [1, P-1]
    public static BigInteger generatePrivateKey() {
        BigInteger priv;
        do {
            priv = new BigInteger(P.bitLength(), random);
        } while (priv.equals(BigInteger.ZERO) || priv.compareTo(P) >= 0);
        return priv;
    }

    public static void main(String[] args) {
        // Base point G
        ECPoint G = new ECPoint(Gx, Gy);

        // === Alice key pair ===
        BigInteger alicePriv = generatePrivateKey();
        ECPoint alicePub = scalarMultiply(alicePriv, G);

        // === Bob key pair ===
        BigInteger bobPriv = generatePrivateKey();
        ECPoint bobPub = scalarMultiply(bobPriv, G);

        System.out.println("Curve: y^2 = x^3 + " + A + "x + " + B + " mod " + P);
        System.out.println("Base point G: " + G);
        System.out.println();

        System.out.println("Alice private key: " + alicePriv);
        System.out.println("Alice public key:  " + alicePub);
        System.out.println();

        System.out.println("Bob private key:   " + bobPriv);
        System.out.println("Bob public key:    " + bobPub);
        System.out.println();

        // === ECDH key agreement ===
        // Alice computes shared = alicePriv * BobPub
        ECPoint sharedAlice = scalarMultiply(alicePriv, bobPub);

        // Bob computes shared = bobPriv * AlicePub
        ECPoint sharedBob = scalarMultiply(bobPriv, alicePub);

        System.out.println("Shared (Alice): " + sharedAlice);
        System.out.println("Shared (Bob):   " + sharedBob);

        if (!sharedAlice.infinity && !sharedBob.infinity && 
            sharedAlice.x.equals(sharedBob.x) && sharedAlice.y.equals(sharedBob.y)) {
            System.out.println("\nECDH successful: shared secrets match.");
            // Example: derive a simple integer key from x-coordinate
            BigInteger sharedKey = sharedAlice.x;
            System.out.println("Derived shared key (x-coordinate): " + sharedKey);
        } else {
            System.out.println("\nError: shared secrets do NOT match.");
        }
    }
}
