/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corediagnosticsystem;

import classes.CommonMethod;
import classes.DBQueries;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import laboratories.dlgPregnancyTest;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author ACC
 */
public class dlgPrintReceiptAndForm extends javax.swing.JDialog {

    private frmMain main;
    private dlgCheckUp checkup;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private HashMap hmap = new HashMap();

    /**
     * Creates new form dlgPrintReceiptAndForm
     */
    public dlgPrintReceiptAndForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    dlgPrintReceiptAndForm(frmMain parent, dlgCheckUp dialog, boolean modal) {
        super(parent, modal);
        initComponents();
        this.main = parent;
        this.checkup = dialog;
        CommonMethod.setDialogScreenLocation(parent, this);
        txtLastChange.setText(checkup.txtChange.getText());
    }

    public void showLoading(int mode, String comment) {
        final dlgLoading loading = new dlgLoading(this, false, comment);
        SwingWorker<?, ?> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                if (mode == 0) {
                    GenerateReports();
                }
                return null;
            }

            protected void done() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    JOptionPane.showMessageDialog(dlgPrintReceiptAndForm.this, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                loading.setVisible(false);
                loading.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }
        };
        worker.execute();
        loading.setVisible(true);
    }

    private void GenerateReports() throws IOException {
        String FILENAME = checkup.lblPatientID.getText()+"_PATIENT_PROFILE";
        btnPrintForm.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        DBQueries db = new DBQueries();
        try {
            db.ConnectToDatabase();
            db.queryPatientInfo(CommonMethod.TrimPatientID(checkup.lblPatientID.getText()));
            if (db.rs.next()) {
                hmap.put("patient_no", checkup.lblPatientID.getText());
                hmap.put("patient_name", checkup.lblPatientName.getText());
                hmap.put("requested_by", "");
                hmap.put("patient_age", db.rs.getString("age"));
                hmap.put("patient_sex", db.rs.getString("gender"));
            }
            db.checkIsPatientNew(CommonMethod.TrimPatientID(checkup.lblPatientID.getText()),CommonMethod.getServerDate());
            if(db.rs.next()){
                jasperReport = JasperCompileManager.compileReport("./src/jrxmls/Patient Profile_Cashier_New.jrxml");//template with out school name above
            }else{
                jasperReport = JasperCompileManager.compileReport("./src/jrxmls/Patient Profile_Cashier_Old.jrxml");//template with out school name above
            }
            //COMPILE JASPER DESIGN, FILL AND VIEW
            jasperPrint = JasperFillManager.fillReport(jasperReport, hmap, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "./src/pdf/" + FILENAME.replaceAll(" ", "_") + ".pdf");

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
                Runtime.getRuntime().exec("cmd.exe /c start ./src/pdf/" + FILENAME.replaceAll(" ", "_") + ".pdf");

                // }
            }
        }
        btnPrintForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        txtLastChange = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnPrintReceipt = new javax.swing.JButton();
        btnPrintForm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Transaction has been successfull!");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Last Change:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        txtLastChange.setBackground(new java.awt.Color(255, 255, 255));
        txtLastChange.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtLastChange.setForeground(new java.awt.Color(0, 51, 255));
        txtLastChange.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLastChange.setText("0.00");
        jPanel2.add(txtLastChange);

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        btnClose.setText("CLOSE");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnPrintReceipt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrintReceipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print_icon.png"))); // NOI18N
        btnPrintReceipt.setText("PRINT RECEIPT");
        btnPrintReceipt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintReceiptActionPerformed(evt);
            }
        });

        btnPrintForm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrintForm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print_icon.png"))); // NOI18N
        btnPrintForm.setText("PRINT FORM");
        btnPrintForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrintForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrintReceipt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrintForm)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClose, btnPrintForm});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrintForm))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClose, btnPrintForm, btnPrintReceipt});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintReceiptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintReceiptActionPerformed

    private void btnPrintFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintFormActionPerformed
        showLoading(0,"Generating Patient Profile Form....");
    }//GEN-LAST:event_btnPrintFormActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to close this window?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            checkup.dispose();
            this.dispose();
        }
    }//GEN-LAST:event_btnCloseActionPerformed

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
            java.util.logging.Logger.getLogger(dlgPrintReceiptAndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgPrintReceiptAndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgPrintReceiptAndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgPrintReceiptAndForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgPrintReceiptAndForm dialog = new dlgPrintReceiptAndForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnPrintForm;
    private javax.swing.JButton btnPrintReceipt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtLastChange;
    // End of variables declaration//GEN-END:variables
}
