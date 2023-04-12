import java.util.HashMap;

public class Inventory{
    HashMap<String, Product> inventory = new HashMap<String, Product>();
    int quantity;

    Inventory(HashMap<String, Product> inventory, int quantity) {
        this.inventory = inventory;
        this.quantity = quantity;
    }
}