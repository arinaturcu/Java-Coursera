import java.util.ArrayList;
import java.util.HashMap;

import LogFiles.*;

public class Main {
    public static void main(String[] args) {
        LogAnalyzer la = new LogAnalyzer();

        la.readFile("data/weblog2_log.txt");
        // la.printAll();

        System.out.println("\nThere are " + la.countUniqueIPs() + " unique IP addresses.\n");

        // la.printAllHigherThanNum(400);

        String day = "Sep 24";
        ArrayList<String> IPs = la.uniqueIPVisitsOnDay(day);
        System.out.println("\nUnique IP addresses that had access on "
                + day + ":\n> " + IPs.size() + "\n> " + IPs);

        int low = 400;
        int high = 499;
        System.out.println("\nNumber of unique IP addresses that have " + 
                "a status code in the range from " + low + " to " + high + 
                ", inclusive:\n> " + la.countUniqueIPsInRange(low, high));

        HashMap<String, Integer> counts = la.countVisitsPerIP();
        // System.out.println("\nUnique IP addresses and the number of times " + 
        //         "they visited the website:\n> " + counts);

        System.out.println("\nMaximum number of visits to this website by a " + 
                "single IP address:\n> " + la.mostNumberVisitsByIP(counts) + "\n" +
                la.iPsMostVisits(counts));

        HashMap<String,ArrayList<String>> daysIPs = la.iPsForDays();
        // System.out.println("\nDays and IP addresses:\n> " + daysIPs);

        System.out.println("\nDay with most IP visits:\n> " + la.dayWithMostIPVisits(daysIPs));

        String secondDay = "Sep 30";
        System.out.println("\nIP addresses that had the most accesses on " + secondDay + ":\n> " 
                + la.iPsWithMostVisitsOnDay(daysIPs, secondDay));
    }
}
