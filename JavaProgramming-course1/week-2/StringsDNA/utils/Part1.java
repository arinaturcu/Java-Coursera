package utils;

public class Part1 {
    private static String findSimpleGene(String dna) {
        int start = dna.indexOf("ATG");
        if (start < 0) {
            return "";
        }

        int stop = dna.indexOf("TAA", start + 3);
        if (stop < 0) {
            return "";
        }

        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop + 3);
        }
        return "";
    }

	public void testSimpleGene() {
        String dna  = "AAATGCCCTAACTAGATTAAGAAACC";
        String dna1 = "AATCATCTAAAGDC";
        String dna2 = "AATGATCTACAGDC";
        String dna3 = "AATCATCTACAGDC";
        String dna4 = "AATGATCATAAAGDC";

        String result = findSimpleGene(dna);
        if (result.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result);
        }

        String result1 = findSimpleGene(dna1);
        if (result1.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result1);
        }

        String result2 = findSimpleGene(dna2);
        if (result2.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result2);
        }

        String result3 = findSimpleGene(dna3);
        if (result3.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result3);
        }

        String result4 = findSimpleGene(dna4);
        if (result4.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result4);
        }
	}
}