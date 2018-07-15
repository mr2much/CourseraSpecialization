package src;


/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    private WordPlay wp;
    
    public CaesarBreaker() {
        // cc = new CaesarCipher();
        wp = new WordPlay();
    }
    
    public String decryptOneKey(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        return cc.breakCaesarCipher(encrypted);
    }
    
    public String decryptTwoKeys(String encrypted) {
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String decrypted = cc.decrypt(encrypted);
        
        return decrypted;
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = start; i < message.length(); i += 2) {
            builder.append(message.charAt(i));
        }
        
        return builder.toString();
    }
    
    public int getKey(String s) {
        int[] freqs = wp.textFingerPrint(s);
        
        CaesarCipher cc = new CaesarCipher();
        
        int maxIndex = cc.maxIndex(freqs);
        
        int key = calculateDecryptionKey(maxIndex);
        
        return key;
    }
    
    private int calculateDecryptionKey(int index) {
        int decryptionKey = index - 4;
        
        if (index < 4) {
            decryptionKey = 26 - (4 - index);
        }
        
        return decryptionKey;
    }
}
