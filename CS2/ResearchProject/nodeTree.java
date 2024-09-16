import java.util.*;

public class nodeTree {
    node activeNode;
    int currentDepth;
    int treeDepth;
    ArrayList<ArrayList<node>> nodes;
    // 
    public nodeTree(node rootNode){
        this.activeNode = rootNode;
        this.currentDepth = 0;
        this.treeDepth = 0;
        this.nodes = new ArrayList<>();
        nodes.add(new ArrayList<>(Arrays.asList(rootNode)));
    }

    
}
