import java.util.ArrayList;

public class Order{
    ArrayList<OrderItem> orderItems;
    float totalPrice;

    Order(ArrayList<OrderItem> orderItems){
        this.orderItems = orderItems;
        this.setTotalPrice();
    }

    public float getDiscount(int discount, int price){
        return (price / 100) * discount;
    }

    public int discountParser(String disocount){
        return AJSuperMarket.stringToInteger(disocount.substring(0, disocount.indexOf("%")));
    }

    void setTotalPrice(){
        for(OrderItem orderItem : this.orderItems){
            if(orderItem.offer.equals("N/A")){
                this.totalPrice += orderItem.quantity * orderItem.totalPrice;
            }else{
                this.totalPrice += getDiscount(discountParser(orderItem.offer), orderItem.quantity * orderItem.totalPrice);
            }
        }
    }

    float calculatetotalprice(OrderItem orderItem){
        return 0;
    }

    void generateBill(){
        System.out.println("== Bill ==");
        for(OrderItem itemSold : this.orderItems){
            Product product = itemSold.product;
            System.out.println(product.productId+" - "+product.productName+" - "+itemSold.quantity+" - "+product.price+" - "+itemSold.offer+" - "+itemSold.totalPrice);
        }
        System.out.println("== Total ==");
        System.out.println();
    }
}   