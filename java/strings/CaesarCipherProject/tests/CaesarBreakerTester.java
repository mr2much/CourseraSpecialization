package tests;


/**
 * Write a description of CaesarBreakerTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import src.CaesarBreaker;
import src.CaesarCipher;

public class CaesarBreakerTester {
    public void performTests() {
        testDecryptionWithOneKey();
        testHalfOfString();
        getKeyShouldReturnKeyUsed();
        testDecryptionWithTwoKeys();
        System.out.println("OK");
    }
    
    public void testDecryptionWithOneKey() {
        CaesarCipher cc = new CaesarCipher();
        
        String original = "I LOVE SEX";
        
        String encrypted = cc.encrypt(original, 16);

        CaesarBreaker cb = new CaesarBreaker();
        
        String decrypted = cb.decryptOneKey(encrypted);
        
        assert decrypted.equals(original) : "Value " + decrypted;
    }
    
    public void testHalfOfString() {
        CaesarBreaker cb = new CaesarBreaker();
        
        String original = "Qbkm Zgis";
        
        assert cb.halfOfString(original, 0).equals("Qk gs");
        assert cb.halfOfString(original, 1).equals("bmZi");
    }
    
    public void testDecryptionWithTwoKeys() {
        CaesarCipher cc = new CaesarCipher();
        
        String original = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        
        String encrypted = cc.encrypt(original, 23, 2);
        
        assert encrypted.equals("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu");
        
        CaesarBreaker cb = new CaesarBreaker();
        
        assert cb.decryptTwoKeys(encrypted).equals(original);
    }
    
    public void getKeyShouldReturnKeyUsed() {
        CaesarCipher cc = new CaesarCipher();
        
        String original = "Letter e";
        
        String encrypted = cc.encrypt(original, 19);
        
        CaesarBreaker cb = new CaesarBreaker();
        
        int key = cb.getKey(encrypted);
        
        assert key == 19;
        
        encrypted = cc.encrypt(original, 11);
        
        assert cb.getKey(encrypted) == 11;
    }
}
