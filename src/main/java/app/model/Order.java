package app.model;

import app.model.Item;

import java.util.List;

public class Order {

    private List<Item> items;
    private Double total = 0.0;
    private Double discount = 0.0;


    public Order(List<Item> items) {
        this.items = items;
    }

    public Double calculateTotal(){
        for(Item item : items){
            total += item.getPrice() * item.getQuantity();
        }

        return total;
    }

    public Double applyDiscount() {
        total = total * (1 - discount);
        return total;
    }

    public List<Item> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}
