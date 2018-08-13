
/**
 * Write a description of VigenereCipherTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class VigenereCipherTester {
    private FileResource fr = new FileResource("messages/titus-small.txt");
    private String encrypted = "";
    private VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
    
    public void performTests() {
        testVigenereEncryption();
        testVigenereDecryption();
        System.out.println("OK");
    }
    
    public void testVigenereEncryption() {
        System.out.println("Before encryption: " + fr.asString());
        System.out.println("Key used: " + vc.toString());
        encrypted = vc.encrypt(fr.asString());
        System.out.println("Encrypted: " + encrypted);
    }
    
    public void testVigenereDecryption() {
        String original = vc.decrypt(encrypted);
        System.out.println("Original: " + original);
    }
}
