import edu.duke.*;
import java.util.HashSet;
/**
 * This class contains test on the VigenereBreaker Class.
 * @author (Abdelmaseh Nabil) 
 * @version (7/12/2023)
 */
public class TestVigenereBreaker {
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println("Test for SliceString method: ");
        String message = "abcdefghijklm";
        for(int j = 3; j <= 5; ++j) {
            for(int i = 0; i < j; ++i) {
                System.out.println(vb.sliceString(message, i, j));
            }
        }
    }
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource(); // athens_keyflute.txt
        String encrypted = fr.asString();
        
        String key = "flute";
        VigenereBreaker vb = new VigenereBreaker();
        int[] keysLength = vb.tryKeyLength(encrypted, key.length(), 'e');
        for(int i = 0; i < key.length(); ++i) {
            System.out.println(keysLength[i]);
        }
    }
    
    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }
    
    public void testReadDictionary() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet<String> hs = vb.readDictionary(fr);
        for(String str : hs) {
            System.out.println(str);
        }
    }
    
    public void testMostCommonChar() {
        FileResource fr = new FileResource(); // english
        VigenereBreaker vb = new VigenereBreaker();
        HashSet<String> dic = vb.readDictionary(fr);
        char mostCommonCH = vb.mostCommonCharIn(dic);
        System.out.println(mostCommonCH); // e
    } 
}
