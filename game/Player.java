public class Player{
    private static int idCounter = 0; // counter for unique player IDs
    private int id; // player's unique playerId

    public Player(){
        this.id = idCounter++;

    }
    
    public int getId(){
        return this.id;
    }
}