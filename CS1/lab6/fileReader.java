import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileReader {
    static void print(String string){
        System.out.println(string);
    }
    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("C:\\Users\\ehorn\\Desktop\\cs1\\lab6\\temperatures.csv");
        Scanner scanner = new Scanner(myFile);

        int counter = 0, tempAbove25 = 0, value = 0;
        double highTemp = Double.MIN_VALUE, lowTemp = Double.MAX_VALUE, total = 0;
        
        while (scanner.hasNextLine()){
            counter++;
            String fileContent = scanner.nextLine();
            value = Integer.parseInt(fileContent);
            total = total + value;
            if(value>highTemp){highTemp = value;}
            if(value<lowTemp){lowTemp = value;}
            if(value>25){tempAbove25++;}
        }
        
        print(""+total/counter);
        print(""+highTemp);
        print(""+lowTemp);
        print(""+tempAbove25);
        scanner.close();
    }
}
