package tests;


/**
 * Write a description of FilesTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import src.FileManager;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FilesTester {
    public void performTests() {
        shouldFailIfPathDoesntExist();
        fileShouldHaveTenLines();
        shouldThrowErrorIfLinesAreNotEqual();
        System.out.println("Tests finished.");
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
}
