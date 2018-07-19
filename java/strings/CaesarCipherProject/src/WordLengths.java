package src;


/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import java.util.List;
import java.util.ArrayList;

public class WordLengths {
    public void countWordLengths(FileResource fr, int[] counts) {
        for (String word : fr.words()) {
            int length = getLengthOfWord(word);
            
            if (length >= counts.length) {
                length = counts.length - 1;
            } else if (length < 0) {
                length = 0;
            }
            
            counts[length]++;        
        }
    }
    
    private int getLengthOfWord(String word) {
        int length = word.length();
        
        char firstChar = Character.toLowerCase(word.charAt(0));
        char lastChar = Character.toLowerCase(word.charAt(length - 1));        
        
        if (!Character.isLetter(firstChar)) {
            length--;
        }
        
        if (!Character.isLetter(lastChar)) {
            length--;
        }
        
        return length;
    }
    
    public int indexOfMax(int[] counts) {
        int maxIndex = 0;
        
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
}
