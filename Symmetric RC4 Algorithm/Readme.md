# 🔐 RC4 Symmetric Cipher in Java

This Java project demonstrates how to use the **RC4 (Rivest Cipher 4)** algorithm for simple encryption and decryption of text using Java's Cryptography Architecture.

> ⚠️ **Note**: RC4 is deprecated in most modern applications due to vulnerabilities. It is included here strictly for educational or legacy purposes.

---

## 📌 Features

- Encrypts a plaintext string using the RC4 stream cipher.
- Decrypts the encrypted string back to the original plaintext.
- Uses Base64 encoding for readable encrypted output.

---

## 🛠️ Requirements

- Java 8 or higher
- A Java Development Environment (IDE or terminal)

---

## 🚀 How to Run

### 1. Compile the Java file:

#### bash
javac RC4.java

### 2. Run the program:
java RC4

### 🔍 Sample Output:
Original Text: Hello RC4 Encryption!
Encrypted Text: nfZyHkzgl14GXPAPNH64mw==
Decrypted Text: Hello RC4 Encryption!


Encrypted output will differ every run, depending on key and system factors.

### 🧠 How It Works

Cipher.getInstance("RC4"): Initializes the RC4 cipher.

SecretKeySpec: Uses a user-defined key for encryption and decryption.

The plaintext is encrypted into bytes and then encoded with Base64.

The encrypted Base64 string is decrypted back into original text.

### ⚠️ Security Warning

RC4 has known vulnerabilities and is not considered secure for modern cryptographic uses. Use AES or ChaCha20 for secure implementations.

### 📁 File Structure
RC4.java       // Java source code
README.md             // Project documentation

### 📚 References

Oracle Java Cryptography Architecture (JCA)

Wikipedia - RC4
