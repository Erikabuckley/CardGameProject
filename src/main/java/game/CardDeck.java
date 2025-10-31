package game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private static int idCounter = 1;
    private int id;
    private ArrayList<Card> cardsTemp = new ArrayList<Card>();
    private List<Card> cards = Collections.synchronizedList(cardsTemp); // makes it threadsafe

    public CardDeck() {
        this.id = idCounter++;
    }

    public synchronized List<Card> getCards() {
        return this.cards;
    }

    public synchronized String formatOut(List<Card> cards) {
        String line = "";
        for (Card c : cards) {
            line = line.concat(" " + Integer.toString(c.getValue()));
        }
        return (line);
    }

    public synchronized void addCard(Card card) {
        cards.add(card);
    }

    public synchronized Card removeCard() {
        return cards.remove(0);
    }

    public synchronized int getId() {
        return this.id;
    }
    //writes the deck at end of game to specific deck file
    public synchronized void writeDeck() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("outputFiles/deck" + Integer.toString(getId()) + "_output.txt"));
        bufferedWriter.write("deck" + Integer.toString(getId()) + " contents: " + formatOut(getCards()));
        bufferedWriter.close();
    }

    public void reset() {
        idCounter = 1;
    }
}
