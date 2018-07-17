package src;


/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
    private String shiftedAlphabet2;
    private WordPlay wp;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        this.wp = new WordPlay();
        this.shiftedAlphabet = shiftAlphabet(key1);  
        this.shiftedAlphabet2 = shiftAlphabet(key2);
        
        this.mainKey1 = key1;
        this.mainKey2 = key2;
    }
    
    private String shiftAlphabet(int key) {
        StringBuilder builder = new StringBuilder(ALPHABET.substring(key))
            .append(ALPHABET.substring(0, key));
            
        return builder.toString();
    }
    
    public String encrypt(String toEncrypt) {
        StringBuilder builder = new StringBuilder(toEncrypt);
        
        for (int i = 0; i < builder.length(); i++) {
            char currentCharacter = toEncrypt.charAt(i);
            
            if (!Character.isLetter(currentCharacter)) {
                continue;
            }
            
            int currentIndex = 
                getCurrentIndex(Character.toUpperCase(currentCharacter));
            
            char encryptedCharacter = 
                getEncryptedCharacter(i, currentIndex, 
                    isLowerCase(currentCharacter));
            
            builder.setCharAt(i, encryptedCharacter);
        }
        
        return builder.toString();
    }
    
    public int getCurrentIndex(char ch) {
        return ALPHABET.indexOf(ch);
    }
    
    private boolean isLowerCase(char ch) {
        return Character.isLowerCase(ch);
    }
    
    private char getEncryptedCharacter(int currIndex, int index, boolean toLowerCase) {
        char result = isEven(currIndex + 1) ? shiftedAlphabet2.charAt(index) : shiftedAlphabet.charAt(index);
        
        if (toLowerCase) {
            result = Character.toLowerCase(result);
        }
        
        return result;
    }
    
    private boolean isEven(int n) {
        return n % 2 == 0;
    }
    
    public String decrypt(String encrypted) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        
        return cc.encrypt(encrypted);
    }
    
    public String breakCaesarCipher(String encrypted) {
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cct.encrypt(encrypted);
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
