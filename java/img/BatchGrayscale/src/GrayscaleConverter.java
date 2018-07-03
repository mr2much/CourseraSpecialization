package src;


/**
 * Write a description of GrayscaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.nio.file.Path;
import java.nio.file.Paths;

public class GrayscaleConverter {
    public String[] getAllImagesIn(String src) {
        Path path = Paths.get(src);
        
        return path.toFile().list();
    }
}
