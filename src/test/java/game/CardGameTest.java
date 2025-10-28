package game;
import static org.junit.Assert.assertArrayEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CardGameTest {
    private CardGame game;
    
    @Before
    public void setUp() throws Exception {
        game = new CardGame();
    }

    @Test
    public void testReadWrite() throws IOException{
        File file = new File("outputFiles/player.txt");
        // writes a number to file then reads it
        CardGame.saveToTxt("outputFiles/player.txt", "2");
        String[] lines = CardGame.readTxtFile("outputFiles/player.txt");

        assertArrayEquals(new String[] { "2" }, lines);
        file.delete();
    }
    
}
