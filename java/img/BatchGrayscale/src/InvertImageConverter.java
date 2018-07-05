package src;


/**
 * Write a description of InvertImageConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class InvertImageConverter {
    List<File> allImages;
    
    {
        allImages = new ArrayList<>();
    }
    
    public void loadAllImagesIn(String dir) {
        File[] files = FileManager.loadAllFilesIn(dir);
        
        allImages.addAll(Arrays.asList(files));
    }
    
    public void loadSelectedImages() {
        File[] files = FileManager.loadSelectedFiles();
        
        allImages.addAll(Arrays.asList(files));
    }
    
    public List<File> getAllImages() {
        return allImages;
    }
    
    public void processImages() {
        for (File file : allImages) {
            ImageResource imgRsc = new ImageResource(file);
            ImageResource newImg = invertImage(imgRsc);
            
            FileManager.saveAsNewImage(newImg, "inverted_");
        }
    }
    
    public ImageResource invertImage(ImageResource imgRes) {
        return calculatePixels(imgRes);
    }
    
    private ImageResource calculatePixels(ImageResource input) {
        ImageResource newImg = input;
        
        for (Pixel pxl : newImg.pixels()) {
            int r = pxl.getRed();
            int g = pxl.getGreen();
            int b = pxl.getBlue();
            pxl.setRed(255 - r);
            pxl.setGreen(255 - g);
            pxl.setBlue(255 - b);
        }
        
        return newImg;
    }
}
