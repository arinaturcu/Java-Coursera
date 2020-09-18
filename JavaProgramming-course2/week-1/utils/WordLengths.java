package utils;

import edu.duke.*;

public class WordLengths {
    private void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int n = word.length();

            if (!Character.isLetter(word.charAt(0))) {
                n--;
            }
            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                n--;
            }

            if (n >= counts.length) {
                counts[counts.length - 1]++;
            } else if (n > 0) {
                counts[n]++;
            }
        }
    }

    private int indexOfMax(int[] values) {
        int currMax = 0;
        int currMaxIndex = 0;

        for (int i = 0; i < values.length; ++i) {
            if (values[i] > currMax) {
                currMax = values[i];
                currMaxIndex = i;
            }
        }

        return currMaxIndex;
    }

    public void test() {
        FileResource f = new FileResource();

        int counts[] = new int[40];
        countWordLengths(f, counts);

        System.out.println("The most common lenght: " + indexOfMax(counts));

        for (int i = 0; i < counts.length; ++i) {
            System.out.println(i + ": " + counts[i]);
        }
    }
}
