package src; 
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String shiftedAlphabet;
    WordPlay wp;
    
    public CaesarCipher() { this.wp = new WordPlay(); }
    
    private void shiftAlphabet(int key) {
        StringBuilder builder = new StringBuilder(ALPHABET.substring(key))
            .append(ALPHABET.substring(0, key));
            
        shiftedAlphabet = builder.toString();
    }
    
    public String encrypt(String toEncrypt, int key) {
        StringBuilder encrypted = new StringBuilder(toEncrypt);
        shiftAlphabet(key);
        
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
    
    public String encrypt(String toEncrypt, int key1, int key2) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < toEncrypt.length(); i++) {
            String currentChar = Character.toString(toEncrypt.charAt(i));
            
            if (isEven(i + 1)) {
                builder.append(encrypt(currentChar, key2));
            } else {
                builder.append(encrypt(currentChar, key1));
            }
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
    
}
