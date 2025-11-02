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
    private Card c1;
    private Card c2;
    private Card t1;
    private Card t2;
    private Card t3;
    private Card t4;
    private Card t5;
    private Card t6;
    

    @Before
    public void setUp() throws Exception {
        playerOne = new Player();
        playerTwo = new Player();
        d1 = new CardDeck();
        d2 = new CardDeck();
        d3 = new CardDeck();
        c1 = new Card(1);
        c2 = new Card(2);
        t1 = new Card(1);
        t2 = new Card(1);
        t3 = new Card(1);
        t4 = new Card(2);
        t5 = new Card(2);
        t6 = new Card(2);

    }

    @Test
    public void testGetId() {
        assertEquals("Test initial player instantiation works", 1, playerOne.getId());
        assertEquals("Test player id incrementation works", 2, playerTwo.getId());
    }

    @Test
    public void testGetCards() {
        playerOne.addCard(c1);
        List<Card> cards = playerOne.getCards();

        assertTrue("Test player hand contains card that has just been added", cards.contains(c1));
        playerOne.removeCard(c1);
    }

    @Test
    public void testFormatOut() {
        playerOne.addCard(c1);
        playerOne.addCard(c2);

        String formatted = playerOne.formatOut(playerOne.getCards());
        assertEquals("Tests that cards can be output as a string", " 1 2", formatted);
    }

    @Test
    public void testAddRemove() {
        playerOne.addCard(c1);

        List<Card> cards = playerOne.getCards();
        assertTrue("Tests cards can be added to a hand", cards.contains(c1));

        playerOne.removeCard(c1);
        List<Card> newCards = playerOne.getCards();
        assertFalse("Tests cards can be removed form a deck", newCards.contains(c1));
    }

    @Test
    public void testWriteInitialMakesFile() throws IOException {
        File outputFolder = new File("outputFiles");
        outputFolder.mkdir();

        playerOne.addCard(c1);
        playerOne.writeInitial();

        File file = new File("outputFiles/player" + playerOne.getId() + "_output.txt");
        assertTrue("Tests wrtieInitial makes file", file.exists());

        file.delete();
    }

    @Test
    public void testWriteEndMakesFile() throws IOException {
        File outputFolder = new File("outputFiles");
        outputFolder.mkdir();

        playerOne.addCard(c1);
        playerOne.writeEnd(1);

        File file = new File("outputFiles/player" + playerOne.getId() + "_output.txt");
        assertTrue("Tetss that writeEnd makes a file", file.exists());

        file.delete();
    }

    @Test
    public void testCheckIfWon() {
        playerOne.addCard(c2);
        playerOne.addCard(t1);
        playerOne.addCard(t2);
        playerOne.addCard(t3);

        assertFalse("Tests only 4 of the same are a win", playerOne.checkIfWon());

        playerOne.removeCard(c2);
        playerOne.addCard(c1);

        assertTrue("Tests 4 of the same are a win", playerOne.checkIfWon());

        playerOne.removeCard(c1);
        playerOne.removeCard(t1);
        playerOne.removeCard(t2);
        playerOne.removeCard(t3);
        playerOne.addCard(c2);
        playerOne.addCard(t4);
        playerOne.addCard(t5);
        playerOne.addCard(t6);

        assertTrue("Tests 4 not equal to player hand are a wnning hand", playerOne.checkIfWon());

        playerOne.removeCard(c2);
        playerOne.removeCard(t4);
        playerOne.removeCard(t5);
        playerOne.removeCard(t6);
    }

    @Test
    public void testDiscard() throws IOException {
        playerTwo.addCard(c1);
        playerTwo.addCard(c2);
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
        assertTrue("Tests card is discarded to correct place", lines.contains("Player " + id + " discards " + c1.getValue() + " to deck " + d3.getId()));

        File file = new File("outputFiles/player" + playerTwo.getId() + "_output.txt");
        assertTrue("Tests file is created", file.exists());

        file.delete();
        playerTwo.removeCard(c2);
    }

    @Test
    public void testDraw() throws IOException {
        d2.addCard(c1);
        d2.addCard(c1);

        playerTwo.draw(d2);
        String line;
        List<String> lines = new ArrayList<String>();

        try (BufferedReader bf = new BufferedReader(new FileReader("outputFiles/player2_output.txt"))) {
            while ((line = bf.readLine()) != null) {
                lines.add(line);
            }
        }
        String id = Integer.toString(playerTwo.getId());
        assertTrue("Tests that deck can be added to by player", lines.contains("Player " + id + " draws " + Integer.toString(c1.getValue()) + " from deck "
                + Integer.toString(d2.getId())));

        File file = new File("outputFiles/player" + playerTwo.getId() + "_output.txt");
        assertTrue(file.exists());

        file.delete();
        playerTwo.removeCard(c2);

    }

    @After
    public void cleanUp() {
        playerOne.reset();
        playerTwo.reset();

    }

}
