package src;


/**
 * Write a description of GrayscaleConverter here.
 * 
 * @author (Anubis Lockward) 
 * @version (03/07/2018)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class GrayscaleConverter {
    List<File> allImages;
    
    {
        allImages = new ArrayList<>();
    }
    
    public void loadAllImagesIn(String dir) {
        File[] files = FileManager.loadAllFilesIn(dir);
        
        allImages.addAll(Arrays.asList(files));
    }
    
    public List<File> getAllImages() {
        return allImages;
    }
    
    public void processImages() {
        for (File file : allImages) {
            ImageResource imgRsc = new ImageResource(file);
            convertToGrayscale(imgRsc);
        }
    }
    
    public ImageResource convertToGrayscale(ImageResource imageRes) {
        System.out.println("Converting image " + imageRes.getFileName());
        
        return calculatePixels(imageRes);
    }
    
    private ImageResource calculatePixels(ImageResource input) {
        ImageResource newImg = input;
        
        for (Pixel pxl : newImg.pixels()) {
            int r = pxl.getRed();
            int g = pxl.getGreen();
            int b = pxl.getBlue();
            int avg = (r + g + b) / 3;
            pxl.setRed(avg);
            pxl.setGreen(avg);
            pxl.setBlue(avg);
        }
        
        return newImg;
    }
}
