
/**
 * Write a description of CaesarCipherTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class CaesarCipherTester {
    private FileResource fr = new FileResource("messages/titus-small.txt");
    
    public void performTests() {
        testEncryption();
        System.out.println("OK");
    }
    
    public void testEncryption() {
        CaesarCipher cc = new CaesarCipher(17);
        
        String encrypted = cc.encrypt(fr.asString());
        
        System.out.println(encrypted);
    }
}
