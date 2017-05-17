package org.itstep.pps2701.blokhin;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vit on 16.05.2017.
 */
public class FileChooser extends JFileChooser {
    public JDialog createDialog(Component parent) throws HeadlessException {
        return super.createDialog(parent);
    }
} // class FileChooser
