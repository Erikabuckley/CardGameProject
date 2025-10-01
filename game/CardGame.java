import java.io.*;
import java.util.Scanner;

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

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        // gather input data
        System.out.println("Please enter the number of players: ");
        String playerNumber = scanner.nextLine();

        System.out.println("Please enter location of plack to load: ");
        String fileName = scanner.nextLine();
        // reads file and converts to
        readTxtFile(fileName);
        
    }
}