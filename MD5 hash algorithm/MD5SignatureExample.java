import java.security.*;
import java.util.Base64;

public class MD5SignatureExample {

    public static void main(String[] args) throws Exception {

        // Step 1: Generate RSA key pair (Private and Public keys)
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        // Step 2: Message to be signed
        String message = "Authenticated message using MD5 with RSA";

        // Step 3: Create a Signature object with MD5withRSA algorithm
        Signature sign = Signature.getInstance("MD5withRSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes());

        // Step 4: Sign the message (Generate the digital signature)
        byte[] signatureBytes = sign.sign();
        String signature = Base64.getEncoder().encodeToString(signatureBytes);

        System.out.println("Original Message: " + message);
        System.out.println("Digital Signature (Base64): " + signature);

        // Step 5: Verify the digital signature using the public key
        Signature verifySig = Signature.getInstance("MD5withRSA");
        verifySig.initVerify(publicKey);
        verifySig.update(message.getBytes());

        // Step 6: Verification Result
        boolean isVerified = verifySig.verify(Base64.getDecoder().decode(signature));
        System.out.println("Signature Verified: " + isVerified);
    }
}
