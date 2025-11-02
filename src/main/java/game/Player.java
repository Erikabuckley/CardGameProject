package game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player {
    private static int idCounter = 1; // counter for unique player IDs
    private int id; // player's unique playerId
    private ArrayList<Card> cardsTemp = new ArrayList<Card>();
    private List<Card> cards = Collections.synchronizedList(cardsTemp); // synchronised list makes it threadsafe
    private static final Random random = new Random();

    public Player() {
        this.id = idCounter++;

    }

    public synchronized int getId() {
        return this.id;
    }

    public synchronized List<Card> getCards() {
        return this.cards;
    }

    // used to display the users hand
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

    public synchronized void removeCard(Card card) {
        cards.remove(card);
    }

    // writes the users initial cards to their txt file
    public synchronized void writeInitial() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("outputFiles/player" + Integer.toString(getId()) + "_output.txt"));
        bufferedWriter.write("Player " + Integer.toString(getId()) + " initial hand" + formatOut(getCards()));
        bufferedWriter.close();
    }

    // writes users endcards to thier txt file
    public synchronized void writeEnd(int winner) throws IOException {
        String id = Integer.toString(getId());
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("outputFiles/player" + id + "_output.txt", true));
        if (winner == getId()) {
            bufferedWriter.write("\nPlayer " + Integer.toString(winner) + " wins");
        } else {
            bufferedWriter.write(
                    "\nPlayer " + winner + " has informed player " + getId() + " that player " + winner + " has won");
        }
        bufferedWriter.write("\nPlayer " + id + " exits");
        bufferedWriter.write("\nPlayer " + id + " final hand " + formatOut(getCards()));
        bufferedWriter.close();
    }

    public synchronized boolean checkIfWon() {
        // checks if all cards are the same - could be different to id
        boolean allSame = true;
        List<Card> cards = getCards();
        if (!cards.isEmpty()) {
            int first = cards.get(0).getValue();
            for (Card c : cards) {
                if (!(c.getValue() == first)) {
                    allSame = false;
                    break;
                }
            }
        }
        return allSame;
    }

    public synchronized void discard(CardDeck deck) throws IOException {
        List<Card> validCards = new ArrayList<>();
        for (Card c : cards) {
            if (c.getValue() != getId()) {
                validCards.add(c);
            }
        }

        if (!validCards.isEmpty()) {
            // pick a random card from validCards
            Card toDiscard = validCards.get(random.nextInt(validCards.size()));
            deck.addCard(toDiscard);
            removeCard(toDiscard);

            // write to output
            String id = Integer.toString(getId());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
                    "outputFiles/player" + id + "_output.txt", true));
            bufferedWriter.write("\nPlayer " + id + " discards " + toDiscard.getValue() + " to deck " + deck.getId());
            bufferedWriter.write("\nPlayer " + id + " current hand " + formatOut(getCards()));
            bufferedWriter.close();
        }
    }

    public synchronized void draw(CardDeck deck) throws IOException {
        Card temp = deck.removeCard();
        addCard(temp);

        String id = Integer.toString(getId());
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("outputFiles/player" + id + "_output.txt", true));
        bufferedWriter.write("\nPlayer " + id + " draws " + Integer.toString(temp.getValue()) + " from deck "
                + Integer.toString(deck.getId()));
        bufferedWriter.close();
    }

    public void reset() {
        idCounter = 1;
    }
}