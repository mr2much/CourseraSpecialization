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
import edu.duke.DirectoryResource;
import java.util.List;
import java.util.ArrayList;

public class FileManager {
    public static File[] loadAllFilesIn(String dir) {
        Path path = Paths.get(dir);
        
        return path.toFile().listFiles();
    }
    
    public static File[] loadSelectedFiles() {
        List<File> files = new ArrayList<>();
        DirectoryResource dr = new DirectoryResource();
        
        for (File file : dr.selectedFiles()) {
            files.add(file);
        }
        
        return files.toArray(new File[files.size()]);
    }
    
    public static void saveAsNewImage(ImageResource input, String prefix) {
        StringBuilder newFilename = new StringBuilder(prefix);
        
        newFilename.append(input.getFileName());
        
        input.setFileName(newFilename.toString());
        input.save();
    }
}
