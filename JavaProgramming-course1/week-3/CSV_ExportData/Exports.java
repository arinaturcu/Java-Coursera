import edu.duke.*;
import org.apache.commons.csv.*;

public class Exports {

    public static String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }

        return "NOT FOUND";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");

            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public static int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;

        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            int index = exports.indexOf(exportItem);

            if (index != -1) {
                count++;
            }
        }

        return count;
    }

    public static void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");

            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }

    public static void tester() {
        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();
        System.out.println("> Informations about Nauru:");
        System.out.println(countryInfo(parser, "Nauru") + "\n");

        parser = fr.getCSVParser();
        System.out.println("> Countries that export cotton and flowers");
        listExportersTwoProducts(parser, "cotton", "flowers");

        parser = fr.getCSVParser();
        System.out.println("\n> Number of countries that export cocoa:");
        System.out.println(numberOfExporters(parser, "cocoa") + "\n");

        parser = fr.getCSVParser();
        System.out.println("> Countries that export one trillion dollars or more:");
        bigExporters(parser, "$999,999,999,999");
    }

    public static void main(String[] args) {
        tester();
    }
}
