package src;


/**
 * Write a description of FileManagerTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagerTest {
    public void performTest() {
        getFilenamesInPath();
        System.out.println("Tests finished.");
    }
    
    public void getFilenamesInPath() {
        GrayscaleConverter gsc = new GrayscaleConverter();
        
        String[] filenames = gsc.getAllImagesIn("images");

        compare(filenames, new String[] {"astrachan.jpg", "dinos.png",
            "duke_blue_devil.png", "duvall.jpg", "eastereggs.jpg", "hilton.jpg",
            "hippieflower.jpg", "pixabayhands.jpg", "rodger.png", 
            "smallhands.png", "smallpanda.png"});
    }
    
    private void compare(Object[] values, String[] otherArray) {
        for (int i = 0; i < otherArray.length; i++) {
            assert values[i].equals(otherArray[i]);
        }
    }
}
