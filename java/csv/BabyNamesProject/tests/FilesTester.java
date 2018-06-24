package tests;


/**
 * Write a description of FilesTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import src.FileManager;
import src.NamesList;
import src.RankedName;
import src.Gender;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.CSVParser;

public class FilesTester {
    public void performTests() {
        shouldFailIfPathDoesntExist();
        fileShouldHaveTenLines();
        shouldThrowErrorIfLinesAreNotEqual();
        testTotalBirthsInFile();
        testLoadingAllFemalesFromExampleCSVFile();
        testLoadingAllMalesFromExampleCSVFile();
        testLoadingAllNamesFromExampleCSVFileUsingCSVParser();
        System.out.println("FileManager - Tests finished.");
    }
    
    public void shouldFailIfPathDoesntExist() {
        FileManager fm = new FileManager();
        
        assert fm.pathExists(Paths.get("res"));
        assert fm.pathExists(Paths.get("res", "example-small.csv"));
    }
    
    public void fileShouldHaveTenLines() {
        FileManager fm = new FileManager();
        
        try {
            List<String> lines = fm.readAllLines(
                Paths.get("res", "example-small.csv"));
                
            assert lines.size() == 10;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void shouldThrowErrorIfLinesAreNotEqual() {
        FileManager fm = new FileManager();
        
        try {
            List<String> lines = fm.readAllLines(
                Paths.get("res", "example-small.csv"));
            
            compare(lines, new String[] {"Emma,F,500", "Olivia,F,400", 
                "Sophia,F,300", "Isabella,F,200", "Ava,F,100", "Noah,M,100", 
                "Liam,M,40", "Mason,M,30", "Jacob,M,20", "William,M,10"});
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void compare(List<String> namesList, String[] namesArr) {
        Object[] arr = namesList.toArray();
        
        for (int i = 0; i < arr.length; i++) {
            assert arr[i].equals(namesArr[i]);
        }
    }

    public void testTotalBirthsInFile() {
        FileManager fm = new FileManager();
        
        String pattern = "res/us_babynames_test/yob%dshort.csv";
        fm.setFilename(String.format(pattern, 2012));
        CSVParser parser = fm.getCSVParser();
        NamesList namesList = fm.getNamesListFrom(parser);
        
        List<RankedName> females = namesList.getAll(Gender.FEMALE);
        List<RankedName> males = namesList.getAll(Gender.MALE);
        
        assert namesList.totalFemales() == 5;
        assert namesList.totalMales() == 5;
        assert namesList.totalBirths() == 10;
    }
    
    public void testLoadingAllFemalesFromExampleCSVFile() {
        FileManager fm = new FileManager();
        
        try {
            List<String> fileInfo = fm.readAllLines(
                Paths.get("res", "example-small.csv"));
            
            NamesList nl = fm.getNamesListFrom(fileInfo);
            
            List<RankedName> females = nl.getAll(Gender.FEMALE);
            
            compare(females, new RankedName[] {
                new RankedName("Emma", "F", 500), 
                new RankedName("Olivia", "F", 400),
                new RankedName("Sophia", "F", 300),
                new RankedName("Isabella", "F", 200),
                new RankedName("Ava", "F", 100)});
        } catch (IOException ex) {
            System.out.println("<" + ex.getClass().getSimpleName() +
                "> - " + ex.getMessage());
        }
    }
    
    public void testLoadingAllMalesFromExampleCSVFile() {
        FileManager fm = new FileManager();
        
        try {
            List<String> fileInfo = fm.readAllLines(
                Paths.get("res", "example-small.csv"));
            
            NamesList nl = fm.getNamesListFrom(fileInfo);
            
            List<RankedName> males = nl.getAll(Gender.MALE);
            
            compare(males, new RankedName[] {
                new RankedName("Noah", "M", 100), 
                new RankedName("Liam", "M", 40),
                new RankedName("Mason", "M", 30),
                new RankedName("Jacob", "M", 20),
                new RankedName("William", "M", 10)});
        } catch (IOException ex) {
            System.out.println("<" + ex.getClass().getSimpleName() +
                "> - " + ex.getMessage());
        }
    }
    
    public void testLoadingAllNamesFromExampleCSVFileUsingCSVParser() {
        FileManager fm = new FileManager();
        
        String pattern = "res/us_babynames_test/yob%dshort.csv";
        fm.setFilename(String.format(pattern, 2012));
        CSVParser parser = fm.getCSVParser();
        NamesList namesList = fm.getNamesListFrom(parser);
        
        List<RankedName> females = namesList.getAll(Gender.FEMALE);
        List<RankedName> males = namesList.getAll(Gender.MALE);
        
        compare(females, new RankedName[] {
            new RankedName("Sophia", "F", 10),            
            new RankedName("Emma", "F", 9),
            new RankedName("Isabella", "F", 8),
            new RankedName("Olivia", "F", 7),
            new RankedName("Ava", "F", 6)});
            
        compare(males, new RankedName[] {
            new RankedName("Jacob", "M", 8),            
            new RankedName("Mason", "M", 7),
            new RankedName("Ethan", "M", 7),
            new RankedName("Noah", "M", 6),
            new RankedName("William", "M", 5)});
            
    }
    
    private void compare(List<RankedName> namesList, RankedName[] namesArr) {
        Object[] arr = namesList.toArray();
        
        for (int i = 0; i < namesArr.length; i++) {
            assert arr[i].equals(namesArr[i]) : "Values: " + 
                arr[i] + " != " + namesArr[i];
        }
    }
    
}
