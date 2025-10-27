
package main;

import config.config;


public class viewUser {
    public static void viewUsers() {
        config db = new config();
        
        String Query = "SELECT * FROM tbl_user";
        
        String[] votersHeaders = {"ID", "Name", "Email", "Type", "Status"};
        String[] votersColumns = {"u_id", "u_name", "u_email", "u_type", "u_status"};
        
        db.viewRecords(Query, votersHeaders, votersColumns);
    }

    
}
