/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ACC
 */
public class DBQueries {

    public Statement st, st2, st3, st4;
    public ResultSet rs, rs2, rs3, rs4;
    public Connection con, con2, con3, con4;

    public void ConnectToDatabase() {
        try {
            Class.forName(DBSetting.db_driver);
            con = DriverManager.getConnection(DBSetting.db_url, DBSetting.db_user, DBSetting.db_paswd);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException | SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage() + " \n\nPlease contact the System Administrator for assisstance!", "ERROR", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(null, "The System will exit!", "ERROR", JOptionPane.ERROR_MESSAGE);
            // System.exit(0);//exit the system
        }
    }

    public Connection con() {
        try {
            return DriverManager.getConnection(DBSetting.db_url, DBSetting.db_user, DBSetting.db_paswd);
        } catch (SQLException ex) {
            Logger.getLogger(DBQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void closeConnection() {
        try {
            con.close();
            st.close();
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void ConnectToDatabase2() {
        try {
            Class.forName(DBSetting.db_driver);
            con2 = DriverManager.getConnection(DBSetting.db_url, DBSetting.db_user, DBSetting.db_paswd);
            st2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException | SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage() + " \n\nPlease contact the System Administrator for assisstance!", "ERROR", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(null, "The System will exit!", "ERROR", JOptionPane.ERROR_MESSAGE);
            // System.exit(0);//exit the system
        }
    }

    public void closeConnection2() {
        try {
            con2.close();
            st2.close();
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public int insertPatientInformation(String patientID, String lname, String fname, String mname, String ext, String gender, Date bdate, String address, String contact) {
        try {
            String sql = "INSERT INTO patient_tb VALUES('" + patientID + "','" + lname + "','" + fname + "','" + mname + "','" + ext + "','" + gender + "','" + bdate + "','" + address + "','" + contact + "',now())";
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    protected String queryServerCurrentYear() {
        try {
            String sql = "SELECT EXTRACT(YEAR FROM NOW()) as server_current_year";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("server_current_year");
            }
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return null;
    }

    protected void queryServerCurrentDate() {
        try {
            String sql = "SELECT now() as server_current_date";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }

    }

    protected void queryAutoPatientNo() {
        try {
            //String sql = "select max(cast(coalesce(substr(patient_id,6,11),'0') as int))+1 as patient_id from patient_tb;";
            String sql = "select max(cast(patient_id as int)) +1 as patient_id from patient_tb;";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryPatientList() {
        try {
            String sql = "select patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age from patient_tb order by lastname;";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean checkExistingPatienInfo(String lname, String fname, String mname) {
        try {
            String sql = "select * from patient_tb where lastname='" + lname + "' and  firstname='" + fname + "' and middlename='" + mname + "'";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

    public void queryPatientInfo(String patientID) {
        try {
            String sql = "select patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age,date_registered from patient_tb where patient_id='" + patientID + "' order by lastname,firstname,middlename";
            rs = st.executeQuery(sql);
            System.out.println(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int updatePatientInformation(String patientID, String lname, String fname, String mname, String ext, String gender, Date bdate, String address, String contact) {
        try {
            String sql = "UPDATE patient_tb SET lastname='" + lname + "', firstname='" + fname + "',middlename='" + mname + "',extension='" + ext + "',gender='" + gender + "',birth_date='" + bdate + "',address='" + address + "',cellphone_no='" + contact + "' where patient_id='" + patientID + "'";
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void searchPatientInformationID(String PatientID) {
        try {

            String sql = "SELECT patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age from patient_tb WHERE patient_id ilike  '%" + PatientID + "%' ";

            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void searchPatientLastname(String searchlastname) {
        try {
            String sql = "SELECT patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age from patient_tb WHERE lastname ilike '%" + searchlastname + "%' order by lastname,firstname,middlename";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void searchPatientFirstnameAndLastname(String searchFirstname, String searchlastname) {
        try {
            String sql = "SELECT patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age from patient_tb WHERE lastname ilike '%" + searchlastname + "%' and firstname ilike '%" + searchFirstname + "%' order by lastname,firstname,middlename";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void searchPatientFirstnameAndLastnameAndMiddlename(String searchFirstname, String searchlastname, String searchMiddlename) {
        try {
            String sql = "SELECT patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age from patient_tb WHERE lastname ilike '%" + searchlastname + "%' and firstname ilike '%" + searchFirstname + "%' and middlename ilike '%" + searchMiddlename + "%' order by lastname,firstname,middlename";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void queryAutoEmployeeNo() {
        try {
            String sql = "select max(cast(coalesce(substr(employee_id,9,11),'0') as int))+1 as employee_id from employee_tb;";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void queryPositionList() {
        try {
            String sql = "select * from position_tb order by position_name;";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean checkExistingEmployeeInfo(String lname, String fname, String mname) {
        try {
            String sql = "select * from employee_tb where emp_lastname='" + lname + "' and  emp_firstname='" + fname + "' and emp_middlename='" + mname + "'";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

    public int insertEmployeeInformation(String employeeID, String lname, String fname, String mname, String ext, String gender, Date bdate, String username, String password, String position, String licenseno, String status, String address, String contact) {
        try {
            String sql = "INSERT INTO employee_tb VALUES('" + employeeID + "','" + lname + "','" + fname + "','" + mname + "','" + ext + "','" + bdate + "','" + gender + "',(SELECT position_id from position_tb WHERE position_name='" + position + "'),'" + licenseno + "','" + address + "','" + contact + "','" + status + "','" + username + "',md5('" + password + "'),now())";
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void queryEmployeeList() {
        try {
            String sql = "select employee_id,emp_lastname,emp_firstname,emp_middlename,emp_extension,emp_birthdate,emp_gender,position_name,license_no,emp_address,emp_cellphoneno,emp_status from employee_tb A,position_tb B where A.position_id=B.position_id order by emp_lastname;";

            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryEmployeeListNotInUserLists() {
        try {
            String sql = "select employee_id,emp_lastname,emp_firstname,emp_middlename,emp_extension from employee_tb A,position_tb B where A.position_id=B.position_id and emp_status='" + GlobalVariables.STAT_EMPLOYED + "' and employee_id not in (select employee_id from users_tb) order by emp_lastname;";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void querySystemInfo() {
        try {
            String sql = "select * from systeminfo_tb";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int insertSystemInformation(String ATTRIBUTES, String VALUES) {
        try {
            String sql = "INSERT INTO systeminfo_tb VALUES('" + ATTRIBUTES + "','" + VALUES + "')";
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void deleteSystemInfo() {
        try {
            String sql = "DELETE FROM systeminfo_tb";
            st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryEmployeeInfo(String employeeID) {
        try {
            String sql = "select employee_id,emp_lastname,emp_firstname,emp_middlename,emp_extension,emp_birthdate,emp_gender,position_name,license_no,emp_address,emp_cellphoneno,emp_status,image from employee_tb A,position_tb B where A.position_id=B.position_id and employee_id='" + employeeID + "'";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryUsersAccount() {
        try {
            String sql = "select username,status,date_created,role,emp_lastname,emp_firstname,emp_middlename,emp_extension from users_tb A,employee_tb B where A.employee_id=B.employee_id;";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void queryCategoryList() {
        try {
            String sql = "select * from labcategory_tb order by lab_categoryname";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean checkExistingLabInfo(String labname) {
        try {
            String sql = "select * from laboratories_tb where lab_name='" + labname + "'";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

    public int insertLaboratoryInformation(String category, String labname, String amount, String status, boolean view) {
        try {
            String sql = "INSERT INTO laboratories_tb(labcategory_id,lab_name,lab_amount,status) VALUES((SELECT labcategory_id FROM labcategory_tb WHERE lab_categoryname='" + category + "'),'" + labname + "'," + amount + ",'" + status + "')";
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public int updateLaboratoryInformation(String ID, String category, String labname, double amount, String status, boolean view) {
        try {
            String sql = "update laboratories_tb set lab_name='" + labname + "',labcategory_id=(SELECT labcategory_id from labcategory_tb where lab_categoryname='" + category + "'),lab_amount=" + amount + ",status='" + status + "',medtech_view=" + view + " where lab_id=" + ID + "";
            System.out.println(sql);
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void queryLaboratoryList() {
        try {
            String sql = "select lab_id,lab_categoryname,lab_name,lab_amount,status,medtech_view from laboratories_tb A,labcategory_tb B where A.labcategory_id=B.labcategory_id order by lab_name;";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryLaboratoryListForCheckUp() {
        try {
            String sql = "select lab_id,lab_name,lab_amount from laboratories_tb order by lab_name";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryConsultationFeeAmount(int feesID) {
        try {
            String sql = "select amount from fees_tb where fees_id=" + feesID + "";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void searchLaboratory(String value) {
        try {
            String sql = "select lab_id,lab_name,lab_amount from laboratories_tb where lab_name ilike '%" + value + "%' order by lab_name";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void queryCheckupID() {
        try {
            String sql = "SELECT nextval('checkup_tb_checkup_id_seq') as checkup_id";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int insertCheckupInfo(int checkupID, String patientID, double amountTendered, double changeAmount, String USERNAME, String discountID, int discountPercentage, String checkupDate) {
        try {
            String sql = "INSERT INTO checkup_tb(checkup_id,checkup_date,patient_id,amount_tendered,"
                    + "amount_changed,username,discount_id,discount_percentage,status) VALUES(" + checkupID + ",'" + checkupDate + "',"
                    + "'" + patientID + "'," + amountTendered + "," + changeAmount + ",'" + USERNAME + "',(SELECT discount_id FROM discount_tb where discount_name='" + discountID + "')," + discountPercentage + ",false)";
            System.out.println(sql);
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void queryDiscountType() {
        try {
            String sql = "SELECT discount_name from discount_tb where status = true order by discount_name";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryDiscountPercentage(String discountName) {
        try {
            String sql = "SELECT discount_percentage from discount_tb where discount_name='" + discountName + "'";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryPatientOncheckUpList() {
        try {
            String sql = "select B.patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age,checkup_date from patient_tb A,checkup_tb B WHERE A.patient_id=B.patient_id and status=false order by lastname;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryPatientDoneCheckUpList() {
        try {
            String sql = "select B.patient_id,lastname,firstname,middlename,extension,gender,birth_date,address,cellphone_no,extract( year from(age(now(),birth_date))) as age,checkup_date from patient_tb A,checkup_tb B WHERE A.patient_id=B.patient_id and status=true order by lastname;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int insertCheckupLaboratories(int checkupID, String labID, double labAmount) {
        try {
            String sql = "INSERT INTO checkuplaboratories_tb(checkup_id,laboratory_id,amount,requested_by,with_results,edited) VALUES(" + checkupID + "," + labID + "," + labAmount + ",'',false,false)";
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public void checkLoginAccount(String username, String password) {
        try {
            //String sql = "select A.employee_id,emp_lastname,emp_firstname,emp_middlename,emp_extension,emp_birthdate,emp_gender,"
            //      + "position_name,license_no,emp_address,emp_cellphoneno,emp_status from employee_tb A,position_tb B  where A.position_id=B.position_id and username='"+username+"' and password=md5('"+password+"')";
            String sql = "select A.employee_id,status,emp_lastname,emp_firstname,emp_middlename,emp_extension,position_name,role from users_tb A,employee_tb B,position_tb C where B.position_id=C.position_id and A.employee_id=B.employee_id  and username='" + username + "' and password=md5('" + password + "')";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void InsertLog(String username, String IP, String Hostname, String TransactionMade) {

        try {
            String sql = "INSERT INTO transactionlogtb(username,date_of_transaction,time_of_transaction,"
                    + "ip_address,hostname,transaction_made) VALUES('" + username + "',now(),now(),'" + IP + "','" + Hostname + "','" + TransactionMade + "')";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryImageOfEmployee(String ID) {
        try {
            //String sql = "select A.employee_id,emp_lastname,emp_firstname,emp_middlename,emp_extension,emp_birthdate,emp_gender,"
            //      + "position_name,license_no,emp_address,emp_cellphoneno,emp_status from employee_tb A,position_tb B  where A.position_id=B.position_id and username='"+username+"' and password=md5('"+password+"')";
            String sql = "select image from employee_tb where employee_id='" + ID + "'";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryMedTechListOfOnCheckupPatient() {
        try {
            String sql = "select checkuplaboratories_id,A.checkup_id,B.patient_id,A.laboratory_id,lab_categoryname,lab_name,checkup_date,lastname,firstname,middlename,extension,with_results from checkuplaboratories_tb A,checkup_tb B,patient_tb C,laboratories_tb D,labcategory_tb E where D.labcategory_id=E.labcategory_id and A.checkup_id=B.checkup_id and B.patient_id=C.patient_id and A.laboratory_id=D.lab_id and medtech_view =true and with_results is false;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryMedTechListOfDoneCheckupPatient(String username) {
        try {
            String sql = "select checkuplaboratories_id,A.checkup_id,B.patient_id,A.laboratory_id,lab_categoryname,lab_name,checkup_date,lastname,firstname,middlename,extension,with_results,med_tech from checkuplaboratories_tb A,checkup_tb B,patient_tb C,laboratories_tb D,labcategory_tb E where D.labcategory_id=E.labcategory_id and A.checkup_id=B.checkup_id and B.patient_id=C.patient_id and A.laboratory_id=D.lab_id and medtech_view =true and med_tech='" + username + "' and with_results is true;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryPhysicianListOfOnCheckupPatient() {
        try {
            String sql = "select checkuplaboratories_id,A.checkup_id,B.patient_id,A.laboratory_id,lab_categoryname,lab_name,checkup_date,lastname,firstname,middlename,extension,with_results from checkuplaboratories_tb A,checkup_tb B,patient_tb C,laboratories_tb D,labcategory_tb E where D.labcategory_id=E.labcategory_id and A.checkup_id=B.checkup_id and B.patient_id=C.patient_id and A.laboratory_id=D.lab_id and lab_name='" + GlobalVariables.LAB_CHECKUP + "' and with_results is false;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryPhysicianListOfDoneCheckupPatient(String username) {
        try {
            String sql = "select checkuplaboratories_id,A.checkup_id,B.patient_id,A.laboratory_id,lab_categoryname,lab_name,checkup_date,lastname,firstname,middlename,extension,with_results,med_tech from checkuplaboratories_tb A,checkup_tb B,patient_tb C,laboratories_tb D,labcategory_tb E where D.labcategory_id=E.labcategory_id and A.checkup_id=B.checkup_id and B.patient_id=C.patient_id and A.laboratory_id=D.lab_id and med_tech='" + username + "' and with_results is true;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryMedTechInfo(String username) {
        try {
            String sql = "select emp_lastname,emp_firstname,emp_middlename,license_no,emp_extension,role from users_tb A,employee_tb B where A.employee_id=B.employee_id and username='alwin';";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int updateCheckupLaboratory(String requestedby, int checkupLaboratoriesID, String username) {
        try {
            String sql = "UPDATE checkuplaboratories_tb SET requested_by='" + requestedby + "',with_results=true,med_tech='" + username + "',edited=true WHERE checkuplaboratories_id=" + checkupLaboratoriesID + "";
            return st.executeUpdate(sql);

        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public int insertLabPregnancyTestInfo(String patientNo, int checkupLaboratoriesID, String results) {
        try {
            String sql = "INSERT INTO lab_pregnancytest_tb(patient_id,checkuplaboratories_id,results) VALUES('" + patientNo + "'," + checkupLaboratoriesID + ",'" + results + "')";
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public int updateLabPregnancyTestInfo(String patientNo, int checkupLaboratoriesID, String results) {
        try {
            String sql = "UPDATE lab_pregnancytest_tb set results='" + results + "' WHERE patient_id='" + patientNo + "' and checkuplaboratories_id=" + checkupLaboratoriesID + "";
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public boolean checkLaboratoryIfAlreadyUpdated(int checkupLaboratoriesID) {
        try {
            String sql = "select * from checkuplaboratories_tb where checkuplaboratories_id =" + checkupLaboratoriesID + " and with_results=true;";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void queryPregnancyTestResults(String checkupLaboratoryID) {
        try {
            String sql = "select A.checkuplaboratories_id,checkup_id,laboratory_id,requested_by,results,edited from checkuplaboratories_tb A,lab_pregnancytest_tb B where A.checkuplaboratories_id=" + checkupLaboratoryID + " and A.checkuplaboratories_id=B.checkuplaboratories_id;";
            System.out.println(sql);
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void queryPregnancyTestResults(String patientID,String checkupLaboratoryID) {
        try {
            String sql = "select results,med_tech from lab_pregnancytest_tb A,checkuplaboratories_tb B where patient_id='"+patientID+"' and A.checkuplaboratories_id="+checkupLaboratoryID+" and A.checkuplaboratories_id=B.checkuplaboratories_id;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean checkCheckUpStatus(String patientID, String checkupDate) {
        try {
            String sql = "select * from checkup_tb where patient_id='" + patientID + "' and checkup_date='" + checkupDate + "';";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public void querySales(Date startDate, Date endDate, String filter) {
        try {
            String sql;
            if (filter.startsWith("ALL")) {
                sql = "select C.checkup_id,C.patient_id,lastname,firstname,middlename,extension,laboratory_id,lab_name,amount,checkup_date from checkuplaboratories_tb A,laboratories_tb B,checkup_tb C,patient_tb D where A.laboratory_id=B.lab_id and A.checkup_id=C.checkup_id and C.patient_id=D.patient_id and checkup_date between '" + startDate + "' and '" + endDate + "' order by checkup_date;";
            } else {
                sql = "select C.checkup_id,C.patient_id,lastname,firstname,middlename,extension,laboratory_id,lab_name,amount,checkup_date from checkuplaboratories_tb A,laboratories_tb B,checkup_tb C,patient_tb D where A.laboratory_id=B.lab_id and A.checkup_id=C.checkup_id and C.patient_id=D.patient_id and checkup_date between '" + startDate + "' and '" + endDate + "' and lab_name='" + filter + "' order by checkup_date;";
            }

            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryMonthlySales(int startMonth, int endMonth, int yearOfTheMonth, String labname) {
        try {
            String sql;
            if (labname.startsWith("ALL")) {
                sql = "select extract(MONTH from checkup_date) as month,lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and extract(MONTH FROM checkup_date) between "+startMonth+" and "+endMonth+" AND extract(YEAR FROM checkup_date)="+yearOfTheMonth+" group by lab_name,amount,month order by month,lab_name;";
            } else {
                sql = "select extract(MONTH from checkup_date) as month,lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and extract(MONTH FROM checkup_date) between "+startMonth+" and "+endMonth+" AND extract(YEAR FROM checkup_date)="+yearOfTheMonth+" and lab_name='"+labname+"' group by lab_name,amount,month order by month,lab_name;";
            }
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void queryDailySales(Date selectedDate, String labname) {
        try {
            String sql;
            if (labname.startsWith("ALL")) {
                //sql = "select lab_name,sum(amount) as amount,checkup_date from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id  GROUP BY lab_name,checkup_date HAVING checkup_date='" + selectedDate + "' order by lab_name;";
                sql = "select lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and checkup_date='"+selectedDate+"' group by lab_name,amount order by lab_name;";
            } else {
                sql = "select lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and checkup_date='"+selectedDate+"' and lab_name='"+labname+"' group by lab_name,amount order by lab_name;";
            }
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryServerYear() {
        try {
            String sql = "select extract(YEAR FROM now()) as year;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryYearlySales(int startYear, int endYear, String labname) {
        try {
            String sql;
            if (labname.startsWith("ALL")) {
                sql = "select extract(YEAR from checkup_date) as year,lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and extract(YEAR FROM checkup_date) between "+startYear+" and "+endYear+" group by lab_name,amount,year order by year,lab_name;";
            } else {
                sql = "select extract(YEAR from checkup_date) as year,lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and extract(YEAR FROM checkup_date) between "+startYear+" and "+endYear+" and lab_name='"+labname+"' group by lab_name,amount,year order by year,lab_name;";
            }
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int insertNewUserAccount(String employeeID, String username, String password, String role) {
        try {
            String sql = "INSERT INTO users_tb values('" + username + "',md5('" + password + "'),'"+GlobalVariables.ACCOUNT_ACTIVE+"',now(),'" + role + "','" + employeeID + "');";
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public int updateUserRole(String username, String role) {
        try {
            String sql = "UPDATE users_tb set role='" + role + "' WHERE username='" + username + "'";
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public int updateUserPassword(String username, String newpassword) {
        try {
            String sql = "UPDATE users_tb set status='"+GlobalVariables.ACCOUNT_RESET+"' WHERE username='" + username + "'";
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public int updateNewPassword(String password, String username) {
        int success =0 ;
        try {
            String sql = "UPDATE users_tb set password=md5('"+password+"') WHERE username='" + username + "'";
            String sql2 = "UPDATE users_tb set status='"+GlobalVariables.ACCOUNT_ACTIVE+"' WHERE username='" + username + "'";
            success += st.executeUpdate(sql);
            success += st.executeUpdate(sql2);
            return success;
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public int updateStatusActive(String USERNAME) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void queryPatientHistory(String patientID) {
        try {
            //String sql = "select laboratory_id,lab_name,count(lab_name) as count from checkuplaboratories_tb A,laboratories_tb B,checkup_tb C where A.laboratory_id=B.lab_id and A.checkup_id=C.checkup_id and C.patient_id='"+patientID+"' GROUP BY lab_name,laboratory_id ORDER BY lab_name;";
            String sql = "select patient_id,checkuplaboratories_id,A.checkup_id,laboratory_id,lab_name,med_tech,checkup_date from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and patient_id='"+patientID+"' order by checkup_date desc;";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void checkIsPatientNew(String TrimPatientID,String serverDate) {
        try {
            String sql = "select * from patient_tb where patient_id='"+TrimPatientID+"' and date_registered='"+serverDate+"'";
            System.out.println(sql);
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

}
