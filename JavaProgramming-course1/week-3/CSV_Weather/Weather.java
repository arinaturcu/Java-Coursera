import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather {
    /** 
     * Finds the minimu value in a given column
     * from two records
     */
    private static CSVRecord findMin(CSVRecord minSoFar, CSVRecord curr, String attribute) {
        double currValue = Double.parseDouble(curr.get(attribute));
        double coldValue = Double.parseDouble(minSoFar.get(attribute));

        if (currValue < coldValue) {
            return curr;
        }

        return minSoFar;
    }
    
    /**
     *  Finds the lowest humidity in a day 
     * (in a single file).
     */
    public static CSVRecord lowestHumidityInFile (CSVParser parser) {
        CSVRecord lowestSoFar = null;

        for (CSVRecord record : parser) {
            if (record.get("Humidity").equals("N/A")) continue;

            if (lowestSoFar == null) {
                lowestSoFar = record;
            } else {
                lowestSoFar = findMin(lowestSoFar, record, "Humidity");
            }
        }

        return lowestSoFar;
    }

    /**
     *  Finds the coldest temperature in a day 
     * (in a single file).
     */
    public static CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord coldestSoFar = null;

        for (CSVRecord record : parser) {
            if (record.get("TemperatureF").equals("-9999")) continue;

            if (coldestSoFar == null) {
                coldestSoFar = record;
            } else {
                coldestSoFar = findMin(coldestSoFar, record, "TemperatureF");
            }
        }

        return coldestSoFar;
    }

    /**
     * Finds the day with the coldest temperature
     * from multiple files and return the name of that file
     */
    public static String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        String fileName = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord curr = coldestHourInFile(fr.getCSVParser());

            if (coldestSoFar == null) {
                coldestSoFar = curr;
                fileName = f.getName();
            } else {
                coldestSoFar = findMin(coldestSoFar, curr, "TemperatureF");
                if (coldestSoFar == curr) {
                    fileName = f.getName();
                }
            }
        }

        return fileName;
    }
    
    /**
     *  Finds the coldest temperature in many files 
     */
    public static CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord curr = lowestHumidityInFile(fr.getCSVParser());

            if (lowestSoFar == null) {
                lowestSoFar = curr;
            } else {
                lowestSoFar = findMin(lowestSoFar, curr, "Humidity");
            }
        }

        return lowestSoFar;
    }

    private static void printColdestTemperatureOfTheDay(String pathToFile) {
        FileResource f = new FileResource(pathToFile);
        CSVParser parser = f.getCSVParser();

        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temperature of that day was " + coldest.get("TemperatureF"));
    }

    public static void printLowestHumidityInFile(String pathToFile) {
        FileResource f = new FileResource(pathToFile);
        CSVParser parser = f.getCSVParser();

        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }

    public static double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int count = 0;

        for (CSVRecord record : parser) {
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currTemp == -9999) continue;

            sum += currTemp;
            count++;
        }

        if (count == 0) return -9999;

        return sum / count;
    }

    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int count = 0;

        for (CSVRecord record : parser) {
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currTemp == -9999 || Integer.parseInt(record.get("Humidity")) < value) continue;

            sum += currTemp;
            count++;
        }

        if (count == 0) return -9999;

        return sum / count;
    }

    public static void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileName);

        
        // Builds the relative path to the file from the given name
        String year = fileName.substring(8, 12);
        String relativePathToFile = "nc_weather/" + year + "/" + fileName;

        // Prints the temperature
        printColdestTemperatureOfTheDay(relativePathToFile);

        //  Builds the FileResource and the CSVParser needed
        // to print all the temperatures on the coldest day
        FileResource f = new FileResource(relativePathToFile);
        CSVParser parser = f.getCSVParser();

        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }

    }

    public static void testLowestHumidityInManyFiles (CSVRecord lowest) {
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }

    public static void testAverageTemperatureInFile(String pathToFile) {
        FileResource f = new FileResource(pathToFile);
        CSVParser parser = f.getCSVParser();

        System.out.println(averageTemperatureInFile(parser));
    }

    public static void testAverageTemperatureWithHighHumidityInFile(String pathToFile) {
        FileResource f = new FileResource(pathToFile);
        CSVParser parser = f.getCSVParser();

        double av = averageTemperatureWithHighHumidityInFile(parser, 80);

        if (av == -9999) {
            System.out.println("No temperatures with that humidity");
            return;
        }

        System.out.println(av);
    }

    public static void main(String[] args) {
        // printColdestTemperatureOfTheDay("nc_weather/2014/weather-2014-05-01.csv");
        testFileWithColdestTemperature();
        // printLowestHumidityInFile("nc_weather/2014/weather-2014-07-22.csv");
        // testLowestHumidityInManyFiles(lowestHumidityInManyFiles());
        // testAverageTemperatureInFile("nc_weather/2013/weather-2013-08-10.csv");
        // testAverageTemperatureWithHighHumidityInFile("nc_weather/2013/weather-2013-09-02.csv");
    }
}
