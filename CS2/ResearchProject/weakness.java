

public class weakness {
    String name;
    String info;
    Long lineNumber;

    public weakness(String name, String info,Long lineNumber){
        this.name = name;
        this.info = info;
        this.lineNumber = lineNumber;
    }

    public String getName(){
        return name;
    }

    public String getInfo() {
        return info;
    }
    
    public long getLineNumber(){
        return lineNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setLineNumber(Long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void printWeakness(){
        System.out.print("Name: "+name+" Info: "+info+" Line: "+lineNumber);
    }
}
