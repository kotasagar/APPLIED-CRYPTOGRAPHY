# RSA Algorithm in Java

A simple Java implementation of the RSA public-key cryptosystem. This project demonstrates how to generate RSA key pairs, encrypt plain text messages, and decrypt ciphertext securely using modular arithmetic and large prime numbers.

## Table of Contents
- [Background](#background)
- [Installation](#installation)
- [Usage](#usage)
- [Repository Structure](#repository-structure)
- [Contributing](#contributing)

## Background

RSA (Rivest-Shamir-Adleman) is an **asymmetric encryption algorithm** that uses a public key for encryption and a private key for decryption. RSA enables secure data transmission, digital signing, and secure key exchange by leveraging the properties of prime numbers and modular exponentiation.

## Installation

1. Clone this repository:
git clone https://github.com/kotasagar/APPLIED-CRYPTOGRAPHY/RSA algorithm.git

2. Compile the Java file:

## Usage

Run the program:
java RSA

**Sample Output:**
Original Text: Hello RSA!
Encrypted Text: 857382748392... (large integer)
Decrypted Text: Hello RSA!


### How It Works

- Generates two large random primes `p` and `q`.
- Calculates modulus `n = p * q` and totient `phi = (p-1)(q-1)`.
- Chooses a public exponent `e` and finds the private key exponent `d`.
- Encrypts and decrypts messages using modular exponentiation:
  - Encryption: \( c = m^e \mod n \)
  - Decryption: \( m = c^d \mod n \)

> **Note:** This implementation uses a key size of 1024 bits for demonstration purposes. For real-world security, use at least 2048 or 4096 bits.

## Repository Structure

#### APPLIED-CRYPTOGRAPHY/RSA algorithm/
##### ├── RSA.java # Main class implementing RSA functionality
##### ├── README.md # Project documentation (this file)



## Contributing

Contributions are welcome! Please fork the repository, create a pull request with your improvements (bug fixes, documentation updates, or added features).  
Submit issues for bugs or questions.
