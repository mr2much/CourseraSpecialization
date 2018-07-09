
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
        FileResource fr = new FileResource();
        
        StringBuilder encrypted = new StringBuilder();
        CaesarCipher cipher = new CaesarCipher();
        
        for(String s : fr.lines()) {
            encrypted.append(cipher.encrypt(s, 17));
            encrypted.append("\n");
        }
        
        System.out.println(encrypted.toString());
    }
}
