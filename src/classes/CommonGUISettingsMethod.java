/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.sun.glass.events.KeyEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ACC
 */
public class CommonGUISettingsMethod {
    public static void CapsLock(JLabel label,ImageIcon icon) {
        if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)) {
            //lblCapsLock.setVisible(true);
            //label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/warning.png"))); // NOI18N
            label.setIcon(icon);
            label.setText("Caps Lock is on");
        } else {
            label.setText("");
            label.setIcon(null);
            //lblCapsLock.setVisible(false);
        }
    }  
}
