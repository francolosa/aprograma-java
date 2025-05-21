package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderList extends ProductList {
    private static ArrayList<OrderList> orderList = new ArrayList<>();
    private int id;
    private ArrayList<OrderProduct> items = new ArrayList<>();

    public OrderList(int id) {
        this.id = id;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderProduct(product, quantity));
    }

    public int getId() {
        return id;
    }

    public ArrayList<OrderProduct> getItems() {
        return items;
    }

    public static void getOrderList() {

    }

    public static void createOrder() {
        if(ProductList.getSize() < 1){
            System.out.println("lo sentimos, actualmente no tenemos stock");
            return;
        }
        System.out.println("*** CREAR ORDEN ***");
        Scanner keyboard = new Scanner(System.in);

        int orderId = orderList.size() + 1;
        OrderList newOrder = new OrderList(orderId);

        boolean seguir = true;

        while (seguir) {
            listProducts();

            System.out.print("Ingrese ID del producto: ");
            int productId = Integer.parseInt(keyboard.nextLine());

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

            System.out.print("Ingrese cantidad: ");
            int quantity = Integer.parseInt(keyboard.nextLine());

            if (quantity > selectedProduct.getStock()) {
                System.out.println("No hay suficiente stock.");
                continue;
            }

            var newStock = selectedProduct.getStock() - quantity;
            selectedProduct.setStock(newStock);
            newOrder.addItem(selectedProduct, quantity);

            System.out.print("¿Desea agregar otro producto? (s/n): ");
            String respuesta = keyboard.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                seguir = false;
            }
        }

        orderList.add(newOrder);
        System.out.println("Orden #" + newOrder.getId() + " creada exitosamente.");
    }

}

