package OOP_CC;

public class CaesarCipherOOP {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipherOOP(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder message = new StringBuilder(input);

        for (int i = 0; i < input.length(); ++i) {
            int indexUp = alphabet.indexOf(input.charAt(i));
            int indexLow = alphabet.toLowerCase().indexOf(input.charAt(i));

            if (indexUp != -1) {
                message.setCharAt(i, shiftedAlphabet.charAt(indexUp));
            } else if (indexLow != -1) {
                message.setCharAt(i, shiftedAlphabet.toLowerCase().charAt(indexLow));
            }
        }

        return message.toString();
    }

    public String decrypt(String encrypted) {
        CaesarCipherOOP cc2 = new CaesarCipherOOP(26 - mainKey);
        return cc2.encrypt(encrypted);
    }
}
