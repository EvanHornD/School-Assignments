import java.util.*;

public class node {
    node parentNode;
    ArrayList<node> childNodes;
    String element;
    String[] elementAttributes;
    String text;

    public node(node parentNode, String element){
        this.parentNode = parentNode;
        this.element = element;
    }
}
