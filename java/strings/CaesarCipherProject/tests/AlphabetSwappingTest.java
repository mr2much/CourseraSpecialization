package tests;
/**
 * Write a description of AlphabetSwappingTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import src.CaesarCipher;
import src.CaesarCipherTwo;

public class AlphabetSwappingTest {
    public void performTests() {
        // testSwappingAlphabetByKey();
        testEncription();
        testEncryptionWithTwoKeys();
        testDecryption();
        System.out.println("OK");
    }
    
    // public void testSwappingAlphabetByKey() {
        // CaesarCipher cipher = new CaesarCipher(17);
        
        // String shiftedAlphabet = cipher.getAlphabet();
        
        // assert shiftedAlphabet.equals("RSTUVWXYZABCDEFGHIJKLMNOPQ") : 
            // "Value: " + shiftedAlphabet;
    // }
    
    public void testEncription() {
        CaesarCipher cipher = new CaesarCipher(17);
        
        String encrypted = cipher.encrypt("I AM");
        
        assert encrypted.equals("Z RD") : "Value: " + encrypted;
        
        cipher = new CaesarCipher(19);
        
        assert "T UTM".equals(cipher.encrypt("A BAT"));
        assert "Tgnubl".equals(cipher.encrypt("Anubis"));
        
        cipher = new CaesarCipher(23);
        assert cipher.encrypt("FIRST LEGION ATTACK EAST FLANK!")
            .equals("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!");
            
        assert cipher.encrypt("First Legion")
            .equals("Cfopq Ibdflk");
            
        cipher = new CaesarCipher(17);
        assert cipher.encrypt("First Legion")
            .equals("Wzijk Cvxzfe");
    }
    
    public void testEncryptionWithTwoKeys() {
        CaesarCipherTwo cipher = new CaesarCipherTwo(23, 17);
                
        String encrypted = cipher.encrypt("First Legion");
        
        assert encrypted.equals("Czojq Ivdzle") : "Value " + encrypted;
    }
    
    public void testDecryption() {
        CaesarCipher cipher = new CaesarCipher(10);
        String original = "Anubis is the enemy";
        String encrypted = cipher.encrypt(original);
        
        String decrypted = cipher.decrypt(encrypted);
        
        assert decrypted.equals(original) : "Value " + decrypted;
    }
}
