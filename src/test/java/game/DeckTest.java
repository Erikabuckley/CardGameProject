package test.java.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.game.*;

public class DeckTest {
    CardDeck deckOne;
    CardDeck deckTwo;

    //before
    @Before
    public void setUp() throws Exception {
        deckOne = new CardDeck();
        deckTwo = new CardDeck();
    }


    //test id is correct, use 2 decks for this
    @Test 
    public void testGetId() {
        assertEquals("Test initial deck instantiation works", 1, deckOne.getId());
        assertEquals("Test deck id incrementation works", 2, deckTwo.getId());
    }

    //test format out
    @Test 
    public void testFormatOut() {
        deckOne.addCard(new Card(1));
        deckOne.addCard(new Card(2));

        String formatted = deckOne.formatOut(deckOne.getCards());
        assertEquals(" 1 2", formatted);
    }

    // test add to deck and remove from deck
    @Test 
    public void testaddCard() {
        Card c = new Card(1);
        deckOne.addCard(c);

        List<Card> cards = deckOne.getCards();
        assertTrue(cards.contains(c));

        deckOne.removeCard();
        List<Card> newCards = deckOne.getCards();
        assertFalse(newCards.contains(c));
    }

    //test file creation use write deck
    @Test 
    public void testWriteDeckMakesFile() throws IOException {
        File outputFolder = new File("outputFiles");
        outputFolder.mkdir();

        deckOne.writeDeck();

        File file = new File("outputFiles/deck" + deckOne.getId() + "_output.txt");
        assertTrue(file.exists());

        //cleanup
        file.delete();
    }

    //after
    @After 
    public void cleanUp() {
        deckOne.reset();
        deckTwo.reset();
    }
    
}
