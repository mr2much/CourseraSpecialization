package com.lockward.tests;


/**
 * Write a description of CodonMapperTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import com.lockward.src.CodonMapper;
import java.util.Map;
import java.util.Collection;

public class CodonMapperTester {
    public void performTests() {
        testKeySetReturnedFromFrame();
        testValuesReturnedFromFrame();
        testMostCommonCodon();
        testPrintCodonCount();
        testBuildCodonMapOneArg();
        System.out.println("OK");
    }
    
    public void testKeySetReturnedFromFrame() {
        String dna = "CGTTCAAGTTCAA";
        CodonMapper cm = new CodonMapper(dna);
        
        Map<String, Integer> map = cm.readFrame(0);
        compare(map.keySet(), new String[] {"CGT", "TCA", "AGT"});
        
        map = cm.readFrame(1);
        compare(map.keySet(), new String[] {"CAA", "GTT"});
        
        map = cm.readFrame(2);
        compare(map.keySet(), new String[] {"TTC", "AAG"});
    }
    
    public void testValuesReturnedFromFrame() {
        String dna = "CGTTCAAGTTCAA";
        CodonMapper cm = new CodonMapper(dna);
        
        Map<String, Integer> map = cm.readFrame(0);
        compare(map.values(), new Integer[] {1, 2, 1});
        
        map = cm.readFrame(1);
        compare(map.values(), new Integer[] {2, 2});
        
        map = cm.readFrame(2);
        compare(map.values(), new Integer[] {2, 1});
    }
    
    public void compare(Collection<? extends Object> collection,
        Object[] compareValues) {
        Object[] valuesToCompare = collection
            .toArray(new Object[collection.size()]);
        
        for (int i = 0; i < compareValues.length; i++) {
            assert valuesToCompare[i].equals(compareValues[i]) :
                "Values: " + valuesToCompare[i] + "\t" +
                compareValues[i];
        }
    }
    
    public void testMostCommonCodon() {
        CodonMapper cm = new CodonMapper();
        String dna = "CGTTCAAGTTCAA";
        
        cm.buildCodonMap(0, dna);        
        String codon = cm.getMostCommonCodon();        
        assert codon.equals("TCA");
        
        cm.buildCodonMap(1, dna);
        codon = cm.getMostCommonCodon();
        assert codon.equals("CAA");
        
        cm.buildCodonMap(2, dna);
        codon = cm.getMostCommonCodon();
        assert codon.equals("TTC");
    }
    
    public void testPrintCodonCount() {
        CodonMapper cm = new CodonMapper();
        String dna = "CGTTCAAGTTCAA";
        
        cm.buildCodonMap(0, dna);
        cm.printCodonCount(0, 5);
        
        cm.buildCodonMap(1, dna);
        cm.printCodonCount(0, 5);
        
        cm.buildCodonMap(2, dna);
        cm.printCodonCount(0, 5);
    }
    
    public void testBuildCodonMapOneArg() {
        CodonMapper cm = new CodonMapper();
        
        cm.buildCodonMap(0);        
        cm.printCodonCount(0,5);
    }
}
