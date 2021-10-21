package com.codeswyft.fielder.util

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Slf4j
@CompileStatic
class PasswordUtil {

    // for hashing passwords
    static final String hash(final String password, final String salt) {
        return byteToBase64(getHash(10, password, base64ToByte(salt)))
    }

    // for checking passwords
    static final boolean check(final String password, final String salt, String hashedPassword) {
        return hash(password, salt) == hashedPassword
    }

    private static byte[] getHash(int iterationNb, String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance('SHA-1')
        digest.reset()
        digest.update(salt)
        byte[] input = digest.digest(password.getBytes('UTF-8'))
        for (int i = 0; i < iterationNb; i++) {
            digest.reset()
            input = digest.digest(input)
        }
        return input
    }

    private static byte[] base64ToByte(String data) throws IOException {
        return Base64.getDecoder().decode(data)
    }

    private static String byteToBase64(byte[] data) {
        Base64.Encoder encoder = Base64.getEncoder()
        return encoder.encodeToString(data)
    }

    static boolean checkPasswordStrength(String password) {
        return password && password.length() >= 8 && password =~ /(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$#!%*?&]){8,}/
    }

}
