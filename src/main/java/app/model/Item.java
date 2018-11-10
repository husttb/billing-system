package app.model;

public class Item {

    private String name;
    private Double price;
    private Integer quantity;
    private Double subtotal;


    public Item(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotal() {
        subtotal = price * quantity;
        return subtotal;
    }

}
