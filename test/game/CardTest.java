package test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CardTest {
    //before
    @Before 
    public void setUp() throws Exception {
        card = new Card();
    }

    //test card created and value correct
    @Test 
    public void testValue() {
        assertEquals("Test card creation works", 1, card.getValue());
    }

    //after
    @After 
    public void cleanUp() {
        card.reset();
    }
}
