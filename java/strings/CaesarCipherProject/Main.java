
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import src.CaesarCipher;
import src.CaesarBreaker;
import src.WordLengths;

public class Main {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        
        String message = "At noon be in the conference room with your hat on " +
            "for a surprise party. YELL LOUD!";
            
        System.out.println(cipher.encrypt(message, 15));
        
        System.out.println(cipher.encrypt(message, 8, 21));
        
        System.out.println("Most common word length of file romeo.txt = " + mostCommonWordLengthOfFile("res/romeo.txt"));
        System.out.println("Most common word length of file lotsOfWords.txt = " + mostCommonWordLengthOfFile("res/lotsOfWords.txt"));
        System.out.println(decryptMessageWithTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
        
        CaesarBreaker cb = new CaesarBreaker();
        
        System.out.println("Decrypted using CaesarBreaker: " + cb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        
        System.out.println("Showing the decrypted file mysteryTwoKeysPractice.txt");
        System.out.println(decryptFileWithTwoKeys("res/mysteryTwoKeysPractice.txt"));
        // FileResource fr = new FileResource();
        
        // StringBuilder encrypted = new StringBuilder();
        
        // for(String s : fr.lines()) {
            // encrypted.append(cipher.encrypt(s, 17));
            // encrypted.append("\n");
        // }
        
        // System.out.println(encrypted.toString());
    }
    
    public static int mostCommonWordLengthOfFile(String filename) {
        FileResource fr = new FileResource(filename);
        WordLengths wl = new WordLengths();
        
        int[] counts = new int[31];
        
        wl.countWordLengths(fr, counts);
        
        return wl.indexOfMax(counts);
    }
    
    public static String decryptMessageWithTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        
        return cc.encrypt(encrypted, 26 - 2, 26 - 20);
    }
    
    public static String decryptFileWithTwoKeys(String filename) {
        FileResource fr = new FileResource(filename);
        
        CaesarBreaker cb = new CaesarBreaker();
        
        return cb.decryptTwoKeys(fr.asString());        
    }
}
