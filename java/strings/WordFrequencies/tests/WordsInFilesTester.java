package tests;


/**
 * Write a description of WordsInFilesTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import src.WordsInFiles;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import edu.duke.DirectoryResource;

public class WordsInFilesTester {
    public void performTests() {
        testOpenSeveralFilesAndReadItsContents();
        // testGettingFileList();
        // testAddWordsFromFile();
        testMaxNumber();
        System.out.println("OK");
    }
    
    public void testOpenSeveralFilesAndReadItsContents() {
        WordsInFiles wf = new WordsInFiles();
        
        wf.readFiles();
        
        wf.showKeysOnMap();
        wf.showFilesInLists();
    }
    
    public void testGettingFileList() {
        WordsInFiles wf = new WordsInFiles();
        
        List<File> files = wf.getFilesFromDirectory();
        
        compare(files, new String[] {"brief1.txt", "brief2.txt", "brief3.txt",
            "brief4.txt"});
    }
    
    public void testAddWordsFromFile() {
        WordsInFiles wf = new WordsInFiles();
        
        DirectoryResource dr = new DirectoryResource();
        
        List<String> words = new ArrayList<>();
        
        for (File file : dr.selectedFiles()) {
            words = wf.getWordsFromFile(file);
        }
        
        compare(words, new String[] {"cats", "are", "funny", "and", "cute"});
        
    }
    
    private void compare(Collection<? extends Object> coll, Object[] cArr) {
        Object[] arr = coll.toArray(new Object[coll.size()]);
        
        for (int i = 0; i < cArr.length; i++) {
            assert arr[i].toString().contains(cArr[i].toString()) : "Value: " +
                arr[i] + "\t" + cArr[i];
        }
    }
    
    public void testMaxNumber() {
        WordsInFiles wf = new WordsInFiles();
        
        wf.readFiles();
        
        assert 3 == wf.maxNumber();
    }
}
