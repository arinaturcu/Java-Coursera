package OOP_CC;

import edu.duke.*;

public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();

        for (int i = start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }

        return half.toString();
    }

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
        String half1 = halfOfString(input, 0);
        String half2 = halfOfString(input, 1);

        int key1 = getKey(half1);
        int key2 = getKey(half2);

        CaesarCipherTwoOOP c = new CaesarCipherTwoOOP(26 - key1, 26 - key2);

        return c.encrypt(input);
    }

    public void test() {
        CaesarCipherTwoOOP c = new CaesarCipherTwoOOP(17, 3);

        FileResource f = new FileResource();
        String input = f.asString();

        String encrypted = c.encrypt(input);

        System.out.println(encrypted);
        System.out.println(breakCaesarCipher(encrypted));
    }
}