import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Playlists {
    public static void main(String[] args) {
        // Initialize the userInput Scanner
        Scanner input = new Scanner(System.in);


        // Initializing playlists
        String popPlaylist = "------Pop Playlist!------";
        String regionalPlaylist = "-----Regional Playlist-----";
        String hiphopPlaylist = "------HipHop Playlist-----";
        String dancePlaylist = "-----Dance Playlist------";

        // Initializing playlists sum of song ratings
        int popRating = 0;
        int regionalRating = 0;
        int hiphopRating = 0;
        int danceRating = 0;


        // Initializing playlists song count
        int popCount = 0;
        int regionalCount = 0;
        int hiphopCount = 0;
        int danceCount = 0;
        
        // Handle File Not Found Exception
        try {
            // Create File and File Scanner Objects
            File myFile = new File("C:\\Users\\ehorn\\Desktop\\cs1\\lab7\\likedsongs.txt");
            Scanner Songs = new Scanner(myFile);
            System.out.println(Songs.nextLine());
    
            // Traverse file and concatenate to corresponding playlists, update values to compute avg
            while(Songs.hasNextLine()){
                System.out.println(Songs.next());
            }





            // Compute average of each playlist and print
            /* YOUR CODE GOES HERE :)) */


            // Compute max average
            /* YOUR CODE GOES HERE :3 */


            // Print the playlists 
            /* YOUR CODE GOES HERE ! */


            // Print the favorite genre :D!
            /* YOUR CODE GOES HERE ! */


        } catch (Exception e) {
            
        }
       
    }
}
