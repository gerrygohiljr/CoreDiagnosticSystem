/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACC
 */
public class CommonMethod {

    public static void getAutoPatientID(JFormattedTextField label) {
        // public static void getAutoPatientID() {

        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            //String ServerCurrentYear = db.queryServerCurrentYear();
            //String ServerCurrentYear = "2018";
            //System.out.println(ServerCurrentYear);

            db.queryAutoPatientNo();
            if (db.rs.next()) {
                String id = db.rs.getString("patient_id");
                if (id == null) {
                    label.setText("000-000-" + "000001");
                } else {
                    switch (id.length()) {
                        case 1:
                            label.setText("000" + "-" + "000" + "-000" + id);
                            break;
                        case 2:
                            label.setText("000" + "-" + "000" + "-00" + id);
                            break;
                        case 3:
                            label.setText("000" + "-" + "000" + "-0" + id);
                            break;
                        case 4:
                            label.setText("000" + "-" + "000" + "-" + id);
                            break;
                        case 5:
                            label.setText("000" + "-" + "00" + id);
                            break;
                        case 6:
                            label.setText("000" + "-" + "0" + id);
                            break;
                        case 7:
                            label.setText("000" + "-" + id);
                            break;
                        case 8:
                            label.setText("00" + id);
                            break;
                        case 9:
                            label.setText("0" + id);
                            break;
                        default:
                            label.setText(id);
                            break;
                    }
                }
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static String convertPatientIDFromNumberToString(String id) {

        switch (id.length()) {
            case 1:
                return "000" + "-" + "000" + "-000" + id;
            case 2:
                return "000" + "-" + "000" + "-00" + id;
            case 3:
                return "000" + "-" + "000" + "-0" + id;
            case 4:
                return "000" + "-" + "000" + "-" + id;
            case 5:
                return "000" + "-" + "00" + id;
            case 6:
                return "000" + "-" + "0" + id;
            case 7:
                return "000" + "-" + id;
            case 8:
                return "00" + id;
            case 9:
                return "0" + id;
            default:
                return id;
        }

    }

    public static String TrimPatientID(String value) {
        String trimmedValue = "";
        for (int i = 0; i < value.length(); i++) {
            char v = value.charAt(i);
            if (v == '0' || v == '-') {
                //trimmedValue += String.valueOf(v);
            } else {
                trimmedValue = value.substring(i, value.length()).replace("-", "");
                break;
            }
        }
        return trimmedValue;
    }

    public static void clearTable(JTable table) {
        int i = 0;
        while (table.getRowCount() > i) {
            ((DefaultTableModel) table.getModel()).removeRow(i);
        }
    }

    public static void setOnlyNumbersAccepted(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACKSPACE || c == KeyEvent.VK_DELETE || c == '.')) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }

    public static void setDialogScreenLocation(JFrame parent, JDialog dialog) {
        Point dime = parent.getLocationOnScreen();
        Dimension di = parent.getSize();
        int parentWidth = di.width / 2;
        int parentHeight = di.height / 2;
        int thisWindowWidth = dialog.getWidth() / 2;
        int thisWindowHeight = dialog.getHeight() / 2;
        int x = (int) dime.getX() + (parentWidth - thisWindowWidth);
        int y = (int) dime.getY() + (parentHeight - thisWindowHeight);
        dialog.setLocation(x, y);
    }

    public static void getAutoEmployeeID(JLabel label) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            String ServerCurrentYear = "CS-" + db.queryServerCurrentYear();
            //String ServerCurrentYear = "2018";
            //System.out.println(ServerCurrentYear);

            db.queryAutoEmployeeNo();
            if (db.rs.next()) {
                String id = db.rs.getString("employee_id");
                if (id == null) {
                    label.setText(ServerCurrentYear + "-" + "001");
                } else {
                    switch (id.length()) {
                        case 1:
                            label.setText(ServerCurrentYear + "-" + "00" + id);
                            break;
                        case 2:
                            label.setText(ServerCurrentYear + "-" + "0" + id);
                            break;
                        default:
                            label.setText(ServerCurrentYear + "-" + id);
                            break;
                    }
                }
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static void getEmployeeListNotInUserList(JComboBox combo) {
        int loop =0;
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryEmployeeListNotInUserLists();
            combo.removeAllItems();
            combo.addItem("-SELECT-");
            while (db.rs.next()) {
                combo.addItem("["+db.rs.getString("employee_id")+"] "+db.rs.getString("emp_lastname")+", "+db.rs.getString("emp_firstname")+" "+db.rs.getString("emp_extension")+" "+db.rs.getString("emp_middlename"));
                loop++;
            }
            if(loop==0){
                combo.addItem("ALL EMPLOYEES ARE USERS ALREADY!");
            }
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }
     public static void getSystemRoles(JComboBox combo) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPositionList();
            combo.removeAllItems();
            combo.addItem("-SELECT-");
            while (db.rs.next()) {
                combo.addItem(db.rs.getString("position_name"));
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }
    public static void getPositionList(JComboBox combo) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPositionList();
            combo.removeAllItems();
            combo.addItem("-SELECT-");
            while (db.rs.next()) {
                combo.addItem(db.rs.getString("position_name"));
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static void getLaboratoryList(JComboBox combo) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryLaboratoryListForCheckUp();
            combo.removeAllItems();
            combo.addItem("ALL LABORATORY");
            while (db.rs.next()) {
                combo.addItem(db.rs.getString("lab_name"));
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static void getCategoryList(JComboBox combo) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryCategoryList();
            combo.removeAllItems();
            combo.addItem("-SELECT-");
            while (db.rs.next()) {
                combo.addItem(db.rs.getString("lab_categoryname"));
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static void getDiscountType(JComboBox combo) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryDiscountType();
            combo.setEnabled(false);
            combo.removeAllItems();
            combo.addItem("-SELECT-");
            while (db.rs.next()) {
                String discountName = db.rs.getString("discount_name");
                if (!discountName.equals(GlobalVariables.NO_DISCOUNT)) {
                    combo.addItem(discountName);
                }
            }
            combo.addItem("OTHERS");
            combo.setEnabled(true);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static double consultationFee() {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryConsultationFeeAmount(GlobalVariables.CONSULTATION_FEE_CODE);
            if (db.rs.next()) {
                return db.rs.getDouble("amount");
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }
        }
        return 0;
    }

    public static void getAutoCheckupID(JLabel field) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryCheckupID();
            if (db.rs.next()) {
                field.setText(db.rs.getString("checkup_id"));
            }
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            field.setText("1");
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static void getServerDate(JLabel field) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryServerCurrentDate();
            if (db.rs.next()) {
                field.setText("" + formatter.format(db.rs.getDate("server_current_date")));
            } else {
                field.setOpaque(true);
                field.setForeground(Color.RED);
                field.setText("ERROR");
            }
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            field.setOpaque(true);
            field.setForeground(Color.RED);
            field.setText("ERROR");
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
    }

    public static int getServerYear() {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryServerYear();
            if (db.rs.next()) {
                return db.rs.getInt("year");
            }
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }
        }
        return 0;
    }
    public static String getServerDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryServerCurrentDate();
            if (db.rs.next()) {
                return formatter.format(db.rs.getDate("server_current_date"));
            } 
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
        return null;
    }
    public static void SaveLog(String Username, String TransactionMade) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.InsertLog(Username, IpAndHost.getIpAddress(), IpAndHost.getHostName(), TransactionMade);
        } catch (UnknownHostException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                db.closeConnection();
            } catch (Exception e) {
            }
        }
    }

    public static String Month(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 13:
                return "December";

            default:
                return "Invalid";
        }
    }
}
