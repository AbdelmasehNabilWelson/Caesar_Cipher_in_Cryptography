# Caesar_Cipher_in_Cryptography
A project shows you how to implement the Caesar Cipher encryption and decryption in java. And how to Implement it with known and unknown keys for different languages.

# Project Resources
[The data used to test these classes at the official site of duke to learn](https://www.dukelearntoprogram.com//course3/files.php).<br>
[The documentation of the methods and packages used throughout the project](https://www.dukelearntoprogram.com/course3/doc/).<br>

# [CaesarCipher class](./CaesarCipher.java)
Contains methods to implement the Caesar Cipher encryption algorithm with known key.<br>
* The methods of the CaesarCipher class
    * CaesarCipher: Uses the Caesar Cipher algorithms to encrypt messages.
    * encryptLetter: to encrypt one single character.
    * decryptLetter: to encrypt decrypt one single character.
    * transformLetter: transforms the letter to the according letter based on the value of the key.
    * transform: transforms a whole message.
    * encrypt: encrypts the message needed to be encrypted.
    * decrypt: decrypts the message that have been encrypted.
# [CaesarCracker class](./CaesarCracker.java)
Contains methods to implement the Caesar Cipher encryption algorithm with unknown key
statistical analysis.<br>
* The methods of the CaesarCracker class
    * countLetters: counts the occurrences of each letter in the encrypted message.
    * maxIndex: finds the index of the maximum letter in the message.
    * getKey: finds the key used to encrypt the message according.
    * decrypt: decrypts the message that have been encrypted.

# [VigenereCipher class](./VigenereCipher.java)
Contains methods to implement the Vigenere Cipher encryption algorithm that uses a keyword to encrypt a message and how decrypt this message.<br>
* The methods of the VigenereCipher class
    * encrypt and decrypt: same as in CaesarCipher but with different implementation and algorithms.

# [VigenereBreaker class](./VigenereBreaker.java)
Contains methods to break the Vigenere Cipher encryption algorithm.<br>



