/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ACC
 */
public class DBSetting {
    public static final String db_user = "postgres";
    //public static final String db_user = "mis";
    //public static final String db_ip="192.168.1.145";
    public static final String db_ip = "localhost";
    //public static final String db_paswd ="MISa6c9c72";
    public static final String db_paswd = "POSTGRES";
    public static final String db_driver = "org.postgresql.Driver";
    public static final String db_url = "jdbc:postgresql://" + db_ip + ":5432/COREdb";
    
}
