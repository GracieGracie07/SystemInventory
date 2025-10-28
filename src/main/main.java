package main;

import config.config;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static main.AdminDashboard.AdminDashboard;
import static main.StaffDashboard.StaffDashboard;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        config db = new config();
        boolean running = true;

        while (running) {
            System.out.println("\n==============================================");
            System.out.println("     WELCOME TO COMMUNERD INVENTORY SYSTEM");
            System.out.println("==============================================");
            System.out.println(" [1] Login");
            System.out.println(" [2] Register");
            System.out.println(" [3] Exit");
            System.out.println("==============================================");
            System.out.print(" Choose (1 - 3): ");

            int choose = sc.nextInt();
            sc.nextLine();

            switch (choose) {
                case 1 :
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();

                    String hash = db.hashPassword(pass);
                    String qry = "SELECT * FROM tbl_user WHERE u_email = ? AND u_pass = ?";
                    List<Map<String, Object>> result = db.fetchRecords(qry, email, hash);

                    if (result.isEmpty()) {
                        System.out.println("\nINVALID CREDENTIALS");
                    } else {
                        Map<String, Object> user = result.get(0);
                        String status = user.get("u_status").toString();
                        String type = user.get("u_type").toString();

                        if (status.equalsIgnoreCase("Pending")) {
                            System.out.println("\nAccount Pending — Contact the Admin!");
                        } else {
                            if (type.equalsIgnoreCase("Admin")) {
                                AdminDashboard();
                            } else if (type.equalsIgnoreCase("Staff")) {
                                StaffDashboard();
                            }
                        }
                    }
                break ;

                case 2 :
                    
                    System.out.println("\n=== REGISTER NEW USER ===");
                    System.out.print("Enter user name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter user email: ");
                    String email1 = sc.nextLine();

                    
                    while (true) {
                        
                        String res = "SELECT * FROM tbl_user WHERE u_email = ?";
                        
                        List<Map<String, Object>> register = db.fetchRecords(res, email1);
                        
                            if (register.isEmpty()){
                                break;
                            }
                            System.out.print("Email already exists. Enter a different email: ");
                            email = sc.nextLine();
                            
                    }

                    System.out.print("Enter user type (1 - Admin / 2 - Staff): ");
                    int type = sc.nextInt();
                    sc.nextLine();

                    while (type < 1 || type > 2) {
                        System.out.print("Invalid, choose 1 or 2 only: ");
                        type = sc.nextInt();
                        sc.nextLine();
                    }

                    String userType = (type == 1) ? "Admin" : "Staff";

                    System.out.print("Enter password: ");
                    String pass1 = sc.nextLine();
                    String hashPass = config.hashPassword(pass1);

                    String sql = "INSERT INTO tbl_user (u_name, u_email, u_type, u_status, u_pass) VALUES (?, ?, ?, ?, ?)";
                    db.addRecord(sql, name, email1, userType, "Pending", hashPass);

                    System.out.println("\nRegistration successful! Waiting for admin approval.");
                break;

                case 3 :
                    System.out.println("\nThank you for using the system!");
                    running = false;
                break;

                default :
                    
                    System.out.println("\nInvalid option! Please choose 1-3.");
            }
        }
    }
}
