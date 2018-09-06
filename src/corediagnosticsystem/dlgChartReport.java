/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corediagnosticsystem;

import classes.CommonMethod;
import classes.DBQueries;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author ACC
 */
public class dlgChartReport extends javax.swing.JDialog {

    private frmMain main;

    /**
     * Creates new form dlgChartReport
     */
    public dlgChartReport(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    dlgChartReport(frmMain parent, boolean modal, Date selectedDate) {
        super(parent, modal);
        initComponents();
        this.main = parent;
        showDailySalesChart(selectedDate);
        lblReportType.setText("DAILY SALES CHART");
        lblDateRange.setText("" + selectedDate);
        CommonMethod.setDialogScreenLocation(parent, this);
    }

    dlgChartReport(frmMain parent, boolean modal, int startMonth, int endMonth, int year) {
        super(parent, modal);
        initComponents();
        this.main = parent;
        showMonthlySalesChart(startMonth, endMonth, year);
        lblReportType.setText("MONTHLY SALES CHART");
        lblDateRange.setText("From: " + CommonMethod.Month(startMonth) + " To: " + CommonMethod.Month(endMonth) + " " + year);
        CommonMethod.setDialogScreenLocation(parent, this);
    }

    dlgChartReport(frmMain parent, boolean modal, int startYear, int endYear) {
        super(parent, modal);
        initComponents();
        this.main = parent;
        showYearlySalesChart(startYear, endYear);
        lblReportType.setText("YEARLY SALES CHART");
        lblDateRange.setText("From: " + startYear + " To: " + endYear);
        CommonMethod.setDialogScreenLocation(parent, this);
    }

    private void showDailySalesChart(Date selectedDate) {
        DBQueries db = new DBQueries();
        // DefaultPieDataset dataset = new DefaultPieDataset();
        String sql = "select lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and checkup_date='" + selectedDate + "' group by lab_name,amount order by lab_name;";
        try {
            //db.ConnectToDatabase();
            // db.querySalesOfTheMonth();
            //while (db.rs.next()) {
            //   dataset.setValue(db.rs.getString("product_description"), db.rs.getDouble("total_sales"));
            //  }

            JDBCCategoryDataset dataset = new JDBCCategoryDataset(db.con(), sql);
            //JFreeChart chart = ChartFactory.createLineChart("DAILY SALES", "LABORATORY NAME", "TOTAL SALES", dataset, PlotOrientation.VERTICAL, false, true, true);
            JFreeChart chart = ChartFactory.createBarChart("DAILY SALES", "LABORATORY NAME", "TOTAL SALES", dataset, PlotOrientation.VERTICAL, true, true, true);
            //JFreeChart chart = ChartFactory.createPieChart("SALES OF THE MONTH", dataset, true, true, false);
            CategoryAxis axis = chart.getCategoryPlot().getDomainAxis();
            axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            //BarRenderer renderer = null;
            //CategoryPlot plot = null;
            //renderer = new BarRenderer();
            System.out.println(sql);
            ChartPanel panel = new ChartPanel(chart);
            pnlChart.removeAll();
            pnlChart.repaint();
            pnlChart.revalidate();
            pnlChart.add(panel, BorderLayout.CENTER);
            pnlChart.repaint();
            pnlChart.revalidate();
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con() != null) {
                try {
                    db.con().close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    private void showMonthlySalesChart(int startMonth, int endMonth, int year) {
        DBQueries db = new DBQueries();
        // DefaultPieDataset dataset = new DefaultPieDataset();
        String sql = "select lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and extract(MONTH FROM checkup_date) between " + startMonth + " and " + endMonth + " AND extract(YEAR FROM checkup_date)=" + year + " group by lab_name,amount order by lab_name;";
        try {
            //db.ConnectToDatabase();
            // db.querySalesOfTheMonth();
            //while (db.rs.next()) {
            //   dataset.setValue(db.rs.getString("product_description"), db.rs.getDouble("total_sales"));
            //  }

            JDBCCategoryDataset dataset = new JDBCCategoryDataset(db.con(), sql);
            //JFreeChart chart = ChartFactory.createLineChart("DAILY SALES", "LABORATORY NAME", "TOTAL SALES", dataset, PlotOrientation.VERTICAL, false, true, true);
            JFreeChart chart = ChartFactory.createBarChart("MONTHLY SALES", "LABORATORY NAME", "TOTAL SALES", dataset, PlotOrientation.VERTICAL, true, true, true);
            //JFreeChart chart = ChartFactory.createPieChart("SALES OF THE MONTH", dataset, true, true, false);
            CategoryAxis axis = chart.getCategoryPlot().getDomainAxis();
            axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            //BarRenderer renderer = null;
            //CategoryPlot plot = null;
            //renderer = new BarRenderer();
            System.out.println(sql);
            ChartPanel panel = new ChartPanel(chart);
            pnlChart.removeAll();
            pnlChart.repaint();
            pnlChart.revalidate();
            pnlChart.add(panel, BorderLayout.CENTER);
            pnlChart.repaint();
            pnlChart.revalidate();
            File BarChart = new File("./src/images/BarChart.jpg");
            ChartUtilities.saveChartAsJPEG(BarChart, chart, 640, 480);
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con() != null) {
                try {
                    db.con().close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    private void showYearlySalesChart(int startYear, int endYear) {
        DBQueries db = new DBQueries();
        // DefaultPieDataset dataset = new DefaultPieDataset();
        String sql = "select lab_name,sum(amount) as amount from checkuplaboratories_tb A,checkup_tb B,laboratories_tb C where A.checkup_id=B.checkup_id and A.laboratory_id=C.lab_id and extract(YEAR FROM checkup_date) between "+startYear+" and "+endYear+" group by lab_name,amount order by lab_name;";
        try {
            //db.ConnectToDatabase();
            // db.querySalesOfTheMonth();
            //while (db.rs.next()) {
            //   dataset.setValue(db.rs.getString("product_description"), db.rs.getDouble("total_sales"));
            //  }

            JDBCCategoryDataset dataset = new JDBCCategoryDataset(db.con(), sql);
            //JFreeChart chart = ChartFactory.createLineChart("DAILY SALES", "LABORATORY NAME", "TOTAL SALES", dataset, PlotOrientation.VERTICAL, false, true, true);
            JFreeChart chart = ChartFactory.createBarChart("YEARLY SALES", "LABORATORY NAME", "TOTAL SALES", dataset, PlotOrientation.VERTICAL, true, true, true);
            //JFreeChart chart = ChartFactory.createPieChart("SALES OF THE MONTH", dataset, true, true, false);
            CategoryAxis axis = chart.getCategoryPlot().getDomainAxis();
            axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
            //BarRenderer renderer = null;
            //CategoryPlot plot = null;
            //renderer = new BarRenderer();
            ChartPanel panel = new ChartPanel(chart);
            pnlChart.removeAll();
            pnlChart.repaint();
            pnlChart.revalidate();
            pnlChart.add(panel, BorderLayout.CENTER);
            pnlChart.repaint();
            pnlChart.revalidate();
            File BarChart = new File("./src/images/BarChart.jpg");
            ChartUtilities.saveChartAsJPEG(BarChart, chart, 640, 480);
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (db.con() != null) {
                try {
                    db.con().close();
                } catch (SQLException ex) {
                }
            }
        }
    }

    private CategoryDataset createMonthlySalesDataset() {
        final String fiat = "FIAT";
        final String audi = "AUDI";
        final String ford = "FORD";
        final String speed = "Speed";
        final String millage = "Millage";
        final String userrating = "User Rating";
        final String safety = "safety";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, fiat, speed);
        dataset.addValue(3.0, fiat, userrating);
        dataset.addValue(5.0, fiat, millage);
        dataset.addValue(5.0, fiat, safety);

        dataset.addValue(5.0, audi, speed);
        dataset.addValue(6.0, audi, userrating);
        dataset.addValue(10.0, audi, millage);
        dataset.addValue(4.0, audi, safety);

        dataset.addValue(4.0, ford, speed);
        dataset.addValue(2.0, ford, userrating);
        dataset.addValue(3.0, ford, millage);
        dataset.addValue(6.0, ford, safety);

        return dataset;
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
        pnlChart = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblReportType = new javax.swing.JLabel();
        lblDateRange = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(881, 700));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        pnlChart.setLayout(new java.awt.GridLayout());

        jLabel3.setText("jLabel3");
        pnlChart.add(jLabel3);

        jPanel2.add(pnlChart, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(881, 70));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        jButton1.setText("CLOSE WINDOW");
        jButton1.setPreferredSize(new java.awt.Dimension(200, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(881, 60));
        jPanel3.setLayout(new java.awt.BorderLayout());

        lblReportType.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblReportType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReportType.setText("CHART REPORT");
        jPanel3.add(lblReportType, java.awt.BorderLayout.CENTER);

        lblDateRange.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDateRange.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDateRange.setText("CHART REPORT");
        jPanel3.add(lblDateRange, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(dlgChartReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgChartReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgChartReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgChartReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgChartReport dialog = new dlgChartReport(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblDateRange;
    private javax.swing.JLabel lblReportType;
    private javax.swing.JPanel pnlChart;
    // End of variables declaration//GEN-END:variables
}
