package savetovaliste.gui.view;

import savetovaliste.Session;
import savetovaliste.gui.view.psihoterapeut.MainScreenPsih;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static MainFrame instance;
    private static MainScreenPsih mainScreenPsih;
    private static MainScreen mainScreenLogin;
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
        mainScreenPsih = MainScreenPsih.getInstance();
        mainScreenLogin = MainScreen.getInstance();
    }
}
