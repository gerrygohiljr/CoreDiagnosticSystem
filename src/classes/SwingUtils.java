/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.Timer;

/**
 *
 * @author ACC
 */
public class SwingUtils {
    /**
     * Creates an animation to fade the dialog opacity from 0 to 1.
     */
    public static void fadeIn(final JDialog dialog) {
        final Timer timer = new Timer(100, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = 0;
            @Override public void actionPerformed(ActionEvent e) {
                opacity += 0.25f;
                dialog.setOpacity(Math.min(opacity, 1));
                if (opacity >= 1) timer.stop();
            }

           
        });

        dialog.setOpacity(0);
        timer.start();
        dialog.setVisible(true);
    }

    /**
     * Creates an animation to fade the dialog opacity from 1 to 0.
     */
    public static void fadeOut(final JDialog dialog) {
        final Timer timer = new Timer(100, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = 1;
            @Override public void actionPerformed(ActionEvent e) {
                opacity -= 0.25f;
                dialog.setOpacity(Math.max(opacity, 0));
                if (opacity <= 0) {
                    timer.stop();
                    dialog.dispose();
                }
            }
        });

        dialog.setOpacity(1);
        timer.start();
    }
}