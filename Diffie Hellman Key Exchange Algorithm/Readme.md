# 🔐 Diffie–Hellman Key Exchange Algorithm

This repository contains a simple and clear **implementation of the Diffie–Hellman key exchange algorithm**, which is one of the earliest practical examples of public key exchange in cryptography.  
It allows two parties to securely generate a shared secret key over an insecure communication channel.

---

## 📘 Table of Contents
- [Overview](#overview)
- [How It Works](#how-it-works)
- [Implementation Details](#implementation-details)
- [Installation & Usage](#installation--usage)
- [Example Output](#example-output)
- [Mathematical Explanation](#mathematical-explanation)
- [References](#references)

---

## 🧩 Overview

The **Diffie–Hellman (DH)** algorithm enables two users to establish a shared secret key that can be used for further encryption of messages.  
Unlike symmetric algorithms, DH doesn’t require prior sharing of a secret key — it’s derived securely during the exchange process.

---

## ⚙️ How It Works

1. Both parties agree on a **large prime number `p`** and a **primitive root modulo `p` (generator `g`)**.
2. Each party chooses a **private key** (kept secret).
3. Each computes a **public key** using:

### PublicKey = (g ^ PrivateKey) mod p
4. The two parties exchange their public keys.
5. Each side computes the **shared secret key** using

### SharedKey = (OtherPublicKey ^ PrivateKey) mod p
6. Both sides now share the same secret key without ever transmitting it directly.

---

## 🧠 Implementation Details

- **Language:** Python (but adaptable to C++, Java, or JavaScript)
- **Algorithm Used:** Modular exponentiation for efficiency
- **Security:** Demonstration only (small primes used for simplicity)

---

## 🖥️ Installation & Usage

### 1️⃣ Clone the repository
```bash
git clone https://github.com/kotasagar/APPLIED-CRYPTOGRAPHY
cd Diffie Hellman Key Exchange Algorithm

### 2️⃣ Run the program
Java DiffieHellman.java

### 💡 Example Output

Public parameters:
  Prime (p): 23
  Primitive Root (g): 5

Private keys:
  Alice's private key (a): 517
  Bob's private key (b): 843

Public keys:
  Alice's public key (A): 4
  Bob's public key (B): 20

Shared secrets:
  Alice's computed secret: 2
  Bob's computed secret: 2

Key exchange successful! Shared secret established.

### 🧮 Mathematical Explanation
Given:

Prime number p
Generator g
Private keys a and b

Then:
A = (g^a) mod p
B = (g^b) mod p

After exchanging A and B:
SharedKey = (B^a) mod p = (A^b) mod p

Both computations yield the same shared key due to properties of modular arithmetic.

### 📚 References

Whitfield Diffie and Martin Hellman, "New Directions in Cryptography", IEEE Transactions on Information Theory, 1976.
Wikipedia – Diffie–Hellman Key Exchange
CryptoBook: Diffie–Hellman Explained
