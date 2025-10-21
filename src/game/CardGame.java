package game;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGame {
    
    private static volatile boolean gameOver = false;

    public static String[] readTxtFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String line = bf.readLine();

        while (line != null) {
            lines.add(line);
            line = bf.readLine();
        }

        bf.close();
        String[] array = lines.toArray(new String[0]);
        return (array);
    }

    public static void saveToTxt(String filename, String text) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, true));// true for append
        bufferedWriter.write(text);
        bufferedWriter.close();
    }

    public static void main(String[] args) throws IOException {

        Boolean valid = false;
        int playerNumber = 0;
        String fileName = "";
        ArrayList<Player> playersTemp = new ArrayList<Player>();
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<CardDeck> deckTemp = new ArrayList<CardDeck>();

        // make them thread safe
        List<Player> players = Collections.synchronizedList(playersTemp);
        List<CardDeck> decks = Collections.synchronizedList(deckTemp);

        Scanner scanner = new Scanner(System.in);
        while (!valid) {
            valid = true;
            // gather input data
            System.out.println("Please enter the number of players: ");
            String playerNumberString = scanner.nextLine();
            try {
                playerNumber = Integer.parseInt(playerNumberString);
                if (playerNumber <= 0) {
                    valid = false;
                }
            } catch (NumberFormatException e) {
                valid = false;
            }
        }

        for (int x = 0; x < playerNumber; x++) {
            Player temp = new Player();
            players.add(temp);
        }

        for (int x = 0; x < playerNumber; x++) {
            CardDeck temp = new CardDeck();
            decks.add(temp);
        }

        valid = false;
        String[] lines = null;
        while (!valid) {
            System.out.println("Please enter location of pack to load: ");
            fileName = scanner.nextLine();
            if (fileName.contains(".txt")){
                File f = new File(fileName);
                if (f.exists()) {
                    lines = readTxtFile(fileName);
                    scanner.close();
                    if (lines.length == (8 * playerNumber)) {
                        valid = true;
                    }else{
                        System.out.println("Inncorect file length. Try again.");
                    }
                }
            }else{
                System.out.println("File does not exist. Try again.");
            }
        }

        int max = lines.length;
        for (int x = 0; x < max; x++) {
            cards.add(new Card(Integer.parseInt(lines[x])));
        }

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < playerNumber; y++) {
                players.get(y).addCard(cards.get(4 * x + y));
            }
        }

        List<Card> leftOver = cards.subList(16, (cards.size()));

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < playerNumber; y++) {
                decks.get(y).addCard(leftOver.get(4 * x + y));
            }
        }

        // write initial hands
        for (Player p : players) {
            p.writeInitial();
        }

        final int numPlayers = playerNumber;
        
        // creates threads
        for (Player p : players) {
            new Thread("Player " + Integer.toString(p.getId())) {  
                @Override public void run(){
                    while(!gameOver){
                        try {
                            if (!p.checkIfWon()) {
                                p.draw(decks.get(p.getId() - 1));
                                p.discard(decks.get((p.getId() + 1) % numPlayers));
                            } else {
                                gameOver = true;
                                for (Player player : players) { 
                                    player.writeEnd(p.getId()); 
                                } 
                                System.out.println("Game over player: " + Integer.toString(p.getId())+ " wins");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
          
        for (CardDeck d : decks){
            d.writeDeck();
        }
        
    //     // write end hands
    //     for (Player p : players) {
    //         p.writeEnd(pWinner);
    //     }
    //     //print winner to terminal
    //     system.out.println();
    }
}