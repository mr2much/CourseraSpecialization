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
    
}
