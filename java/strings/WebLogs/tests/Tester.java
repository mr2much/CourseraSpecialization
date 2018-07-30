package tests;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import src.LogEntry;
import src.LogAnalyzer;

public class Tester
{
    private static LogAnalyzer logAnalyzer = new LogAnalyzer();
    
    public void performTests() {
        testLogEntry();
        testLogAnalyzer();
        System.out.println("OK");
    }
    
    public void testLogEntry() {
        LogEntry le = new LogEntry.LogEntryBuilder().ip("1.2.3.4").accessDate(new Date()).request("example request")
            .status(200).bytes(500).build();
            
        System.out.println(le);
        
        LogEntry le2 = new LogEntry.LogEntryBuilder().ip("1.2.100.4").accessDate(new Date()).request("example request 2")
            .status(300).bytes(400).build();
            
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        String filename = "res/short-test_log";
        logAnalyzer.readFile(filename);
        logAnalyzer.printAll();
    }
}
