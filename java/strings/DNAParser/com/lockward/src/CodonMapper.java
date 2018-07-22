package com.lockward.src;


/**
 * Write a description of CodonMapper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Map;
import java.util.HashMap;

public class CodonMapper {
    String dna;
    
    public CodonMapper(String dna) {
        this.dna = dna;
    }
    
    public Map<String, Integer> readFrame(int startIndex) {
        Map<String, Integer> frame = new HashMap<>();
        
        int currentIndex = startIndex;
        int stopIndex = currentIndex + 3;
        
        while (stopIndex <= dna.length()) {
            String codon = dna.substring(currentIndex, stopIndex);
            
            if (frame.containsKey(codon)) {
                frame.put(codon, frame.get(codon) + 1);
            } else {
                frame.put(codon, 1);
            }
            
            currentIndex += 3;
            stopIndex = currentIndex + 3;
        }

        return frame;
    }
}
