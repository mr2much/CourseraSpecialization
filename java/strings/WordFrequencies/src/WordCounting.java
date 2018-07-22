package src;
/**
 * Write a description of WordCounting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import edu.duke.FileResource;

public class WordCounting {
    private Map<String, Integer> wordFreqs;
    
    public WordCounting() {
        wordFreqs = new HashMap<>();
    }
    
    public void findWords() {
        FileResource fr = new FileResource();
        
        for (String s : fr.words()) {
            // s = removePunctuation(s).toLowerCase();
            s = s.toLowerCase();
            
            if (!wordFreqs.containsKey(s)) {
                wordFreqs.put(s, 1);
            } else {
                wordFreqs.put(s, wordFreqs.get(s) + 1);
            }            
        }
    }
    
    private String removePunctuation(String word) {
        StringBuilder builder = new StringBuilder(word);
        
        for (int i = 0; i < builder.length(); i++) {
            char ch = builder.charAt(i);
            
            if (!Character.isLetter(ch)) {
                builder.deleteCharAt(i);
            }
        }
        
        if (!Character.isLetter(builder.charAt(builder.length() - 1))) {
            builder.deleteCharAt(builder.length() - 1);
        }
        
        return builder.toString();
    }
    
    public void showResults() {
        System.out.printf("There are %d unique words.%n", wordFreqs.size());
        
        for (String key : wordFreqs.keySet()) {
            System.out.println(key + "\t" + wordFreqs.get(key));
        }
    }
    
    public void showWordWithHighestFrequency() {
        int maxCount = Integer.MIN_VALUE;
        int index = 0;
        String word = "";
        
        for (String key : wordFreqs.keySet()) {
            if (wordFreqs.get(key) > maxCount) {
                maxCount = wordFreqs.get(key);
                word = key;
            }
        }
        
        System.out.printf("The word with the highest frequency is %s " +
            "which occurs %d times.%n", word, maxCount);        
    }
    
    public void test() {
        findWords();
        showResults();
        showWordWithHighestFrequency();
    }
}
