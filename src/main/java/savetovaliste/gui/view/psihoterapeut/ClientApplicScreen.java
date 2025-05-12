package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Klijent;
import savetovaliste.model.Prijava;
import savetovaliste.model.Psihoterapeut;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class ClientApplicScreen extends JPanel implements ISubscriber {
    private static ClientApplicScreen instance;
    private JTable table;
    private DefaultTableModel model;
    private JLabel lblHeading;

    public static ClientApplicScreen getInstance(){
        if(instance == null) {
            instance = new ClientApplicScreen();
            instance.initialize();
            instance.initializeGUI();
            instance.fetchData();
        }
        return instance;
    }

    private void initialize() {
        Session.getInstance().addSubscriber(this);

        lblHeading = new JLabel("Prijave Klijenata");
        lblHeading.setFont(new Font("Arial", Font.BOLD, 18));

        model = new DefaultTableModel();
    }

    private void initializeGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        model.addColumn("ID");
        model.addColumn("Klijent ID");
        model.addColumn("Ime");
        model.addColumn("Prezime");
        model.addColumn("Email");
        model.addColumn("Telefon");
        model.addColumn("Pol");
        model.addColumn("Datum Rodjenja");
        model.addColumn("Ranije terapije");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        lblHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(lblHeading);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
    }

    private void fetchData(){
        model.setRowCount(0);

        Psihoterapeut psihoterapeut;
        if((psihoterapeut = Session.getInstance().getPsihoterapeut()) == null)
            return;
        try {
            for (Prijava prijava : JDBCUtils.getPsihoterapeutPrijave(psihoterapeut)) {
                Klijent klijent = prijava.getKlijent();
                model.addRow(new Object[]{prijava.getId() + "", klijent.getId() + "", klijent.getIme(), klijent.getPrezime(), klijent.getEmail(), klijent.getTelefon(), klijent.getPol(), klijent.getDatumRodjenja().toString(), klijent.isRanijeTerapije() ? "DA" : "NE"});
            }
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object value) {
        fetchData();
    }
}
