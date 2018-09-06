/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

/**
 *
 * @author ACC
 */
public class SalesReportBean {

    private String patient_no;
    private String lastname;
    private String firstname;
    private String middlename;
    private String checkup_date;
    private String particulars;
    private String amount;

    public String getPatient_no() {
        return patient_no;
    }

    public void setPatient_no(String patient_no) {
        this.patient_no = patient_no;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getCheckup_date() {
        return checkup_date;
    }

    public void setCheckup_date(String checkup_date) {
        this.checkup_date = checkup_date;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
