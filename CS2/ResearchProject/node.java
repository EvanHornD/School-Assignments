import java.util.*;

public class node {
    node parentNode;
    ArrayList<node> childNodes = new ArrayList<>();
    String element;
    HashMap<String,String> elementAttributes = new HashMap<>(); 
    String text = "";

    public node(node parentNode, String element){
        this.parentNode = parentNode;
        this.element = element;
    }

    public void addText(String text){
        this.text += text;
    }

    public void addAttribute(String[] attribute){
        this.elementAttributes.put(attribute[0],attribute[1]);
    }

    public void addChildNode(node childNode){
        this.childNodes.add(childNode);
    }

    public node getParentNode(){
        return this.parentNode;
    }
}
