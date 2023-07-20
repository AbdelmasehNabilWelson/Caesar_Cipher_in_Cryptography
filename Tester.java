import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author (Abdelmaseh Nabil) 
 * @version (7/12/2023)
 */
public class Tester {
    public void testCeaserCipher() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        CaesarCipher cc = new CaesarCipher(4);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }
    
    public void testCaesarCracker() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        
        CaesarCracker ck = new CaesarCracker();
        int key = ck.getKey(message);
        System.out.println(key);
        CaesarCipher cc = new CaesarCipher(key);
        System.out.println(cc.decrypt(message));
        
        FileResource frPort = new FileResource();
        String messagePort = frPort.asString();
        
        char mostCommonCharInPort = 'a';
        CaesarCracker ckPort = new CaesarCracker(mostCommonCharInPort);
        System.out.println(ckPort.getKey(messagePort));
    }
    
    public void testVigenereCipher() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        int[] keys = {17, 14, 12, 4};
        VigenereCipher vc = new  VigenereCipher(keys);
        String encrypted = vc.encrypt(message);
        String decrypted = vc.decrypt(encrypted);
        System.out.println(encrypted + '\n' + decrypted);
    }
    
}
