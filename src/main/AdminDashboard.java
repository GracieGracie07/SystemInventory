package main;

import config.config;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class AdminDashboard {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    

    public static void AdminDashboard() {
        Scanner sc = new Scanner(System.in);
        config con = new config();
        boolean a = true;
        
    while (a){
        
        System.out.println("\n==============================================");
        System.out.println("         COMMUNERD INVENTORY SYSTEM");
        System.out.println("==============================================");
        System.out.println("                ADMIN DASHBOARD");
        System.out.println("==============================================");
        System.out.println(" [1] Manage Accounts");
        System.out.println(" [2] Manage Products");
        System.out.println(" [3] Log Out");
        System.out.println("==============================================");
        System.out.print(" Choose an option (1-3): ");
        int choose = sc.nextInt();

        switch (choose) {

            case 1 :
                ManageAccount();
                break;
                
            case 2 :
                ManageProduct();
                break;
                
            case 3 : 
                a = false;
                break;
            default:
                
                System.out.println("Invalid! Try again.");
        }
    }
    }
    
    private static void ManageAccount(){
        config db = new config();
        Scanner sc = new Scanner(System.in);
        boolean a = true;
        
    while (a){
        System.out.println("\n----------------------------------------------");
        System.out.println("             ACCOUNT MANAGEMENT");
        System.out.println("----------------------------------------------");
        System.out.println(" [1] Approve Account");
        System.out.println(" [2] Suspend Account");
        System.out.println(" [3] Back");
        System.out.println("----------------------------------------------");
        System.out.print(" Choose an option (1-3): ");
        int choice = sc.nextInt();
        
            switch ( choice ){
                
                case 1 :
                    
                    String name[] = {"User ID", "Name", "Email", "Role", "Status"}; 
                    String sql = "SELECT * FROM tbl_user";
                    
                    System.out.println("\n");
                    System.out.printf("%-10s | %-10s | %-20s | %-10s | %-10s |\n", name[0], name[1], name[2], name[3], name[4]);
                    System.out.println("-------------------------------------------------------------------------");

                    List<Map<String, Object>> list = db.fetchRecords(sql);

                    for ( Map<String, Object> row : list ){

                        System.out.printf("%-10s | %-10s | %-20s | %-10s | %-10s |\n", row.get("u_id"), row.get("u_name"), row.get("u_email"), row.get("u_type"), row.get("u_status"));

                    }
                    
                    System.out.println("-------------------------------------------------------------------------");
                    
                    System.out.println("\nEnter the ID to approved (0 to exit): ");
                    int id = sc.nextInt();
                    
                        if (id == 0){
                            
                            return;
                        }
                        
                        String find = "UPDATE tbl_user SET u_status = ? WHERE u_id = ?";
                        
                        db.updateRecord(find, "Approved", id);
                        
                        System.out.println("Account approved successfully!");
                        
                        
                    
                    
                    break;
                    
                case 2 :
                    
                    String name1[] = {"User ID", "Name", "Email", "Role", "Status"}; 
                    String sql1 = "SELECT * FROM tbl_user";
                    
                    System.out.println("\n");
                    System.out.printf("%-10s | %-10s | %-20s | %-10s | %-10s |\n", name1[0], name1[1], name1[2], name1[3], name1[4]);
                    System.out.println("-------------------------------------------------------------------------");

                    List<Map<String, Object>> sus = db.fetchRecords(sql1);

                    for ( Map<String, Object> row : sus ){

                        System.out.printf("%-10s | %-10s | %-20s | %-10s | %-10s |\n", row.get("u_id"), row.get("u_name"), row.get("u_email"), row.get("u_type"), row.get("u_status"));

                    }
                    
                    System.out.println("-------------------------------------------------------------------------");
                    
                    System.out.println("\nEnter the ID to approved (0 to exit): ");
                    int id1 = sc.nextInt();
                    
                        if (id1 == 0){
                            
                            return;
                        }
                        
                        String fetch = "UPDATE tbl_user SET u_status = ? WHERE u_id = ?";
                        
                        db.updateRecord(fetch, "Denied", id1);
                        
                    break;
                    
                case 3 :
                    
                    a = false;
                    break;
                    
                default:
                    
                    System.out.println("Invalid Choice!");
            }
    }   
    }
    
    private static void ManageProduct(){
        Scanner sc = new Scanner(System.in);
        boolean a = true;
    
    while(a){
        System.out.println("\n----------------------------------------------");
        System.out.println("              PRODUCT MANAGEMENT");
        System.out.println("----------------------------------------------");
        System.out.println(" [1] Add Product");
        System.out.println(" [2] View Products");
        System.out.println(" [3] Update Product");
        System.out.println(" [4] Delete Product");
        System.out.println(" [5] Back");
        System.out.println("----------------------------------------------");
        System.out.print(" Choose an option (1-5): ");

        int choice = sc.nextInt();
        
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
                    deleteProduct();
                    break;
                    
                case 5 :
                    a = false;
                    break;
                    
                default:
                    
                    System.out.println("Invalid Choice!");
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
        
        viewProducts();
        
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

        System.out.println("\nProduct updated successfully!");
    }

    
    public static void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        viewProducts();
        
        System.out.println("\n=== DELETE PRODUCT ===");
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM products WHERE id = ?";
        conf.deleteRecord(sql, id);

        System.out.println("\nProduct deleted successfully!");
    }

}
