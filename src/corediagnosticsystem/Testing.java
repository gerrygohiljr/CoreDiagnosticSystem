/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corediagnosticsystem;

/**
 *
 * @author ACC
 */
public class Testing {

    public static String TrimPatientID(String value) {
        String trimmedValue = "";
        for (int i = 0; i < value.length(); i++) {
            char v = value.charAt(i);
            if (v == '0' || v == '-') {
                //trimmedValue += String.valueOf(v);
            }else{
                trimmedValue = value.substring(i, value.length()).replace("-", "");
                break;
            }
        }
        return trimmedValue;
    }

    public static void main(String args[]) {
        //Testing test = new Testing();
        System.out.println(TrimPatientID("000-180-0000"));
    }
}
