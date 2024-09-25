import java.util.Arrays;
public class Item {
    Item next;
    String[] attributes;

    public Item(String[] attributes){
        this.attributes = attributes;
    }

    public void displayAttributes(String[] attributeNames){
        int attributeNameWidth = 20;
        int attributeWidth = 70;
        String inventoryInterface = "";
        inventoryInterface+="+" + "-".repeat(attributeNameWidth) + "+" + "-".repeat(attributeWidth)+ "+" +"\n";
        inventoryInterface+=String.format("| %-18s | %-68s |%n", "", "Item Statistics");
        inventoryInterface+="+" + "-".repeat(attributeNameWidth) + "+" + "-".repeat(attributeWidth)+ "+" +"\n";
        for (int i = 0; i < attributeNames.length; i++) {
            inventoryInterface+=String.format("| %-18s | %-68s |%n", attributeNames[i], attributes[i+1]);
        }
        inventoryInterface+="+" + "-".repeat(attributeNameWidth) + "+" + "-".repeat(attributeWidth)+ "+" +"\n";
        System.out.println(inventoryInterface);
    }
}