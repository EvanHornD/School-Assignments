

import java.io.*;
import java.util.*;


public class xmlReader {
    private RandomAccessFile xmlFile;
    private String filePath;
    private Map<Integer, Long> lineIndex;
    private ArrayList<String[]> weaknesses;

    public xmlReader(String filePath) throws IOException{
        this.filePath = filePath;
        this.xmlFile = new RandomAccessFile(filePath, "r");
        this.lineIndex = new HashMap<>();
        this.weaknesses = new ArrayList<String[]>();
        indexFile();
    }

    private void indexFile() throws IOException {
        long fileIndexPointer = 0;
        int lineNumber = 0;
        String line;

        // Open a temporary BufferedReader to index the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if(line.contains("<Weakness ")){
                    lineIndex.put(lineNumber, fileIndexPointer);
                    String name = "";
                    for(int i=0;i<line.length();i++){
                        if(line.charAt(i)=='N'){
                            i+=6;
                            while(line.charAt(i)!='"'){
                                name+=line.charAt(i);
                                i++;
                            }
                            String info = "";
                            while(line.charAt(i)!='"'){
                                name+=line.charAt(i);
                                i++;
                            }
                        }
                    }
                    String[] n = {name,Long.toString(lineNumber)};
                    weaknesses.add(n);
                }
                fileIndexPointer += line.getBytes().length + System.lineSeparator().getBytes().length;
                lineNumber++;
            }
        }
    }
    
    public void printWeaknessNames() {
        for(int i=0; i<weaknesses.size();i++){
            System.out.println(Arrays.toString(weaknesses.get(i))+" "+lineIndex.get(Integer.parseInt(weaknesses.get(i)[1])));
        }
    }

    public void close() throws IOException {
        xmlFile.close();
    }
}
