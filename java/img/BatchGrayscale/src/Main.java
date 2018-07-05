package src;


/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    public static void main(String[] args) {
        execute();
    }
    
    public static void execute() {
        GrayscaleConverter gsc = new GrayscaleConverter();
        
        gsc.loadSelectedImages();
        
        gsc.processImages();
    }
}
