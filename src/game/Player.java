package game;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player{
    private static int idCounter = 0; // counter for unique player IDs
    private int id; // player's unique playerId
    private ArrayList<Card> cardsTemp = new ArrayList<Card>();  
    private List<Card> cards = Collections.synchronizedList(cardsTemp); // synchronised list makes it threadsafe

    
    public Player(){
        this.id = idCounter++;

    }
    
    public int getId(){
        return this.id;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void writeInitial() throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("player"+ Integer.toString(getId()) +"_output.txt"));
        bufferedWriter.write("Player " + Integer.toString(getId()) + " initial hand " + getCards());
        bufferedWriter.close();
    }
    public void writeGo(Card draw, Card discard) throws IOException{
        String id = Integer.toString(getId());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("player"+ id +"_output.txt", true));
        bufferedWriter.write("Player " + id + " draws " + draw + " from deck" + Integer.toString(getId() - 1));
        bufferedWriter.write("Player " + id  + " discards " + discard + " to deck " + Integer.toString((getId() + 1) % 4));
        bufferedWriter.write("Player " + id + " current hand " + getCards());
        bufferedWriter.close();
    }

    public void writeEnd(int winner) throws IOException{
        String id = Integer.toString(getId());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("player"+ id +"_output.txt", true));
        bufferedWriter.write("Player " + Integer.toString(winner) + " wins");
        bufferedWriter.write("Player " + id  + " exits");
        bufferedWriter.write("Player " + id + " final hand " + getCards());
        bufferedWriter.close();
    }

    // add method checkIfWon()
    // itterate over player cards
    // check if card value is equal to id
}