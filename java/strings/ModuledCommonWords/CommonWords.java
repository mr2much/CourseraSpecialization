
/**
 * Write a description of CommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class CommonWords {
    public String[] getCommon() {
        FileResource fr = new FileResource("res/common.txt");
        String[] common = new String[20];
        int index = 0;
        
        for (String word : fr.words()) {
            common[index] = word;
            index += 1;
        }
        
        return common;
    }
    
    public void countShakespeare() {
        String[] plays = {"small.txt"};
        
        // String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
            // "likeit.txt", "macbeth.txt", "romeo.txt"};
            
        String[] common = getCommon();
        
        int[] counts = new int[common.length];
        
        for (int i = 0; i < plays.length; i++) {
            FileResource fr = new FileResource("res/" + plays[i]);
            countWords(fr, common, counts);
            
            System.out.println("Finished reading " + plays[i]);
        }
        
        for (int i = 0; i < counts.length; i++) {
            System.out.println(common[i] + "\t" + counts[i]);
        }
    }
    
    public void countWords(FileResource fr, String[] common, int[] count) {
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = indexOf(word, common);
            if (index != -1) {
                count[index]++;
            }
        }
    }
    
    private int indexOf(String word, String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                return i;
            }
        }
        
        return -1;
    }
}
