package ImprovingGladLibs;

import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> map;
    
    public CodonCount() {
        map = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        map.clear();

        int index = start;
        while (index < dna.length() - 2) {
            String key = dna.substring(index, index + 3);
            
            if(map.containsKey(key) == false) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }

            index += 3;
        }
    }

    public String getMostCommonCodon() {
        String mostCommon = "";
        int maxValue = 0;

        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value > maxValue) {
                maxValue = value;
                mostCommon = key;
            }
        }

        return mostCommon;
    }

    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");

        for (String key : map.keySet()) {
            int value = map.get(key);

            if (value >= start && value <= end) {
                System.out.println(key + ": " + value);
            }
        }
    }

    public void test() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toLowerCase();

        System.out.println();

        for (int i = 0; i < 3; ++i) {
            buildCodonMap(i, dna);

            System.out.println("Reading frame starting with " + i + " results in " + map.size() + " unique codons.");

            String mostCommon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommon + " with count " + map.get(mostCommon));

            printCodonCounts(7, 7);
            System.out.println();
        }
    }
}
