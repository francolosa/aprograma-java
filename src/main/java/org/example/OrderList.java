package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderList {
    private static ArrayList<OrderList> orderList = new ArrayList<>();
    private int id;
    private ArrayList<OrderProduct> items = new ArrayList<>();

    public OrderList(int id) {
        this.id = id;
    }

    public static void createOrder() {
        if(ProductList.getSize() < 1){
            System.out.println("lo sentimos, actualmente no tenemos stock");
            return;
        }
        System.out.println("*** CREAR ORDEN ***");

        int orderId = orderList.size() + 1;
        OrderList newOrder = new OrderList(orderId);

        boolean seguir = true;

        while (seguir) {
            ProductList.listProducts();

            int productId = InputHelper.readInt("Ingrese ID del producto: ");

            Product selectedProduct = null;
            for (Product p : ProductList.getProducts()) {
                if (p.getId() == productId) {
                    selectedProduct = p;
                    break;
                }
            }

            if (selectedProduct == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            int quantity = InputHelper.readInt("Ingrese cantidad: ");

            if (quantity > selectedProduct.getStock()) {
                System.out.println("No hay suficiente stock.");
                continue;
            }

            var newStock = selectedProduct.getStock() - quantity;
            selectedProduct.setStock(newStock);
            newOrder.addItem(selectedProduct, quantity);

            String respuesta = InputHelper.readString("¿Desea agregar otro producto? (s/n): ");
            if (!respuesta.equalsIgnoreCase("s")) {
                seguir = false;
            }
        }

        orderList.add(newOrder);
        System.out.println("Orden #" + newOrder.getId() + " creada exitosamente.");
    }

    public static void listOrders() {
        for (OrderList order : orderList) {
            System.out.print("Orden numero: " + order.getId());
            System.out.print("\nPrecio total: " + order.getOrderPrice());
            System.out.print("\nProductos:");

            for (OrderProduct item : order.getItems()) {
                System.out.print("Nombre: " + item.getProduct().getName() + " | Precio: " + item.getProduct().getPrice() + " | Cantidad: " + item.getQuantity());
            }
        }
    }

    public double getOrderPrice() {
        double totalPrice = 0;
        for(OrderProduct item : items) {
            totalPrice = totalPrice + (item.getProduct().getPrice() * item.getQuantity());
        }
        return totalPrice;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderProduct(product, quantity));
    }

    public ArrayList<OrderProduct> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

}

