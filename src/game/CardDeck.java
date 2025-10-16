package game;

import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck{
    private static int idCounter = 0;
    private int id;
    private ArrayList<Card> cardsTemp = new ArrayList<Card>();
    private List<Card> cards = Collections.synchronizedList(cardsTemp); // makes it threadsafe
    

    public CardDeck() {
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public String formatOut(List<Card> cards){
        String line = "";
        for(Card c: cards){
            line = line.concat(" " + Integer.toString(c.getValue()));
        }
        return(line);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
    
    public int getId() {
        return this.id;
    }

    public void writeDeck() throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("deck" + Integer.toString(getId()) + "_output.txt"));
        bufferedWriter.write("deck" + Integer.toString(getId()) + "contents: " + formatOut(getCards()));
        bufferedWriter.close();
    }

    
}