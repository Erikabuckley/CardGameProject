package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {
    CardDeck deckOne;
    CardDeck deckTwo;
    Card c1;
    Card c2;

    // before
    @Before
    public void setUp() throws Exception {
        deckOne = new CardDeck();
        deckTwo = new CardDeck();
        c1 = new Card(1);
        c2 = new Card(2);
    }

    // test id is correct, use 2 decks for this
    @Test
    public void testGetId() {
        assertEquals("Test initial deck instantiation works", 1, deckOne.getId());
        assertEquals("Test deck id incrementation works", 2, deckTwo.getId());
    }

    // test format out
    @Test
    public void testFormatOut() {
        deckOne.addCard(c1);
        deckOne.addCard(c2);

        String formatted = deckOne.formatOut(deckOne.getCards());
        assertEquals("Tests format out contains correct cards", " 1 2", formatted);

        deckOne.removeCard();
        deckOne.removeCard();
    }

    // test add to deck and remove from deck
    @Test
    public void testAddRemoveCard() {
        deckOne.addCard(c1);

        List<Card> cards = deckOne.getCards();
        assertTrue("Tests a deck can be added to", cards.contains(c1));

        deckOne.removeCard();
        List<Card> newCards = deckOne.getCards();
        assertFalse("Tests a deck can be removed from", newCards.contains(c1));
    }

    // test file creation use write deck
    @Test
    public void testWriteDeckMakesFile() throws IOException {
        deckOne.addCard(c1);
        File outputFolder = new File("outputFiles");
        outputFolder.mkdir();

        deckOne.writeDeck();

        File file = new File("outputFiles/deck" + deckOne.getId() + "_output.txt");
        assertTrue("Tests writeDeck makes a file with the correct name", file.exists());

        file.delete();
        deckOne.removeCard();
    }

    // after
    @After
    public void cleanUp() {
        deckOne.reset();
        deckTwo.reset();
    }

}
