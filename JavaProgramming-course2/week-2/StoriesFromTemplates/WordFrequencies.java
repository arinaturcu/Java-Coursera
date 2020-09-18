package StoriesFromTemplates;

import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> MyWords;
    private ArrayList<Integer> MyFreqs;

    public WordFrequencies() {
        MyWords = new ArrayList<String>();
        MyFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        MyWords.clear();
        MyFreqs.clear();

        FileResource file = new FileResource();

        for (String word : file.words()) {
            int index = MyWords.indexOf(word.toLowerCase());

            if (index == -1) {
                MyWords.add(word.toLowerCase());
                MyFreqs.add(1);
            } else {
                int value = MyFreqs.get(index);
                MyFreqs.set(index, value + 1);
            }
        }
    }

    public int findIndexOfMax() {
        int index = -1;
        int max = -1;
        
        for (int i = 0; i < MyFreqs.size(); ++i) {
            if (MyFreqs.get(i) > max) {
                max = MyFreqs.get(i);
                index = i;
            }
        }

        return index;
    }

    public void testFindUnique() {
        findUnique();

        System.out.println("Number of unique words: " + MyWords.size());

        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "
            + MyWords.get(index) + " " + MyFreqs.get(index)
        );  
    }
}
