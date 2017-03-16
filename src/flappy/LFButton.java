package flappy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LFButton implements ActionListener {
    boolean lf = true;

    @Override public void actionPerformed(ActionEvent e) {
        if (lf == true) {
            Renderer.initSystemLookAndFeel();
            lf = false;
        } else if (lf == false) {
            Renderer.initMetalLookAndFeel();
            lf = true;
        }

    }
}
