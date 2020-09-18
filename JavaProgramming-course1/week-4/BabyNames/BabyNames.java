import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.*;

public class BabyNames {
    public static void totalBirths(FileResource fr) {
        int total = 0;
        int totalGirls = 0;
        int totalBoys = 0;

        for (CSVRecord record: fr.getCSVParser(false)) {
            total++;

            if (record.get(1).equals("F")) {
                totalGirls++;
            } else {
                totalBoys++;
            }
        }

        System.out.println("Total: " + total +
                           "\nTotal girls: " + totalGirls +
                           "\nTotal boys: " + totalBoys);
    }

    public static void testTotal(String path) {
        FileResource fr = new FileResource(path);
        totalBirths(fr);
    }

    // aici modifica
    public static long getRank(int year, String name, String gender) {
        String filePath = "data/us_babynames_by_year/yob" + year + ".csv";
        // String filePath = "data/us_babynames_test/yob" + year + "short.csv";
        FileResource fr = new FileResource(filePath);

        long lastGirl = -1;
        long rank = -1;

        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals("M") && lastGirl == -1) {
                lastGirl = record.getRecordNumber() - 1;
            }

            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                if (gender.equals("F")) {
                    rank = record.getRecordNumber();
                    break;
                } else {
                    rank = record.getRecordNumber() - lastGirl;
                    break;
                }
            }
        }

        return rank;
    }

    public static void testRank(int year, String name, String gender) {
        System.out.println("Rank for " + name + ", " + gender + ", " + year + ": " + getRank(year, name, gender));
    }

    // aici modifica
    public static String getName(int year, long rank, String gender) {
        String filePath = "data/us_babynames_by_year/yob" + year + ".csv";
        // String filePath = "data/us_babynames_test/yob" + year + "short.csv";
        FileResource fr = new FileResource(filePath);
        String name = "NO NAME";

        long lastGirl = -1;

        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals("M") && lastGirl == -1) {
                lastGirl = record.getRecordNumber() - 1;
            }

            if (record.get(1).equals(gender)) {
                if (gender.equals("F")) {
                    if (record.getRecordNumber() == rank) {
                        name = record.get(0);
                        break;
                    }
                } else {
                    if (record.get(1).equals("M") && (record.getRecordNumber() - lastGirl) == rank) {
                        name = record.get(0);
                        break;
                    }
                }
            }
        }

        return name;
    }

    public static void testGetName(int year, long rank, String gender) {
        System.out.println("Name for rank " + rank + " in year " + year + ", " + gender + ": " + getName(year, rank, gender));
    }

    public static void whatIsNameInYear(String name, int year, int newYear, String gender) {
        long rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String pronoun;

        if (gender.equals("F")) {
            pronoun = "she";
        } else {
            pronoun = "he";
        }

        System.out.println(name + " born in year " + year +
                           " would be " + newName + " if " + pronoun +
                           " was born in " + newYear + ".");
    }

    public static void testWhat(String name, int year, int newYear, String gender) {
        whatIsNameInYear(name, year, newYear, gender);
    }

    public static int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int currYear, highestYear = -1;
        long currRank = -1;
        long highestRank = 0;

        for (File f : dr.selectedFiles()) {
            currYear = Integer.parseInt(f.getName().substring(3, 7));
            currRank = getRank(currYear, name, gender);

            if (highestRank == 0 && currRank != -1) {
                highestRank = currRank;
                highestYear = currYear;
                continue;
            }

            if (currRank != -1 && currRank < highestRank) {
                highestRank = currRank;
                highestYear = currYear;
            }
        }

        return highestYear;
    }

    public static void testYearOfHighest(String name, String gender) {
        int year = yearOfHighestRank(name, gender);

        if (year == -1) {
            System.out.println("NAME NOT FOUND");
            return;
        }
        System.out.println("Year of highest rank for " + name + "(" + gender + ") is " + year);
    }

    public static double getAverageRank(String name, String gender) {
        long sum = 0;
        long rank;
        int count = 0;
        int year;

        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            year = Integer.parseInt(f.getName().substring(3, 7));
            rank = getRank(year, name, gender);

            if (rank != -1) {
                sum += rank;
                count++;
            }
        }

        return (double) sum / (double) count;
    }

    public static void testAverageRank(String name, String gender) {
        System.out.println("Average rank for "+ name + "(" + gender + ") is " + getAverageRank(name, gender));
    }

    // aici modifica
    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        long rank = getRank(year, name, gender);
        long curr = 0;
        int total = 0;

        String filePath = "data/us_babynames_by_year/yob" + year + ".csv";
        // String filePath = "data/us_babynames_test/yob" + year + "short.csv";
        FileResource fr = new FileResource(filePath);

        for(CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                curr++;
                if (curr == rank) break;

                total += Integer.parseInt(record.get(2));
            }
        }

        return total;
    }

    public static void testTotal(int year, String name, String gender) {
        System.out.println("Total number of births of those names with the same gender and same year who are ranked higher than " +
                            name + ": " + getTotalBirthsRankedHigher(year, name, gender));
    }

    public static void main(String[] args) {
        // testTotal("data/us_babynames_by_year/yob1900.csv");
        // testTotal("data/us_babynames_by_year/yob1905.csv");

        // testRank(1960, "Emily", "F");
        // testRank(1971, "Frank", "M");

        // testGetName(1980, 350, "F");
        // testGetName(1982, 450, "M");

        // testWhat("Susan", 1972, 2014, "F");
        // testWhat("Owen", 1974, 2014, "M");

        // testYearOfHighest("Genevieve", "F");
        // testYearOfHighest("Mich", "M");

        // testAverageRank("Susan", "F");
        // testAverageRank("Robert", "M");

        // testTotal(1990, "Emily", "F");
        testTotal(1990, "Drew", "M");
    }
}
