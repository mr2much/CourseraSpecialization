package src;


/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args) {
        convertToGrayscale();
        invertImages();
    }
    
    public static void convertToGrayscale() {
        GrayscaleConverter gsc = new GrayscaleConverter();
        
        gsc.loadSelectedImages();
        
        gsc.processImages("../img/grayscale_");
    }
    
    public static void invertImages() {
        InvertImageConverter iic = new InvertImageConverter();
        
        iic.loadSelectedImages();
        
        iic.processImages("../img/inverted_");
    }
}
