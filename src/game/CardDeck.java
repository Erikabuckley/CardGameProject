package game;
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

    public void addCard(Card card) {
        cards.add(card);
    }
    
    public int getID() {
        return this.id;
    }

    
}