package com.lockward.src;


/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args) {
        CodonMapper cm = new CodonMapper();
        
        cm.buildCodonMap(1);
        cm.printCodonCount(4, 7);
    }
}
