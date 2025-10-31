package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    private Player playerOne;
    private Player playerTwo;
    private CardDeck d1;
    private CardDeck d2;
    private CardDeck d3;

    @Before
    public void setUp() throws Exception {
        playerOne = new Player();
        playerTwo = new Player();
        d1 = new CardDeck();
        d2 = new CardDeck();
        d3 = new CardDeck();

    }

    @Test
    public void testGetId() {
        assertEquals("Test initial player instantiation works", 1, playerOne.getId());
        assertEquals("Test player id incrementation works", 2, playerTwo.getId());
    }

    @Test
    public void testGetCards() {
        Card c = new Card(1);
        playerOne.addCard(c);
        List<Card> cards = playerOne.getCards();

        assertTrue(cards.contains(c));
    }

    @Test
    public void testFormatOut() {
        playerOne.addCard(new Card(1));
        playerOne.addCard(new Card(2));

        String formatted = playerOne.formatOut(playerOne.getCards());
        assertEquals(" 1 2", formatted);
    }

    @Test
    public void testAddRemove() {
        Card c = new Card(1);
        playerOne.addCard(c);

        List<Card> cards = playerOne.getCards();
        assertTrue(cards.contains(c));

        playerOne.removeCard(c);
        List<Card> newCards = playerOne.getCards();
        assertFalse(newCards.contains(c));
    }

    @Test
    public void testWriteInitialMakesFile() throws IOException {
        File outputFolder = new File("outputFiles");
        outputFolder.mkdir();

        playerOne.writeInitial();

        File file = new File("outputFiles/player" + playerOne.getId() + "_output.txt");
        assertTrue(file.exists());

        // clean up
        file.delete();
    }

    @Test
    public void testWriteEndMakesFile() throws IOException {
        File outputFolder = new File("outputFiles");
        outputFolder.mkdir();

        playerOne.writeEnd(1);

        File file = new File("outputFiles/player" + playerOne.getId() + "_output.txt");
        assertTrue(file.exists());

        // clean up
        file.delete();
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

        playerOne.removeCard(card1);
        playerOne.removeCard(card1);
        playerOne.removeCard(card1);
        playerOne.removeCard(card1);
        playerOne.addCard(card1);
        playerOne.addCard(card1);
        playerOne.addCard(card1);
        playerOne.addCard(card1);

        assertTrue(playerOne.checkIfWon());

    }

    @Test
    public void testDiscard() throws IOException {
        Card card2 = new Card(1);
        playerTwo.addCard(card2);
        playerTwo.addCard(card2);
        playerTwo.writeInitial();
        playerTwo.discard(d3);
        String line;
        List<String> lines = new ArrayList<String>();

        try (BufferedReader bf = new BufferedReader(new FileReader("outputFiles/player2_output.txt"))) {
            while ((line = bf.readLine()) != null) {
                lines.add(line);
            }
        }
        String id = Integer.toString(playerTwo.getId());
        assertTrue(lines.contains("Player " + id + " discards " + card2.getValue() + " to deck " + d3.getId()));

        File file = new File("outputFiles/player" + playerTwo.getId() + "_output.txt");
        assertTrue(file.exists());

        // clean up
        file.delete();
    }

    @Test
    public void testDraw() throws IOException {
        Card c = new Card(1);
        d2.addCard(c);
        d2.addCard(c);

        playerTwo.draw(d2);
        String line;
        List<String> lines = new ArrayList<String>();

        try (BufferedReader bf = new BufferedReader(new FileReader("outputFiles/player2_output.txt"))) {
            while ((line = bf.readLine()) != null) {
                lines.add(line);
            }
        }
        String id = Integer.toString(playerTwo.getId());
        assertTrue(lines.contains("Player " + id + " draws " + Integer.toString(c.getValue()) + " from deck "
                + Integer.toString(d2.getId())));

        File file = new File("outputFiles/player" + playerTwo.getId() + "_output.txt");
        assertTrue(file.exists());

        // clean up
        file.delete();

    }

    @After
    public void cleanUp() {
        playerOne.reset();
        playerTwo.reset();

    }

}
