package src;


/**
 * Write a description of FileManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static File[] loadAllFilesIn(String dir) {
        Path path = Paths.get(dir);
        
        return path.toFile().listFiles();
    }
}
