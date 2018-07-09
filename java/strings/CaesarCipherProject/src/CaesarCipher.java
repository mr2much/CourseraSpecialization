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
        StringBuilder builder = new StringBuilder(ALPHABET);
        int startPosition = ALPHABET.indexOf('A');
        char currentChar = ALPHABET.charAt(startPosition);
        
        char newChar = ' ';
        for (int i = 0; startPosition < (ALPHABET.length() - key) ; i++) {
            newChar = ALPHABET.charAt(startPosition + key);
            System.out.println("currentChar: " + currentChar +
                " newChar: " + newChar);
            swap(currentChar, newChar, builder);
            startPosition++;
            currentChar = ALPHABET.charAt(startPosition);
            System.out.println("StartPosition: " + startPosition);
        }
        
        System.out.println("newChar: " + newChar);
        shiftedAlphabet = builder.toString();
    }
    
    private void swap(char firstChar, char secondChar, StringBuilder builder) {
        int indexOfFirstChar = ALPHABET.indexOf(Character.toString(firstChar));
        int indexOfSecondChar = ALPHABET.indexOf(Character.toString(secondChar));
        
        builder.insert(indexOfFirstChar, secondChar);
        builder.deleteCharAt(indexOfSecondChar);
    }
}
