package org.example;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class ProductList {
    private static ArrayList<Product> productsList = new ArrayList<>();

    public static int getSize () {
        return productsList.size();
    }
    public static void addProduct(){
        System.out.print("***AGREGAR PRODUCTO***\n");
        int id = productsList.size()+1;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Ingrese nombre:");
        String inputName = keyboard.nextLine();
        System.out.print("Ingrese precio:");
        double inputPrice = Double.parseDouble(keyboard.nextLine());
        System.out.print("Ingrese stock:");
        int inputStock = Integer.parseInt(keyboard.nextLine());

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
                System.out.println(product.getProduct());
            }
        }
    }

    public static void searchproduct(){
        System.out.print("***BUSCAR/EDITAR PRODUCTO***\n");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Ingrese su busqueda:");
        String inputSearch = keyboard.nextLine();
        Optional<Product> product = productsList.stream()
                    .filter(p -> p.getName().equalsIgnoreCase(inputSearch) || isInteger(inputSearch) && p.getId() == Integer.parseInt(inputSearch))
                    .findAny();
            if(product.isEmpty()) {
                System.out.print("No se encontraron resultados");
            } else {
                System.out.print("Se encontro el siguiente item:\n");
                System.out.print(product.get().getProduct());
                System.out.print("Desea actualizarlo?\n-1: si\n-2: no, volver");
                keyboard = new Scanner(System.in);
                String inputUpdate = keyboard.nextLine();

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

        System.out.print("Que deseas actualizar?\n-1: precio\n-2: stock");
        Scanner keyboard = new Scanner(System.in);
        String inputUpdate = keyboard.nextLine();

        switch (inputUpdate){
            case "1":
                System.out.print("Precio anterior: "+product.get().getPrice()+"\n inserte nuevo precio");
                keyboard = new Scanner(System.in);
                String newPrice = keyboard.nextLine();
                double number = Double.parseDouble(newPrice);
                product.get().setPrice(number);
                System.out.print("Confirmado, item actualizado:\n"+product.get().getProduct());
                break;
            case "2":
                System.out.print("Stock anterior: "+product.get().getStock()+"\n inserte nuevo stock");
                keyboard = new Scanner(System.in);
                String newStock = keyboard.nextLine();
                int stock = Integer.parseInt(newStock);
                product.get().setStock(stock);
                System.out.print("Confirmado, item actualizado:\n"+product.get().getProduct());
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
        System.out.print("Ingrese el ID del item que deseas eliminar");
        Scanner inputIdToDelete = new Scanner(System.in);
        String inputDelete = inputIdToDelete.nextLine();
        productsList.removeIf(product -> product.getId() == Integer.parseInt(inputDelete));
        listProducts();
    }

}
