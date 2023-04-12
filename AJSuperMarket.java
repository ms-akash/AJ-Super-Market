import java.util.ArrayList;
import java.util.Scanner;

public class AJSuperMarket {
    static ArrayList<Offer> offers = new ArrayList<>();
    static ArrayList<Inventory> inventories = new ArrayList<>();

    public static String inputParser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Input (EXIT, INVENTORY, SALE, STOCK)");
        String inputString = scanner.nextLine();
        scanner.close();
        return inputString;
    }

    public static void doTask(String inputString){
        if(inputString.equals("EXIT")){
            System.exit(0);
        }
        else if(inputString.equals("INVENTORY")){
            addToInventory();
        }
        else if(inputString.equals("SALE")){
            makeSale();
        }
        else if(inputString.equals("STOCK")){
            Inventory inventory = new Inventory();
            String stockCommand = inputParser();
            inventory.printStock(stringConvertor(stockCommand)[0]);
        }
        else{
            System.out.println("Invalid Command");
        }
    }

    public static LineItem getLineItem(String productId, String productName, int pricePerProduct, int quantity){
        Product product = new   Product(productId, productName, pricePerProduct);
        return new LineItem(product, quantity);
    }

    public static void addToInventory(){
        String product = inputParser();
        String[] products = stringConvertor(product); // products => {"productId|productName|quantity|pricePerQuantity", .......}
        Inventory inventory = new Inventory();

        for(int i = 0; i < products.length; i++){
            String[] productInfo = stringParser(products[i]); // productInfo => {0-productId, 1-productName, 2-quantity, 3-pricePerQuantity}
            inventory.addProductToinventory(productInfo[0], getLineItem(productInfo[0], productInfo[1], stringToInteger(productInfo[2]), stringToInteger(productInfo[3])));
        }
        inventories.add(inventory);
    }

    public static int stringToInteger(String input){
        return Integer.parseInt(input);
    }

    public static String[] stringParser(String input){
        return input.split("//|");
    }

    public static String[] stringConvertor(String input){
        String[] output;
        output = input.substring(input.indexOf(">") + 1).split(";");
        return output;
    }

    public static void makeSale(){
        Inventory inventoryObject = new Inventory();
        ArrayList<OrderItem> itemsSold = new ArrayList<OrderItem>();

        String saleCommand = inputParser();
        String sales[] = stringConvertor(saleCommand); // sales => {1|2, 2|3, .....}
        
        for(int i = 0; i < sales.length; i++){
            String currentSale[] = stringParser(sales[i]); //currentSale => {productId (1), quantity (2)}

            String productId = currentSale[0];
            int quantity = stringToInteger(currentSale[1]);
            
            Inventory inventory = getInventory(productId);

            if(hadStocks(inventory.inventoryItems.get(productId), quantity)){
                itemsSold.add(generateOrderItem(productId, quantity));
                inventoryObject.updateInventory(inventory, productId, quantity);
            }
            else{
                System.out.println("Dont have enough stock in inventory");
                inventoryObject.printStock(productId);
            }
        }
        Order order = new Order(itemsSold);
        order.generateBill();
    }

    public static boolean hadStocks(LineItem lineItem, int quantity){
        return lineItem.quantity >= quantity;
    }

    public static Inventory getInventory(String productId){
        for(Inventory inventory : inventories){
            for(String currentProductId : inventory.inventoryItems.keySet()){
                if(currentProductId.equals(productId)){
                    return inventory;
                }
            }
        }
        return null;
    }

    public static OrderItem generateOrderItem(String productId, int quantity){
            String offer = getOffer(productId);
            Inventory inventory = getInventory(productId);
            LineItem lineItem = inventory.inventoryItems.get(productId);
            
            return new OrderItem(lineItem.product, quantity, offer, quantity);
    }   

    public static String getOffer(String productId){
        for(Offer offer : offers){
            if(offer.productId.equals(productId)){
                return offer.discount;
            }
        }
        return "N/A";
    }

    public static void main(String[] args) {
        while(true){
            String inputString = inputParser();
            doTask(inputString);
        }
    }
}
