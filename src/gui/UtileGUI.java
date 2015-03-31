package gui;

import java.awt.Component;

import javax.swing.JPanel;

public class UtileGUI {
	public static JPanel putInPanel(Component component) {
        JPanel pan=new JPanel();
        pan.add(component);
        return pan;
    }

}
