/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;



import javax.swing.JComboBox;


/**
 *
 * @author GgClaud
 */
public class JComboBoxSetter {

    public static int setComboBox(JComboBox cmb, String nt) {
        String value = "";
   
        int y = 0;
        if (!nt.trim().equals("") || nt != null) {
            for (int i = 0; i < cmb.getItemCount(); i++) {
                value = String.valueOf(cmb.getItemAt(i));
                if (value.equalsIgnoreCase(nt)) {
                    y = i;
                }
            }
        } else {

        }
        return y;
    }

   
}
