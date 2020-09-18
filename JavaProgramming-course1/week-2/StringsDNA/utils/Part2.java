package utils;

public class Part2 {
    private static String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int start = dna.indexOf(startCodon);
        if (start < 0) {
            return "";
        }

        int stop = dna.indexOf(stopCodon, start + 3);
        if (stop < 0) {
            return "";
        }

        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop + 3);
        }
        return "";
    }


	public void testSimpleGene() {
        String dna  = "AATGATCTAAAGDC";
        String dna1 = "AATCATCTAAAGDC";
        String dna2 = "AATGATCTACAGDC";
        String dna3 = "AATCATCTACAGDC";
        String dna4 = "AATGATCATAAAGDC";

        String result = findSimpleGene(dna, "ATG", "TAA");
        if (result.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result);
        }

        String result1 = findSimpleGene(dna1, "ATG", "TAA");
        if (result1.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result1);
        }

        String result2 = findSimpleGene(dna2, "ATG", "TAA");
        if (result2.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result2);
        }

        String result3 = findSimpleGene(dna3, "ATG", "TAA");
        if (result3.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result3);
        }

        String result4 = findSimpleGene(dna4, "ATG", "TAA");
        if (result4.isEmpty()) {
            System.out.println("No gene found!");
        } else {
            System.out.println(result4);
        }
	}
}