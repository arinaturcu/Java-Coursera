import StoriesFromTemplates.*;
import ImprovingGladLibs.*;

public class Main {
    public static void main(String[] args) {
        WordFrequencies w = new WordFrequencies();
        w.testFindUnique();

        CharactersInPlay c = new CharactersInPlay();
        c.charactersWithNumParts(10, 999999);
        
        GladLibMap story = new GladLibMap();
        story.makeStory();

        CodonCount cc = new CodonCount();
        cc.test();

        WordsInFiles wf = new WordsInFiles();
        wf.test();
    }
}
