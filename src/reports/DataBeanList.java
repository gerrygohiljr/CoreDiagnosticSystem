/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author ACC
 */
public class DataBeanList {
    
 public ArrayList<SalesReportBean> getDataBeanSalesReport(JTable table){
    
        ArrayList<SalesReportBean> dataBeanSalesReport = new ArrayList<SalesReportBean>();
        int rowCount = table.getRowCount();
        for(int i=0;i<rowCount;i++){
            String PATIENT_NO = String.valueOf(table.getValueAt(i, 1));
            String LASTNAME = String.valueOf(table.getValueAt(i, 2));
            String FIRSTNAME = String.valueOf(table.getValueAt(i, 3));
            String MIDDLENAME = String.valueOf(table.getValueAt(i, 4));
            String CHECKUPDATE = String.valueOf(table.getValueAt(i, 5));
            String PARTICULARS = String.valueOf(table.getValueAt(i, 6));
            String AMOUNT = String.valueOf(table.getValueAt(i, 7));
            //System.out.println(LASTNAME+" "+ FIRSTNAME+" "+ MIDDLEINI+" "+ COURSEYEAR+" "+ FINALGRADE+" "+ EQUIVALENT+" "+ REMARKS);
            dataBeanSalesReport.add(produce(PATIENT_NO,LASTNAME, FIRSTNAME, MIDDLENAME, CHECKUPDATE, PARTICULARS, AMOUNT));
        }
        return dataBeanSalesReport;
    }
    private SalesReportBean produce(String patientno,String lastname,String firstname,String middlename,String checkupDate,String particulars,String amount){
        SalesReportBean dataBean = new SalesReportBean();
        dataBean.setPatient_no(patientno);
        dataBean.setLastname(lastname);
        dataBean.setFirstname(firstname);
        dataBean.setMiddlename(middlename);
        dataBean.setCheckup_date(checkupDate);
        dataBean.setParticulars(particulars);
        dataBean.setAmount(amount);
        return dataBean;
    }   
   
}

