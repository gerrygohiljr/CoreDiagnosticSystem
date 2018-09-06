/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corediagnosticsystem;

import classes.CommonMethod;
import classes.DBQueries;
import classes.GlobalVariables;
import static corediagnosticsystem.dlgPatientNew.INSERT;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ACC
 */
public class dlgDiscount extends javax.swing.JDialog {

    private frmMain main;
    private dlgCheckUp chekcup;

    /**
     * Creates new form dlgLaboratories
     */
    public dlgDiscount(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.main = (frmMain) parent;
        CommonMethod.setDialogScreenLocation(main, this);
        //txtCellphoneNo.setDocument(new JTextFieldLimit(11));
        //CommonMethod.getAutoEmployeeID(lblEmployeeID);

    }

    dlgDiscount(frmMain main, dlgCheckUp aThis, boolean modal) {
        super(main, modal);
        initComponents();
        this.main = main;
        this.chekcup = aThis;
        CommonMethod.setDialogScreenLocation(main, this);
        CommonMethod.getDiscountType(cmbPatientType);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbPatientType = new javax.swing.JComboBox<>();
        txtPercentage = new javax.swing.JSpinner();
        txtIDNo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(3, 0, 0, 2));

        cmbPatientType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPatientType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPatientTypeActionPerformed(evt);
            }
        });
        jPanel1.add(cmbPatientType);

        txtPercentage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtPercentage);

        txtIDNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIDNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtIDNo);

        jPanel5.setLayout(new java.awt.GridLayout(3, 0, 0, 2));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("PATIENT TYPE:");
        jPanel5.add(jLabel5);

        lblName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblName.setForeground(new java.awt.Color(0, 204, 153));
        lblName.setText("PERCENTAGE");
        jPanel5.add(lblName);

        lblAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblAmount.setText("ID NO.");
        jPanel5.add(lblAmount);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("%");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(263, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(588, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());
        jPanel6.add(jSeparator1, java.awt.BorderLayout.PAGE_END);

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));
        jPanel6.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jLabel10.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        jLabel10.setText(" ADDING DISCOUNT");
        jPanel6.add(jLabel10, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel8.add(jSeparator2, java.awt.BorderLayout.PAGE_START);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear_icon.png"))); // NOI18N
        jButton1.setMnemonic('c');
        jButton1.setText("CANCEL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save_icon.png"))); // NOI18N
        jButton2.setMnemonic('s');
        jButton2.setText("ADD");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(69, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(23, Short.MAX_VALUE)))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to cancel this transaction?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String discountName = cmbPatientType.getSelectedItem().toString();
        int percentage = Integer.parseInt(txtPercentage.getValue().toString());
        String idNo = txtIDNo.getText().trim();
        if (discountName.equals("-SELECT-")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No Discount Item Selected. Please select Discount Item!", "ERROR", JOptionPane.ERROR_MESSAGE);
            cmbPatientType.requestFocus();
            return;
        }
        if (!discountName.equals("OTHERS")) {
            if (idNo.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "No. ID Number found. Please enter "+discountName+" number!", "ERROR", JOptionPane.ERROR_MESSAGE);
                txtIDNo.requestFocus();
                return;
            }
        }
        chekcup.lblPercentageDiscount.setText(""+percentage);
        chekcup.lblPercentageDiscount.setName(idNo);
        chekcup.txtLess.setName(discountName);
        chekcup.computeSales(Double.parseDouble(chekcup.txtTotal.getText().replaceAll(",","")),Double.parseDouble(chekcup.lblPercentageDiscount.getText().replaceAll(",", "")),Double.parseDouble(chekcup.txtTendered.getText().replaceAll(",", "")));
        chekcup.btnRemoveDiscount.setText("RMV DISCOUNT");
        this.dispose();
        /*
        String category = cmbCategory.getSelectedItem().toString();
        String labname = txtName.getText().trim();
        String amount = txtAmount.getText().trim();
        String status = cmbStatus.getSelectedItem().toString();
        
        if (labname.equals("")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No Laboratory Name found. Please enter laboratory name!", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtName.requestFocus();
            return;
        }
        if (amount.equals("")) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "No Laboratory Amount found. Please enter Laboratory Amount!", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtAmount.requestFocus();
            return;
        }
        DBQueries db = new DBQueries();
        try {
            int CONFIRM = JOptionPane.showConfirmDialog(this, "Are all information correct?\n\n"
                    + "Lab. Category: "+category+"\n"
                    + "Lab. Name:     "+labname+"\n"
                    + "Lab. Amount:   "+amount+"\n"
                    + "Lab. Status:   "+status+"\n\n", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (CONFIRM == JOptionPane.YES_OPTION) {
                db.ConnectToDatabase();
                boolean exist = db.checkExistingLabInfo(labname);
                if (exist) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(this, labname + " already exist in the database record!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtName.requestFocus();
                    return;
                }
                int i = db.insertLaboratoryInformation(category,labname,amount,status);
                if (i > 0) {
                    INSERT = true;
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Inserted Successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    int CONFIRM2 = JOptionPane.showConfirmDialog(this, "Do you want to add more laboratory?", "CONFIRM", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (CONFIRM2 == JOptionPane.YES_OPTION) {
                        //CommonMethod.getAutoPatientID(lblPatientID);
                        ClearFields();
                    } else {
                        this.dispose();
                        main.showLoading(GlobalVariables.FORM_LABORATORY, "Refreshing Laboratory Lists....");
                    }

                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Insert Failed!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (HeadlessException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con != null) {
                db.closeConnection();
            }

        }
         */
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbPatientTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPatientTypeActionPerformed
        if (cmbPatientType.isEnabled()) {
            if (cmbPatientType.getSelectedIndex() == 0) {
                txtPercentage.setValue(0);
                txtPercentage.setEnabled(false);
                txtIDNo.setText("");
            } else if (cmbPatientType.getSelectedItem().toString().equals(GlobalVariables.OTHER_DISCOUNT)) {
                txtPercentage.setValue(0);
                txtPercentage.setEnabled(true);
                //txtPercentage.setEditable(true);
                txtPercentage.requestFocus();
                txtIDNo.setText("");
            } else {
                DBQueries db = new DBQueries();
                try {
                    db.ConnectToDatabase();
                    db.queryDiscountPercentage(cmbPatientType.getSelectedItem().toString());
                    if (db.rs.next()) {
                        //txtPercentage.setText();
                        txtPercentage.setValue(db.rs.getInt("discount_percentage"));
                        txtPercentage.setEnabled(false);
                        txtIDNo.requestFocus();
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

        }
    }//GEN-LAST:event_cmbPatientTypeActionPerformed

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
            java.util.logging.Logger.getLogger(dlgDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgDiscount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgDiscount dialog = new dlgDiscount(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cmbPatientType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblName;
    private javax.swing.JTextField txtIDNo;
    private javax.swing.JSpinner txtPercentage;
    // End of variables declaration//GEN-END:variables
}
