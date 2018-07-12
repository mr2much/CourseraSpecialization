package src;


/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public static String VOWELS = "AEIOUaeiou";
    public static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public WordPlay() {}
    
    public boolean isVowel(char ch) {
        return VOWELS.indexOf(ch) >= 0 ? true : false;
    }
    
    public String replaceVowels(String original, char ch) {
        StringBuilder builder = new StringBuilder(original);
        
        for (int i = 0; i < original.length(); i++) {
            if (isVowel(original.charAt(i))) {
                builder.setCharAt(i, ch);
            }
        }
        
        return builder.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder builder = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(phrase.charAt(i)) == 
                Character.toLowerCase(ch)) {
                builder.setCharAt(i, isEven(i + 1) ? '+' : '*');
            }
        }
        
        return builder.toString();
    }
    
    private boolean isEven(int n) {
        return n % 2 == 0;
    }
    
    public int[] textFingerPrint(String input) {
        int[] result = new int[ALPHABET.length()];

        for (int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            int index = getIndexOfChar(letter);
            
            if (index != -1) {
                result[index]++;
            }
            
        }
        
        return result;
    }
    
    private int getIndexOfChar(char ch) {
        return ALPHABET.indexOf(Character.toLowerCase(ch));
    }
}
