package src;


/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    private static String vowels = "AEIOUaeiou";
    public WordPlay() {}
    
    public boolean isVowel(char ch) {
        return vowels.indexOf(ch) >= 0 ? true : false;
    }
    
    public String replaceVowels(String original, char ch) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < original.length(); i++) {
            if (isVowel(original.charAt(i))) {
                builder.append(ch);
            } else {
                builder.append(original.charAt(i));
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
}
