import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slice = "";
        for(int i = whichSlice; i < message.length(); i += totalSlices) {
            slice += message.charAt(i);
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; ++i) {
            String slice = sliceString(encrypted, i, klength);
            CaesarCracker ck = new CaesarCracker(mostCommon);
            key[i] = ck.getKey(slice);
        }
        return key;
    }
    
    private HashMap<String, HashSet<String>> readAllDictionaries() {
        HashMap<String, HashSet<String>> mapLangToDic = new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            String fileName = f.getName();
            FileResource fr = new FileResource(f);
            mapLangToDic.put(fileName, readDictionary(fr));
            System.out.println(fileName);
        }
        return mapLangToDic;
    }
    
    public void breakVigenere () {
        HashMap<String, HashSet<String>> mapLangToDic = readAllDictionaries(); 
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        breakForAllLangs(encrypted, mapLangToDic);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> hs = new HashSet<String>();
        for(String line : fr.lines()) {
            hs.add(line.toLowerCase());
        }
        return hs;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int count = 0;
        for(String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String getFirstLineFromMessage(String decrypted) {
        String firstLine = "";
        int i = 0;
        int length = decrypted.length();
        for(i = 0; i < length; ++i) {
            char ch = decrypted.charAt(i);
            if (ch == '\n') {
                break;
            } else {
                firstLine += ch;
            }
        }
        return firstLine;
    }
    
    public ArrayList<String> breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String possibleDecrypted = "";
        int maxRealWordsCount = -1;
        int keyLength = -1;
        String decrypted = "";
        char mostCommonCH = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; ++i) {
            int[] keys = tryKeyLength(encrypted, i, mostCommonCH);
            VigenereCipher vc = new VigenereCipher(keys);
            possibleDecrypted = vc.decrypt(encrypted);
            int count = countWords(possibleDecrypted, dictionary);
            if (count > maxRealWordsCount) {
                maxRealWordsCount = count;
                decrypted = possibleDecrypted;
                keyLength = i;
            }
        } 
        
        ArrayList<String> list = new ArrayList<String>();
        list.add(decrypted);
        list.add(Integer.toString(maxRealWordsCount));
        return list;
    }
    
    private HashMap<Character, Integer> mapCharsToCounts(HashSet<String> dictionary) {
        HashMap<Character, Integer> myMap = new HashMap<Character, Integer>();
        for (String str : dictionary) {
            int length = str.length();
            for (int i = 0; i < length; ++i) {
                char ch = str.charAt(i);
                if (!myMap.containsKey(ch)) {
                    myMap.put(ch, 1);
                } else {
                    myMap.put(ch, myMap.get(ch) + 1);
                }
            }
        }
        return myMap;        
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> myMap = mapCharsToCounts(dictionary);
        char ch = ' ';
        int count = -1;
        for(char currCh : myMap.keySet()) {
            int currCount = myMap.get(currCh);
            if (currCount > count) {
                ch = currCh;
                count = currCount;
            }
        }
        return ch;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int maxCount = -1;
        String decrypted = "";
        String langUsed = "";
        for(String lang : languages.keySet()) {
            ArrayList<String> list = breakForLanguage(encrypted, languages.get(lang));
            String currDecrypted = list.get(0);
            int currCount = Integer.parseInt(list.get(1));
            if (currCount > maxCount) {
                maxCount = currCount;
                decrypted = currDecrypted;
                langUsed = lang;
            }
        }
        
        System.out.println("The lang Used : " + langUsed + '\n');
        System.out.println("The First Line in the message: " + '\n' + getFirstLineFromMessage(decrypted) + '\n');
        System.out.println("The decrypted message : " + '\n' + decrypted + '\n');
    }
    
}
