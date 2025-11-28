# DES Cryptosystem – Java Implementation

This project implements the Data Encryption Standard (DES) using Java’s built-in cryptographic libraries.
It demonstrates how DES performs symmetric key encryption and decryption on text using an 8-byte key.

The example code is simple, compact, and suitable for academic projects or lab submissions.

### Overview

DES is a symmetric block cipher that:

- Uses a 56-bit effective key (8 bytes including parity bits)

- Operates on 64-bit blocks

- Uses 16 Feistel rounds internally

This implementation uses Java’s standard cryptography API:

DES/ECB/PKCS5Padding


### Features:

- Encrypt plaintext to Base64 ciphertext

- Decrypt ciphertext back to plaintext

- Key validation (must be 8 bytes)

File Structure
DESExample.java


Contains:

encrypt() – encrypts a string using DES

decrypt() – decrypts Base64 ciphertext

main() – demo with sample text and key

### How to Run

Compile:

javac DESExample.java


Run:

java DESExample

### Example Output
Plaintext: HELLO DES TEST
Key: 12345678
Ciphertext (Base64): U31CjOmewaI3m8JXS1pVOg==
Decrypted: HELLO DES TEST

### Key Requirements

DES strictly requires an 8-byte key

Example:

"12345678"
"ABCDEFGH"
"MYSECRET"

Anything else will throw an exception.

### Security Warning

DES is not secure today due to its small key size.
It can be brute-forced with modern hardware.

Use this implementation for learning only.
For real security, use AES or modern cipher suites.
