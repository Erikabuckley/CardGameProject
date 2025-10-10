package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player{
    private static int idCounter = 0; // counter for unique player IDs
    private int id; // player's unique playerId
    private ArrayList<Card> cardsTemp = new ArrayList<Card>();
    private List<Card> cards = Collections.synchronizedList(cardsTemp);

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


}