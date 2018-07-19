
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
    
    public WordCounting() {
        myWords = new ArrayList<>();
    }
    
    public void findWords() {
        FileResource fr = new FileResource();
        
        for (String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            
            if (index < 0) {
                myWords.add(s);
            }
        }
    }
    
    public void showResults() {
        System.out.printf("There are %d unique words.", myWords.size());
    }
    
    public void test() {
        findWords();
        showResults();
    }
}
