package savetovaliste.gui.view.psihoterapeut;

import org.w3c.dom.Text;
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

    private JFrame profileFrame;
    private JFrame clientApplicationsFrame;
    private JFrame pastSessionsFrame;
    private JFrame upcomingSessionsFrame;
    private JFrame paymentsFrame;

    private Button btnLogout;
    private Button btnShowProfile;
    private Button btnClientApplications;
    private Button btnPastSessions;
    private Button btnUpcomingSessions;
    private Button btnPayments;
    private JLabel lblGreeting;

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
        lblGreeting.setText("Dobrodosli " + psihoterapeut.getIme() + "!");
    }

    public void update(Object value){
        if((value instanceof Integer userId) && userId == -1){
            profileFrame.setVisible(false);
            clientApplicationsFrame.setVisible(false);
            pastSessionsFrame.setVisible(false);
            upcomingSessionsFrame.setVisible(false);
            paymentsFrame.setVisible(false);
        }
        updateData();
    }

    private void initialize() {
        Session.getInstance().addSubscriber(this);

        profileFrame = new JFrame("Moj Profil");
        profileFrame.setContentPane(ProfileScreen.getInstance());
        profileFrame.setSize(500, 320);

        clientApplicationsFrame = new JFrame("Prijave klijenta");
        clientApplicationsFrame.setContentPane(ClientApplicScreen.getInstance());
        clientApplicationsFrame.setSize(800, 400);

        pastSessionsFrame = new JFrame("Odrzane Seanse");
        pastSessionsFrame.setContentPane(PastSessionsScreen.getInstance());
        pastSessionsFrame.setSize(900, 320);

        upcomingSessionsFrame = new JFrame("Buduci termini");
        upcomingSessionsFrame.setContentPane(UpcSessionsScreen.getInstance());
        upcomingSessionsFrame.setSize(900, 320);

        paymentsFrame = new JFrame("Uplate i dugovanja");
        paymentsFrame.setContentPane(PaymentsScreen.getInstance());
        paymentsFrame.setSize(800, 400);

        btnLogout = new Button("Odjavi se");
        btnShowProfile = new Button("Moj Profil");
        btnClientApplications = new Button("Prijave klijenta");
        btnPastSessions = new Button("Odrzane Seanse");
        btnUpcomingSessions = new Button("Buduci termini");
        btnPayments = new Button("Uplate i dugovanja");

        lblGreeting = new JLabel("Pozdrav!");
    }

    private void initializeGUI() {
        setLayout(new BorderLayout());

        JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        headingPanel.add(lblGreeting);
        add(headingPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        addButton(mainPanel, btnShowProfile);
        addButton(mainPanel, btnClientApplications);
        addButton(mainPanel, btnPastSessions);
        addButton(mainPanel, btnUpcomingSessions);
        addButton(mainPanel, btnPayments);
        addButton(mainPanel, btnLogout);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, Button button) {
        button.setPreferredSize(new Dimension(300, 40));
        panel.add(button);
    }

    private void initControllers(){
        btnLogout.addActionListener(_ -> {
            if(JOptionPane.showConfirmDialog(null, "Jeste li sigurni da zelite da se odjavite", "Potvrdite odjavu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                Session.getInstance().logoutUser();
        });
        btnShowProfile.addActionListener(_ -> profileFrame.setVisible(true));
        btnClientApplications.addActionListener(_ -> clientApplicationsFrame.setVisible(true));
        btnPastSessions.addActionListener(_ -> pastSessionsFrame.setVisible(true));
        btnUpcomingSessions.addActionListener(_ -> upcomingSessionsFrame.setVisible(true));
        btnPayments.addActionListener(_ -> paymentsFrame.setVisible(true));
    }
}
