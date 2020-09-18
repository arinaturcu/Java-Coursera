package LogFiles;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public void printAll() {
        System.out.println("\nAll log entries:");

        for (LogEntry le : records) {
            System.out.println("> " + le);
        }
    }

    public int countUniqueIPs() {
        HashMap<String, Integer> counts = countVisitsPerIP();

        return counts.size();
    }

    public void printAllHigherThanNum(int num) {
        System.out.println("Entries with a status code greater than " + num + ":");

        for (LogEntry entry : records) {
            if (entry.getStatusCode() > num) {
                System.out.println("> " + entry);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> IPs = new ArrayList<String>();

        for (LogEntry entry : records) {
            String address = entry.getIpAddress();
            if (entry.getAccessTime().toString().contains(someday) && !IPs.contains(address)) {
                IPs.add(address);
            }
        }

        return IPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> IPs = new ArrayList<String>();

        for (LogEntry entry : records) {
            int statusCode = entry.getStatusCode();
            String address = entry.getIpAddress();

            if (statusCode >= low && statusCode <= high && !IPs.contains(address)) {
                IPs.add(address);
            }
        }
        
        return IPs.size();
    }

    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();

        for (LogEntry entry : records) {
            String address = entry.getIpAddress();

            if (!counts.containsKey(address)) {
                counts.put(address, 1);
            } else {
                counts.put(address, counts.get(address) + 1);
            }
        }

        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
        int maxVisits = 0;
        
        for (String address : counts.keySet()) {
            int visits = counts.get(address);

            if (visits > maxVisits) {
                maxVisits = visits;
            } 
        }

        return maxVisits;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
        ArrayList<String> IPs = new ArrayList<String>();
        int maxVisits = mostNumberVisitsByIP(counts);

        for (String address : counts.keySet()) {
            if (counts.get(address) == maxVisits) {
                IPs.add(address);
            }
        }        

        return IPs;
    }

    public HashMap<String,ArrayList<String>> iPsForDays() {
        HashMap<String,ArrayList<String>> daysIPs = new HashMap<String,ArrayList<String>>();

        for (LogEntry entry : records) {
            String key = entry.getAccessTime().toString().substring(4, 10);
            ArrayList<String> value;

            if (!daysIPs.containsKey(key)) {
                value = new ArrayList<String>();
            } else {
                value = daysIPs.get(key);
            }

            value.add(entry.getIpAddress());
            daysIPs.put(key, value);
        }

        return daysIPs;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> daysIPs) {
        int maxVisits = 0;
        String maxDay = "";
        
        for (String key : daysIPs.keySet()) {
            int visits = daysIPs.get(key).size();
            if (visits > maxVisits) {
                maxVisits = visits;
                maxDay = key;
            }
        }

        return maxDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> daysIPs, String day) {
        HashMap<String, Integer> ipsInDay = new HashMap<String, Integer>();
        
        ArrayList<String> IPlist = daysIPs.get(day);

        for (String ip : IPlist) {
            if (ipsInDay.containsKey(ip)) {
                ipsInDay.put(ip, ipsInDay.get(ip) + 1);
            } else {
                ipsInDay.put(ip, 1);
            }
        }

        return iPsMostVisits(ipsInDay); 
    }
}
