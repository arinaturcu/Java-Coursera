package utils;

public class Part2 {
    public int howMany(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        int count = 0;

        while (index != -1) {
            count++;
            index = stringb.indexOf(stringa, index + stringa.length());
        }

        return count;
    }

    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }
}