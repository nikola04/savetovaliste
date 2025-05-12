package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.model.Psihoterapeut;
import savetovaliste.model.Sertifikat;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends JPanel implements ISubscriber {
    private static ProfileScreen instance;
    private JLabel lblHeading;
    // Profil
    private JLabel lblId;
    private JLabel lblIme;
    private JLabel lblPrezime;
    private JLabel lblJmbg;
    private JLabel lblEmail;
    private JLabel lblTelefon;
    private JLabel lblDatumRodjenja;
    private JLabel lblIdValue;
    private JLabel lblImeValue;
    private JLabel lblPrezimeValue;
    private JLabel lblJmbgValue;
    private JLabel lblEmailValue;
    private JLabel lblTelefonValue;
    private JLabel lblDatumRodjenjaValue;
    // Sertifikat
    private JLabel lblSertifikatDatum;
    private JLabel lblSertifikatOblast;
    private JLabel lblSertifikatValue;
    private JLabel lblSertifikatOblastValue;

    public static ProfileScreen getInstance(){
        if(instance == null) {
            instance = new ProfileScreen();
            instance.initialize();
            instance.initializeGUI();
            instance.setProfileData(Session.getInstance().getPsihoterapeut());
        }
        return instance;
    }

    private void initialize(){
        Session.getInstance().addSubscriber(this);

        lblHeading = new JLabel("Moj profil");
        lblHeading.setFont(new Font("Arial", Font.BOLD, 18));
        lblId = new JLabel("ID: ");
        lblIme = new JLabel("Ime: ");
        lblPrezime = new JLabel("Prezime: ");
        lblJmbg = new JLabel("Jmbg: ");
        lblEmail = new JLabel("Email: ");
        lblTelefon = new JLabel("Telefon: ");
        lblDatumRodjenja = new JLabel("Datum rodjenja: ");
        lblSertifikatDatum = new JLabel("Datum sertifikata: ");
        lblSertifikatOblast = new JLabel("Oblast sertifikata: ");

        lblIdValue = new JLabel();
        lblImeValue = new JLabel();
        lblPrezimeValue = new JLabel();
        lblJmbgValue = new JLabel();
        lblEmailValue = new JLabel();
        lblTelefonValue = new JLabel();
        lblDatumRodjenjaValue = new JLabel();
        lblSertifikatValue = new JLabel();
        lblSertifikatOblastValue = new JLabel();
    }

    private void initializeGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        headingPanel.add(lblHeading);
        add(headingPanel, BorderLayout.NORTH);

        addRow(panel, gbc, 0, lblId, lblIdValue);
        addRow(panel, gbc, 1, lblIme, lblImeValue);
        addRow(panel, gbc, 2, lblPrezime, lblPrezimeValue);
        addRow(panel, gbc, 3, lblJmbg, lblJmbgValue);
        addRow(panel, gbc, 4, lblEmail, lblEmailValue);
        addRow(panel, gbc, 5, lblTelefon, lblTelefonValue);
        addRow(panel, gbc, 6, lblDatumRodjenja, lblDatumRodjenjaValue);
        addRow(panel, gbc, 7, lblSertifikatDatum, lblSertifikatValue);
        addRow(panel, gbc, 8, lblSertifikatOblast, lblSertifikatOblastValue);

        add(panel, BorderLayout.CENTER);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, JLabel label, JLabel value) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.25;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.75;
        panel.add(value, gbc);
    }

    private void setProfileData(Psihoterapeut psihoterapeut){
        if(psihoterapeut == null) return;
        lblIdValue.setText(psihoterapeut.getId() + "");
        lblImeValue.setText(psihoterapeut.getIme());
        lblPrezimeValue.setText(psihoterapeut.getPrezime());
        lblJmbgValue.setText(psihoterapeut.getJmbg());
        lblEmailValue.setText(psihoterapeut.getEmail());
        lblTelefonValue.setText(psihoterapeut.getTelefon());
        lblDatumRodjenjaValue.setText(psihoterapeut.getDatumRodjenja().toString());

        Sertifikat sertifikat = psihoterapeut.getSertifikat();
        lblSertifikatValue.setText(sertifikat.getDatum().toString());
        lblSertifikatOblastValue.setText(sertifikat.getOblast().toString());
    }

    @Override
    public void update(Object value) {
        setProfileData(Session.getInstance().getPsihoterapeut());
    }
}
