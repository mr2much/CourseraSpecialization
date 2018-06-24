package src;
/**
 * Write a description of FileManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

public class FileManager {
    public static String RESOURCE_PATH = "res";
    public static String DECADE_PATH = "us_babynames_by_decade";
    public static String YEAR_PATH = "us_babynames_by_year";
 
    public List<String> readAllLines(Path path) throws IOException {
        if (pathExists(path)) {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        
        return new ArrayList<String>();
    }
    
    public NamesList getNamesListFrom(List<String> fileInfo) {
        return new NamesList();
    }
    
    public boolean pathExists(Path path) {
        return Files.exists(path);
    }
}
