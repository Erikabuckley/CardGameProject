package game;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CardGame {
    // static to ensure that all threads can access flag 
    private static volatile boolean gameOver = false;

    // method to read a text file passed as a perameter, only keeps lines which are valid cards
    public static String[] readTxtFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String line = bf.readLine();

        while (line != null && !line.trim().isEmpty()) {
            try {
                int lineInt = Integer.parseInt(line);
                if (lineInt > 0) { // ensures line is a positive integer so valid for game
                    lines.add(line);
                    line = bf.readLine();
                }
            } catch (NumberFormatException e) {
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

    public static String[] getFile(int playerNumber, Scanner scanner) throws IOException {
        boolean valid = false;
        String fileName = "";
        String[] lines = null;
        // gets input from user and ensures that it is correct length and has valid digits in it
        while (!valid) {
            System.out.println("Please enter location of pack to load: ");
            fileName = scanner.nextLine();
            if (fileName.contains(".txt")) {
                File f = new File(fileName);
                if (f.exists()) {
                    lines = readTxtFile(fileName);
                    if (lines.length == (8 * playerNumber)) {
                        valid = true;
                    // returns a request to user to input a different file, if first is invalid
                    } else {
                        System.out.println("Inncorect file length/ contains invalid characters. Try again.");
                    }
                } else {
                    System.out.println("File does not exist. Try again.");
                }
            } else {
                System.out.println("File does not exist. Try again.");
            }
        }
        return lines;
    }

    public static int getNumber(Scanner scanner) {
        boolean valid = false;
        int playerNumber = 0;
        while (!valid) {
            valid = true;
            System.out.println("Please enter the number of players: ");
            String playerNumberString = scanner.nextLine();
            try {
                playerNumber = Integer.parseInt(playerNumberString);
                if (playerNumber <= 0) { // makes sure this is a positive integer to be a valid number of players
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

    // main game, takes number and gets file then creates threads for the players and executrs the game
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

        int max = lines.length;
        for (int x = 0; x < max; x++) {
            cards.add(new Card(Integer.parseInt(lines[x])));
        }

        // deals cards to players
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < playerNumber; y++) {
                players.get(y).addCard(cards.get(playerNumber * x + y));
            }
        }
        int totalPlayerCards = 4 * playerNumber;
        List<Card> leftOver = cards.subList(totalPlayerCards, (cards.size()));

        // using the left over cards, adds them to the decks
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < playerNumber; y++) {
                decks.get(y).addCard(leftOver.get(playerNumber * x + y));
            }
        }

        // writes initial player hands
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
                            // check if this player wins
                            if (p.checkIfWon()) {
                                gameOver = true;

                                // write game results to specific files
                                synchronized (players) {
                                    for (Player player : players) {
                                        player.writeEnd(p.getId());
                                    }
                                }
                                // prints winner to terminal
                                System.out.println("Game over: Player " + p.getId() + " wins");

                                // writes decks results to specific files
                                synchronized (decks) {
                                    for (CardDeck d : decks) {
                                        d.writeDeck();
                                    }
                                }
                            }
                            barrier.await();
                            if (gameOver) {
                                // thread exits loop if gameOver flag is true
                                break;
                            } else {
                                // otherwise continue with normal gameplay
                                synchronized (p){
                                    p.draw(decks.get(p.getId() - 1));
                                    p.discard(decks.get(p.getId() % numPlayers));
                                }

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

    // gainser user input and then calls the runGame method to start the game
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numPlayers = getNumber(scanner);
        runGame(numPlayers, scanner);
    }

}