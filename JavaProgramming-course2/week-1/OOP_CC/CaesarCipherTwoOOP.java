package OOP_CC;

public class CaesarCipherTwoOOP {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwoOOP(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder message = new StringBuilder(input);

        for (int k = 0; k < input.length(); ++k) {
            if (k % 2 == 0)
            {
                int indexUp = alphabet.indexOf(input.charAt(k));
                int indexLow = alphabet.toLowerCase().indexOf(input.charAt(k));
    
                if (indexUp != -1) {
                    message.setCharAt(k, shiftedAlphabet1.charAt(indexUp));
                } else if (indexLow != -1) {
                    message.setCharAt(k, shiftedAlphabet1.toLowerCase().charAt(indexLow));
                }
            }
            else
            {
                int indexUp = alphabet.indexOf(input.charAt(k));
                int indexLow = alphabet.toLowerCase().indexOf(input.charAt(k));
    
                if (indexUp != -1) {
                    message.setCharAt(k, shiftedAlphabet2.charAt(indexUp));
                } else if (indexLow != -1) {
                    message.setCharAt(k, shiftedAlphabet2.toLowerCase().charAt(indexLow));
                }
            }
        }

        return message.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwoOOP c = new CaesarCipherTwoOOP(26 - mainKey1, 26 - mainKey2);
        return c.encrypt(input);
    }
}