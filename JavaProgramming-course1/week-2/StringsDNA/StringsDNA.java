import utils.*;

public class StringsDNA {
    public static void main(String argv[]) {
        Part1 p1 = new Part1();
        Part2 p2 = new Part2();
        Part3 p3 = new Part3();
        Part4 p4 = new Part4();

        System.out.println("Part 1:");
        p1.testSimpleGene();

        System.out.println("\nPart 2:");
        p2.testSimpleGene();

        System.out.println("\nPart 3:");
        p3.testing();

        System.out.println("\nPart 4:");
        p4.findYouTubeLinks("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
