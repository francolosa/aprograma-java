package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static String barName = "barcito";
    public static String ownerName = "franco";
    public static String address = "Av. Libertador 3941, CABA";
    public static boolean exit = false;

    public static void getInfo() {
        System.out.println("Nombre del bar: " + barName);
        System.out.println("Nombre del dueño: " + ownerName);
        System.out.println("Ubicación: " + address);
    };

    public static boolean getExit(){
        return exit;
    };

    public static void setExit(boolean set){
        exit = set;
    };

    public static void main(String[] args) {
        setExit(false);

        while (!getExit()){
            Scanner keyboard = new Scanner(System.in);
            System.out.print("\n************************\n[ADMIN MODE]\nbienvenido a barcito\n -que vas a hacer?");
            System.out.print("\n-1: agregar productos\n-2: listar productos\n-3: buscar/editar producto\n-4: eliminar producto\n-5: crear orden\n-6: informacion\n-7: salir");
            String option = keyboard.nextLine();

            switch (option) {
                case "1":
                    ProductList.addProduct();
                    break;
                case "2":
                    ProductList.listProducts();
                    break;
                case "3":
                    ProductList.searchproduct();
                    break;
                case "4":
                    ProductList.deleteProduct();
                    break;
                case "5":
                    OrderList.createOrder();
                    break;
                case "7":
                        OrderList.getOrderList();
                        break;
                case "8":
                    getInfo();
                    break;
                case "9":
                    exit = true;

                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
