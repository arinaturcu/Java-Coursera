package OOP_CC;

import edu.duke.*;

public class TestCaesarCipher {
    private int[] countLetters(String input) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] freq = new int[26];
        
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if (!Character.isLetter(ch)) continue;

            int index = alphabet.toLowerCase().indexOf(Character.toLowerCase(ch));
            freq[index]++;
        }

        return freq;
    }

    private int maxIndex(int[] freq) {
        int currMax = 0;
        int currMaxIndex = 0;

        for (int i = 0; i < freq.length; ++i) {
            if (freq[i] > currMax) {
                currMax = freq[i];
                currMaxIndex = i;
            }
        }

        return currMaxIndex;
    }

    private int getKey(String s) {
        int[] freq = countLetters(s);
        int index = maxIndex(freq);
        int dkey = index - 4;

        if (index < 4) {
            dkey = 26 - (4 - index);
        } 
        
        return dkey;
    }

    public String breakCaesarCipher(String input) {
        int key = getKey(input);

        CaesarCipherOOP cc2 = new CaesarCipherOOP(key);
        return cc2.decrypt(input);
    }

    public void test() {
        FileResource fr = new FileResource();
        String message = fr.asString();

        CaesarCipherOOP cc2 = new CaesarCipherOOP(18);

        String encrypted = cc2.encrypt(message);
        System.out.println(encrypted);

        String decrypted = breakCaesarCipher(encrypted);
        System.out.println(decrypted);
    }
}