import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public void breakVigenere () {
        FileResource fr = new FileResource();
        FileResource dictionaryResource = 
            new FileResource("dictionaries/English");
            
        String encrypted = fr.asString();
        HashSet<String> dictionary = readDictionary(dictionaryResource);         
        
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();
        
        for (String word : fr.lines()) {
            dictionary.add(word.toLowerCase());
        }
        
        return dictionary;
    }
    
    public String breakForLanguage(String encrypted, 
        HashSet<String> dictionary) {
        
        String decrypted = "";
        int validWords = Integer.MIN_VALUE;
        int wordCount = 0;
        int keyLength = 0;
        
        for (int i = 1; i <= 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String message = vc.decrypt(encrypted);
            
            wordCount = countWords(message, dictionary);
            
            if (wordCount > validWords) {
                validWords = wordCount;
                decrypted = message;
                keyLength = i;
            }
        }
        
        System.out.println("They key length found was: " + keyLength);
        
        System.out.println("Valid Words: " + validWords + 
            ", Word Count: " + encrypted.split("\\W+").length);
            
        return decrypted;        
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
}
