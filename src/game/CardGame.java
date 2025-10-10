package game;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGame{
    public static String[] readTxtFile(String fileName) throws IOException{ 
        // list that holds strings of a file
        List<String> lines = new ArrayList<String>();
      
        // load data from file
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
      
        // read entire line as string
        String line = bf.readLine();
      
        // checking for end of file
        while (line != null) {
            lines.add(line);
            line = bf.readLine();
        }
      
        // closing bufferreader object
        bf.close();
      
        // storing the data in arraylist to array
        String[] array = lines.toArray(new String[0]);
        return(array);
    }

    public static void saveToTxt(String filename, String text) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename,true));//true for append
        bufferedWriter.write(text);
        bufferedWriter.close();
    }


    public static void main(String[] args) throws IOException{

        Boolean valid = false;
        int playerNumber = 0;
        String fileName = "";
        ArrayList<Player> playersTemp = new ArrayList<Player>();
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<CardDeck> deckTemp = new ArrayList<CardDeck>();


        //make them thread safe
        List<Player> players = Collections.synchronizedList(playersTemp);
        List<CardDeck> decks = Collections.synchronizedList(deckTemp);


        Scanner scanner = new Scanner(System.in);
        while (!valid){
            valid = true;
            // gather input data
            System.out.println("Please enter the number of players: ");
            String playerNumberString = scanner.nextLine();
            try{
                playerNumber = Integer.parseInt(playerNumberString);
                if (playerNumber <= 0){
                    valid = false;
                }
            }catch (NumberFormatException e) {                
                valid = false;
            }
        }

        for (int x = 0; x < playerNumber ; x++){
            Player temp = new Player();
            players.add(temp);
        }   

        for (int x = 0; x < playerNumber ; x++){
            CardDeck temp = new CardDeck();
            decks.add(temp);
        }
    
        valid = false;
        String [] lines;
        while (!valid){
            valid = true;
            System.out.println("Please enter location of pack to load: ");
            fileName = scanner.nextLine();
            File f = new File(fileName);
            if (!f.exists()){
                valid = false;
            }
            // reads file
            lines = readTxtFile(fileName);
            if (lines.length != (8 *playerNumber)){
                valid = false;
            }
        }
        scanner.close();
        
        // itterate through lines
        // convert to int
        // create card
        // add to ArrrayList cards
        
        for (int x = 0; x < 4; x++){
            for (int y = 0; y < playerNumber; x++){
                players.get(y).addCard(cards.get(4*x + y));
            }
        }

        List<Card> leftOver = cards.subList(16,(cards.size() -1));

        for (int x = 0; x < 4; x++){
            for (int y = 0; y < playerNumber; x++){
                decks.get(y).addCard(leftOver.get(4*x + y));
            }
        }
        
        //check if someone has won
            // write intitsl card values to file
        // create threds
        // run threds to simulate game 
            // update deckx file
            // record move in playerx file
        // check winner
            // record who won
        // print to terminal
    }
}