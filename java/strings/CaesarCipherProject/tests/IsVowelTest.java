package tests;


/**
 * Write a description of IsVowelTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import src.WordPlay;

public class IsVowelTest {
    public void performTests() {
        shouldReturnTrueIfCharIsVowel();
        shouldReplaceVowelsWithChar();
        testEmphasize();
        System.out.println("OK");
    }
    
    public void shouldReturnTrueIfCharIsVowel() {
        WordPlay wp = new WordPlay();
        
        assert wp.isVowel('a');
        
        assert !wp.isVowel('F');
    }
    
    public void shouldReplaceVowelsWithChar() {
        WordPlay wp = new WordPlay();
        
        String replacedString = wp.replaceVowels("Hello World", '*');
        
        assert replacedString.equals("H*ll* W*rld");
    }
    
    public void testEmphasize() {
        WordPlay wp = new WordPlay();
        
        String emphasized = wp.emphasize("dna ctgaaactga", 'a');
        
        assert emphasized.equals("dn* ctg+*+ctg+");
        
        emphasized = wp.emphasize("Mary Bella Abracadabra", 'a');
        
        assert emphasized.equals("M+ry Bell+ +br*c*d*br+") : 
            "Value " + emphasized;
    }

}
