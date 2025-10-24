package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.*;

public class PlayerTest {
    private Player playerOne;
    private Player playerTwo;

    @Before
    public void setUp() throws Exception {
        playerOne = new Player();
        playerTwo = new Player();
    }

    @Test
    public void testGetId() {
        assertEquals("Test initial player instantiation works", 1, playerOne.getId());
    }

    @Test
    public void testGetIdIncrements() {
        assertEquals("Test player id incrementation works", 2, playerTwo.getId());
    }

    @Test
    public void testFormatOut() {
        playerOne.addCard(new Card(1));
        playerOne.addCard(new Card(2));

        String formatted = playerOne.formatOut(playerOne.getCards());
        assertEquals(" 1 2", formatted);
    }

    @Test
    public void testaddCard() {
        Card c = new Card(1);
        playerOne.addCard(c);

        List<Card> cards = playerOne.getCards();
        assertTrue(cards.contains(c));

        playerOne.removeCard(c);
        List<Card> newCards = playerOne.getCards();
        assertFalse(newCards.contains(c));
    }

    @Test
    public void testCheckIfWon() {
        Card card1 = new Card(1);
        Card card2 = new Card(2);
        playerOne.addCard(card1);
        playerOne.addCard(card1);
        playerOne.addCard(card1);
        playerOne.addCard(card2);

        assertFalse(playerOne.checkIfWon());

        playerOne.removeCard(card2);
        playerOne.addCard(card1);

        assertTrue(playerOne.checkIfWon());
    }

    @After
    public void cleanUp() {
        playerOne.reset();
        playerTwo.reset();
    }

}
