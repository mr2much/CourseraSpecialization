package tests;


/**
 * Write a description of WordLengthsTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.FileResource;
import src.WordLengths;

public class WordLengthsTester {
    public void performTests() {
        countWordsTest();
        indexOfMaxShouldReturn3();
        System.out.println("OK");
    }
    
    public void countWordsTest() {
        WordLengths wl = new WordLengths();
        
        FileResource fr = new FileResource("res/smallHamlet.txt");
        
        int[] counts = new int[31];
        wl.countWordLengths(fr, counts);

        assert counts[2] == 2;
        assert counts[3] == 3;
        assert counts[4] == 2;
        assert counts[5] == 1;
        assert counts[6] == 1;
        assert counts[7] == 1;
        assert counts[8] == 2;
        assert counts[11] == 1;
    }
    
    private void compare(int[] original, int[] other) {
        for (int i = 0; i < other.length; i++) {
            assert original[i] == other[i] : "Value " + other[i];
        }
    }
    
    public void indexOfMaxShouldReturn3() {
        WordLengths wl = new WordLengths();
        
        FileResource fr = new FileResource("res/smallHamlet.txt");
        
        int[] counts = new int[31];
        
        wl.countWordLengths(fr, counts);
        
        int max = wl.indexOfMax(counts);
        
        assert max == 3 : "Value " + max;
    }
}
