import java.util.Map;

public class InventoryLL{
    // initialized the attributes and the colors used for drawing the inventory
    static Map<String, String> rarityColors = Map.of("Common", "\u001b[37m","Uncommon", "\u001b[32m","Rare", "\u001b[34m","Very Rare", "\u001b[35m","Legendary", "\u001b[31m");
    int length = 0;
    Item head;
    Item tail;
 
    public InventoryLL(){}

    public void addToInventory(String[] item){
        Item itemItem = new Item(item);
        if(this.head == null){
            this.head = itemItem;
            this.tail = itemItem;
        } else{
            this.tail.next = itemItem;
            this.tail = itemItem;
        }
        this.length++;
    } 

    public boolean inInventory(String itemName){
        if(this.head == null){
            return false;
        }
        Item currItem = this.head;
        for (int i = 0; i < this.length; i++) {
            if (currItem.attributes[1].equals(itemName)) {
                return true;
            }
            currItem = currItem.next;
        }
        return false;
    }

    public String[] removeItem(String itemName){

        if(this.head == null){
            return null;
        }
        this.length--;
        // check if the first item is the one being removed
        Item currItem = this.head;
        if (currItem.attributes[1].equals(itemName)) {
            // checks if the list only has one item
            // sets the attributes of the item we are removing because it wont be accessible if it is the tail of the list
            String[] attributes = currItem.attributes;
            if(this.tail == this.head){
                this.head = null;
                this.tail = null;
            } else {
                this.head = currItem.next;
            }
            return attributes;
        }

        // checks every Item after the first one if it is being removed
        while(currItem.next!=null){
            if (currItem.next.attributes[1].equals(itemName)) {
                // sets the attributes of the item we are removing because it wont be accessible if it is the tail of the list
                String[] attributes = currItem.next.attributes;
                if(currItem.next == this.tail){
                    this.tail = currItem;
                    this.tail.next = null;
                } else {
                    currItem.next = currItem.next.next;
                }
                return attributes;
            }
            currItem = currItem.next;
        }

        System.out.println("There was an error");
        return null;
    }

    // same method as in main
    public static void drawNextFrame(String frame){
        System.out.print("\033[H\033[2J" + frame);
        System.out.flush(); 
    }

    // same method as in main
    public static String getScrollBar(int totalMenuItems, int menuSize, int menuTopIndex, int menuBottomIndex, int currentIndex){
        double itemsPerBar = totalMenuItems/((menuSize)*1.0);
        double scrollBarTopIndex = Math.round(menuTopIndex/itemsPerBar);
        double scrollBarBottomIndex = Math.round(menuBottomIndex/itemsPerBar);
        double scrollBarIndex = currentIndex-menuTopIndex;
        if(scrollBarIndex+.5>=scrollBarTopIndex && scrollBarIndex+.5<=scrollBarBottomIndex){
            return "\u001b[37;47m \u001b[37;40m";
        }
        return "|";
    }
 
    // same menu drawing code but the loops are a bit different because it is looping through a linked list
    public void displayItems(int cursor, int displayHeight) {
        int nameWidth = 32;
        int rarityWidth = 12;
        int hpWidth = 12;
        String inventoryInterface = "";
        inventoryInterface+="  +" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(hpWidth) + "+"+"\n";
        inventoryInterface+=String.format("  | %-30s | %-10s | %-10s |%n", "Name", "Rarity", "HP");
        inventoryInterface+="--+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(hpWidth) + "+"+"\n";

        if(displayHeight>this.length){
            displayHeight=this.length;
        }
        int topIndex = cursor-displayHeight/2;
        int bottomIndex = (int)Math.ceil(cursor+(displayHeight/2.));
        if(topIndex<0){
            bottomIndex-=topIndex; 
            topIndex=0;
        }
        if(bottomIndex>this.length){
            topIndex+=this.length-bottomIndex;
            bottomIndex = this.length;
        }

        if(this.head==null){
            inventoryInterface+=String.format("  | %-56s |%n", "Nothing to see here");
            inventoryInterface+="--+" + "-".repeat(nameWidth+rarityWidth+hpWidth+2) + "+"+"\n";
        }else{
            Item currItem = this.head;
            for(int i = 0;i<topIndex;i++){
                currItem=currItem.next;
            }
            for (int i = topIndex; i < bottomIndex; i++) {
                if(cursor==i){
                    String scrollBar = getScrollBar(this.length, displayHeight+2, topIndex, bottomIndex, i);
                    inventoryInterface+=scrollBar+" +" + "=".repeat(nameWidth) + "+" + "=".repeat(rarityWidth) + "+" + "=".repeat(hpWidth) + "+"+"\n";
                    scrollBar = getScrollBar(this.length, displayHeight+2, topIndex, bottomIndex, i+1);
                    inventoryInterface+=String.format(scrollBar+" | %-35s | %-10s | %-15s |%n", "--> "+rarityColors.get(currItem.attributes[2])+currItem.attributes[1], currItem.attributes[2], currItem.attributes[4]+"\u001b[37m");
                    scrollBar = getScrollBar(this.length, displayHeight+2, topIndex, bottomIndex, i+2);
                    inventoryInterface+=scrollBar+" +" + "=".repeat(nameWidth) + "+" + "=".repeat(rarityWidth) + "+" + "=".repeat(hpWidth) + "+"+"\n";
                }else{
                    String scrollBar = "";
                    if(i>cursor){
                        scrollBar = getScrollBar(this.length, displayHeight+2, topIndex, bottomIndex, i+2);
                    } else {
                        scrollBar = getScrollBar(this.length, displayHeight+2, topIndex, bottomIndex, i);
                    }
                    inventoryInterface+=String.format(scrollBar+" | %-35s | %-10s | %-15s |%n", rarityColors.get(currItem.attributes[2])+currItem.attributes[1], currItem.attributes[2], currItem.attributes[4]+"\u001b[37m");
                }
                currItem=currItem.next;
            }
            inventoryInterface+="--+" + "-".repeat(nameWidth) + "+" + "-".repeat(rarityWidth) + "+" + "-".repeat(hpWidth) + "+"+"\n";
        }
        drawNextFrame(inventoryInterface);
    }
    
    public Item getFromInventory(int index) {
        Item currItem = this.head;
        for (int i = 0; i < index; i++) {
            
            currItem = currItem.next;
        }
        return currItem;
    }
}





