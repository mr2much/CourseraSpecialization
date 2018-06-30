package src;


/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args) {
        showNumberOfGirlsNamesIn1900();
        showNumberOfBoyNamesIn1905();
        showRankOfEmilyIn1960();
        showRankOfFrankIn1971();
        showGirlsNameRanked350In1980();
        showBoysNameRanked450In1982();
    }
    
    public static void showNumberOfGirlsNamesIn1900() {
        ListByYear listByYear = new ListByYear();
        
        listByYear.loadListForFile("res/us_babynames_by_year/yob1900.csv");
        
        NamesList nl = listByYear.getListForYear(1900);
        
        int females = nl.totalGirlNames();
        
        System.out.println("Total girl names in file yob1900.csv = " + females);
    }
    
    public static void showNumberOfBoyNamesIn1905() {
        ListByYear listByYear = new ListByYear();
        
        listByYear.loadListForFile("res/us_babynames_by_year/yob1905.csv");
        
        NamesList nl = listByYear.getListForYear(1905);
        
        int boys = nl.totalBoyNames();
        
        System.out.println("Total boy names in file yob1905.csv = " + boys);
    }
    
    public static void showRankOfEmilyIn1960() {
        ListByYear listByYear = new ListByYear();
        
        listByYear.loadListForFile("res/us_babynames_by_year/yob1960.csv");
        
        int rank = listByYear.getRank(1960, "Emily", Gender.FEMALE);
        
        System.out.println("Emily's name was ranked " + rank + " in 1960.");
    }
    
    public static void showRankOfFrankIn1971() {
        ListByYear listByYear = new ListByYear();
        
        listByYear.loadListForFile("res/us_babynames_by_year/yob1971.csv");
        
        int rank = listByYear.getRank(1971, "Frank", Gender.MALE);
        
        System.out.println("Frank's name was ranked " + rank + " in 1971.");
    }
    
    public static void showGirlsNameRanked350In1980() {
        ListByYear listByYear = new ListByYear();
        
        listByYear.loadListForFile("res/us_babynames_by_year/yob1980.csv");
        
        RankedName name = listByYear.getName(1980, 350, Gender.FEMALE);
        
        System.out.println("The girl's name ranked 350 in 1980 was " + name.getName());
    }
    
    public static void showBoysNameRanked450In1982() {
        ListByYear listByYear = new ListByYear();
        
        listByYear.loadListForFile("res/us_babynames_by_year/yob1982.csv");
        
        RankedName name = listByYear.getName(1982, 450, Gender.MALE);
        
        System.out.println("The boy's name ranked 450 in 1982 was " + name.getName());
    }
}
