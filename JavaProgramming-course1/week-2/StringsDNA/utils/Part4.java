package utils;

import edu.duke.*;

public class Part4 {

    public void findYouTubeLinks(String url) {
        URLResource source = new URLResource(url);

        for (String word : source.words()) {
            String lowerCopy = word.toLowerCase();
            
            if (lowerCopy.indexOf("youtube.com") >= 0) {
                int start = lowerCopy.indexOf("\"");
                int stop  = lowerCopy.indexOf("\"", start + 1);
                
                System.out.println(word.substring(start + 1, stop));
            }
        }
    }
}