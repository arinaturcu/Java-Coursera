import java.util.ArrayList;

import edu.duke.*;

public class Test {


    public static void main(String[] args) {
        FileResource file1 = new FileResource();
        FileResource file2 = new FileResource();
        FileResource file3 = new FileResource();
        FileResource file4 = new FileResource();
        FileResource file5 = new FileResource();

        ArrayList<String> array1 = new ArrayList<String>();
        ArrayList<String> array2 = new ArrayList<String>();
        ArrayList<String> array3 = new ArrayList<String>();
        ArrayList<String> array4 = new ArrayList<String>();
        ArrayList<String> array5 = new ArrayList<String>();

        int count = 0;

        for (String word : file1.words()) {
            if (array1.contains(word)) continue;
            array1.add(word);
        }
        for (String word : file2.words()) {
            if (array2.contains(word)) continue;
            array2.add(word);
        }
        for (String word : file3.words()) {
            if (array3.contains(word)) continue;
            array3.add(word);
        }
        for (String word : file4.words()) {
            if (array4.contains(word)) continue;
            array4.add(word);
        }
        for (String word : file5.words()) {
            if (array5.contains(word)) continue;
            array5.add(word);
        } 

        for (String word : array1) {
            if (array2.contains(word) && array3.contains(word) && array4.contains(word) &&  array5.contains(word)) {
                count++;
            }
        }

        for (String word : array1) {
            if (array2.contains(word) && array3.contains(word) && array4.contains(word)) {
                count++;
            }
        }
        for (String word : array1) {
            if (array2.contains(word) && array3.contains(word) && array5.contains(word)) {
                count++;
            }
        }
        for (String word : array1) {
            if (array2.contains(word) && array4.contains(word) && array5.contains(word)) {
                count++;
            }
        }
        for (String word : array1) {
            if (array3.contains(word) && array4.contains(word) && array5.contains(word)) {
                count++;
            }
        }
        for (String word : array2) {
            if (array3.contains(word) && array4.contains(word) && array5.contains(word)) {
                count++;
            }
        }

        System.out.println(count);
    }
}