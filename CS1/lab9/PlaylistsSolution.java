package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlaylistsSolution {
    public static void main(String[] args) {
        String popPlaylist = "------Pop Playlist!------\n";
        String regionalPlaylist = "-----Regional Playlist-----\n";
        String hiphopPlaylist = "------HipHop Playlist-----\n";
        String dancePlaylist = "-----Dance Playlist------\n";
        int popSum = 0, regionalSum = 0, hiphopSum = 0, danceSum = 0;
        int popCount = 0, regionalCount = 0, hiphopCount = 0, danceCount = 0;

        try {
            File Playlist = new File("lab9\\likedsongs.txt");
            Scanner playlistS = new Scanner(Playlist);
            while(playlistS.hasNext()){
                String s = addToPlaylist(playlistS.next(),playlistS.next());
                switch(playlistS.next()){
                    case"Pop":popPlaylist+=s;popCount+=1;popSum+=playlistS.nextInt(); break;
                    case"Regional":regionalPlaylist+=s;regionalCount+=1;regionalSum+=playlistS.nextInt(); break;
                    case"HipHop":hiphopPlaylist+=s;hiphopCount+=1;hiphopSum+=playlistS.nextInt(); break;
                    case"Dance":dancePlaylist+=s;danceCount+=1;danceSum+=playlistS.nextInt(); break;
                }
            }

            playlistS.close();
            int p=calculateAverage(popSum, popCount),h=calculateAverage(hiphopSum, hiphopCount),d=calculateAverage(danceSum, danceCount),r=calculateAverage(regionalSum, regionalCount);
            printResults(popPlaylist, hiphopPlaylist, dancePlaylist, regionalPlaylist, determineFavoriteGenre(p,h,d,r),p,h,d,r);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

       
    }

    public static String addToPlaylist(String songName, String songArtist) {
        return(songName+" "+songArtist+"\n");
    }

    public static int calculateAverage(int sum, int count) {
        return(sum/count);
    }

    public static String determineFavoriteGenre(int popAvg, int hiphopAvg, int danceAvg, int regionalAvg) {
        if((popAvg>=hiphopAvg)&&(popAvg>=danceAvg)&&(popAvg>=regionalAvg)){return("Pop");}
        else if((hiphopAvg>=popAvg)&&(hiphopAvg>=danceAvg)&&(hiphopAvg>=regionalAvg)){return("HipHop");}
        else if((danceAvg>=popAvg)&&(danceAvg>=hiphopAvg)&&(danceAvg>=regionalAvg)){return("Dance");}
        else {return("Regional");}
    }

    public static void printResults(String popPlaylist, String hiphopPlaylist, String dancePlaylist, String regionalPlaylist, String favoriteGenre, int popAvg, int hiphopAvg, int danceAvg, int regionalAvg) {
        System.out.println(popPlaylist);
        System.out.println("Average Rating: "+popAvg);
        System.out.println(hiphopPlaylist);
        System.out.println("Average Rating: "+hiphopAvg);
        System.out.println(dancePlaylist);
        System.out.println("Average Rating: "+danceAvg);
        System.out.println(regionalPlaylist);
        System.out.println("Average Rating: "+regionalAvg);
        System.out.println("");
        System.out.println("The favorite genre is "+favoriteGenre+" with the highest average rating");
    }   
}
