package game;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import game.*;

public class CardGameTest {
    private CardGame game;
    
    @Before
    public void setUp() throws Exception {
        game = new CardGame();
    }

    //test read file
    // @Test
    // public void testReadTxtFile(){
    //     File outputFolder = new File("outputFiles");
    //     outputFolder.mkdir();
    //     File file = new File("outputFiles/player.txt");

    //     String[] lines = game.readTxtFile("outputFiles/player.txt");
    //     
    //     file.delete();
    // }

    // test write file

    // test get file
    // @Test
    // public void testGetFile(){
    //     assert("Test can ask for valid file", game.getFile(5, new Scanner(null)));
    // }

    //after
    @After
    public void cleanUp() {
        game.reset()      
    
    
}
