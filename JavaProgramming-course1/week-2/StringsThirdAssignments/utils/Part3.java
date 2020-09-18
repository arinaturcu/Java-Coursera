package utils;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part3 {
    public void processGenes(StorageResource sr) {
        int countNine  = 0;
        int countRatio = 0;
        int maxLength  = 0;

        Part2 p2 = new Part2();

        StorageResource longerNine = new StorageResource();
        StorageResource ratio      = new StorageResource();

        System.out.println("TOTAL NUMBER OF GENES: " + sr.size());

        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                countNine++;
                longerNine.add(gene);
            }

            if (p2.cgRatio(gene) > 0.35) {
                countRatio++;
                ratio.add(gene);
            }
            
            if (gene.length() > maxLength) {
                maxLength = gene.length();
            }
        }

        System.out.println("\nGenes longer that 60 characters:");
        for (String s : longerNine.data()) {
            System.out.println(s);
        }
        System.out.println("Number of genes longer that 60 characters: " + countNine);

        System.out.println("\nGenes whose C-G-ratio is higher than 0.35:");
        for (String s : ratio.data()) {
            System.out.println(s);
        }
        System.out.println("Number of genes whose C-G-ratio is higher than 0.35: " + countRatio);

        System.out.println("\nLength of the longest gene: " + maxLength);
    }

    public void testProcessGenes() {
        Part1 p1 = new Part1();
        Part2 p2 = new Part2();

        FileResource f = new FileResource("brca1line.fa");
        String dna = f.asString();

        dna = dna.toUpperCase();
        processGenes(p1.getAllGenes(dna));
        System.out.println("\nCTG occurences: " + p2.ctgCount(dna));
    }
}