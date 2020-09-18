package StoriesFromTemplates;

import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String>  Characters;
    private ArrayList<Integer> Freqs;

    public CharactersInPlay() {
        Characters = new ArrayList<String>();
        Freqs      = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = Characters.indexOf(person);

        if (index == -1) {
            Characters.add(person);
            Freqs.add(1);
        } else {
            int value = Freqs.get(index);
            Freqs.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        Characters.clear();
        Freqs.clear();

        FileResource file = new FileResource();

        for (String line : file.lines()) {
            int index = line.indexOf('.');
            
            if (index != -1) {
                String name = line.substring(0, index);
                update(name);
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        findAllCharacters();

        for (int i = 0; i < Characters.size(); ++i) {
            int f = Freqs.get(i);
            if (f >= num1 && f <= num2) {
                System.out.println(Characters.get(i) + " " + Freqs.get(i));
            }
        }
    }

    public void test() {
        findAllCharacters();

        for (int i = 0; i < Characters.size(); ++i) {
            if (Freqs.get(i) > 1) {
                System.out.println(Characters.get(i) + " " + Freqs.get(i));
            }
        }
    }
}
