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
        assertEquals("Test player instantiation works", playerOne.getId(), 1);
    }
    
    @Test
    public void TestGetIdIncrements(){
        assertEquals("Test player instantiation works", playerTwo.getId(), 2);
    } 
    
}
