package tests;


/**
 * Write a description of CharactersInPlayTests here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import src.CharactersInPlay;
import java.util.List;

public class CharactersInPlayTests {
    public void performTests() {
        canGetAName();
        testUpdate();
        testUpdateWithMultipleNames();
        // testFindAllCharacters();
        testFindMainCharacters();
        System.out.println("OK");
    }
    
    public void canGetAName() {
        CharactersInPlay cip = new CharactersInPlay();
        
        String name = cip.extractName("MACBETH. My dearest love,");
        
        assert "macbeth".equals(name);
    }
    
    public void testUpdate() {
        CharactersInPlay cip = new CharactersInPlay();
        
        cip.update("MACBETH. My dearest love,");
        cip.update("MACBETH. My dearest love,");        
        
        List<String> names = cip.getAllNames();
        List<Integer> frequencies = cip.getAllNameFrequencies();
        
        compare(names, new String[] {"macbeth"});
        compare (frequencies, new Integer[] {2});
    }
    
    public void testUpdateWithMultipleNames() {
        CharactersInPlay cip = new CharactersInPlay();
        
        cip.update("MACBETH. My dearest love,");
        cip.update("MACBETH. I don't know.");
        cip.update("FRESH. Gonna smash!");
        
        List<String> names = cip.getAllNames();
        List<Integer> frequencies = cip.getAllNameFrequencies();
        
        compare(names, new String[] {"macbeth", "fresh"});
        compare(frequencies, new Integer[] {2, 1});
    }
    
    public void testFindAllCharacters() {
        CharactersInPlay cip = new CharactersInPlay();
        
        cip.findAllCharacters();
        cip.showNamesAndFrequencies();
    }
    
    public void testFindMainCharacters() {
        CharactersInPlay cip = new CharactersInPlay();
        
        cip.findAllCharacters();
        cip.findMainCharacters(3, 4);
        
        List<String> mainCharacters = cip.getMainCharacters();
        
        compare(mainCharacters, new String[] {"macbeth"});
        
        cip.showMainCharacters();
    }
    
    private void compare(List<? extends Object> objectList, Object[] compareArr) {
        Object[] objectArr = objectList.toArray();
        
        for (int i = 0; i < compareArr.length; i++) {
            assert objectArr[i].equals(compareArr[i]) : 
            "Value: " + objectArr[i] + ", " + compareArr[i];
        }
    }
}
