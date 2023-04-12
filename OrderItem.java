public class OrderItem{
    Product product;
    int quantity;
    String offer;
    int totalPrice;

    OrderItem(Product product, int quantity, String offer, int totalPrice){
        this.product = product;
        this.quantity = quantity;
        this.offer = offer;
        this.totalPrice = totalPrice;
    }
}