package src;


/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    private CaesarCipher cc;
    
    public CaesarBreaker() {
        cc = new CaesarCipher();
    }
    
    public String decryptOneKey(String encrypted) {
        return cc.decrypt(encrypted);
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = start; i < message.length(); i += 2) {
            builder.append(message.charAt(i));
        }
        
        return builder.toString();
    }
}
