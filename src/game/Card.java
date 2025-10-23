package game;

public class Card {
    private int value;

    public Card(int value) {
        this.value = value;
    }

    public synchronized int getValue() {
        return this.value;
    }

}