package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Kandidat;
import savetovaliste.model.Klijent;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class KandidatSeanseFrame extends JFrame {
    private static KandidatSeanseFrame instance;
    private int kandidatId;
    private Kandidat kandidat;
    private JTable tableOdrzane;
    private JTable tableNaredne;
    private DefaultTableModel modelOdrzane;
    private DefaultTableModel modelNaredne;

    private KandidatSeanseFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Seanse Kandidata");
        setLocationRelativeTo(null);
        setSize(900, 600);
    }

    public static KandidatSeanseFrame getInstance(int kandidatId) {
        if(instance == null) {
            instance = new KandidatSeanseFrame();
            instance.initializeGUI();
        }
        instance.kandidatId = kandidatId;
        instance.fetchData();
        return instance;
    }

    private void initializeGUI() {
        // Glavni panel sa vertikalnim rasporedom
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel za Odrzane seanse
        JLabel lblOdrzane = new JLabel("Odrzane seanse:");
        lblOdrzane.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblOdrzane);
        mainPanel.add(Box.createVerticalStrut(5));

        modelOdrzane = new DefaultTableModel(new Object[] {
                "ID", "Datum", "Vreme", "Trajanje", "Placeno",
                "Klijent ID", "Ime", "Prezime", "Email", "Telefon",
                "Pol", "Datum Rodjenja", "Ranije terapije"
        }, 0);
        tableOdrzane = new JTable(modelOdrzane);
        JScrollPane scrollPaneOdrzane = new JScrollPane(tableOdrzane);
        scrollPaneOdrzane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPaneOdrzane.setPreferredSize(new Dimension(850, 220));
        scrollPaneOdrzane.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPaneOdrzane);

        mainPanel.add(Box.createVerticalStrut(20));

        // Panel za Naredne seanse
        JLabel lblNaredne = new JLabel("Naredne seanse:");
        lblNaredne.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblNaredne);
        mainPanel.add(Box.createVerticalStrut(5));

        modelNaredne = new DefaultTableModel(new Object[] {
                "ID", "Datum", "Vreme", "Trajanje", "Placeno",
                "Klijent ID", "Ime", "Prezime", "Email", "Telefon",
                "Pol", "Datum Rodjenja", "Ranije terapije"
        }, 0);
        tableNaredne = new JTable(modelNaredne);
        JScrollPane scrollPaneNaredne = new JScrollPane(tableNaredne);
        scrollPaneNaredne.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPaneNaredne.setPreferredSize(new Dimension(850, 220));
        scrollPaneNaredne.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPaneNaredne);

        // Dodaj glavni panel u JFrame
        setContentPane(mainPanel);
    }

    private void fetchData() {
        modelOdrzane.setRowCount(0);
        modelNaredne.setRowCount(0);

        try {
            if ((kandidat == null || kandidat.getId() != kandidatId) && (kandidat = JDBCUtils.getKandidat(kandidatId)) == null)
                return;

            for (Seansa seansa : JDBCUtils.getOdrzaneSeanse(kandidat)) {
                Klijent klijent = seansa.getKlijent();
                modelOdrzane.addRow(new Object[]{
                        seansa.getId(), seansa.getDatum().toString(), seansa.getVreme().toString(),
                        seansa.getTrajanje() + "min", seansa.isPlaceno() ? "DA" : "NE",
                        klijent.getId() + "", klijent.getIme(), klijent.getPrezime(),
                        klijent.getEmail(), klijent.getTelefon(), klijent.getPol(),
                        klijent.getDatumRodjenja().toString(), klijent.isRanijeTerapije() ? "DA" : "NE"
                });
            }
            for (Seansa seansa : JDBCUtils.getBuduceSeanse(kandidat)) {
                Klijent klijent = seansa.getKlijent();
                modelNaredne.addRow(new Object[]{
                        seansa.getId(), seansa.getDatum().toString(), seansa.getVreme().toString(),
                        seansa.getTrajanje() + "min", seansa.isPlaceno() ? "DA" : "NE",
                        klijent.getId() + "", klijent.getIme(), klijent.getPrezime(),
                        klijent.getEmail(), klijent.getTelefon(), klijent.getPol(),
                        klijent.getDatumRodjenja().toString(), klijent.isRanijeTerapije() ? "DA" : "NE"
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
