
/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import src.CaesarCipher;

public class Main {
    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher();
        
        String message = "At noon be in the conference room with your hat on " +
            "for a surprise party. YELL LOUD!";
            
        System.out.println(cipher.encrypt(message, 15));
        
        System.out.println(cipher.encrypt(message, 8, 21));
        // FileResource fr = new FileResource();
        
        // StringBuilder encrypted = new StringBuilder();
        
        // for(String s : fr.lines()) {
            // encrypted.append(cipher.encrypt(s, 17));
            // encrypted.append("\n");
        // }
        
        // System.out.println(encrypted.toString());
    }
}
