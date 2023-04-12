public class LineItem{
    Product product;
    int quantity;

    LineItem(){
        // Default constructor
    }

    LineItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

}