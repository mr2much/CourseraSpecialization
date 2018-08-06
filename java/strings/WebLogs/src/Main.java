package src;


/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Map;
import java.util.List;

public class Main {
    private static LogAnalyzer laLog1;
    private static LogAnalyzer laLog2;
    
    static {
        laLog1 = new LogAnalyzer();
        laLog2 = new LogAnalyzer();
        laLog1.readFile("res/weblog1_log");
        laLog2.readFile("res/weblog2_log");
    }
    
    public static void main(String[] args) {
        System.out.println("Testing weblog1.");
        showMostNumberOfVisits(laLog1);
        showIPWithMostVisits(laLog1);
        showDayWithMostVisits(laLog1);
        showIPWithMostVisitsOnDay(laLog1, "Mar 17");
        System.out.println("Testing weblog2.");
        showUniqueIPCount(laLog2);
        showUniqueIPVisitsOnDay(laLog2, "Sep 27");
        showUniqueIPsInRange(laLog2, 200, 299);
        showMostNumberOfVisits(laLog2);
        showIPWithMostVisits(laLog2);
        showDayWithMostVisits(laLog2);
        showIPWithMostVisitsOnDay(laLog2, "Sep 29");
    }
    
    public static void showMostNumberOfVisits(LogAnalyzer la) {
        Map<String, Integer> entries = la.countVisitsPerIP();
        
        System.out.println("Most visits per IP is " + 
            la.mostNumberVisitsByIP(entries));
    }
    
    public static void showIPWithMostVisits(LogAnalyzer la) {
        Map<String, Integer> entries = la.countVisitsPerIP();
        List<String> ips = la.IPsMostVisits(entries);
        
        for (String ip : ips) {
            System.out.println("    " + ip);
        }
    }
    
    public static void showDayWithMostVisits(LogAnalyzer la) {
        Map<String, List<String>> entries = la.IPsForDays();
        
        System.out.println("The day with most vistis was: " + la.dayWithMostIPVisits(entries));
    }
    
    public static void showIPWithMostVisitsOnDay(LogAnalyzer la, String day) {
        Map<String, List<String>> entries = la.IPsForDays();
        
        List<String> visits = la.IPsWithMostVisitsOnDay(entries, day);
        
        System.out.println("Show most visits per IP on day: " + day);
        for (String ip : visits) {
            System.out.println("IP: "  + ip);
        }
    }
    
    public static void showUniqueIPCount(LogAnalyzer la) {
        System.out.println("There are " + la.countUniqueIPs() + " unique IPs.");
    }
    
    public static void showUniqueIPVisitsOnDay(LogAnalyzer la, String day) {
        Map<String, List<String>> entries = la.IPsForDays();
        
        System.out.println("The number of visits on day " + day + " was " +
            entries.get(day).size());
    }
    
    public static void showUniqueIPsInRange(LogAnalyzer la, int low, int high) {
        System.out.printf("There are %d unique IPs in the range %d and %d%n", la.countUniqueIPsInRange(low, high),
            low, high);
    }
}
