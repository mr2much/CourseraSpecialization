package com.lockward.src;


/**
 * Write a description of CodonMapper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Map;
import java.util.HashMap;
import edu.duke.FileResource;

public class CodonMapper {
    private String dna;
    private Map<String, Integer> codonMap;
    
    public CodonMapper() {
    }
    
    public CodonMapper(String dna) {
        this.dna = dna;
    }
    
    public void buildCodonMap(int startIndex) {
        FileResource fr = new FileResource();
        
        codonMap = new HashMap<>();
        Map<String, Integer> tempMap = new HashMap<>();
        
        for (String dna : fr.lines()) {
            this.dna = dna.trim();
            tempMap = readFrame(startIndex);
            codonMap.putAll(tempMap);
        }
    }
    
    public void buildCodonMap(int startIndex, String dna) {
        this.dna = dna;
        codonMap = readFrame(startIndex);
    }
    
    public Map<String, Integer> readFrame(int startIndex) {
        codonMap = new HashMap<>();
        
        int currentIndex = startIndex;
        int stopIndex = currentIndex + 3;
        
        while (stopIndex <= dna.length()) {
            String codon = dna.substring(currentIndex, stopIndex);
            
            if (codonMap.containsKey(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            } else {
                codonMap.put(codon, 1);
            }
            
            currentIndex += 3;
            stopIndex = currentIndex + 3;
        }

        return codonMap;
    }
    
    public String getMostCommonCodon() {
        String codon = "";
        int maxCount = Integer.MIN_VALUE;
        for (String key : codonMap.keySet()) {
            int value = codonMap.get(key);
            
            if (value > maxCount) {
                maxCount = value;
                codon = key;
            }
        }
        
        return codon;
    }
    
    public void printCodonCount(int start, int end) {
        int codonCount = 0;
        for (String key : codonMap.keySet()) {
            codonCount++;
            int value = codonMap.get(key);
            if (value >= start && value < end) {
                System.out.println(key  + "\t" + value);
            }
        }
        
        System.out.println("Codon count: " + codonCount);
    }
}
