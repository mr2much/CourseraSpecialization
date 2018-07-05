package src;


/**
 * Write a description of FileManagerTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import edu.duke.ImageResource;

public class FileManagerTest {
    public void performTest() {
        getFilenamesInPath();
        testConvertingImageToGrayscale();
        testSavingNewImage();
        System.out.println("Tests finished.");
    }
    
    public void getFilenamesInPath() {
        GrayscaleConverter gsc = new GrayscaleConverter();
        
        gsc.loadAllImagesIn("images");
        List<File> filenames = gsc.getAllImages();
        
        compare(filenames, new String[] {"images\\astrachan.jpg", 
            "images\\dinos.png", "images\\duke_blue_devil.png", 
            "images\\duvall.jpg", "images\\eastereggs.jpg", 
            "images\\hilton.jpg", "images\\hippieflower.jpg", 
            "images\\pixabayhands.jpg", "images\\rodger.png", 
            "images\\smallhands.png", "images\\smallpanda.png"});
    }
    
    private void compare(List<File> list, String[] otherArray) {
        Object[] arr = list.toArray();
        
        for (int i = 0; i < otherArray.length; i++) {
            assert arr[i].toString().equals(otherArray[i]);
        }
    }
    
    public void testConvertingImageToGrayscale() {
        ImageResource res = new ImageResource(Paths.get("images",
            "pixabayhands.jpg").toFile());
        
        GrayscaleConverter gsc = new GrayscaleConverter();
        
        ImageResource other = gsc.convertToGrayscale(res);
        
        other.draw();
    }
    
    public void testSavingNewImage() {
        ImageResource res = new ImageResource(Paths.get("images",
            "pixabayhands.jpg").toFile());
            
        FileManager.saveAsNewImage();
    }
}
