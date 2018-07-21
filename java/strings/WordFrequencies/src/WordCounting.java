package src;
/**
 * Write a description of WordCounting here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import edu.duke.FileResource;

public class WordCounting {
    private ArrayList<String> myWords;
    private ArrayList<Integer> wordFrequencies;
    
    public WordCounting() {
        myWords = new ArrayList<>();
        wordFrequencies = new ArrayList<>();
    }
    
    public void findWords() {
        FileResource fr = new FileResource();
        
        for (String s : fr.words()) {
            // s = removePunctuation(s).toLowerCase();
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            
            if (index < 0) {
                myWords.add(s);
                wordFrequencies.add(1);
            } else {
                int value = wordFrequencies.get(index);
                wordFrequencies.set(index, value + 1);
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
        System.out.printf("There are %d unique words.%n", myWords.size());
        
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t" +
                wordFrequencies.get(i));
        }
    }
    
    public void showWordWithHighestFrequency() {
        int maxCount = Integer.MIN_VALUE;
        int index = 0;
        
        for (int i = 0; i < myWords.size(); i++) {
            if (wordFrequencies.get(i) > maxCount) {
                maxCount = wordFrequencies.get(i);
                index = i;
            }
        }
        
        System.out.printf("The word with the highest frequency is %s " +
            "which occurs %d times.%n", myWords.get(index), maxCount);
    }
    
    public void test() {
        findWords();
        showResults();
    }
}
