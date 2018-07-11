package tests;
/**
 * Write a description of AlphabetSwappingTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import src.CaesarCipher;

public class AlphabetSwappingTest {
    public void performTests() {
        // testSwappingAlphabetByKey();
        testEncription();
        testEncryptionWithTwoKeys();
        System.out.println("OK");
    }
    
    // public void testSwappingAlphabetByKey() {
        // CaesarCipher cipher = new CaesarCipher(17);
        
        // String shiftedAlphabet = cipher.getAlphabet();
        
        // assert shiftedAlphabet.equals("RSTUVWXYZABCDEFGHIJKLMNOPQ") : 
            // "Value: " + shiftedAlphabet;
    // }
    
    public void testEncription() {
        CaesarCipher cipher = new CaesarCipher();
        
        String encrypted = cipher.encrypt("I AM", 17);
        
        assert encrypted.equals("Z RD") : "Value: " + encrypted;
        
        cipher = new CaesarCipher();
        
        assert "T UTM".equals(cipher.encrypt("A BAT", 19));
        assert "Tgnubl".equals(cipher.encrypt("Anubis", 19));
        assert cipher.encrypt("FIRST LEGION ATTACK EAST FLANK!", 23)
            .equals("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!");
            
        assert cipher.encrypt("First Legion", 23)
            .equals("Cfopq Ibdflk");
            
        assert cipher.encrypt("First Legion", 17)
            .equals("Wzijk Cvxzfe");
    }
    
    public void testEncryptionWithTwoKeys() {
        CaesarCipher cipher = new CaesarCipher();
        
        assert cipher.encrypt("First Legion", 23, 17)
            .equals("Czojq Ivdzle");
    }
}
