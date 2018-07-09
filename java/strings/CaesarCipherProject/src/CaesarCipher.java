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
    
    public CaesarCipher() { }
    
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
            
            int currentIndex = ALPHABET.indexOf(currentChar);
            char encryptedChar = getEncryptedCharacterFor(currentIndex);
            
            encrypted.setCharAt(i, encryptedChar);
        }
        
        return encrypted.toString();
    }
    
    private char getEncryptedCharacterFor(int index) {
        return shiftedAlphabet.charAt(index);
    }
}
