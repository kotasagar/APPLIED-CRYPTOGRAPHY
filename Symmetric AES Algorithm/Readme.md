# AES Encryption/Decryption in Java

This project demonstrates how to use **AES (Advanced Encryption Standard)** for encrypting and decrypting text using Java's `javax.crypto` package.

## 🔐 What is AES?

AES (Advanced Encryption Standard) is a symmetric encryption algorithm widely used across the globe for securing data. Symmetric means the same key is used for both encryption and decryption.

Key features of AES:
- **Block cipher**: Encrypts data in fixed-size blocks (128 bits).
- **Key sizes**: 128, 192, or 256 bits (this project uses 128-bit keys).
- **Modes of operation**: ECB, CBC, etc. This example uses **ECB** (Electronic Codebook), which is simple but not suitable for sensitive data in real applications due to its deterministic nature.

---

## 📜 How the Code Works

### 🔧 AES.java

This Java class provides the full encryption and decryption flow using AES.

### ✅ Features:

1. **Key Generation**  
   ```java
   KeyGenerator keyGen = KeyGenerator.getInstance("AES");
   keyGen.init(128); // 128-bit key
   SecretKey key = keyGen.generateKey();
This generates a new AES key every time the program runs.

2. **Encryption**

java
Copy code
Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, key);
byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
The plaintext is encrypted using the AES key and the ECB mode with PKCS5 padding. The result is then Base64 encoded for easy display.

3. **Decryption**

java
Copy code
cipher.init(Cipher.DECRYPT_MODE, key);
byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
The encrypted Base64 string is decoded and decrypted using the same AES key.

4. **User Interaction**
The program asks the user to input a string, then:

Encrypts it

Prints the encrypted version

Decrypts it back

Prints the decrypted version

### 🧪 Example Output
bash
Copy code
Enter the text to encrypt:
Hello, AES!
Encrypted: ud7VmLbepqpEGgzZiwxuZQ==
Decrypted: Hello, AES!

### 📁 How to Run
1. **Compile the code:**

bash
Copy code
javac AES.java

2. **Run the program:**

bash
Copy code
java AES
Make sure you have Java installed (JDK 8+ recommended).

## 📚 References
Java Cryptography Architecture (JCA)

AES - Wikipedia
