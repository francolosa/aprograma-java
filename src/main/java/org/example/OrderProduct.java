package org.example;

public class OrderProduct {
    private Product product;
    private int quant;

    public OrderProduct(Product product, int quantity){
        this.product = product;
        this.quant = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity(){
        return this.quant;
    }

    public void setQuantity(int quantity){
        this.quant = quantity;
    }

}
