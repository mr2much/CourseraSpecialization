package src;


/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args) {
        String filename = "yob1881.csv";
        
        int year = extractYear(filename);
        
        assert year == 1881;
        
        year = extractYear("yob2015.csv");
        
        assert year == 2015;
        
        String[] files = {"yob2014.csv", "yob1881.csv", "yob2000.csv"};
        int[] compare = {2014, 1881, 2000};
        
        for(int i = 0; i < compare.length; i++) {
            year = extractYear(files[i]);
            
            assert year == compare[i];
        }
    }
    
    public static int extractYear(String text) {
        text = text.replaceAll("\\D+", "");
        
        return Integer.parseInt(text);
    }
}
