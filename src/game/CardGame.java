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
        ArrayList<Card> cardsTemp = new ArrayList<Card>();
        //make them thread safe
        List<Player> players = Collections.synchronizedList(playersTemp);
        List<Card> cards = Collections.synchronizedList(cardsTemp);

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
            System.out.println(lines);
            if (lines.length != (8 *playerNumber)){
                valid = false;
            }
        }
        scanner.close();




        //split into queues
        /////////////////////
        /// 
        


    }
}