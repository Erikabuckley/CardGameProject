package game;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private static int idCounter = 1; // counter for unique player IDs
    private int id; // player's unique playerId
    private ArrayList<Card> cardsTemp = new ArrayList<Card>();
    private List<Card> cards = Collections.synchronizedList(cardsTemp); // synchronised list makes it threadsafe

    public Player() {
        this.id = idCounter++;

    }

    public int getId() {
        return this.id;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public String formatOut(List<Card> cards) {
        String line = "";
        for (Card c : cards) {
            line = line.concat(" " + Integer.toString(c.getValue()));
        }
        return (line);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void writeInitial() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("player" + Integer.toString(getId()) + "_output.txt"));
        bufferedWriter.write("Player " + Integer.toString(getId()) + " initial hand " + formatOut(getCards()));
        bufferedWriter.close();
    }

    public void writeGo(Card draw, Card discard) throws IOException {
        String id = Integer.toString(getId());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("player" + id + "_output.txt", true));
        bufferedWriter.write("Player " + id + " draws " + draw + " from deck" + Integer.toString(getId() - 1));
        bufferedWriter
                .write("Player " + id + " discards " + discard + " to deck " + Integer.toString((getId() + 1) % 4));
        bufferedWriter.write("Player " + id + " current hand " + formatOut(getCards()));
        bufferedWriter.close();
    }

    public void writeEnd(int winner) throws IOException {
        String id = Integer.toString(getId());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("player" + id + "_output.txt", true));
        bufferedWriter.write("Player " + Integer.toString(winner) + " wins");
        bufferedWriter.write("Player " + id + " exits");
        bufferedWriter.write("Player " + id + " final hand " + formatOut(getCards()));
        bufferedWriter.close();
    }

    // add method checkIfWon()
    public Bool checkIfWon() {
        Bool won = False;
        id = getId();
        // iterate over player cards must always be four
        for (Card c : cards) {
            line = line.concat(" " + Integer.toString(c.getValue()));
        }
            // i need to get the cards that the player has, not from the card deck.
            // for each card in the List
            // ask if it is the same as the id
            // if it is all the same then won=True then the player has won


        // check if card value is equal to id
        

        
    }

    // discard

    // draw
}