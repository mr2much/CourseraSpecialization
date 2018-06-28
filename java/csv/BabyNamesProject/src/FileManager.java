package src;
/**
 * Write a description of FileManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class FileManager {
    public static String RESOURCE_PATH = "res";
    public static String DECADE_PATH = "us_babynames_by_decade";
    public static String YEAR_PATH = "us_babynames_by_year";
    public static String TEST_PATH = "us_babynames_test";
 
    String filename;
    
    public void setFilename(String filename) { this.filename = filename; }
    public String getFilename() { return filename; }
    
    public FileManager() { }
    
    public CSVParser getCSVParser() {
        // String filename = String.format(pattern, year);
        FileResource fr = new FileResource(Paths.get(filename).toString());
        
        return fr.getCSVParser(false);
    }
    
    public CSVParser getCSVParser(String filename) {
        FileResource fr = new FileResource(Paths.get(filename).toString());
        
        return fr.getCSVParser(false);
    }
    
    public List<String> readAllLines(Path path) throws IOException {
        if (pathExists(path)) {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        
        return new ArrayList<String>();
    }
    
    public NamesList getNamesListFrom(List<String> fileInfo) {
        NamesList namesList = new NamesList();
        
        for (String line : fileInfo) {
            namesList.insert(line);
        }
        
        return namesList;
    }
    
    public NamesList getNamesListFrom(CSVParser parser) {
        NamesList namesList = new NamesList();
        
        for (CSVRecord record : parser) {
            namesList.insert(record);
        }
        
        return namesList;
    }
    
    public boolean pathExists(Path path) {
        return Files.exists(path);
    }
}
