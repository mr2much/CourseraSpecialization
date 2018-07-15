package src; 
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
    private WordPlay wp;
    private int mainKey;
    
    public CaesarCipher() {
        this.wp = new WordPlay();
    }
    
    public CaesarCipher(int key) {
        this.wp = new WordPlay(); 
        shiftAlphabet(key);
        this.mainKey = key;
    }
    
    private void shiftAlphabet(int key) {
        StringBuilder builder = new StringBuilder(ALPHABET.substring(key))
            .append(ALPHABET.substring(0, key));
            
        shiftedAlphabet = builder.toString();
    }
    
    public String encrypt(String toEncrypt) {
        StringBuilder encrypted = new StringBuilder(toEncrypt);
        
        for (int i = 0; i < toEncrypt.length(); i++) {
            char currentChar = encrypted.charAt(i);
            
            if(!Character.isLetter(currentChar)) {
                continue;
            }
            
            int currentIndex = getCurrentIndex(currentChar);
            char encryptedChar = getEncryptedCharacterFor(currentIndex,
                isLowerCase(currentChar));
            
            encrypted.setCharAt(i, encryptedChar);
        }
        
        return encrypted.toString();
    }
    
    // public String encrypt(String toEncrypt, int key) {
        // StringBuilder encrypted = new StringBuilder(toEncrypt);
        // shiftAlphabet(key);
        
        // for (int i = 0; i < toEncrypt.length(); i++) {
            // char currentChar = encrypted.charAt(i);
            
            // if(!Character.isLetter(currentChar)) {
                // continue;
            // }
            
            // int currentIndex = getCurrentIndex(currentChar);
            // char encryptedChar = getEncryptedCharacterFor(currentIndex,
                // isLowerCase(currentChar));
            
            // encrypted.setCharAt(i, encryptedChar);
        // }
        
        // return encrypted.toString();
    // }
    
    public String encrypt(String toEncrypt, int key1, int key2) {
        StringBuilder builder = new StringBuilder(toEncrypt);
        
        CaesarCipher cc1 = new CaesarCipher(key1);
        CaesarCipher cc2 = new CaesarCipher(key2);
        
        String encrypted1 = cc1.encrypt(toEncrypt);
        String encrypted2 = cc2.encrypt(toEncrypt);
        
        for (int i = 0; i < builder.length(); i++) {
            builder.setCharAt(i, isEven(i + 1) ? encrypted2.charAt(i) :
                encrypted1.charAt(i));
        }
        
        return builder.toString();
    }
    
    private boolean isEven(int n) {
        return n % 2 == 0;
    }
    
    public int getCurrentIndex(char ch) {
        return isLowerCase(ch) ? ALPHABET.toLowerCase().indexOf(ch) :
            ALPHABET.indexOf(ch);
    }
    
    private boolean isLowerCase(char ch) {
        return Character.isLowerCase(ch);
    }
    
    private char getEncryptedCharacterFor(int index, boolean toLowerCase) {
        return toLowerCase ? shiftedAlphabet.toLowerCase().charAt(index) :
            shiftedAlphabet.charAt(index);
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        return cc.encrypt(encrypted);
    }
    
    public String breakCaesarCipher(String encrypted) {
        int[] freqs = wp.textFingerPrint(encrypted);
        int maxIndex = maxIndex(freqs);
        
        int decryptionKey = maxIndex - 4;
        
        if (maxIndex < 4) {
            decryptionKey = 26 - (4 - maxIndex);
        }
        
        CaesarCipher cc = new CaesarCipher(26 - decryptionKey);
        return cc.encrypt(encrypted);
    }
    
    public int maxIndex(int[] frequencies) {
        int maxIndex = 0;
        
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > frequencies[maxIndex]) {
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
}
