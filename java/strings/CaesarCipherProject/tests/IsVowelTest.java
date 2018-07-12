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
        testTextFingerPrint();
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
    
    public void testTextFingerPrint() {
        WordPlay wp = new WordPlay();
        
        int[] letterCount = wp.textFingerPrint("Anubis");
        
        compare(letterCount, new int[] {1, 1, 1, 1, 1, 1});
    }
    
    private void compare(int[] toTest, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            assert toTest[i] == arr[i];
        }
    }

}
