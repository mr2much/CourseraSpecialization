package src;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.ArrayList;
import edu.duke.FileResource;

public class WordFrequencies {
    private List<String> myWords;
    private List<Integer> myFrequencies;
    private int wordsRead;
    
    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFrequencies = new ArrayList<>();
    }
    
    public void tester() {
        findUnique();
        showWordFrequencies();
        int indexOfMax = findIndexOfHighestFrequency(myFrequencies);
        showIndexOfHighestFrequency(indexOfMax);
    }
    
    public void findUnique() {        
        initialize();
        myWords = getUniqueWordsFromFile();
    }
    
    private void initialize() {
        myWords.clear();
        myFrequencies.clear();
        wordsRead = 0;
    }
    
    private List<String> getUniqueWordsFromFile() {
        List<String> uniqueWords = new ArrayList<>();
        
        FileResource fr = new FileResource();
        
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
                myFrequencies.add(1);
            } else {
                int kthPosition = uniqueWords.indexOf(word);
                int value = myFrequencies.get(kthPosition);
                myFrequencies.set(kthPosition, value + 1);
            }
            wordsRead++;
        }
        
        return uniqueWords;
    }
    
    public void showWordFrequencies() {
        System.out.printf("%d words where read from the file, of which " +
            "%d are unique words.%n", wordsRead, myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t" + 
                myFrequencies.get(i));
        }
    }
    
    public int findIndexOfHighestFrequency(List<Integer> frequencies) {
        int index = -1;
        int maxValue = Integer.MIN_VALUE;
        
        for (int i = 0; i < frequencies.size(); i++) {
            
            if (frequencies.get(i) > maxValue) {
                maxValue = frequencies.get(i);
                index = i;
            }
        }
        
        return index;
    }
    
    public void showIndexOfHighestFrequency(int index) {
        System.out.printf("The word with the highest frequency is '%s' " +
            "which occurred %d times.%n", myWords.get(index), 
            myFrequencies.get(index));
    }
}
