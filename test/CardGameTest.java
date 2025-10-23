package test;

import org.junit.Before;
import org.junit.Test;

import game.CardGame;

public class CardGameTest {
    private CardGame game;
    
    @Before
    public void setUp() throws Exception {
        game = new CardGame();
    }

    // @Test
    // public void TestGetFile(){
    //     assert("Test can ask for valid file", game.getFile(5, new Scanner(null)));
    // }
    
    // @Test
    // public void TestGetIdIncrements(){
    //     assertEquals("Test player id incrementation works", 2, playerTwo.getId());
    // } 

    
    
}
