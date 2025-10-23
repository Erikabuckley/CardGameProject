package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import game.Player;

public class PlayerTest {
    private Player playerOne;
    private Player playerTwo;
    
    @Before
    public void setUp() throws Exception {
        playerOne = new Player();
        playerTwo = new Player();
    }

    @Test
    public void TestGetId(){
        assertEquals("Test initial player instantiation works", 1, playerOne.getId());
    }
    
    @Test
    public void TestGetIdIncrements(){
        assertEquals("Test player id incrementation works", 2, playerTwo.getId());
    } 

    
    
}
