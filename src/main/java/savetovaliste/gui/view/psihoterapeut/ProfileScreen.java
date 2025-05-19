package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.model.Kandidat;
import savetovaliste.model.Psihoterapeut;
import savetovaliste.model.Sertifikat;
import savetovaliste.model.SertifikovaniPsihoterapeut;

import javax.swing.*;
import java.awt.*;

public class ProfileScreen extends JPanel implements ISubscriber {
    private static ProfileScreen instance;
    private JLabel lblHeading;
    private GridBagConstraints gbc;
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
    // Kandidat
    private JLabel lblKandidatPrebivaliste;
    private JLabel lblKandidatStepenStudija;
    private JLabel lblKandidatFakultet;
    private JLabel lblKandidatUniverzitet;
    private JLabel lblKandidatCentarZaObuku;
    private JLabel lblKandidatPrebivalisteValue;
    private JLabel lblKandidatStepenStudijaValue;
    private JLabel lblKandidatFakultetValue;
    private JLabel lblKandidatUniverzitetValue;
    private JLabel lblKandidatCentarZaObukuValue;

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
        lblKandidatPrebivaliste = new JLabel("Prebivaliste: ");
        lblKandidatStepenStudija = new JLabel("Stepen studija: ");
        lblKandidatFakultet = new JLabel("Fakultet: ");
        lblKandidatUniverzitet = new JLabel("Univerzitet: ");
        lblKandidatCentarZaObuku = new JLabel("Centar za obuku: ");

        lblIdValue = new JLabel();
        lblImeValue = new JLabel();
        lblPrezimeValue = new JLabel();
        lblJmbgValue = new JLabel();
        lblEmailValue = new JLabel();
        lblTelefonValue = new JLabel();
        lblDatumRodjenjaValue = new JLabel();
        lblSertifikatValue = new JLabel();
        lblSertifikatOblastValue = new JLabel();
        lblKandidatPrebivalisteValue = new JLabel();
        lblKandidatStepenStudijaValue = new JLabel();
        lblKandidatFakultetValue = new JLabel();
        lblKandidatUniverzitetValue = new JLabel();
        lblKandidatCentarZaObukuValue = new JLabel();
    }

    private void initializeGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
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
        addRow(panel, gbc, 9, lblKandidatPrebivaliste, lblKandidatPrebivalisteValue);
        addRow(panel, gbc, 10, lblKandidatStepenStudija, lblKandidatStepenStudijaValue);
        addRow(panel, gbc, 11, lblKandidatFakultet, lblKandidatFakultetValue);
        addRow(panel, gbc, 12, lblKandidatUniverzitet, lblKandidatUniverzitetValue);
        addRow(panel, gbc, 13, lblKandidatCentarZaObuku, lblKandidatCentarZaObukuValue);

        add(panel, BorderLayout.CENTER);

        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    private void updateGUI(Psihoterapeut psihoterapeut){
        if(psihoterapeut instanceof Kandidat kandidat) {
            lblKandidatPrebivaliste.setVisible(true);
            lblKandidatStepenStudija.setVisible(true);
            lblKandidatFakultet.setVisible(true);
            lblKandidatUniverzitet.setVisible(true);
            lblKandidatCentarZaObuku.setVisible(true);
            lblKandidatFakultetValue.setVisible(true);
            lblKandidatStepenStudijaValue.setVisible(true);
            lblKandidatPrebivalisteValue.setVisible(true);
            lblKandidatUniverzitetValue.setVisible(true);
            lblKandidatCentarZaObukuValue.setVisible(true);

            System.out.println(kandidat.getPrebivaliste());
            System.out.println(kandidat.getStudija());
            System.out.println(kandidat.getFakultet());
            System.out.println(kandidat.getCentarZaObuku());

            lblKandidatFakultetValue.setText(kandidat.getFakultet().toString());
            lblKandidatStepenStudijaValue.setText(kandidat.getStudija().getNaziv());
            lblKandidatPrebivalisteValue.setText(kandidat.getPrebivaliste());
            lblKandidatUniverzitetValue.setText(kandidat.getFakultet().getUniverzitet().getNaziv());
            lblKandidatCentarZaObukuValue.setText(kandidat.getCentarZaObuku().getNaziv());
        }else{
            lblKandidatPrebivaliste.setVisible(false);
            lblKandidatStepenStudija.setVisible(false);
            lblKandidatFakultet.setVisible(false);
            lblKandidatUniverzitet.setVisible(false);
            lblKandidatCentarZaObuku.setVisible(false);
            lblKandidatPrebivalisteValue.setVisible(false);
            lblKandidatFakultetValue.setVisible(false);
            lblKandidatUniverzitetValue.setVisible(false);
            lblKandidatCentarZaObukuValue.setVisible(false);
            lblKandidatStepenStudijaValue.setVisible(false);
        }
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

        if(psihoterapeut instanceof SertifikovaniPsihoterapeut sertifikovaniPsihoterapeut) {
            Sertifikat sertifikat = sertifikovaniPsihoterapeut.getSertifikat();
            lblSertifikatValue.setText(sertifikat.getDatum().toString());
            lblSertifikatOblastValue.setText(sertifikat.getOblast().toString());
        }else if (psihoterapeut instanceof Kandidat) {
            lblSertifikatValue.setText("Nema");
            lblSertifikatOblastValue.setText("Nema");
        }

        updateGUI(psihoterapeut);
    }

    @Override
    public void update(Object value) {
        setProfileData(Session.getInstance().getPsihoterapeut());
    }
}
