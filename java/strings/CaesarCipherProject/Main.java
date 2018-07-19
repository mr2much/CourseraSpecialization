
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import src.CaesarCipher;
import src.CaesarCipherTwo;
import src.CaesarBreaker;
import src.WordLengths;

public class Main {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher(15);
        
        String message = "At noon be in the conference room with your hat on " +
            "for a surprise party. YELL LOUD!";
            
        System.out.println(cipher.encrypt(message));
        
        // System.out.println(cipher.encrypt(message, 8, 21));
        System.out.println("Most common word length of file romeo.txt = " + mostCommonWordLengthOfFile("res/romeo.txt"));
        System.out.println("Most common word length of file lotsOfWords.txt = " + mostCommonWordLengthOfFile("res/lotsOfWords.txt"));
        System.out.println(decryptMessageWithTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
        
        CaesarBreaker cb = new CaesarBreaker();
        
        System.out.println("Decrypted using CaesarBreaker: " + cb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        
        System.out.println("Showing the decrypted file mysteryTwoKeysPractice.txt");
        System.out.println(decryptFileWithTwoKeys("res/mysteryTwoKeysPractice.txt"));
        
        testCaesarCipherTwo();
        
        System.out.println("Final Quizz");
        
        message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        
        System.out.println(encryptSingleKey(message, 15));
        System.out.println(encryptTwoKeys(message, 21, 8));
        
        System.out.println("Most common word length of file errors.txt = " + mostCommonWordLengthOfFile("res/errors.txt"));
        System.out.println("Most common word length of file manywords.txt = " + mostCommonWordLengthOfFile("res/manywords.txt"));
        
        CaesarCipherTwo cct = new CaesarCipherTwo(14, 24);
        System.out.println("Decrypted Quizz: " + 
            cct.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
            
        System.out.println("Breaking the Quizz: " +
            cct.breakCaesarCipher(
                "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
                
        System.out.println(
            decryptFileWithTwoKeys("res/mysteryTwoKeysQuiz.txt"));
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
        CaesarCipherTwo cc = new CaesarCipherTwo(2, 20);
        
        return cc.decrypt(encrypted);
    }
    
    public static String decryptFileWithTwoKeys(String filename) {
        FileResource fr = new FileResource(filename);
        
        CaesarCipherTwo cct = new CaesarCipherTwo(1, 2);
        
        return cct.breakCaesarCipher(fr.asString());        
    }
    
    public static void testCaesarCipherTwo() {
        FileResource fr = new FileResource("res/smallHamlet.txt");
        
        CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
        
        String encrypted = cct.encrypt(fr.asString());
        
        System.out.println("Encrypted with Two Keys\n" + encrypted);
        
        System.out.println("\nDecrypted Using Own Encryptor\n" + 
            cct.decrypt(encrypted));
        System.out.println("\nDouble Encryption Broken\n" + 
            cct.breakCaesarCipher(encrypted));
        
    }
    
    public static String encryptSingleKey(String message, int key) {
        CaesarCipher cc = new CaesarCipher(key);
        
        return cc.encrypt(message);
    }
    
    public static String encryptTwoKeys(String message, int key1, int key2) {
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        
        return cct.encrypt(message);
    }
}
