package utils;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder message = new StringBuilder(input);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String modAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i = 0; i < input.length(); ++i) {
            int indexUp = alphabet.indexOf(input.charAt(i));
            int indexLow = alphabet.toLowerCase().indexOf(input.charAt(i));

            if (indexUp != -1) {
                message.setCharAt(i, modAlphabet.charAt(indexUp));
            } else if (indexLow != -1) {
                message.setCharAt(i, modAlphabet.toLowerCase().charAt(indexLow));
            }
        }

        return message.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        String message;

        // split the input into 2 strings
        String subString1 = halfOfString(input, 0);
        String subString2 = halfOfString(input, 1);

        // encrypt the 2 strings
        String encSubString1 = encrypt(subString1.toString(), key1);
        String encSubString2 = encrypt(subString2.toString(), key2);

        // combine for final encrypted message
        message = makeWhole(encSubString1, encSubString2);

        return message;
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

    public String decrypt(String encrypted) {
        return encrypt(encrypted, 26 - getKey(encrypted));
    }

    private String halfOfString(String message, int start) {
        StringBuilder half = new StringBuilder();

        for (int i = start; i < message.length(); i += 2) {
            half.append(message.charAt(i));
        }

        return half.toString();
    }

    private String makeWhole(String string1, String string2) {
        StringBuilder whole = new StringBuilder();
        int i = 0;
        int j = 0;

        for (int k = 0; k < string1.length() + string2.length(); ++k) {
            if (k % 2 == 0) {
                whole.append(string1.charAt(i));
                i++;
            } else {
                whole.append(string2.charAt(j));
                j++;
            }
        }

        return whole.toString();
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
    
    public String decryptTwoKeys(String encrypted) {
        String half1 = halfOfString(encrypted, 0);
        String half2 = halfOfString(encrypted, 1);

        int key1 = getKey(half1);
        int key2 = getKey(half2);

        System.out.println("Keys used to encrypt: " + key1 + ", " + key2);

        String deHalf1 = encrypt(half1, 26 - key1);
        String deHalf2 = encrypt(half2, 26 - key2);

        return makeWhole(deHalf1, deHalf2);
    }
}
