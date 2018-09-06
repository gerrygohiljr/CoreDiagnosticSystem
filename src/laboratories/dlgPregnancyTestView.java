/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratories;

import classes.CommonMethod;
import classes.DBQueries;
import classes.GlobalVariables;
import classes.SwingUtils;
import corediagnosticsystem.dlgConfirmPassword;
import corediagnosticsystem.dlgLoading;
import corediagnosticsystem.frmMain;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import reports.DataBeanList;

/**
 *
 * @author ACC
 */
public class dlgPregnancyTestView extends javax.swing.JDialog {

    private frmMain main;
    private String patientNo;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private HashMap hmap = new HashMap();

    /**
     * Creates new form dlgPregnancyTest
     */
    public dlgPregnancyTestView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public dlgPregnancyTestView(frmMain parent, dlgConfirmPassword password, boolean modal, String labCategory, String labName, String patientNo, String checkupDate, String checkupLaboratoryID, String medTech) {
        super(parent, modal);
        initComponents();
        this.main = parent;
        CommonMethod.setDialogScreenLocation(parent, this);
        lblPatientID.setText(patientNo);
        lblLaboratoryName.setText(labName);
        lblCategoryName.setText(labCategory);
        lblTitle.setName(checkupLaboratoryID);
        this.patientNo = patientNo;
        searchPatientInfo(CommonMethod.TrimPatientID(patientNo));
        searchMedTechInfo(medTech);
        System.out.println(checkupLaboratoryID);
        getPregnancyTestResults(checkupLaboratoryID);
        //showLoading(0,"Loading Patient Information...");
    }

    public void showLoading(int mode, String comment) {
        final dlgLoading loading = new dlgLoading(this, false, comment);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (mode == 0) {

                } else if (mode == 1) {
                    GenerateReports();
                }
                return null;
            }

            protected void done() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(dlgPregnancyTestView.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                loading.setVisible(false);
                loading.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }
        };
        worker.execute();
        loading.setVisible(true);
    }

    private void getPregnancyTestResults(String checkupLaboratoryID) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPregnancyTestResults(checkupLaboratoryID);
            if (db.rs.next()) {
                txtRequestedBy.setText(db.rs.getString("requested_by"));
                txtResults.setText(db.rs.getString("results"));
            }
            System.out.println(db.rs.getBoolean("edited"));
            if(db.rs.getBoolean("edited")){
                lblProcessing.setText("Edited by");
            }else{
                lblProcessing.setText("Processed by");
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

    private void searchPatientInfo(String patientNo) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPatientInfo(patientNo);
            if (db.rs.next()) {
                lblPatientName.setText(db.rs.getString("lastname") + ", " + db.rs.getString("firstname") + " " + db.rs.getString("extension") + " " + db.rs.getString("middlename"));
                lblPatientSex.setText(db.rs.getString("gender"));
                lblPatientAge.setText(db.rs.getString("age"));
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

    private void searchMedTechInfo(String username) {
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryMedTechInfo(username);
            if (db.rs.next()) {
                lblMedTechName.setText(db.rs.getString("emp_firstname") + " " + db.rs.getString("emp_middlename") + " " + db.rs.getString("emp_lastname") + " " + db.rs.getString("emp_extension"));
                lblMedTechPosition.setText(db.rs.getString("role"));
                lblMedTechLicenseNo.setText(db.rs.getString("license_no"));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCenter = new javax.swing.JPanel();
        pnlAdd = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblLaboratoryName = new javax.swing.JLabel();
        lblCategoryName = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblInstructions = new javax.swing.JLabel();
        txtResults = new javax.swing.JTextField();
        lblProcessing = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblPatientName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblPatientAge = new javax.swing.JLabel();
        lblPatientSex = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtRequestedBy = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblPatientID = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        lblMedTechName = new javax.swing.JLabel();
        lblMedTechPosition = new javax.swing.JLabel();
        lblMedTechLicenseNo = new javax.swing.JLabel();
        btnAddSave = new javax.swing.JButton();
        btnAddCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnlCenter.setLayout(new java.awt.CardLayout());

        pnlAdd.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblLaboratoryName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLaboratoryName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLaboratoryName.setText("Pregnancy Test");

        lblCategoryName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblCategoryName.setForeground(new java.awt.Color(51, 0, 255));
        lblCategoryName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCategoryName.setText("CATEGORY NAME");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 51));
        jLabel9.setText("Results:");

        lblInstructions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInstructions.setForeground(new java.awt.Color(255, 51, 0));
        lblInstructions.setText("Click Print button to generate form.");

        txtResults.setEditable(false);
        txtResults.setBackground(new java.awt.Color(255, 255, 255));
        txtResults.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtResults.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblProcessing.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProcessing.setForeground(new java.awt.Color(255, 51, 0));
        lblProcessing.setText("  Processed By:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addComponent(lblLaboratoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtResults, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 272, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblProcessing, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(271, 271, 271)
                        .addComponent(lblInstructions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(210, 210, 210)
                    .addComponent(lblCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(266, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(lblLaboratoryName, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtResults, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(154, 154, 154)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInstructions)
                    .addComponent(lblProcessing)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(lblCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(303, Short.MAX_VALUE)))
        );

        pnlAdd.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(588, 80));
        jPanel6.setLayout(new java.awt.BorderLayout());
        jPanel6.add(jSeparator1, java.awt.BorderLayout.PAGE_END);

        jPanel11.setPreferredSize(new java.awt.Dimension(710, 60));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(856, 60));

        lblPatientName.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPatientName.setForeground(new java.awt.Color(255, 51, 0));
        lblPatientName.setText("JUAN DELACRUZ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Patient Name:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Sex:");

        lblPatientAge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPatientAge.setForeground(new java.awt.Color(255, 51, 0));
        lblPatientAge.setText("50 Yrs Old");

        lblPatientSex.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPatientSex.setForeground(new java.awt.Color(255, 51, 0));
        lblPatientSex.setText("Male");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Age:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Requested by:");

        txtRequestedBy.setEditable(false);
        txtRequestedBy.setBackground(new java.awt.Color(255, 255, 255));
        txtRequestedBy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtRequestedBy.setPreferredSize(new java.awt.Dimension(6, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPatientName, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPatientAge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPatientSex, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRequestedBy, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPatientName)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPatientAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPatientSex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRequestedBy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jPanel11.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(856, 35));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        lblTitle.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 24)); // NOI18N
        lblTitle.setText(" Pregnancy Test");
        jPanel5.add(lblTitle);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Patient No.");
        jPanel7.add(jLabel4);

        lblPatientID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPatientID.setForeground(new java.awt.Color(255, 51, 0));
        lblPatientID.setText("000-000-0000");
        jPanel7.add(lblPatientID);

        jPanel5.add(jPanel7);

        jPanel11.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.add(jPanel11, java.awt.BorderLayout.CENTER);

        pnlAdd.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel8.setPreferredSize(new java.awt.Dimension(856, 70));
        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel8.add(jSeparator2, java.awt.BorderLayout.PAGE_START);

        lblMedTechName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMedTechName.setText("Medical Technologist Name");

        lblMedTechPosition.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMedTechPosition.setText("Postion:");

        lblMedTechLicenseNo.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblMedTechLicenseNo.setText("License #");

        btnAddSave.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print_icon.png"))); // NOI18N
        btnAddSave.setText("PRINT");
        btnAddSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSaveActionPerformed(evt);
            }
        });

        btnAddCancel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnAddCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnAddCancel.setText("CLOSE");
        btnAddCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMedTechLicenseNo)
                    .addComponent(lblMedTechName)
                    .addComponent(lblMedTechPosition))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
                .addComponent(btnAddCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddSave, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblMedTechName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMedTechPosition)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMedTechLicenseNo)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddSave, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        pnlAdd.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        pnlCenter.add(pnlAdd, "card2");

        getContentPane().add(pnlCenter);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCancelActionPerformed
        int CONFIRM = JOptionPane.showConfirmDialog(this, "Do you want to close this window?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (CONFIRM == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btnAddCancelActionPerformed

    private void GenerateReports() throws IOException {
        String FILENAME = lblPatientID.getText() + "_" + lblLaboratoryName.getText();
        btnAddSave.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        try {
            //SET PARAMETERS
            hmap.put("patient_no", lblPatientID.getText());
            hmap.put("patient_name", lblPatientName.getText());
            hmap.put("requested_by", txtRequestedBy.getText().trim());
            hmap.put("patient_age", lblPatientAge.getText());
            hmap.put("patient_sex", lblPatientSex.getText());
            hmap.put("lab_category", lblCategoryName.getText());
            hmap.put("lab_name", lblLaboratoryName.getText());
            hmap.put("lab_results", "\"" + txtResults.getText() + "\"");
            hmap.put("medtech_name", lblMedTechName.getText());
            hmap.put("position", lblMedTechPosition.getText());
            hmap.put("license_no", lblMedTechLicenseNo.getText());
            //COMPILE JASPER DESIGN, FILL AND VIEW
            jasperReport = JasperCompileManager.compileReport("./src/jrxmls/Lab_PregnancyTest.jrxml");//template with out school name above
            jasperPrint = JasperFillManager.fillReport(jasperReport, hmap, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/reports/" + FILENAME.replaceAll(" ", "_") + ".pdf");

        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (jasperPrint != null) {
                //Settings.SaveLog("" + ID, "Generating list to PDF.");
                //int CONFIRM = JOptionPane.showConfirmDialog(this, "The list has been exported successfuly!\n\n DO YOU WANT TO OPEN THE FILE AUTOMATICALLY?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // if (CONFIRM == JOptionPane.YES_OPTION) {
                //OPEN EXCEL EXPLICITLY
                //ToastDialog.makeToast(this, "Please wait, an Excel Application will open the exported list after a few seconds!", 5);
                Runtime.getRuntime().exec("cmd.exe /c start ./src/reports/" + FILENAME.replaceAll(" ", "_") + ".pdf");

                // }
            }
        }
        btnAddSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    private void btnAddSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSaveActionPerformed
         showLoading(1, "Generating Pregnancy Test Report...");

    }//GEN-LAST:event_btnAddSaveActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        SwingUtils.fadeOut(this);
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(dlgPregnancyTestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgPregnancyTestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgPregnancyTestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgPregnancyTestView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgPregnancyTestView dialog = new dlgPregnancyTestView(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAddCancel;
    private javax.swing.JButton btnAddSave;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCategoryName;
    private javax.swing.JLabel lblInstructions;
    private javax.swing.JLabel lblLaboratoryName;
    private javax.swing.JLabel lblMedTechLicenseNo;
    private javax.swing.JLabel lblMedTechName;
    private javax.swing.JLabel lblMedTechPosition;
    private javax.swing.JLabel lblPatientAge;
    private javax.swing.JLabel lblPatientID;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblPatientSex;
    private javax.swing.JLabel lblProcessing;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JTextField txtRequestedBy;
    private javax.swing.JTextField txtResults;
    // End of variables declaration//GEN-END:variables
}
