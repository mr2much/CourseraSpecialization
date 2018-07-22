package src;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Map;
import java.util.HashMap;
import edu.duke.FileResource;

public class WordFrequencies {
    Map<String, Integer> wordFrequencies;
    private int wordsRead;
    
    public WordFrequencies() {
        wordFrequencies = new HashMap<>();
    }
    
    public void tester() {
        findUnique();
        showWordFrequencies();
        String word = findWordWithHighestFrequency();
        showWordWithHighestFrequency(word);
    }
    
    public void findUnique() {        
        initialize();
        getUniqueWordsFromFile();
    }
    
    private void initialize() {
        wordFrequencies.clear();
        wordsRead = 0;
    }
    
    private void getUniqueWordsFromFile() {
        FileResource fr = new FileResource();
        
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if (!wordFrequencies.containsKey(word)) {
                wordFrequencies.put(word, 1);
            } else {
                wordFrequencies.put(word, wordFrequencies.get(word) + 1);
            }
            wordsRead++;
        }
        
    }
    
    public void showWordFrequencies() {
        System.out.printf("%d words where read from the file, of which " +
            "%d are unique words.%n", wordsRead, wordFrequencies.size());
        
        for (String key : wordFrequencies.keySet()) {
            System.out.println(key + "\t" + 
                wordFrequencies.get(key));
        }
    }
    
    public String findWordWithHighestFrequency() {
        String word = "";
        int maxValue = Integer.MIN_VALUE;
        
        for (String key : wordFrequencies.keySet()) {
            if (wordFrequencies.get(key) > maxValue) {
                maxValue = wordFrequencies.get(key);
                word = key;
            }
        }
        
        return word;
    }
        
    public void showWordWithHighestFrequency(String word) {
        System.out.printf("The word with the highest frequency is '%s' " +
            "which occurred %d times.%n", word, 
            wordFrequencies.get(word));
    }
    
}
