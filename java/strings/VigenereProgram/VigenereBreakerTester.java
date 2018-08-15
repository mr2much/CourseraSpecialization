
/**
 * Write a description of VigenereBreakerTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

public class VigenereBreakerTester {
    private VigenereBreaker vb = new VigenereBreaker();
    
    public void performTests() {
        testSliceString();
        testKeyLength();
        System.out.println("OK");
    }
    
    public void testSliceString() {
        assert vb.sliceString("abcdefghijklm", 0, 3).equals("adgjm");
        assert vb.sliceString("abcdefghijklm", 1, 3).equals("behk");
        assert vb.sliceString("abcdefghijklm", 2, 3).equals("cfil");
        assert vb.sliceString("abcdefghijklm", 0, 4).equals("aeim");
        assert vb.sliceString("abcdefghijklm", 1, 4).equals("bfj");
        assert vb.sliceString("abcdefghijklm", 2, 4).equals("cgk");
        assert vb.sliceString("abcdefghijklm", 3, 4).equals("dhl");
        assert vb.sliceString("abcdefghijklm", 0, 5).equals("afk");
        assert vb.sliceString("abcdefghijklm", 1, 5).equals("bgl");
        assert vb.sliceString("abcdefghijklm", 2, 5).equals("chm");
        assert vb.sliceString("abcdefghijklm", 3, 5).equals("di");
        assert vb.sliceString("abcdefghijklm", 4, 5).equals("ej");
    }
    
    public void testKeyLength() {
        FileResource fr = new FileResource("messages/athens_keyflute.txt");
        int[] keys = vb.tryKeyLength(fr.asString(), 4, 'e');
        
        compare(keys, new int[] {5, 11, 20, 19, 4});
    }
    
    private void compare(int[] a1, int[] a2) {
        for(int i : a1) {
            System.out.println(i);
        }
        for (int i = 0; i < a1.length; i++) {
            assert a1[i] == a2[i] : "Value: " + a1[i] + ", " + a2[i];
        }
    }
}
