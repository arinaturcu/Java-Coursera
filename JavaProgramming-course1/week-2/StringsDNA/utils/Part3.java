package utils;

public class Part3 {
    private boolean twoOccurrences(String stringa, String stringb) {
        int firstOcc = stringb.indexOf(stringa);

        if (firstOcc < 0) return false;

        int secondOcc = stringb.indexOf(stringa, firstOcc + stringa.length());

        if (secondOcc < 0) return false;

        return true;
    }

    private String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);

        if (index < 0) return stringb;
        
        return stringb.substring(index + stringa.length(), stringb.length());
    }

    public void testing() {
        System.out.println(twoOccurrences("atg", "asdfatgs"));
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }
}