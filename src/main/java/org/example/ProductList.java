package org.example;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ProductList {
    private static ArrayList<Product> productsList = new ArrayList<>();
    private static Scanner keyboard = new Scanner(System.in);

    public static int getSize () {
        return productsList.size();
    }

    public static void addProduct(){
        System.out.print("***AGREGAR PRODUCTO***\n");
        int id = productsList.size()+1;

        String inputName = InputHelper.readString("Ingrese nombre:");

        double inputPrice = InputHelper.readDouble("Ingrese precio:");

        int inputStock = InputHelper.readInt("Ingrese stock:");

        productsList.add(new Product(id, inputName, inputPrice, inputStock));

    }

    public static ArrayList<Product> getProducts(){
        return productsList;
    }

    public static void listProducts(){
        System.out.print("***LISTAR PRODUCTOS***\n");
        if(productsList.isEmpty()){
            System.out.println("No hay productos para mostrar.");
        } else {
            System.out.println("Lista de productos:");
            for (Product product : productsList) {
                System.out.println(product);
            }
        }
    }

    public static void searchProduct(){
        System.out.print("***BUSCAR/EDITAR PRODUCTO***\n");

        String inputSearch = InputHelper.readString("Ingrese su busqueda:");
        Optional<Product> product = productsList.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(inputSearch) || isInteger(inputSearch) && p.getId() == Integer.parseInt(inputSearch))
                    .findAny();
            if(product.isEmpty()) {
                System.out.print("No se encontraron resultados");
            } else {
                System.out.print("Se encontro el siguiente item:\n");
                System.out.print(product);
                String inputUpdate = InputHelper.readString("Desea actualizarlo?\n-1: si\n-2: no, volver");

                switch (inputUpdate){
                    case "1":
                        editProduct(product.get().getId());
                        break;
                    case "2":
                    default:
                        System.out.println("Opción no válida");
                }
            }
    }

    private static void editProduct(int id){

        System.out.print("***EDITAR PRODUCTO***\n");
        Optional<Product> product = productsList.stream()
                .filter(p -> p.getId() == id)
                .findAny();

        String inputUpdate = InputHelper.readString("Que deseas actualizar?\n-1: precio\n-2: stock");

        switch (inputUpdate){
            case "1":
                double number = InputHelper.readDouble("Precio anterior: "+product.get().getPrice()+"\n inserte nuevo precio");
                product.get().setPrice(number);
                System.out.print("Confirmado, item actualizado:\n"+product);
                break;
            case "2":
                int newStock = InputHelper.readInt("Stock anterior: "+product.get().getStock()+"\n ingrese nuevo stock");
                product.get().setStock(newStock);
                System.out.print("Confirmado, item actualizado:\n"+product);
                break;
            default:
                System.out.println("Opción no válida");
        }    }

    private static boolean isInteger(String str) {
        return str.matches("-?\\d+");
    }

    public static void deleteProduct(){

        System.out.print("***ELIMINAR PRODUCTO***\n");
        listProducts();
        String inputDelete = InputHelper.readString("Ingrese el ID del item que deseas eliminar");
        productsList.removeIf(product -> product.getId() == Integer.parseInt(inputDelete));
        listProducts();
    }

}
