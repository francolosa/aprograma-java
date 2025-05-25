package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String barName = "barcito";
    private static String ownerName = "franco";
    private static String address = "Av. Libertador 3941, CABA";
    private static boolean exit = false;

    public static void main(String[] args) {
        setExit(false);
        Scanner keyboard = new Scanner(System.in);

        while (!getExit()){
            showMenu(keyboard);
        }
        keyboard.close();
    }

    public static void showMenu(Scanner keyboard){
        System.out.print("\n************************\n[ADMIN MODE]\nbienvenido a barcito\n -que vas a hacer?");
        System.out.print("\n-1: agregar producto\n-2: listar productos\n-3: buscar/actualizar producto\n-4: eliminar producto\n-5: crear orden\n-6: listar ordenes\n-7: info\n-8: salir");
        String option = keyboard.nextLine();

        switch (option) {
            case "1":
                ProductList.addProduct();
                break;
            case "2":
                ProductList.listProducts();
                break;
            case "3":
                ProductList.searchProduct();
                break;
            case "4":
                ProductList.deleteProduct();
                break;
            case "5":
                OrderList.createOrder();
                break;
            case "6":
                OrderList.listOrders();
                break;
            case "7":
                getInfo();
                break;
            case "8":
                setExit(true);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    public static void getInfo() {
        System.out.println("Nombre del bar: " + barName);
        System.out.println("Nombre del dueño: " + ownerName);
        System.out.println("Ubicación: " + address);
    }

    public static boolean getExit(){
        return exit;
    }

    public static void setExit(boolean set){
        exit = set;
    }

}
