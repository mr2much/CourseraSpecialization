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
        System.out.println("OK");
    }
    
    public void testSwappingAlphabetByKey() {
        CaesarCipher cipher = new CaesarCipher(17);
        
        String shiftedAlphabet = cipher.getAlphabet();
        
        assert shiftedAlphabet.equals("RSTUVWXYZABCDEFGHIJKLMNOPQ") : 
            "Value: " + shiftedAlphabet;
    }
}
