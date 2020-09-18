package utils;

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
    
    //     |     v              |        v  v     
    // GTTAATGTCGTGAATAACCTGAGAAATGAGGCACTGATAATGCCTAATGA

    public void printAllGenes(String dna) {
        int currIndex = 0;
        String gene;

        while (true) {
            gene = findGene(dna, currIndex);
            
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);

            currIndex = dna.indexOf(gene, currIndex) + gene.length();
        }
    }

    public void testFindStopCodon() {
        System.out.println(findStopCodon("ATGCCGTGAATGTGA", 9, "TGA"));
    }

    public void testFindGene() {
        System.out.println(findGene("GTTAATGTCGTAGGAAACCTGAGAAGCAAGGCACTGAAAATGCCTAATGA", 0));
    }
}