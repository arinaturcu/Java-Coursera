package utils;

import edu.duke.*;

public class Part1 {
    private int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex);

        if (stopIndex == -1) {
            return dna.length();
        }

        while ((stopIndex - startIndex) % 3 != 0) {
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);

            if (stopIndex == -1) {
                return dna.length();
            }
        }

        return stopIndex;
    }

    private String findGene(String dna, int start) {
        int startIndex = dna.indexOf("ATG", start);
        if (startIndex == -1) return "";

        int TAAindex = findStopCodon(dna, startIndex, "TAA");
        int TAGindex = findStopCodon(dna, startIndex, "TAG");
        int TGAindex = findStopCodon(dna, startIndex, "TGA");

        // chose the minimum index
        int minIndex = Math.min(TAAindex, Math.min(TAGindex, TGAindex));

        if (minIndex == dna.length()) return "";

        return dna.substring(startIndex, minIndex + 3);
    }

    public StorageResource getAllGenes(String dna) {
        int currIndex = 0;
        String gene;
        StorageResource sr = new StorageResource();

        while (true) {
            gene = findGene(dna, currIndex);
            
            if (gene.isEmpty()) {
                break;
            }

            sr.add(gene);

            currIndex = dna.indexOf(gene, currIndex) + gene.length();
        }

        return sr;
    }

    public void test() {
        StorageResource sr = getAllGenes("GTTAATGTCGTGAATAACCAGGAAATGAGGCACTGATAATGCCTAATGA");

        for (String s : sr.data()) {
            System.out.println(s);
        }
    }
}
