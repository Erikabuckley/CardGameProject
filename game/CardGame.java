import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class CardGame{
    public static void readTxtFile(String fileName) throws IOException{ 
        // open a file for reading and pass to a buffer 
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        
        // define a variable which will store the lines as we load them
        String line;
    
        // loop to read and print lines until file end
        line = String.format("Contents of '%s'", fileName);
        while(line!=null) {
            System.out.println(line);
            line = bufferedReader.readLine();
        } 
        
        // close buffer/file io stream
        bufferedReader.close();
        System.out.println("### Exit readTxtFile()!");
    }

    public static void saveToTxt(String filename, String text) throws IOException{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename,true));//true for append
        bufferedWriter.write(text);
        bufferedWriter.close();
    }


    public static void main(String[] args) throws IOException{
        int n = 4;
        int max = 8 * n;
        Random r = new Random();
        for (int i=0; i < max; i++) {
            int r1 = r.nextInt(1, 100); // so always greater than zero
            String str = String.valueOf(r1) + "\n";
            saveToTxt("test1.txt", str);
        }

        Boolean valid = false;
        int playerNumber;
        String fileName = "";
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
    
        valid = false;
        while (!valid){
            valid = true;
            System.out.println("Please enter location of pack to load: ");
            fileName = scanner.nextLine();
            File f = new File(fileName);
            if (!f.exists()){
                valid = false;
            }
        }
        // reads file
        readTxtFile(fileName);
        
    }
}