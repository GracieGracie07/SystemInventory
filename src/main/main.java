package main;

import config.config;
import java.util.Scanner;
import static main.AdminDashboard.AdminDashboard;
import static main.StaffDashboard.StaffDashboard;
import static main.viewUser.viewUsers;

public class main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        config db = new config();
        
        
        System.out.println("Welcome to Communered Inventory System");
       
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("Choose(1 - 3): ");
        int choose = sc.nextInt();

        switch (choose) {

            case 1:
                
                System.out.print("Enter email: ");
                String em = sc.next();
                System.out.print("Enter Password: ");
                String pas = sc.next(); 
                
                String hash = db.hashPassword(pas);
                
                while (true) {

                    String qry = "SELECT * FROM tbl_user WHERE u_email = ? AND u_pass = ?";
                    java.util.List<java.util.Map<String, Object>> result = db.fetchRecords(qry, em, hash);

                    if (result.isEmpty()) {
                        System.out.println("INVALID CREDENTIALS");
                        break;
                    } else {
                        java.util.Map<String, Object> user = result.get(0);
                        String stat = user.get("u_status").toString();
                        String type = user.get("u_type").toString();
                        if (stat.equals("Pending")) {
                            System.out.println("Account is Pending, Contact tha Admin!");
                            break;
                        } else {
                            System.out.println("LOGIN SUCCESS!");
                            if (type.equals("Admin")) {
                                System.out.println("WELCOME TO ADMIN DASHBOARD");
                                AdminDashboard();
                            } else if (type.equals("Staff")) {
                                System.out.println("WELCOME TO STAFF DASHBOARD");
                                StaffDashboard();
                            }

                            break;
                        }

                    }
                }

                break;

            case 2:
                
                System.out.print("Enter user name: ");
                String name = sc.nextLine();
                sc.nextLine();
                
                System.out.print("Enter user email: ");
                String email = sc.nextLine();
                

                while (true) {

                    String qry = "SELECT * FROM tbl_user WHERE u_email = ?";
                    java.util.List<java.util.Map<String, Object>> result = db.fetchRecords(qry, email);

                    if (result.isEmpty()) {
                        break;
                    } else {
                        System.out.print("Email already exists, Enter other Email: ");
                        email = sc.next();
                    }
                }

                System.out.print("Enter user Type (1 - Admin/2 -Staff): ");
                int type = sc.nextInt();
                while (type > 2 || type < 1) {
                    System.out.print("Invalid, choose between 1 & 2 only: ");
                    type = sc.nextInt();
                }
                String tp = "";
                if (type == 1) {
                    tp = "Admin";
                } else {
                    tp = "Staff";
                }

                System.out.print("Enter Password: ");
                String pass = sc.next();
                
                String hashs = config.hashPassword(pass);

                String sql = "INSERT INTO tbl_user(u_name, u_email, u_type, u_status, u_pass) VALUES (?, ?, ?, ?, ?)";
                db.addRecord(sql, name, email, tp, "Pending", hashs);

                break;

            case 3:

                break;

            default:
                System.out.println("ERROR!");

        }

    }

}
