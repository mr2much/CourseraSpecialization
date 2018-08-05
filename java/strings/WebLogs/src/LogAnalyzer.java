package src;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.text.SimpleDateFormat;

public class LogAnalyzer
{
     private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
     
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
     
     public Map<String, Integer> countVisitsPerIP() {
         Map<String, Integer> counts = new HashMap<>();
         
         for (LogEntry le : records) {
             String ip = le.getIPAddress();
             
             if (counts.containsKey(ip)) {
                 counts.put(ip, counts.get(ip) + 1);
             } else {
                 counts.put(ip, 1);
             }
         }
         
         return counts;
     }
     
     public int mostNumberVisitsByIP(Map<String, Integer> entries) {
         int maxVisits = Integer.MIN_VALUE;
         
         for (String ip : entries.keySet()) {
             int value = entries.get(ip);
             
             if (value > maxVisits) {
                 maxVisits = value;
             }
         }
         
         return maxVisits;
     }
     
     public List<String> IPsMostVisits(Map<String, Integer> entries) {
         int mostVisits = mostNumberVisitsByIP(entries);
         
         List<String> visits = new ArrayList<>();
         
         for (String ip : entries.keySet()) {
             if (entries.get(ip) == mostVisits) {
                 visits.add(ip);
             }
         }
                  
         return visits;
     }
     
     public Map<String, List<String>> IPsForDays() {
         Map<String, List<String>> entries = new HashMap<>();
         
         List<String> visits = new ArrayList<>();
         
         for (LogEntry le : records) {
             String date = dateFormat.format(le.getAccessTime());
             
             if (entries.containsKey(date)) {
                 List<String> ips = entries.get(date);
                 ips.add(le.getIPAddress());
                 entries.put(date, ips);
             } else {
                 List<String> ips = new ArrayList<>();
                 ips.add(le.getIPAddress());
                 entries.put(date, ips);
             }
             
         }
         
         return entries;
     }
     
     public String dayWithMostIPVisits(Map<String, List<String>> entries) {
         String day = "";
         int mostVisits = Integer.MIN_VALUE;
         
         for (String key : entries.keySet()) {
             int visits = entries.get(key).size();
             
             if (visits > mostVisits) {
                 mostVisits = visits;
                 day = key;
             }
         }
         
         return day;
     }
     
     public List<String> IPsWithMostVisitsOnDay(Map<String, List<String>> entries, String day) {
         List<String> ips = new ArrayList<>();        
         
         if (entries.containsKey(day)) {
             Map<String, Integer> visitsPerIP = mapVisitCount(entries.get(day));
             int higherCount = mostNumberVisitsByIP(visitsPerIP);
             
             for (String ip : visitsPerIP.keySet()) {
                 
                 if (visitsPerIP.get(ip) == higherCount) {
                     ips.add(ip);
                 }
             }
         }
         
         return ips;
     }
     
     private Map<String, Integer> mapVisitCount(List<String> ips) {
         Map<String, Integer> visits = new HashMap<>();
         
         for (String ip : ips) {
             if (visits.containsKey(ip)) {
                 visits.put(ip, visits.get(ip) + 1);
             } else {
                 visits.put(ip, 1);
             }
         }
         
         return visits;
     }
}
