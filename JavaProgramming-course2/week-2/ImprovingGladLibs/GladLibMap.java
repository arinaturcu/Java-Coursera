package ImprovingGladLibs;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> wordsUsed;
    private ArrayList<String> labelsUsed;
    
	private Random myRandom;

	// private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    int count;

	public GladLibMap() {
        myMap      = new HashMap<String, ArrayList<String>>();
        myRandom   = new Random();
        wordsUsed  = new ArrayList<String>();
        labelsUsed = new ArrayList<String>();

        initializeFromSource(dataSourceDirectory);
        count = 0;
    }

    public GladLibMap(String source) {
        myMap      = new HashMap<String, ArrayList<String>>();
        myRandom   = new Random();
        wordsUsed  = new ArrayList<String>();
        labelsUsed = new ArrayList<String>();

        initializeFromSource(source);
        count = 0;
    }

    private void initializeFromSource(String source) {
        String[] labels = { "adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit" };

        for (String s : labels) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return " " + myRandom.nextInt(50) + 5;
        }

        ArrayList<String> list = myMap.get(label);
        if (list == null) {
            return "**UNKNOWN**";
        }

        if (!labelsUsed.contains(label)) {
            labelsUsed.add(label);
        }

        return randomFrom(myMap.get(label));
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);

        if (first == -1 || last == -1) {
            return w;
        }

        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));

        while (wordsUsed.contains(sub) == true) {
            sub = getSubstitute(w.substring(first + 1, last));
            count++;
        }

        wordsUsed.add(sub);

        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;

        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }

            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";

        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }

        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);

            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);

            for (String line : resource.lines()) {
                list.add(line);
            }
        }

        return list;
    }

    public int totalWordsInMap() {
        int total = 0;

        for (String key : myMap.keySet()) {
            total += myMap.get(key).size();
        }

        return total;
    }

    public int totalWordsConsidered() {
        int total = 0;

        for (String label : labelsUsed) {
            total += myMap.get(label).size();
        }

        return total;
    }

    public void makeStory() {
        String story = fromTemplate("data/madtemplate2.txt");

        System.out.println();
        printOut(story, 60);

        System.out.println("\n\n" + totalWordsInMap() + " total words to pick from");
        System.out.println(totalWordsConsidered() + " words considered");
        System.out.println(count + " words replaced");

        wordsUsed.clear();
	}
}
