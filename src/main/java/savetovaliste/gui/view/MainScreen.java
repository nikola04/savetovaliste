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
        Button showPsihoBtn = new Button("Prikazi info o psihoterapeutima");

        Button logInBtn = new Button("Log in");

        Button signUpBtn = new Button("Sign up");

        JLabel regLbl = new JLabel("Savetovaliste \"Novi Pocetak\"");
        regLbl.setFont(new Font("Arial", Font.BOLD, 20));

        setLayout(new BorderLayout());

        JPanel lblPanel = new JPanel();
        lblPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // top, left, bottom, right
        lblPanel.add(regLbl, BorderLayout.CENTER);
        add(lblPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        bottomPanel.add(showPsihoBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        buttonPanel.add(logInBtn);
        logInBtn.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(signUpBtn);
        signUpBtn.setPreferredSize(new Dimension(200, 40));


        buttonPanel.setMaximumSize(new Dimension(200, 100));

        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new GridBagLayout());
        centerWrapper.add(buttonPanel);

        add(centerWrapper, BorderLayout.CENTER);

        JFrame infoFrame = new JFrame();

        showPsihoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PsihoInfoForm pane = new PsihoInfoForm();
                infoFrame.setContentPane(pane.getPanel1());
                infoFrame.pack();
                infoFrame.setVisible(true);
            }
        });
        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                logIn = new LogInFrom();
                logIn.setVisible(true);

            }
        });

        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpForm signIn = new SignUpForm();
            }
        });
    }
}
