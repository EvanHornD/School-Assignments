import java.util.Map;

public class InventoryLL{
    // TODO: Create a Linked List with the correct Attributes
    static Map<String, String> rarityColors = Map.of("Common", "\u001b[37m","Uncommon", "\u001b[32m","Rare", "\u001b[34m","Very Rare", "\u001b[35m","Legendary", "\u001b[31m");
    int length = 0;
    Node head;
    Node tail;

    // TODO: Create a Linked List Constructor 
    public InventoryLL(){

    }

    // TODO
    public void addToInventory(String[] item){
        Node itemNode = new Node(item);
        if(this.head == null){
            this.head = itemNode;
            this.tail = itemNode;
        } else{
            this.tail.next = itemNode;
            this.tail = itemNode;
        }
        this.length++;
    } 

    // TODO
    public boolean inInventory(String itemName){
        if(this.head == null){
            return false;
        }
        Node currNode = this.head;
        for (int i = 0; i < this.length; i++) {
            if (currNode.attributes[1].equals(itemName)) {
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    // TODO
    public String[] removeItem(String itemName){

        if(this.head == null){
            return null;
        }
        this.length--;
        // check if the first item is the one being removed
        Node currNode = this.head;
        if (currNode.attributes[1].equals(itemName)) {
            // checks if the list only has one item
            // sets the attributes of the item we are removing because it wont be accessible if it is the tail of the list
            String[] attributes = currNode.attributes;
            if(this.tail == this.head){
                this.head = null;
                this.tail = null;
            } else {
                this.head = currNode.next;
            }
            return attributes;
        }

        // checks every node after the first one if it is being removed
        while(currNode.next!=null){
            if (currNode.next.attributes[1].equals(itemName)) {
                // sets the attributes of the item we are removing because it wont be accessible if it is the tail of the list
                String[] attributes = currNode.next.attributes;
                if(currNode.next == this.tail){
                    this.tail = currNode;
                    this.tail.next = null;
                } else {
                    currNode.next = currNode.next.next;
                }
                return attributes;
            }
            currNode = currNode.next;
        }

        System.out.println("There was an error");
        return null;
    }

    public static void drawNextFrame(String frame){
        System.out.print("\033[H\033[2J" + frame);
        System.out.flush(); 
    }
 
    // TODO
    public void displayItems(int cursor) {
        int nameWidth = 32;
        int rarityWidth = 12;
        int hpWidth = 12;
        String inventoryInterface = "";
        inventoryInterface+="+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(hpWidth) + "+"+"\n";
        inventoryInterface+=String.format("| %-30s | %-10s | %-10s |%n", "Name", "Rarity", "HP");
        inventoryInterface+="+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(hpWidth) + "+"+"\n";

        if(this.head==null){
            inventoryInterface+=String.format("| %-56s |%n", "Nothing to see here");
            inventoryInterface+="+" + "-".repeat(nameWidth+rarityWidth+hpWidth+2) + "+"+"\n";
        }else{
            Node currNode = this.head;
            for (int i = 0; i < this.length; i++) {
                if(cursor==i){
                    inventoryInterface+="+" + "=".repeat(nameWidth) + "+" + "=".repeat(rarityWidth) + "+" + "=".repeat(hpWidth) + "+"+"\n";
                    inventoryInterface+=String.format("| %-35s | %-10s | %-15s |%n", "--> "+rarityColors.get(currNode.attributes[2])+currNode.attributes[1], currNode.attributes[2], currNode.attributes[4]+"\u001b[37m");
                    inventoryInterface+="+" + "=".repeat(nameWidth) + "+" + "=".repeat(rarityWidth) + "+" + "=".repeat(hpWidth) + "+"+"\n";
                }else{
                    inventoryInterface+=String.format("| %-35s | %-10s | %-15s |%n", rarityColors.get(currNode.attributes[2])+currNode.attributes[1], currNode.attributes[2], currNode.attributes[4]+"\u001b[37m");
                }
                currNode=currNode.next;
            }
            inventoryInterface+="+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(hpWidth) + "+"+"\n";
        }
        drawNextFrame(inventoryInterface);
    }
    
    // TODO
    public Node getFromInventory(int index) {
        Node currNode = this.head;
        for (int i = 0; i < index; i++) {
            
            currNode = currNode.next;
        }
        return currNode;
    }
}





