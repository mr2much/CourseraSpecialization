package src;


/**
 * Write a description of Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main {
    
    public static void main(String[] args) {
        testWithFullFile();
    }
    
    public static void testWithFullFile() {
        CharactersInPlay cip = new CharactersInPlay();
        
        cip.findAllCharacters();
        cip.findMainCharacters(50, 500);
        cip.showMainCharacters();
    }
}
