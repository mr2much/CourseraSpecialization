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
        testLogAnalyzerUniqueIPs();
        testHigherThanNum();
        testUniqueVisitsOnDay();
        testUniqueIPsInRange();
        testPrintHigherThan400();
        testCountVisitsPerIP();
        testMostNumberOfVisits();
        testIPsMostVisits();
        testIPsForDays();
        testDayWithMostVisits();
        testIPsWithMostVisitsOnDay();
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
    
    public void testLogAnalyzerUniqueIPs() {
        assert logAnalyzer.countUniqueIPs() == 4;
    }
    
    public void testHigherThanNum() {
        logAnalyzer.printAllHigherThanNum(150);
        logAnalyzer.printAllHigherThanNum(200);
    }
    
    public void testUniqueVisitsOnDay() {
        String filename = "res/weblog-short_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        
        List<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 14");        
        assert uniqueIPs.size() == 2;        
        compare(uniqueIPs, new String[] {"84.133.195.161", "177.4.40.87"});
        
        uniqueIPs = la.uniqueIPVisitsOnDay("Sep 30");
        assert uniqueIPs.size() == 3;
        compare(uniqueIPs, new String[] {"84.189.158.117", "61.15.121.171",
            "177.4.40.87"});
    }
    
    public void testUniqueIPsInRange() {
        assert logAnalyzer.countUniqueIPsInRange(200, 299) == 4;
        assert logAnalyzer.countUniqueIPsInRange(300, 399) == 2;
    }
    
    public void testPrintHigherThan400() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = "res/weblog1_log";
        la.readFile(filename);
        
        la.printAllHigherThanNum(400);
        
        System.out.println("Unique IP vistis on Mar 17: " +
            la.uniqueIPVisitsOnDay("Mar 17").size());
            
        System.out.println("Numbers in range 200, 299: " +
            la.countUniqueIPsInRange(200, 299));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = "res/short-test_log";
        la.readFile(filename);
        Map<String, Integer> compare = new HashMap<>();
        
        compare.put("157.55.39.203", 1);
        compare.put("152.3.135.44", 3);
        compare.put("152.3.135.63", 2);
        compare.put("110.76.104.12", 1);
        
        assert compare.equals(la.countVisitsPerIP()) : "Value: " +
            la.countVisitsPerIP();
    }
    
    public void testMostNumberOfVisits() {
        String filename = "res/weblog3-short_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        
        Map<String, Integer> entries = la.countVisitsPerIP();
        
        assert la.mostNumberVisitsByIP(entries) == 3;
    }
    
    public void testIPsMostVisits() {
        String filename = "res/weblog3-short_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        
        Map<String, Integer> entries = la.countVisitsPerIP();
        
        List<String> ips = la.IPsMostVisits(entries);
        
        compare(ips, new String[] {"61.15.121.171", "84.133.195.161"});
    }
    
    public void testIPsForDays() {
        String filename = "res/weblog3-short_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        
        Map<String, List<String>> entries = la.IPsForDays();
        assert entries.get("Sep 14").size() == 1;
        assert entries.get("Sep 21").size() == 4;
        assert entries.get("Sep 30").size() == 5;
    }
    
    public void testDayWithMostVisits() {
        String filename = "res/weblog3-short_log";
        LogAnalyzer la = new LogAnalyzer();
        
        la.readFile(filename);
        
        Map<String, List<String>> entries = la.IPsForDays();
        
        assert la.dayWithMostIPVisits(entries).equals("Sep 30");
    }
    
    public void testIPsWithMostVisitsOnDay() {
        String filename = "res/weblog3-short_log";
        LogAnalyzer la = new LogAnalyzer();
        
        la.readFile(filename);
        Map<String, List<String>> entries = la.IPsForDays();
        
        List<String> ips = la.IPsWithMostVisitsOnDay(entries, "Sep 30");
        
        compare(ips, new String[] {"61.15.121.171", "177.4.40.87"});
    }
    
    private void compare(List<? extends Object> list, Object[] arr) {
        Object[] cArr = list.toArray(new Object[list.size()]);
        
        for (int i = 0; i < arr.length; i++) {
            assert cArr[i].equals(arr[i]);
        }
    }
}
