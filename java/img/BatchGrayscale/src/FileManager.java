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
import edu.duke.ImageResource;

public class FileManager {
    public static File[] loadAllFilesIn(String dir) {
        Path path = Paths.get(dir);
        
        return path.toFile().listFiles();
    }
    
    public static void saveAsNewImage(ImageResource input, String prefix) {
        StringBuilder newFilename = new StringBuilder(prefix);
        
        newFilename.append(input.getFileName());
        
        input.setFileName(newFilename.toString());
        input.save();
    }
}
