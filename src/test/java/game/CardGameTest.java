package game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CardGameTest {

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
        Scanner scanner = mock(Scanner.class);

        when(scanner.nextLine())
            .thenReturn("Hello")
            .thenReturn("0")
            .thenReturn("1");
        
        int number = CardGame.getNumber(scanner);
        assertEquals(1,number);
    }

    @Test
    public void testGetNumber2() {
        Scanner scanner = mock(Scanner.class);

        when(scanner.nextLine())
            .thenReturn("")
            .thenReturn("4");
        
        int number = CardGame.getNumber(scanner);
        assertEquals(4,number);
    }

    @Test
    public void testGetFile() throws IOException {
        Scanner scanner = mock(Scanner.class);
        int num = 4;

        when(scanner.nextLine())
            .thenReturn("src/test/java/game/testFiles/invalid4.txt")
            .thenReturn("src/test/java/game/testFiles/valid4.txt");

        String[] result = CardGame.getFile(num, scanner);

        assertArrayEquals(new String[]{"9", "1", "25", "83", "86", "1", "48", "4", "77", "20", "5", "37", "1", "26", "53", "14", "80", "17", "4", "52", "44", "66", "25", "11", "80", "98", "96", "62", "23", "56", "85", "1"}, result);
    }

    @Test
    public void testGetFile2() throws IOException {
        Scanner scanner = mock(Scanner.class);
        int num = 3;

        when(scanner.nextLine())
            .thenReturn("src/test/java/game/testFiles/invalid3.txt")
            .thenReturn("src/test/java/game/testFiles/valid3.txt");

        String[] result = CardGame.getFile(num, scanner);

        assertArrayEquals(new String[]{"2", "36", "29", "2", "8", "30", "2", "31", "44", "2", "23", "24", "25", "28", "45", "28", "45", "11", "13", "3", "24", "19", "26", "37"}, result);
    }
}
