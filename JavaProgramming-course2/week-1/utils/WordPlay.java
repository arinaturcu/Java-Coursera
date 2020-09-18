package utils;

public class WordPlay {
    public static boolean isVowel(char c) {
        String vowels = "aeiou";

        if (vowels.indexOf(c) != -1 || vowels.toUpperCase().indexOf(c) != -1) {
            return true;
        }
        
        return false;
    }

    public static String replaceVowels(String phrase, char ch) {
        StringBuilder phraseMod = new StringBuilder(phrase);

        for (int i = 0; i < phrase.length(); ++i) {
            if (isVowel(phrase.charAt(i))) {
                phraseMod.setCharAt(i, ch);
            }
        }
        
        return phraseMod.toString();
    }

    public static String emphasize(String phrase, char ch) {
        StringBuilder phraseMod = new StringBuilder(phrase);

        for (int i = 0; i < phrase.length(); ++i) {
            if (phrase.charAt(i) == ch) {
                if (i % 2 == 0) {
                    phraseMod.setCharAt(i, '*');
                } else {
                    phraseMod.setCharAt(i, '+');
                }
            }
        }

        return phraseMod.toString();
    }
}