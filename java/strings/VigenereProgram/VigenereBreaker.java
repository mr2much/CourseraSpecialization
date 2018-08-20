import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slice.append(message.charAt(i));
        }
        
        return slice.toString();
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

    public void breakVigenere () {
        FileResource fr = new FileResource();
        
        String encrypted = fr.asString();
        
        int[] keys = tryKeyLength(encrypted, 5, 'e');
        
        VigenereCipher vc = new VigenereCipher(keys);
        
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
}
