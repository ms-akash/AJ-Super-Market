import java.util.HashMap;

public class Inventory{
    HashMap<String, LineItem> inventoryItems = new HashMap<String, LineItem>();

    public void addProductToinventory(String productId, LineItem lineItem){
        this.inventoryItems.put(productId, lineItem);
        System.out.println("Inventory Updated");
    }

    public void printStock(String productId){
        LineItem lineItem = AJSuperMarket.getInventory(productId).inventoryItems.get(productId);
        Product product = lineItem.product;
        System.out.println(product.productName+" - "+ lineItem.quantity);
    }

    public void updateInventory(Inventory inventory, String productId, int quantity){
        LineItem lineItem = inventory.inventoryItems.get(productId);
        lineItem.quantity -= quantity;

        inventory.inventoryItems.put(productId, lineItem);
    }
}