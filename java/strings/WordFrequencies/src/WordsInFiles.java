package src;


/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class WordsInFiles {
    List<File> fileList;
    Map<String, List<String>> wordsMap;
    
    public WordsInFiles() {
        fileList = new ArrayList<>();
        wordsMap = new HashMap<>();
    }
    
    public void readFiles() {
        wordsMap.clear();
        fileList = getFilesFromDirectory();
        
        for (File filename : fileList) {
            FileResource fr = new FileResource(filename);
            
            addWordsFromFile(filename);
        }
    }
    
    public void addWordsFromFile(File f) {
        List<String> contents = getWordsFromFile(f);

        for (String word : contents) {
            if (wordsMap.containsKey(word)) {
                List<String> list = wordsMap.get(word);
                
                if (!list.contains(f.getName())) {
                    list.add(f.getName());
                }
                
                wordsMap.put(word, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(f.getName());
                wordsMap.put(word, list);
            }
        }
    }
    
    public List<File> getFilesFromDirectory() {
        List<File> files = new ArrayList<>();
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File filename : dr.selectedFiles()) {
            files.add(filename);
        }
        
        return files;
    }
    
    public List<String> getWordsFromFile(File file) {
        List<String> fileContents = new ArrayList<>();
        
        FileResource fr = new FileResource(file);
        
        for (String word : fr.words()) {
            fileContents.add(word);
        }
        
        return fileContents;
    }
    
    public int maxNumber() {
        int max = Integer.MIN_VALUE;
        
        for (String key : wordsMap.keySet()) {
            List<String> list = wordsMap.get(key);
            
            if (list.size() > max) {
                max = list.size();
            }
        }
        
        return max;
    }
    
    public List<String> wordsInNumFiles(int number) {
        List<String> words = new ArrayList<>();
        
        for (String key : wordsMap.keySet()) {
            List<String> list = wordsMap.get(key);
            
            if (list.size() == number) {
                words.add(key);
            }
        }
        
        return words;
    }
    
    public void showKeysOnMap() {
        System.out.println("Displaying keys");
        for (String key : wordsMap.keySet()) {
            System.out.println(key);
        }
    }
    
    public void showFilesInLists() {
        System.out.println("Displaying contents.");
        for (String key : wordsMap.keySet()) {
            System.out.println("Filenames associated with key: " + key);
            for (String filename : wordsMap.get(key)) {
                System.out.println(filename);
            }
        }
    }
}
