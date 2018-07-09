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
        testSwappingAlphabetByKey();
        testEncription();
        System.out.println("OK");
    }
    
    public void testSwappingAlphabetByKey() {
        CaesarCipher cipher = new CaesarCipher(17);
        
        String shiftedAlphabet = cipher.getAlphabet();
        
        assert shiftedAlphabet.equals("RSTUVWXYZABCDEFGHIJKLMNOPQ") : 
            "Value: " + shiftedAlphabet;
    }
    
    public void testEncription() {
        CaesarCipher cipher = new CaesarCipher(17);
        
        String encrypted = cipher.encrypt("I AM");
        
        assert encrypted.equals("Z RD") : "Value: " + encrypted;
        
        cipher = new CaesarCipher(19);       
        
        assert "T UTM".equals(cipher.encrypt("A BAT"));
    }
}
