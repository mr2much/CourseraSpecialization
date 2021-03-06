package src;


/**
 * Write a description of InvertImageTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.io.File;
import edu.duke.ImageResource;
import java.nio.file.Paths;

public class InvertImageTester {
    public void performTest() {
        getFilenamesInPath();
        testInvertImageConverter();
        testSavingNewImage();
        System.out.println("Tests finished.");
    }
    
    public void getFilenamesInPath() {
        InvertImageConverter iic = new InvertImageConverter();
        
        iic.loadAllImagesIn("images");
        List<File> filenames = iic.getAllImages();
        
        compare(filenames, new String[] {"images\\astrachan.jpg", 
            "images\\dinos.png", "images\\duke_blue_devil.png", 
            "images\\duvall.jpg", "images\\eastereggs.jpg", 
            "images\\hilton.jpg", "images\\pixabayhands.jpg", 
            "images\\rodger.png", "images\\smallhands.png", 
            "images\\smallpanda.png"});
    }
    
    private void compare(List<File> list, String[] otherArray) {
        Object[] arr = list.toArray();
        
        for (int i = 0; i < otherArray.length; i++) {
            assert arr[i].toString().equals(otherArray[i]) : "Value of i: " + i;
        }
    }
    
    public void testInvertImageConverter() {
        ImageResource res = new ImageResource(Paths.get("images",
            "duvall.jpg").toFile());
        
        InvertImageConverter iic = new InvertImageConverter();
        
        ImageResource other = iic.invertImage(res);
        
        other.draw();
    }
    
    public void testSavingNewImage() {
        ImageResource res = new ImageResource(Paths.get("images",
            "duvall.jpg").toFile());
            
        FileManager.saveAsNewImage(res, "../img/inverted_");
    }
}
