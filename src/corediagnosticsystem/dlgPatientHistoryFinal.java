/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corediagnosticsystem;

import classes.CommonMethod;
import classes.DBQueries;
import classes.GlobalVariables;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACC
 */
public class dlgPatientHistoryFinal extends javax.swing.JDialog {

    private frmMain main;
    private String patientID;
    private DefaultTableModel model;
    private int rowCount =0,rowNumber=1;

    /**
     * Creates new form dlgPatientHistoryPhysician
     */
    public dlgPatientHistoryFinal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    dlgPatientHistoryFinal(frmMain parent, boolean modal, String patientNo) {
        super(parent, modal);
        initComponents();
        this.main = (frmMain) parent;
        this.patientID = patientNo;
        CommonMethod.setDialogScreenLocation(main, this);
        //hide table header
        //tbPatientHistory.getTableHeader().setVisible(false);
        showPatientInfo();
        showPatientHistory();
    }

    private void showPatientInfo() {
        
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPatientInfo(patientID);
            if (db.rs.next()) {
                lblPatientID.setText(CommonMethod.convertPatientIDFromNumberToString(patientID));
                lblPatientName.setText(db.rs.getString("lastname") + ", " + db.rs.getString("firstname") + " " + db.rs.getString("extension") + " " + db.rs.getString("middlename"));
                lblGender.setText(db.rs.getString("gender"));
                lblAge.setText(db.rs.getString("age"));
                lblBirthdate.setText(db.rs.getString("birth_date"));
                lblAddress.setText(db.rs.getString("address"));
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
    private void showPatientHistory() {
        String initialCheckupDate = "",passedCheckupDate="";
        model = (DefaultTableModel) tbPatientHistory.getModel();  
        DBQueries db = new DBQueries();
        try {
            CommonMethod.clearTable(tbPatientHistory);
            db.ConnectToDatabase();
            db.queryPatientHistory(patientID);
            while (db.rs.next()) {
                String checkupDate = db.rs.getString("checkup_date");
                if(rowCount==0){
                    initialCheckupDate = checkupDate;
                    model.insertRow(rowCount++, new Object[]{"","<html><b>Date: "+db.rs.getString("checkup_date")+"</b></html>"});
                    model.insertRow(rowCount++, new Object[]{"","","<html><u>TEST NAME</u></html>","<html><u>RESULTS</u></html>","<html><u>EXAMINED BY</u></html>","<html><u>VIEW</u></html>"});
                    checkLabName(db.rs.getString("laboratory_id"),patientID, db.rs.getString("checkuplaboratories_id"),db.rs.getString("checkup_id"));
                }else{
                     initialCheckupDate = checkupDate;
                    //if is the continuaditon of the rowCount==0 condition
                    if(passedCheckupDate.equals(initialCheckupDate)){
                        checkLabName(db.rs.getString("laboratory_id"),patientID, db.rs.getString("checkuplaboratories_id"),db.rs.getString("checkup_id"));
                    }else{
                        initialCheckupDate = checkupDate;
                        model.insertRow(rowCount++, new Object[]{"","<html><b>Date: "+db.rs.getString("checkup_date")+"</b></html>"});
                        model.insertRow(rowCount++, new Object[]{"","","<html><u>TEST NAME</u></html>","<html><u>RESULTS</u></html>","<html><u>EXAMINED BY</u></html>","<html><u>VIEW</u></html>"});
                        checkLabName(db.rs.getString("laboratory_id"),patientID, db.rs.getString("checkuplaboratories_id"),db.rs.getString("checkup_id"));
                    }
                }
                passedCheckupDate = initialCheckupDate;
            }
            if(rowCount==0){
                model.insertRow(rowCount++, new Object[]{"","<html><b color='red'>NO HISTORY FOUND...</b></html>"});
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
  private void checkLabName(String labid,String patientID,String checkupLabID,String checkupID){
        switch (labid) {
        //1 is fecalysis
            case "1":
                FecalysisResults(patientID,checkupLabID);
                break;
        //2 is urinalysis
            case "2":
                UrinalysisResults(patientID, checkupLabID);
                break;
        //3 is Complete blood count
            case "3":
                CompleteBloodCountResults(patientID, checkupLabID);
                break;
        //4 is Blood Typing
            case "4":
                BloodTypingResults(patientID, checkupLabID);
                break;     
        //5 is clotting time/bleeding time
            case "5":
                ClottingTimeBleedingTimeResults(patientID, checkupLabID);
                break;        
        //6 is pregnancy test
            case "6":
                PregnancyTestResults(patientID, checkupLabID);
                break;        
        //7 is checkup/consultation
            case "7":
                CheckUpConsultationResults(patientID, checkupLabID);
                break;        
            default:
                break;
        }
  }
  private void FecalysisResults(String patientID,String checkupLabID){
      DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(patientID,checkupLabID);
            if (db.rs.next()) {
                 //model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>"+db.rs.getString("results")+"</b></html>",db.rs.getString("med_tech"),"<html><u color='blue'>Details...</u></html>"});
            }else{
                model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_FECALYSIS,"<html><b color='red'>NO RESULTS YET</b></html>","",""});
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
  private void UrinalysisResults(String patientID,String checkupLabID){
      DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(patientID,checkupLabID);
            if (db.rs.next()) {
                 //model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>"+db.rs.getString("results")+"</b></html>",db.rs.getString("med_tech"),"<html><u color='blue'>Details...</u></html>"});
            }else{
                model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_URINALYSIS,"<html><b color='red'>NO RESULTS YET</b></html>","",""});
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
  private void CompleteBloodCountResults(String patientID,String checkupLabID){
      DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(patientID,checkupLabID);
            if (db.rs.next()) {
                 //model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>"+db.rs.getString("results")+"</b></html>",db.rs.getString("med_tech"),"<html><u color='blue'>Details...</u></html>"});
            }else{
                model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_CTBT,"<html><b color='red'>NO RESULTS YET</b></html>","",""});
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
  private void BloodTypingResults(String patientID,String checkupLabID){
      DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(patientID,checkupLabID);
            if (db.rs.next()) {
                 //model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>"+db.rs.getString("results")+"</b></html>",db.rs.getString("med_tech"),"<html><u color='blue'>Details...</u></html>"});
            }else{
                model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_BLOOD_TYPING,"<html><b color='red'>NO RESULTS YET</b></html>","",""});
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
  private void ClottingTimeBleedingTimeResults(String patientID,String checkupLabID){
      DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(patientID,checkupLabID);
            if (db.rs.next()) {
                 //model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>"+db.rs.getString("results")+"</b></html>",db.rs.getString("med_tech"),"<html><u color='blue'>Details...</u></html>"});
            }else{
                model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_CBC,"<html><b color='red'>NO RESULTS YET</b></html>","",""});
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
  private void PregnancyTestResults(String patientID,String checkupLabID){
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(patientID,checkupLabID);
            if (db.rs.next()) {
                 model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>"+db.rs.getString("results")+"</b></html>",db.rs.getString("med_tech"),""});
            }else{
                model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>NO RESULTS YET</b></html>","",""});
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
  private void CheckUpConsultationResults(String patientID,String checkupLabID){
      DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(patientID,checkupLabID);
            if (db.rs.next()) {
                 //model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_PREGNANCY_TEST,"<html><b color='red'>"+db.rs.getString("results")+"</b></html>",db.rs.getString("med_tech"),"<html><u color='blue'>Details...</u></html>"});
            }else{
                model.insertRow(rowCount++, new Object[]{"",""," "+GlobalVariables.LAB_CHECKUP,"<html><b color='red'>NO RESULTS YET</b></html>","",""});
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
/*
    private void showPatientHistory() {
        int rowCount =0;
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPatientHistory(patientID);
            pnlPatientHistory.setLayout(new GridLayout(8, 4));
            while (db.rs.next()) {
                JButton button = new JButton(db.rs.getString("lab_name")+" ["+db.rs.getString("count")+"]");
                button.setActionCommand(patientID+"--"+db.rs.getInt("laboratory_id"));
                button.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                            JOptionPane.showMessageDialog(null,evt.getActionCommand());
                    }
                });
                pnlPatientHistory.add(button);
                rowCount++;
            }
            if(rowCount==0){
                JLabel label = new JLabel("NO HISTORYY FOUND...");
                pnlPatientHistory.add(label);
            }else{
                if(rowCount<35){
                while(rowCount<=35){
                    JLabel label = new JLabel("");
                    pnlPatientHistory.add(label);
                    rowCount++;
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
    */
    private void showForms(String patientID,int laboratory_id){
        
        //JOptionPane.showMessageDialog(this, patientID+" - "+laboratory_id);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlPatientHistory = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPatientHistory = new javax.swing.JTable();
        pnlPatientInfo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblPatientName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblAge = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblBirthdate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPatientID = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        pnlPatientHistory.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient History", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        pnlPatientHistory.setLayout(new java.awt.GridLayout(0, 1));

        tbPatientHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "checkup_laboratory_id", "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPatientHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbPatientHistory);
        if (tbPatientHistory.getColumnModel().getColumnCount() > 0) {
            tbPatientHistory.getColumnModel().getColumn(0).setMinWidth(0);
            tbPatientHistory.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbPatientHistory.getColumnModel().getColumn(0).setMaxWidth(0);
            tbPatientHistory.getColumnModel().getColumn(1).setMinWidth(120);
            tbPatientHistory.getColumnModel().getColumn(1).setPreferredWidth(120);
            tbPatientHistory.getColumnModel().getColumn(1).setMaxWidth(120);
            tbPatientHistory.getColumnModel().getColumn(2).setMinWidth(200);
            tbPatientHistory.getColumnModel().getColumn(2).setPreferredWidth(200);
            tbPatientHistory.getColumnModel().getColumn(2).setMaxWidth(200);
            tbPatientHistory.getColumnModel().getColumn(4).setMinWidth(150);
            tbPatientHistory.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbPatientHistory.getColumnModel().getColumn(4).setMaxWidth(150);
            tbPatientHistory.getColumnModel().getColumn(5).setMinWidth(120);
            tbPatientHistory.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbPatientHistory.getColumnModel().getColumn(5).setMaxWidth(120);
        }

        pnlPatientHistory.add(jScrollPane1);

        jPanel2.add(pnlPatientHistory, java.awt.BorderLayout.CENTER);

        pnlPatientInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Information"));
        pnlPatientInfo.setPreferredSize(new java.awt.Dimension(767, 95));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Patient Name:");

        lblPatientName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPatientName.setForeground(new java.awt.Color(255, 51, 0));
        lblPatientName.setText("Patient Name");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Sex:");

        lblGender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblGender.setForeground(new java.awt.Color(255, 51, 0));
        lblGender.setText("Sex:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Patient Address:");

        lblAddress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(255, 51, 0));
        lblAddress.setText("Patient Address");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Age:");

        lblAge.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAge.setForeground(new java.awt.Color(255, 51, 0));
        lblAge.setText("Age");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Birthday:");

        lblBirthdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBirthdate.setForeground(new java.awt.Color(255, 51, 0));
        lblBirthdate.setText("Birthday");

        javax.swing.GroupLayout pnlPatientInfoLayout = new javax.swing.GroupLayout(pnlPatientInfo);
        pnlPatientInfo.setLayout(pnlPatientInfoLayout);
        pnlPatientInfoLayout.setHorizontalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAge, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addComponent(lblGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(48, 48, 48))
                    .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                        .addComponent(lblBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pnlPatientInfoLayout.setVerticalGroup(
            pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPatientInfoLayout.createSequentialGroup()
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAge, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPatientInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel2.add(pnlPatientInfo, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(767, 50));

        jButton1.setText("GENERATE REPORT");
        jButton1.setPreferredSize(new java.awt.Dimension(200, 35));
        jPanel5.add(jButton1);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(767, 60));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText(" [PATIENT HISTORY]");
        jPanel4.add(jLabel1, java.awt.BorderLayout.WEST);

        jPanel6.setPreferredSize(new java.awt.Dimension(300, 58));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Patient No:");

        lblPatientID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPatientID.setForeground(new java.awt.Color(255, 51, 0));
        lblPatientID.setText("Patient No:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);
        jPanel3.add(jSeparator1, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgPatientHistoryFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgPatientHistoryFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgPatientHistoryFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgPatientHistoryFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgPatientHistoryFinal dialog = new dlgPatientHistoryFinal(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblBirthdate;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JPanel pnlPatientHistory;
    private javax.swing.JPanel pnlPatientInfo;
    private javax.swing.JTable tbPatientHistory;
    // End of variables declaration//GEN-END:variables
}
