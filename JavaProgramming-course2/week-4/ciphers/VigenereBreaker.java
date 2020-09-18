package ciphers;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();

        for (int i = whichSlice; i < message.length(); i+= totalSlices) {
            slice.append(message.charAt(i));
        }

        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker crack = new CaesarCracker(mostCommon);

        for (int i = 0; i < klength; ++i) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = crack.getKey(slice);
        }

        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();

        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        String lNames[] = {"Danish", "Dutch", "English", "French", "German", 
            "Italian", "Portuguese", "Spanish"};

        for (String l : lNames) {
            FileResource file = new FileResource("dictionaries/" + l);
            HashSet<String> dictionary = readDictionary(file);

            languages.put(l, dictionary);
        }

        String decrypted = breakForAllLangs(encrypted, languages);
        System.out.println("FIRST LINE:\n" + decrypted.split("\\n")[0]);
    }

    public HashSet<String> readDictionary(FileResource file) {
        HashSet<String> dictionary = new HashSet<String>();

        for (String line : file.lines()) {
            dictionary.add(line.toLowerCase());
        }

        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        
        for (String word : message.split("\\W")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }

        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String decrypted  = "";
        int maxRealWords  = 0;
        int realKey[]     = null;
        
        for (int kl = 1; kl <= 100; ++kl) {
            char mostCommon = mostCommonCharIn(dictionary);
            int key[] = tryKeyLength(encrypted, kl, mostCommon);

            VigenereCipher vc = new VigenereCipher(key);            
            String message = vc.decrypt(encrypted);

            int realWords = countWords(message, dictionary);
            

            if (realWords > maxRealWords) {
                maxRealWords = realWords;
                decrypted    = message;
                realKey      = key;
            }
        }

        printInfo(maxRealWords, realKey);

        return decrypted;
    }

    private void printInfo(int maxRealWords, int[] realKey) {
        System.out.println("VALID WORDS: " + maxRealWords);
        System.out.print("KEY: {");

        StringBuilder keyWord = new StringBuilder();

        for (int i = 0; i < realKey.length; ++i) {
            if (i < realKey.length - 1) {
                System.out.print(realKey[i] + ", ");
            } else {
                System.out.print(realKey[i] + "} -> ");
            }

            keyWord.append(Character.toString((char) ('a' + realKey[i])));

        }

        System.out.print(keyWord.toString().toUpperCase() + "\n\n");
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        int freq[] = new int[26];
        String alph = "abcdefghijklmnopqrstuvwxyz";

        for (String word : dictionary) {
            for (int i = 0; i < word.length(); ++i) {
                int index = alph.indexOf(Character.toLowerCase(word.charAt(i)));
                if (index != -1) {
                    freq[index]++;
                }
            }
        }

        int maxFreq = 0;
        int posMax  = 0;

        for (int i = 0; i < freq.length; ++i) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                posMax  = i;
            }
        }
        
        return alph.charAt(posMax);
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int best = 0;
        String decrypted = "";
        String language  = "";
        
        for (String langKey : languages.keySet()) {
            System.out.println("LANGUAGE: " + langKey);
            String message = breakForLanguage(encrypted, languages.get(langKey));
            int validWords = countWords(message, languages.get(langKey));

            if (validWords > best) {
                best      = validWords;
                decrypted = message;
                language  = langKey;
            }
        }
        
        System.out.println("LANGUAGE FOUND: " + language);
        return decrypted;
    }
}
