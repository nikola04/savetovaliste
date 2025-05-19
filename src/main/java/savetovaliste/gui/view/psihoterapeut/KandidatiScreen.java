package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.btnactions.BtnEditor;
import savetovaliste.controller.btnactions.BtnRenderer;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Kandidat;
import savetovaliste.model.Klijent;
import savetovaliste.model.Psihoterapeut;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class KandidatiScreen extends JPanel implements ISubscriber {
    private static KandidatiScreen instance;

    private JTable table;
    private DefaultTableModel model;

    private KandidatiScreen() {
    }

    public static KandidatiScreen getInstance() {
        if(instance == null) {
            instance = new KandidatiScreen();
            instance.initialize();
            instance.initializeGUI();
        }
        instance.fetchData();
        return instance;
    }

    private void initialize() {
        Session.getInstance().addSubscriber(this);
    }

    private void initializeGUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        model = new DefaultTableModel(new Object[] { "ID", "Ime", "Prezime", "Email", "JMBG", "Telefon", "Prebivaliste", "Pregled Seansi" }, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(Box.createVerticalStrut(10));
        add(scrollPane);
    }

    private void fetchData(){
        model.setRowCount(0);

        Psihoterapeut psihoterapeut;
        if((psihoterapeut = Session.getInstance().getPsihoterapeut()) == null)
            return;
        try {
            for (Kandidat kandidat : JDBCUtils.getKandidati(psihoterapeut)) {
                model.addRow(new Object[]{ kandidat.getId(), kandidat.getIme(), kandidat.getPrezime(), kandidat.getEmail(), kandidat.getJmbg(), kandidat.getTelefon(), kandidat.getPrebivaliste() });
            }
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.getColumn("Pregled Seansi").setMinWidth(150);
        table.getColumn("Pregled Seansi").setPreferredWidth(150);
        table.getColumn("Pregled Seansi").setCellRenderer(new BtnRenderer("Seanse"));
        table.getColumn("Pregled Seansi").setCellEditor(new BtnEditor(table,"Seanse"));
    }

    @Override
    public void update(Object value) {
        fetchData();
    }
}
