package game;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

public class CardGameTest {
    private ByteArrayInputStream fileIn;
    private ByteArrayInputStream dataIn;
    private ByteArrayOutputStream outputStream;


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
    public void testGetNumber() {
        String num1 = "Hello";
        int num2 = 0;
        int num3 = 1;

        dataIn = new ByteArrayInputStream((num1  + "\n" + num2  + "\n"+ num3  + "\n").getBytes());
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        CardGame.getNumber(new Scanner (dataIn));

        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Must be greater than 0."));
        assertTrue(consoleOutput.contains("Must be a number."));
    }

    @Test
    public void testGetNumber2() {
        String num1 = "";
        int num2 = 4;

        dataIn = new ByteArrayInputStream((num1  + "\n" + num2  + "\n").getBytes());
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        CardGame.getNumber(new Scanner (dataIn));

        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Must be a number."));
    }

    @Test
    public void testGetFile() throws IOException {
        int num = 4;
        String file = "src/test/java/game/testFiles/valid4.txt";
        fileIn = new ByteArrayInputStream(file.getBytes());

        String[] lines = CardGame.getFile(num, new Scanner (fileIn));
        String [] realLines = CardGame.readTxtFile(file);

        assertArrayEquals(realLines, lines);
    }

    @Test
    public void testGetFileInvalid() throws IOException {
        int num = 3;
        String invFile = "src/test/java/game/testFiles/valid4.txt";
        String vFile = "src/test/java/game/testFiles/valid3.txt";
        fileIn = new ByteArrayInputStream((invFile + "\n" + vFile  + "\n").getBytes());
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        String[] lines = CardGame.getFile(num, new Scanner (fileIn));
        String [] realLines = CardGame.readTxtFile(vFile);

        assertArrayEquals(realLines, lines);

        String consoleOutput = outputStream.toString();
        System.out.println(consoleOutput);
        assertTrue(consoleOutput.contains("Inncorect file length/ contains invalid characters. Try again."));

    }

    @Test
    public void testGetFileInvalid2() throws IOException {
        int num = 3;
        String invFile = "src/test/java/game/testFiles/invalid3.txt";
        String vFile = "src/test/java/game/testFiles/valid3.txt";
        fileIn = new ByteArrayInputStream((invFile + "\n" + vFile  + "\n").getBytes());
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        String[] lines = CardGame.getFile(num, new Scanner (fileIn));
        String [] realLines = CardGame.readTxtFile(vFile);

        assertArrayEquals(realLines, lines);

        String consoleOutput = outputStream.toString();
        System.out.println(consoleOutput);
        assertTrue(consoleOutput.contains("Inncorect file length/ contains invalid characters. Try again."));

    }

}
