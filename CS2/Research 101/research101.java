import java.io.*;
import java.util.*;

public class research101 {
    static RandomAccessFile xmlFile;
    static String filePath;
    static Map<Integer, Long> lineIndex;
    static ArrayList<String[]> weaknesses = new ArrayList<String[]>();

    public static void indexFile() throws IOException {
        long filePointer = 0;    // Tracks the current byte position in the file
        int lineNumber = 0;      // Tracks the current line number
        String line;

        // Open a temporary BufferedReader to index the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null&& lineNumber<100) {
                if(line.contains("<Weakness ")){
                    lineIndex.put(lineNumber, filePointer);
                    String name = "";
                    for(int i=0;i<line.length();i++){
                        if(line.charAt(i)=='N'){
                            i+=6;
                            while(line.charAt(i)!='"'){
                                name+=line.charAt(i);
                                i++;
                            }
                        }
                    }
                    String[] n = {name,Long.toString(filePointer)};
                    weaknesses.add(n);
                }

                // Store the line number and the current byte position
                //lineIndex.put(lineNumber, filePointer);

                // Move the filePointer forward by the length of the current line plus the newline character
                filePointer += line.getBytes().length + System.lineSeparator().getBytes().length;
                lineNumber++;
            }
        }
    }
    
    public static void main(String args[]) throws Exception {
        filePath = "School-Assignments\\CS2\\Research 101\\cwec_v4.15.xml";
        xmlFile = new RandomAccessFile(filePath, "r");
        lineIndex = new HashMap<>();
        indexFile();
        System.out.println(lineIndex.get(2));
        for(int i=0; i<weaknesses.size();i++){
            System.out.println(weaknesses.get(i));
        }
    }
}
