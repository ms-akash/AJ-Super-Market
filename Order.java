import java.util.ArrayList;

public class Order{
    ArrayList<OrderItem> orderItems;
    float totalPrice;

    Order(ArrayList<OrderItem> orderItems){
        this.setOrderItems(orderItems);
        this.setTotalPrice();
    }

    void setOrderItems(ArrayList<OrderItem> orderItems){
        this.orderItems = orderItems;
    }
    void setTotalPrice(){
        this.totalPrice = calculatetotalprice(this.orderItems);
    }

    float calculatetotalprice(ArrayList<OrderItem> orderItems){
        return 0;
    }
}   