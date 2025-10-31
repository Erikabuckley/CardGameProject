package game;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CardGame {

    private static volatile boolean gameOver = false;

    public static String[] readTxtFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String line = bf.readLine();

        while (line != null && !line.equals(" ")) {
            try{
                int lineInt = Integer.parseInt(line);
                if (lineInt > 0){
                    lines.add(line);
                    line = bf.readLine();
                } 
            }catch(NumberFormatException e){
                break;
            }
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
     //uses error handling to ensure valid input from the user
    public static String[] getFile(int playerNumber, Scanner scanner) throws IOException {
        boolean valid = false;
        String fileName = "";
        String[] lines = null;

        while (!valid) {
            System.out.println("Please enter location of pack to load: ");
            fileName = scanner.nextLine();
            if (fileName.contains(".txt")) {
                File f = new File(fileName);
                if (f.exists()) {
                    lines = readTxtFile(fileName);
                    if (lines.length == (8 * playerNumber)) {
                        valid = true;
                    } else {
                        System.out.println("Inncorect file length/ contains invalid characters. Try again.");
                    }
                }else{
                    System.out.println("File does not exist. Try again.");
                }
            } else {
                System.out.println("File does not exist. Try again.");
            }
        }
        return lines;
    }

    public static int getNumber(Scanner scanner){
        boolean valid = false;
        int playerNumber = 0;
        while (!valid) {
            valid = true;
            // gather input data
            System.out.println("Please enter the number of players: ");
            String playerNumberString = scanner.nextLine();
            try {
                playerNumber = Integer.parseInt(playerNumberString);
                if (playerNumber <= 0) {
                    System.out.println("Must be greater than 0.");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Must be a number.");
                valid = false;
            }
        }
        return playerNumber;
    }

    public static void runGame(int playerNumber, Scanner scanner) throws IOException {
        ArrayList<Player> playersTemp = new ArrayList<Player>();
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<CardDeck> deckTemp = new ArrayList<CardDeck>();

        // make them thread safe
        List<Player> players = Collections.synchronizedList(playersTemp);
        List<CardDeck> decks = Collections.synchronizedList(deckTemp);

        for (int x = 0; x < playerNumber; x++) {
            Player temp = new Player();
            players.add(temp);
        }

        for (int x = 0; x < playerNumber; x++) {
            CardDeck temp = new CardDeck();
            decks.add(temp);
        }

        String[] lines = getFile(playerNumber, scanner);
        scanner.close();

        int max = lines.length;
        for (int x = 0; x < max; x++) {
            cards.add(new Card(Integer.parseInt(lines[x])));
        }

        //deals cards to players
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < playerNumber; y++) {
                players.get(y).addCard(cards.get(playerNumber * x + y));
            }
        }
        int totalPlayerCards = 4 * playerNumber;
        List<Card> leftOver = cards.subList(totalPlayerCards, (cards.size()));

        // using the left over cards, adding them to the decks of players
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < playerNumber; y++) {
                decks.get(y).addCard(leftOver.get(playerNumber * x + y));
            }
        }

        // write initial hands
        for (Player p : players) {
            p.writeInitial();
        }

        final int numPlayers = playerNumber;
        CyclicBarrier barrier = new CyclicBarrier(numPlayers);

        for (Player p : players) {
            new Thread("Player " + p.getId()) {
                public void run() {
                    try {
                        while (true) {
                            // Check if this player wins
                            if (p.checkIfWon()) {
                                gameOver = true;

                                // Write game results
                                synchronized (players) {
                                    for (Player player : players) {
                                        player.writeEnd(p.getId());
                                    }
                                }

                                System.out.println("Game over: Player " + p.getId() + " wins");

                                synchronized (decks) {
                                    for (CardDeck d : decks) {
                                        d.writeDeck();
                                    }
                                }
                            }
                            barrier.await();
                            if (gameOver){
                                break;
                            }else{
                                // Continue with normal gameplay
                                p.draw(decks.get(p.getId() - 1));
                                barrier.await();
                                p.discard(decks.get(p.getId() % numPlayers));
                                barrier.await();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        int numPlayers = getNumber(scanner);
        runGame(numPlayers, scanner);
    }

}