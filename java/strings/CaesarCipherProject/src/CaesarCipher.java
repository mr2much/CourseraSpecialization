package src; 
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int key;
    String shiftedAlphabet;
    
    public String getAlphabet() { return shiftedAlphabet; }
    
    public CaesarCipher(int key) {
        this.key = key;
        shiftAlphabet();
    }
    
    private void shiftAlphabet() {
        StringBuilder builder = new StringBuilder(ALPHABET.substring(0, key));
        String lettersToSwap = ALPHABET.substring(key);
        
        builder.insert(0, lettersToSwap);

        shiftedAlphabet = builder.toString();
    }
    
    public String encrypt(String toEncrypt) {
        StringBuilder encrypted = new StringBuilder(toEncrypt);
        
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
