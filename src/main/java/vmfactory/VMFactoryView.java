package vmfactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VMFactoryView {
    private JFrame mainFrame;

    public VMFactoryView() {
        this.mainFrame = new JFrame("VMFactory GUI");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.mainFrame.setSize(500, 800);


    }
}
