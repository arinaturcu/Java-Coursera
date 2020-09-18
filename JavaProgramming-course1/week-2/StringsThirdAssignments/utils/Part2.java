package utils;

public class Part2 {
    public float cgRatio(String dna) {
        if (dna.isEmpty()) return 0;

        int countC = 0;
        int countG = 0;

        int indexC = dna.indexOf("C");
        while (indexC != -1) {
            countC++;
            indexC = dna.indexOf("C", indexC + 1);
        }

        int indexG = dna.indexOf("G");
        while (indexG != -1) {
            countG++;
            indexG = dna.indexOf("G", indexG + 1);
        }

        
        return (float) (countC + countG) / (float) dna.length();
    }

    public int ctgCount(String dna) {
        int count = 0;
        int index = dna.indexOf("CTG");

        while (index != -1) {
            count++;
            index = dna.indexOf("CTG", index + 1);
        }

        return count;
    }
}