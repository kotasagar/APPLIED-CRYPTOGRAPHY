# RSA Based Signature System in Java

This project demonstrates an implementation of a digital signature system using the RSA algorithm in Java. It covers generating RSA key pairs, creating digital signatures with a private key, and verifying those signatures with the corresponding public key.

## Table of Contents
- [Background](#background)
- [Installation](#installation)
- [Usage](#usage)
- [How It Works](#how-it-works)
- [Repository Structure](#repository-structure)
- [Contributing](#contributing)

## Background

Digital signatures provide authentication, integrity, and non-repudiation for digital messages. Using RSA for signatures means signing a hash of the message with a private key, and verification uses the public key to confirm the signature matches the message.

## Installation

1. Clone the repository:
git clone https://github.com/kotasagar/APPLIED-CRYPTOGRAPHY.git

cd rsa-signature-java

2. Compile the Java source file(s):

javac RSASignature.java


## Usage

Run the program:
java RSASignature


Expected output will show the original message, the generated signature in hexadecimal format, and the verification result (true or false).

## How It Works

- Generates a 2048-bit RSA key pair (public and private keys).
- Creates a SHA-256 hash of the input message.
- Signs the hash using the RSA private key (`SHA256withRSA`).
- Verifies the signature using the RSA public key.

## Repository Structure

#### rsa-signature-java/
##### ├── RSASignature.java # Main class implementing RSA signature and verification
##### ├── README.md # Project documentation (this file)


## Contributing

Contributions are welcome! Feel free to fork the project and submit pull requests with improvements or bug fixes.

