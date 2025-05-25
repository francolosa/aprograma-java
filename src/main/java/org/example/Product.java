package org.example;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + name +
                " | Precio: $" + price +
                " | Stock: " + stock;
    }


    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double number){
        if(number >= 0 ) this.price = number;
    }

    public int getStock(){
        return this.stock;
    }

    public void setStock(int number){
        if(number >= 0) this.stock = number;
    }
}
