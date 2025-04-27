package savetovaliste.gui.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
            instance.initializeGUI();
        }
        return instance;
    }
    private MainFrame(){}

    public void initialize() {
    }
    private void initializeGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int appWidth = screenSize.width * 3/4;
        int appHeight = screenSize.height * 3/4;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(appWidth, appHeight);
        setTitle("Savetovaliste");

        JPanel desktop = new JPanel(new BorderLayout());
        this.getContentPane().add(desktop, BorderLayout.CENTER);
    }
}
