package game;

import java.util.Random;
import java.io.*;

public class testFileGenerationCode {
    public static void main(String[] args) throws IOException {
        int n = 10;
        int max = 8 * n;
        Random r = new Random();
        for (int i = 0; i < max; i++) {
            int r1 = r.nextInt(1, 50); // so always greater than zero
            String str = String.valueOf(r1) + "\n";
            CardGame.saveToTxt("test/java/testFiles/valid3.txt", str);
        }
    }
}