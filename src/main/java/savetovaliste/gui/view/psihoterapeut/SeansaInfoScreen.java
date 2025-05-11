package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.CenaSeanse;
import savetovaliste.model.Klijent;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SeansaInfoScreen extends JFrame {
    private static SeansaInfoScreen instance;
    private static int seansaId;
    private Seansa seansa;

    private JFrame frameObjavljeniPodaci;

    private final JLabel lblSeansaId = new JLabel();
    private final JLabel lblPrva = new JLabel();
    private final JLabel lblDan = new JLabel();
    private final JLabel lblVreme = new JLabel();
    private final JLabel lblVremeTrajanja = new JLabel();
    private final JLabel lblNaRate = new JLabel();
    private final JLabel lblPlaceno = new JLabel();

    private final JLabel lblKlijentID = new JLabel();
    private final JLabel lblKlijentIme = new JLabel();
    private final JLabel lblKlijentPrezime = new JLabel();
    private final JLabel lblKlijentDatum = new JLabel();
    private final JLabel lblKlijentPol = new JLabel();
    private final JLabel lblKlijentEmail = new JLabel();
    private final JLabel lblKlijentTelefon = new JLabel();
    private final JLabel lblklijentRanijeTerapije = new JLabel();
    private final JLabel lblKlijentBrojPrijave = new JLabel();

    private final JLabel lblCena = new JLabel();
    private final JLabel lblCenaDatum = new JLabel();

    private final JButton btnBeleske = new JButton("Pogledaj Beleske sa Seanse");
    private final JButton btnObjavljeniPodaci = new JButton("Objavljeni Podaci");

    public static SeansaInfoScreen getInstance(int id){
        if(instance == null){
            instance = new SeansaInfoScreen();
        }
        seansaId = id;
        instance.fetchData();
        return instance;
    }

    private SeansaInfoScreen() {
        setTitle("Informacije o Seansi");
        setSize(700, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeGUI();
        registerActions();
    }

    private void initializeGUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(4, 4, 4, 4);
        gbcLeft.anchor = GridBagConstraints.WEST;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        row = addRow(leftPanel, gbcLeft, row, "Seansa ID:", lblSeansaId);
        row = addRow(leftPanel, gbcLeft, row, "Prva Seansa:", lblPrva);
        row = addRow(leftPanel, gbcLeft, row, "Datum:", lblDan);
        row = addRow(leftPanel, gbcLeft, row, "Vreme:", lblVreme);
        row = addRow(leftPanel, gbcLeft, row, "Trajanje:", lblVremeTrajanja);
        row = addRow(leftPanel, gbcLeft, row, "Na rate:", lblNaRate);
        row = addRow(leftPanel, gbcLeft, row, "Plaćeno:", lblPlaceno);
        row = addRow(leftPanel, gbcLeft, row, "Cena Seanse:", lblCena);
        row = addRow(leftPanel, gbcLeft, row, "Datum promene cene:", lblCenaDatum);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(4, 4, 4, 4);
        gbcRight.anchor = GridBagConstraints.WEST;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        int rRow = 0;
        rRow = addRow(rightPanel, gbcRight, rRow, "Klijent ID:", lblKlijentID);
        rRow = addRow(rightPanel, gbcRight, rRow, "Ime:", lblKlijentIme);
        rRow = addRow(rightPanel, gbcRight, rRow, "Prezime:", lblKlijentPrezime);
        rRow = addRow(rightPanel, gbcRight, rRow, "Datum rođenja:", lblKlijentDatum);
        rRow = addRow(rightPanel, gbcRight, rRow, "Pol:", lblKlijentPol);
        rRow = addRow(rightPanel, gbcRight, rRow, "Email:", lblKlijentEmail);
        rRow = addRow(rightPanel, gbcRight, rRow, "Telefon:", lblKlijentTelefon);
        rRow = addRow(rightPanel, gbcRight, rRow, "Ranije terapije:", lblklijentRanijeTerapije);
        rRow = addRow(rightPanel, gbcRight, rRow, "Broj prijave:", lblKlijentBrojPrijave);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        add(mainPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(btnBeleske);
        btnPanel.add(btnObjavljeniPodaci);
        add(btnPanel, BorderLayout.CENTER);

        frameObjavljeniPodaci = new JFrame("Objavljeni podaci");
        frameObjavljeniPodaci.setSize(700, 380);
        frameObjavljeniPodaci.setLayout(new BoxLayout(frameObjavljeniPodaci.getContentPane(), BoxLayout.Y_AXIS));
    }


    private int addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JLabel valueLabel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(valueLabel, gbc);

        return row + 1;
    }

    private void fetchData() {
        try {
            seansa = JDBCUtils.getSeansa(seansaId);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        lblSeansaId.setText(String.valueOf(seansa.getId()));
        lblPrva.setText(seansa.isPrva() ? "Da" : "Ne");
        lblDan.setText(seansa.getDatum().toString());
        lblVreme.setText(seansa.getVreme().toString());
        lblVremeTrajanja.setText(seansa.getTrajanje() + " min");
        lblNaRate.setText(seansa.isNaRate() ? "Da" : "Ne");
        lblPlaceno.setText(seansa.isPlaceno() ? "Da" : "Ne");

        Klijent k = seansa.getKlijent();
        lblKlijentID.setText(String.valueOf(k.getId()));
        lblKlijentIme.setText(k.getIme());
        lblKlijentPrezime.setText(k.getPrezime());
        lblKlijentDatum.setText(k.getDatumRodjenja().toString());
        lblKlijentPol.setText(k.getPol());
        lblKlijentEmail.setText(k.getEmail());
        lblKlijentTelefon.setText(k.getTelefon());
        lblklijentRanijeTerapije.setText(k.isRanijeTerapije() ? "Da" : "Ne");
        lblKlijentBrojPrijave.setText("N/A"); // Ako imas podatke, zameni

        CenaSeanse cs = seansa.getCenaSeanse();
        lblCena.setText(cs.getCena() + " RSD");
        lblCenaDatum.setText(cs.getDatumPromene().toString());
    }

    private void registerActions() {
        btnBeleske.addActionListener(_ -> {
            BeleskeScreen.getInstance(seansa).setVisible(true);
        });

        btnObjavljeniPodaci.addActionListener(_ -> {
            frameObjavljeniPodaci.setContentPane(ObjavljeniPodaci.getInstance(seansa));
            frameObjavljeniPodaci.setVisible(true);
        });
    }
}
