import utils.*;

public class DNA {
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        Part2 p2 = new Part2();
        Part3 p3 = new Part3();

        System.out.println("Part 1:");
        // p1.testFindStopCodon();
        // p1.testFindGene();
        p1.printAllGenes("AATGCTAACTAGCTGACTAAT");
        
        System.out.println("\nPart 2:");
        p2.testHowMany();

        System.out.println("\nPart 3:");
        int c = p3.countAllGenes("GTTAATGTCGTGAATAACCAGGAAATGAGGCACTGATAATGCCTAATGA");
        System.out.println(c);
    }
}