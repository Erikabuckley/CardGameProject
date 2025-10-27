package test.game;

import static org.junit.Assert.assertEquals;

import game.*;
import org.junit.Before;
import org.junit.Test;


public class CardTest {

    private Card card;
    //before
    @Before 
    public void setUp() throws Exception {
        card = new Card(1);
    }

    //test card created and value correct
    @Test 
    public void testValue() {
        assertEquals("Test card creation works", 1, card.getValue());
    }
}
