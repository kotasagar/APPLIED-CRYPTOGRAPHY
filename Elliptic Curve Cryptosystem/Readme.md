# Elliptic Curve Cryptosystem (ECC) – Java Implementation

This project provides a clear, educational implementation of an Elliptic Curve Cryptosystem using Java.
It demonstrates how elliptic-curve arithmetic works and includes a working ECDH key exchange example.
The code uses a small toy curve for learning purposes only.

### Overview

The program implements:

- Finite-field arithmetic

- Point addition

- Point doubling

- Scalar multiplication (double-and-add)

- Key pair generation

- ECDH shared secret derivation

Everything is contained in one file for simplicity: ECCExample.java

Curve Definition

The project uses a simple demonstrative curve:

y² = x³ + ax + b   (mod p)

p = 751
a = 750
b = 1
G = (0, 1)


These values are intentionally small to make the math traceable.

File Structure
ECCExample.java


Main parts inside:

ECPoint – point representation

add() – point addition

doublePoint() – point doubling

scalarMultiply() – k·P using double-and-add

generatePrivateKey() – random private key generator

main() – Alice–Bob key exchange demo

How to Run

Compile:

javac ECCExample.java


Run:

java ECCExample


The output will show:

Curve details

Alice and Bob’s private/public keys

Shared ECC point computed on both sides

A shared key derived from the x-coordinate

Example Output (short)
Curve: y^2 = x^3 + 750x + 1 mod 751
Base point G: Point(0, 1)

Alice private key: 247
Alice public key:  Point(387, 223)

Bob private key:   105
Bob public key:    Point(425, 384)

Shared (Alice): Point(155, 153)
Shared (Bob):   Point(155, 153)

ECDH successful: shared secrets match.
Derived shared key: 155

Security Warning

This implementation is only meant for academic use.
It does not provide real-world security because:

The curve is too small

No hashing or key-derivation is used

No side-channel protections

Arithmetic is not hardened

For production-grade ECC, use:

Java EC APIs

BouncyCastle

NIST P-256, secp256k1, Curve25519, etc.
