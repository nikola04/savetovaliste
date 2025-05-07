package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.model.Psihoterapeut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenPsih extends JPanel implements ISubscriber {
    private static MainScreenPsih instance;
    private Psihoterapeut psihoterapeut;
    private Button btnLogout;
    private Button btnShowProfile;
    private Button btnClientApplications;
    private Button btnPastSessions;
    private Button btnUpcomingSessions;
    private JLabel msg;
    private JFrame profileFrame;

    public static MainScreenPsih getInstance() {
        if (instance == null) {
            instance = new MainScreenPsih();
            instance.initialize();
            instance.initializeGUI();
            instance.initControllers();
            instance.updateData();
        }
        return instance;
    }
    private MainScreenPsih(){}

    private void updateData(){
        psihoterapeut = Session.getInstance().getPsihoterapeut();
        if(psihoterapeut == null) return;
        msg.setText("Pozdrav " + psihoterapeut.getIme() + "!");
    }

    public void update(Object value){
        if((value instanceof Integer userId) && userId == -1){
            profileFrame.setVisible(false);
        }
        updateData();
    }

    private void initialize() {
        Session.getInstance().addSubscriber(this);

        profileFrame = new JFrame("Moj Profil");
        profileFrame.setContentPane(ProfileScreen.getInstance());
        profileFrame.setSize(500, 320);

        btnLogout = new Button("Odjavi se");
        btnShowProfile = new Button("Moj Profil");
        btnClientApplications = new Button("Prijave klijenta");
        btnPastSessions = new Button("Odrzane Seanse");
        btnUpcomingSessions = new Button("Buduci termini");

        msg = new JLabel("Pozdrav!");
    }

    private void initControllers(){
        btnLogout.addActionListener(e -> Session.getInstance().logoutUser());
        btnShowProfile.addActionListener(e -> {
            profileFrame.setVisible(true);
        });
    }

    private void initializeGUI() {
        setLayout(new BorderLayout());

        JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        headingPanel.add(msg);
        add(headingPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        addButton(mainPanel, btnShowProfile);
        addButton(mainPanel, btnClientApplications);
        addButton(mainPanel, btnPastSessions);
        addButton(mainPanel, btnUpcomingSessions);
        addButton(mainPanel, btnLogout);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, Button button) {
        button.setPreferredSize(new Dimension(300, 40));
        panel.add(button);
    }

}
