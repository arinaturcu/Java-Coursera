import OOP_CC.*;
import utils.*;
import edu.duke.*;

public class Main {
    public static void main(String[] args) {
        // TestCaesarCipherTwo testTwo = new TestCaesarCipherTwo();
        // testTwo.test();

        // TestCaesarCipher test = new TestCaesarCipher();
        // test.test();

        CaesarCipher c = new CaesarCipher();
        FileResource f = new FileResource();
        System.out.println(c.decryptTwoKeys(f.asString()));

        // WordLengths wl = new WordLengths();
        // wl.test();
    }
}
