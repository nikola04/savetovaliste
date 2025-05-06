package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenPsih extends JPanel {
    private static MainScreenPsih instance;
    private Button logoutButton;
    private JLabel msg;
    public static MainScreenPsih getInstance() {
        if (instance == null) {
            instance = new MainScreenPsih();
            instance.initialize();
            instance.initializeGUI();
            instance.initControllers();
        }
        return instance;
    }
    private MainScreenPsih(){}

    private void initialize() {
        logoutButton = new Button("Odjavi se");
        msg = new JLabel("Pozdrav! Ulogovan si.");
    }

    private void initControllers(){
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Session.getInstance().logoutUser();
            }
        });
    }

    private void initializeGUI() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);

        panel.add(msg);
        panel.add(logoutButton);
    }
}
