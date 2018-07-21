package src;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.List;
import java.util.ArrayList;
import edu.duke.FileResource;

public class CharactersInPlay {
    List<String> allNames;
    List<String> mainCharacters;    
    List<Integer> nameFrequencies;
    List<Integer> mainCharacterFrequencies;
    
    public List<String> getAllNames() { return allNames; }
    public List<String> getMainCharacters() { return mainCharacters; }
    public List<Integer> getAllNameFrequencies() { return nameFrequencies; }
    
    public CharactersInPlay() {
        allNames = new ArrayList<>();
        nameFrequencies = new ArrayList<>();
        mainCharacters = new ArrayList<>();
        mainCharacterFrequencies = new ArrayList<>();
    }
    
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        
        for (String line : fr.lines()) {
            update(line);
        }
    }
    
    public void update(String input) {
        String name = findName(input);
        if (!name.isEmpty()) {            
            if (!allNames.contains(name)) {
                allNames.add(name);
                nameFrequencies.add(1);
            } else {
                int index = allNames.indexOf(name);
                int currentFrequency = nameFrequencies.get(index);
                nameFrequencies.set(index, currentFrequency + 1);
            }
        }        
    }
    
    public String extractName(String input) {
        return findName(input);
    }
    
    private String findName(String input) {
        int startIndex = 0;
        int stopIndex = input.indexOf(".");
        
        if (stopIndex < 0) {
            return "";
        }
        
        return input.substring(startIndex, stopIndex).toLowerCase().trim();
    }
    
    public void showNamesAndFrequencies() {
        System.out.printf("There are %d characters in the file.%n",
            allNames.size());
        for (int i = 0; i < allNames.size(); i++) {
            System.out.printf("Character: %s speaks %d times.%n",
                allNames.get(i), nameFrequencies.get(i));
        }
    }
    
    public void findMainCharacters(int spokenLines, int maxSpokenLines) {
        charactersWithNumParts(spokenLines, maxSpokenLines);
    }
    
    public void charactersWithNumParts(int spokenLines, 
        int maxSpokenLines) {
        
        for (int i = 0; i < allNames.size(); i++) {
            int frequency = nameFrequencies.get(i);
            
            if (frequency >= spokenLines && frequency < maxSpokenLines) {
                String name = allNames.get(i);
                mainCharacters.add(name);
                mainCharacterFrequencies.add(frequency);
            }
        }
    }
    
    public void showMainCharacters() {
        for (int i = 0; i < mainCharacters.size(); i++) {
            System.out.printf("%s speaks %d times.%n", 
                mainCharacters.get(i), mainCharacterFrequencies.get(i));
        }
    }
}
