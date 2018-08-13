
/**
 * Write a description of CaesarBreakerTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class CaesarBreakerTester {
    private FileResource fr;
    public void performTest() {
        testSmallKey5();
        testOslusiadas();
        System.out.println("OK");
    }
    
    public void testSmallKey5() {
        fr = new FileResource("messages/titus-small_key5.txt");
        CaesarCracker cc = new CaesarCracker();
        
        String original = cc.decrypt(fr.asString());
        
        System.out.println(original);
    }
    
    public void testOslusiadas() {
        fr = new FileResource("messages/oslusiadas_key17.txt");
        CaesarCracker cc = new CaesarCracker('a');
        
        String original = cc.decrypt(fr.asString());
        
        System.out.println(original);
    }
}
