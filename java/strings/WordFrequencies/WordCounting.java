
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
    
    public void showResults() {
        System.out.printf("There are %d unique words.%n", myWords.size());
        
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t" +
                wordFrequencies.get(i));
        }
    }
    
    public void test() {
        findWords();
        showResults();
    }
}
