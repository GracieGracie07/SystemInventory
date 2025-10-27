package main;

import config.config;
import java.util.Scanner;
import static main.viewUser.viewUsers;

public class AdminDashboard {

    public static void viewProduct() {
        config conf = new config();
        String productQuery = "SELECT * FROM tbl_product";
        String[] productHeaders = {"ID", "Name", "Description", "Quantity"};
        String[] productColumns = {"product_id", "product_name", "product_description", "product_quantity"};

        conf.viewRecords(productQuery, productHeaders, productColumns);
    }

    public static void AdminDashboard() {
        Scanner sc = new Scanner(System.in);
        config con = new config();

        System.out.println("\n=== COMMUNERD INVENTORY ADMIN DASHBOARD ===");
        System.out.println("1. Approve Account!");
        System.out.println("2. Add Product");
        System.out.println("3. View Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Exit");
        System.out.println("Choose (1-6): ");
        int choose = sc.nextInt();

        switch (choose) {

            case 1:

                viewUsers();
                System.out.print("Enter ID to Approve: ");
                int ids = sc.nextInt();

                String sql = "UPDATE tbl_user SET u_status = ? WHERE u_id = ?";
                con.updateRecord(sql, "Approved", ids);
                break;

            case 2:

                System.out.println("Enter Product Name: ");
                String pn = sc.next();
                System.out.println("Enter Product Description: ");
                String pd = sc.next();
                System.out.println("Enter Product Quantity: ");
                int pq = sc.nextInt();

                sql = "INSERT INTO tbl_product (product_name, product_description, product_quantity) VALUES (?, ?, ?)";
                con.addRecord(sql, pn, pd, pq);

                break;

            case 3:

                viewProduct();
                break;

            case 4:

                viewProduct();
                System.out.println("Enter ID to update: ");
                int id = sc.nextInt();
                System.out.println("Enter new product quantity: ");
                int quan = sc.nextInt();
                config dbConfig = new config();
                String sqlUpdate = "UPDATE tbl_product SET product_quantity = ? WHERE product_id = ?";
                dbConfig.updateRecord(sqlUpdate, quan, id);
                break;

            case 5:

                viewProduct();
                System.out.println("Enter ID of product to delete: ");
                int delete_id = sc.nextInt();
                config confi = new config();
                String sqlDelete = "DELETE FROM tbl_product WHERE product_id = ?";
                confi.deleteRecord(sqlDelete, delete_id);

                break;
        }

    }

}
