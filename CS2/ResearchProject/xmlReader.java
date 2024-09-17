

import java.io.*;
import java.util.*;


public class xmlReader {
    private RandomAccessFile xmlFile;
    private String filePath;
    public Map<Long, Long> lineIndex;
    public ArrayList<weakness> weaknesses;

    public xmlReader(String filePath) throws IOException{
        this.filePath = filePath;
        this.xmlFile = new RandomAccessFile(filePath, "r");
        this.lineIndex = new HashMap<>();
        weaknesses = new ArrayList<>();
        indexFile();
    }

    private void indexFile() throws IOException {
        long fileIndexPointer = 0;
        long lineNumber = 0;
        String line;

        // Open a temporary BufferedReader to index the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if(line.contains("<Weakness ")){
                    lineIndex.put(lineNumber, fileIndexPointer);
                    String name = "";
                    String info = "";
                    for(int i=0;i<line.length();i++){
                        if(line.charAt(i)=='N'){
                            i+=6;
                            while(line.charAt(i)!='"'){
                                name+=line.charAt(i);
                                i++;
                            }
                            i+=2;
                            while(line.charAt(i)!='>'){
                                info+=line.charAt(i);
                                i++;
                            }
                        }
                    }
                    weaknesses.add(new weakness(name, info, lineNumber));
                }
                fileIndexPointer += line.getBytes().length + System.lineSeparator().getBytes().length;
                lineNumber++;
            }
        }
    }
    
    public static boolean checkIfElement(String element){
        if(element.contains("xhtml:")){
            return false;
        }
        return true;
    }

    public static ArrayList<Object> getElement(int lineIndex, String line){
        String element = "";
        int lineCursor = lineIndex;
        boolean isClosingTag = false;
        for (int i = lineCursor; i < line.length(); i++) {
            if(!" >".contains(String.valueOf(line.charAt(i)))){
                if(line.charAt(i)!='/'){
                    element+=line.charAt(i);
                } else {
                    isClosingTag = true;
                }
            }else{
                lineCursor = i;
                break;
            }
        }
        return new ArrayList<>(Arrays.asList(element,lineCursor,isClosingTag));
    }    

    public static ArrayList<Object> getElementAttributes(int lineIndex, String line){
        ArrayList<String[]> Attributes = new ArrayList<>();
        int lineCursor = lineIndex;
        boolean isClosingTag = false;
        boolean done = false;
        int parity = 1;
        String[] attribute = new String[2];
        String text = "";

        for (int i = lineCursor; i < line.length(); i++) {
            switch (parity%2) {
                case 1: // get the name of the attribute
                if(line.charAt(i) != '"'){
                    text+=line.charAt(i);
                }else{
                    parity++;
                    attribute[0]=text;
                    text = "";
                    i++;
                } 
                    break;
                case 0: // get the details of the attribute
                    if(!"\"".contains(String.valueOf(line.charAt(i)))){
                        text+=line.charAt(i);
                    }else{
                        parity++;
                        attribute[1]=text;
                        switch (line.charAt(i+1)) {
                            case '/': isClosingTag = true; done = true; lineCursor = i+3; break;
                            case '>': done = true; lineCursor = i+2; break;
                            case ' ': text=""; i+=2; break;
                            default:break;
                        }
                        Attributes.add(attribute);
                        attribute = new String[2];
                    } 
                    break;
                default: break;
            }
            if(done){break;}
        }
        return new ArrayList<>(Arrays.asList(Attributes,lineCursor,isClosingTag));
    }    

    public nodeTree createNodeTree(Long targetLine) throws IOException {
        Long filePosition = lineIndex.get(targetLine);
        xmlFile.seek(filePosition);
        String line = xmlFile.readLine();
        String element = "";
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i)=='<'){
                ArrayList<Object> parsedElement = getElement(i+1, line);
                element = (String) parsedElement.get(0);
                i = (Integer) parsedElement.get(1);
                break;
            }
        }
        System.out.println(element);
        nodeTree tree = new nodeTree(new node(null,element));
        tree.activeNode.


        return null;
    }


    
    public void printWeaknessNames() throws IOException {
        for(int i=0; i<weaknesses.size();i++){
            weaknesses.get(i).printWeakness();
            System.out.print(" charIndex: "+lineIndex.get(weaknesses.get(i).getLineNumber())+"\n");
        }
        createNodeTree(weaknesses.get(1).getLineNumber());
    }

    public void close() throws IOException {
        xmlFile.close();
    }
}
