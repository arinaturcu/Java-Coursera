package ImprovingGladLibs;
import edu.duke.*;

import java.io.*;
import java.util.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;

    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);

        for (String word : fr.words()) {
            ArrayList<String> files;

            if (map.containsKey(word)) {
                files = map.get(word);
                if (files.contains(f.getName())) {
                    continue;
                }
            } else {
                files = new ArrayList<String>();
            }

            files.add(f.getName());
            map.put(word, files);
        }
    }

    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int number = 0;

        for (String key : map.keySet()) {
            int size = map.get(key).size();

            if (size > number) {
                number = size;
            }
        }

        return number;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();

        for (String key : map.keySet()) {     
            if (map.get(key).size() == number) {
                words.add(key);
            }
        }

        return words;
    }

    public void printFilesIn(String word) {
        ArrayList<String> files = map.get(word);

        System.out.println("Word \"" + word +"\" appears in files:");
        for (int i = 0; i < files.size(); ++i) {
            System.out.println(files.get(i));
        }
    }

    public void test() {
        buildWordFileMap();

        // System.out.println(map.size());
        // for (String key : map.keySet()) {
        //     System.out.println(key + ": " + map.get(key));
        // }

        ArrayList<String> words = wordsInNumFiles(4);
        // for (int i = 0; i < words.size(); ++i) {
        //     printFilesIn(words.get(i));
        // }
        System.out.println(words.size());

        // System.out.println(map.get("tree"));
    }
}
