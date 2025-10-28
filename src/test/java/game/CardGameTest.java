package game;
import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class CardGameTest {
    private ByteArrayInputStream fileIn;

    @Test
    public void testReadWrite() throws IOException{
        File file = new File("outputFiles/player.txt");
        // writes a number to file then reads it
        CardGame.saveToTxt("outputFiles/player.txt", "2");
        String[] lines = CardGame.readTxtFile("outputFiles/player.txt");

        assertArrayEquals(new String[] { "2" }, lines);
        file.delete();
    }

    @Test
    public void testGetFile() throws IOException {
        int num = 4;
        String file = "src/test/java/game/testFiles/valid4.txt";
        fileIn = new ByteArrayInputStream(file.getBytes());

        String[] lines = CardGame.getFile(num, new Scanner (fileIn));
        String [] realLines = CardGame.readTxtFile(file);

        // Assert that the method returns the correct result
        assertArrayEquals(realLines, lines);
    }

    @Test
    public void testGetFileInvalid() throws IOException {
        int num = 3;
        String invFile = "src/test/java/game/testFiles/valid4.txt";
        String vFile = "src/test/java/game/testFiles/valid3.txt";
        fileIn = new ByteArrayInputStream((invFile + "\n" + vFile  + "\n").getBytes());

        String[] lines = CardGame.getFile(num, new Scanner (fileIn));
        String [] realLines = CardGame.readTxtFile(vFile);

        assertArrayEquals(realLines, lines);
    }

}
