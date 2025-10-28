/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import config.config;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class StaffDashboard {
    
    public static void StaffDashboard(){
        
        Scanner sc = new Scanner(System.in);
        boolean a = true;
        
        while (a) {
            System.out.println("\n==============================================");
            System.out.println("         COMMUNERD INVENTORY SYSTEM");
            System.out.println("==============================================");
            System.out.println("                STAFF DASHBOARD");
            System.out.println("==============================================");
            System.out.println(" [1] Add Product");
            System.out.println(" [2] View Products");
            System.out.println(" [3] Update Product");
            System.out.println(" [4] Exit");
            System.out.println("==============================================");
            System.out.print(" Choose an option (1-4): ");

            int choice = sc.nextInt();
            sc.nextLine();
            
                switch (choice){
                    
                    case 1 : 
                        addProduct();
                        break;
                        
                    case 2 : 
                        viewProducts();
                        break;
                        
                    case 3 :
                        updateProduct();
                        break;
                        
                    case 4 :
                        a = false;
                        break ;
                        
                    default :
                        
                        System.out.println("\nInvalid choice! Please try again.");
                }
        }
    }
    
    public static void addProduct() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.println("\n=== ADD PRODUCT ===");
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        String sql = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, name, category, qty, price);

        System.out.println("\nProduct added successfully!");
    }

   
    public static void viewProducts() {
        config conf = new config();
        System.out.println("\n=== PRODUCT LIST ===");
        String name[] = {"Product ID", "Name", "Category", "Quantity", "Price"};
        
        String view = "SELECT * FROM products";
        
        System.out.printf("%-10s | %-20s | %-15s | %-10s | %-10s |\n", name[0], name[1], name[2], name[3], name[4]);
        System.out.println("---------------------------------------------------------------------------");
        List<Map<String, Object>> find = conf.fetchRecords(view);
        
        for ( Map<String, Object> row : find ){
            System.out.printf("%-10s | %-20s | %-15s | %-10s | %-10s |\n", row.get("id"), row.get("name"), row.get("category"), row.get("quantity"), row.get("price"));
            
        
    }
        System.out.println("---------------------------------------------------------------------------");
    }

  
    public static void updateProduct() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.println("\n=== UPDATE PRODUCT ===");
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter new Name: ");
        String name = sc.nextLine();
        System.out.print("Enter new Category: ");
        String category = sc.nextLine();
        System.out.print("Enter new Quantity: ");
        int qty = sc.nextInt();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        String sql = "UPDATE products SET name = ?, category = ?, quantity = ?, price = ? WHERE id = ?";
        conf.updateRecord(sql, name, category, qty, price, id);

        System.out.println("\n🛠 Product updated successfully!");
    }
        
    
}
