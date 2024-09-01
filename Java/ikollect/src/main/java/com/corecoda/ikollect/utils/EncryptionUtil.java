package com.corecoda.ikollect.utils;

import com.corecoda.ikollect.settings.ApplicationSettings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class EncryptionUtil {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final ApplicationSettings applicationSettings;

    public String aesEncryptWithVector(Object toBeEncrypted, boolean deserializeAgain) throws Exception {
        String plaintext;

        if (!deserializeAgain) {
            plaintext = toBeEncrypted.toString();
        } else {
            plaintext = objectMapper.writeValueAsString(toBeEncrypted);
        }
        // Encrypt the string to an array of bytes.
        byte[] encrypted = encryptStringToBytes_Aes(plaintext, applicationSettings.getSecretKey(), applicationSettings.getIV());
        return byteArrayToString(encrypted);
    }

    private byte[] encryptStringToBytes_Aes(String plainText, String key, String iv) throws Exception {
        if (plainText == null || plainText.length() <= 0) {
            throw new IllegalArgumentException("plainText cannot be null or empty");
        }

        byte[] encrypted;

        // Generate key and IV
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

        // Create cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Encrypt the data
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             CipherOutputStream cipherStream = new CipherOutputStream(byteStream, cipher);
             OutputStreamWriter writer = new OutputStreamWriter(cipherStream, StandardCharsets.UTF_8)) {

            writer.write(plainText);
            writer.flush();
            cipherStream.flush();
            encrypted = byteStream.toByteArray();
        }

        return encrypted;
    }

    private String byteArrayToString(byte[] ba) {
        StringBuilder hex = new StringBuilder(ba.length * 2);
        for (byte b : ba) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    public String aesDecryptWithVector(String ciphertext) throws Exception {
        try {
            // Create a new instance of the Aes class
            var key = applicationSettings.getSecretKey().getBytes(StandardCharsets.UTF_8);
            var iv = applicationSettings.getIV().getBytes(StandardCharsets.UTF_8);

           return decryptStringFromBytesAes(ciphertext, key, iv);
        } catch (Exception ex) {
            throw new Exception("Decryption error: " + ex.getMessage(), ex);
        }
    }
    private String decryptStringFromBytesAes(String cipherText, byte[] key, byte[] iv) throws Exception {
        // Check arguments
        if (cipherText == null || cipherText.length() <= 0) {
            throw new IllegalArgumentException("cipherText");
        }
        if (key == null || key.length <= 0) {
            throw new IllegalArgumentException("Key");
        }
        if (iv == null || iv.length <= 0) {
            throw new IllegalArgumentException("IV");
        }

        // Declare the string used to hold the decrypted text
        String plaintext = null;

        // Create a SecretKeySpec and IvParameterSpec
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Create the AES Cipher with CBC mode and PKCS5Padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Convert the hexadecimal cipher text to a byte array
        byte[] cipherBytes = hexadecimalStringToByteArray(cipherText);

        // Create streams used for decryption
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(cipherBytes);
             CipherInputStream cipherStream = new CipherInputStream(byteStream, cipher);
             InputStreamReader inputStreamReader = new InputStreamReader(cipherStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            // Read the decrypted bytes and place them in a string
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            plaintext = sb.toString();
        }

        return plaintext;
    }

    private byte[] hexadecimalStringToByteArray(String input) {
        int outputLength = input.length() / 2;
        byte[] output = new byte[outputLength];

        try (StringReader sr = new StringReader(input)) {
            for (int i = 0; i < outputLength; i++) {
                char[] chars = new char[2];
                chars[0] = (char) sr.read();
                chars[1] = (char) sr.read();
                output[i] = (byte) Integer.parseInt(new String(chars), 16);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception if necessary
        }

        return output;
    }
}
