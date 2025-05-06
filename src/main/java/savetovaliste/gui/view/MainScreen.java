package savetovaliste.gui.view;

import savetovaliste.gui.view.forms.LogInFrom;
import savetovaliste.gui.view.forms.PsihoInfoForm;
import savetovaliste.gui.view.forms.SignUpForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel {
    private static MainScreen instance;
    private Button showPsihoBtn;
    private Button signInBtn;
    private Button logInBtn;
    private JLabel regLbl;
    private LogInFrom logIn;


    public static MainScreen getInstance() {
        if (instance == null) {
            instance = new MainScreen();
            instance.initialize();
            instance.initializeGUI();
        }
        return instance;
    }
    private MainScreen(){}

    public void initialize() {
    }

    private void initializeGUI() {

        setLayout(new BorderLayout());

        showPsihoBtn = new Button("Prikazi info o psihoterapeutima");
        Dimension buttonSize = new Dimension(240, 80);

        logInBtn = new Button("Log in");
        logInBtn.setMaximumSize(buttonSize);

        signInBtn = new Button("Sign in");
        signInBtn.setMaximumSize(buttonSize);

        regLbl = new JLabel("Prijava Psihoterapeuta");

        // Koristi JDesktopPane za unutrašnje prozore
        JPanel desktop = new JPanel();
        JPanel bottomPanel = new JPanel(new FlowLayout());
        add(desktop, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.add(showPsihoBtn);

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(regLbl);
        centerPanel.add(logInBtn);
        centerPanel.add(signInBtn);
        desktop.add(centerPanel, BorderLayout.CENTER);

        showPsihoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                PsihoInfoForm pane = new PsihoInfoForm();
                frame.setContentPane(pane.getPanel1());
                frame.pack();
                frame.setVisible(true);
            }
        });
        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                logIn = new LogInFrom();
                logIn.setVisible(true);

            }
        });

        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpForm signIn = new SignUpForm();
            }
        });
    }
}
