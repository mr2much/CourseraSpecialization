package src;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         
         for (String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         List<String> uniqueIPs = new ArrayList<>();
         
         for (LogEntry le : records) {
             String ip = le.getIPAddress();
             
             if (!uniqueIPs.contains(ip)) {
                 uniqueIPs.add(ip);
             }
         }
         
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int code) {
         System.out.println("Printing all entries with status code higher " +
            "than: " + code);
         for (LogEntry le : records) {
             if (le.getStatusCode() > code) {
                 System.out.println(le.toString());
             }
         }
     }
     
     public List<String> uniqueIPVisitsOnDay(String someday) {
         List<String> visitsOnDay = new ArrayList<>();
         
         for (LogEntry le : records) {
             if (le.getAccessTime().toString().contains(someday)) {
                 if (!visitsOnDay.contains(le.getIPAddress())) {
                     visitsOnDay.add(le.getIPAddress());
                 }
             }
         }
         
         return visitsOnDay;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         List<String> uniqueIPs = new ArrayList<>();
         
         for (LogEntry le : records) {
             int code = le.getStatusCode();
             
             if(code >= low && code <= high) {
                 String ip = le.getIPAddress();
                 if (!uniqueIPs.contains(ip)) {
                     uniqueIPs.add(ip);
                 }
             }
         }
         
         return uniqueIPs.size();
     }
}
