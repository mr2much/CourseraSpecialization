import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public void breakVigenere () {
        Map<String, HashSet<String>> languages = new HashMap<>();
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File file : dr.selectedFiles()) {
            String language = file.getName();
            System.out.println("Reading " + language + " dictionary.");
            FileResource fr = new FileResource(file);
            HashSet<String> dictionary = readDictionary(fr);
            
            if (!languages.containsKey(language)) {
                languages.put(language, dictionary);
            }            
        }
        
        FileResource fr = new FileResource();
            
        String encrypted = fr.asString();
        String decrypted = breakForAllLangs(encrypted, languages);
        System.out.println();
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        
        for (String word : fr.lines()) {
            dictionary.add(word.toLowerCase());
        }
        
        return dictionary;
    }
    
    public String breakForAllLangs(String encrypted, Map<String, HashSet<String>> languages) {
        String decrypted = "";
        int maxWords = Integer.MIN_VALUE;
        String lang = "";
        
        for (String language : languages.keySet()) {
            System.out.println("Testing for language: " + language);
            HashSet dictionary = languages.get(language);
            decrypted = breakForLanguage(encrypted, dictionary);
            int wordCount = countWords(decrypted, dictionary);
            
            if (wordCount > maxWords) {
                maxWords = wordCount;
                lang = language;
            }
        }
        
        System.out.println("Decrypted using language: " + lang);
        
        return decrypted;
    }
    
    public String breakForLanguage(String encrypted, 
        HashSet<String> dictionary) {
        
        String decrypted = "";
        int validWords = Integer.MIN_VALUE;
        int wordCount = 0;
        int keyLength = 0;
        int[] keysFound = {};
        
        char commonChar = mostCommonCharIn(dictionary);
        
        for (int i = 1; i <= 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, commonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String message = vc.decrypt(encrypted);
            
            wordCount = countWords(message, dictionary);
            
            if (wordCount > validWords) {
                validWords = wordCount;
                decrypted = message;
                keyLength = i;
                keysFound = keys;
            }
        }
        
        System.out.println("Keys: " + Arrays.toString(keysFound));
        System.out.println("They key length found was: " + keyLength);
        
        System.out.println("Valid Words: " + validWords + 
            ", Word Count: " + encrypted.split("\\W+").length);
            
        return decrypted;        
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        Map<Character, Integer> letterCount = new HashMap<>();
        int highestCount = Integer.MIN_VALUE;
        char mostCommon = ' ';
        
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char letter = word.charAt(i);
                
                if (letterCount.containsKey(letter)) {
                    letterCount.put(letter, letterCount.get(letter) + 1);
                } else {
                    letterCount.put(letter, 1);
                }
            }
        }
        
        for (Character letter : letterCount.keySet()) {
            if (letterCount.get(letter) > highestCount) {
                highestCount = letterCount.get(letter);
                mostCommon = letter;
            }
        }
        
        return mostCommon;
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        
        for (int i = 0; i < key.length; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);;
        }
        
        return key;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slice.append(message.charAt(i));
        }
        
        return slice.toString();
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int wordCount = 0;
        
        String[] words = message.split("\\W+");
        
        for (String word : words) {
            word = word.toLowerCase();
            
            if (dictionary.contains(word)) {
                wordCount++;
            }
        }
        
        return wordCount;
    }
    
}
