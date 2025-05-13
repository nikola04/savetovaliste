package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.btnactions.BtnEditor;
import savetovaliste.controller.btnactions.BtnRenderer;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Klijent;
import savetovaliste.model.Psihoterapeut;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class PastSessionsScreen extends JPanel implements ISubscriber {
    private static PastSessionsScreen instance;
    private JTable table;
    private DefaultTableModel model;

    public static PastSessionsScreen getInstance(){
        if(instance == null) {
            instance = new PastSessionsScreen();
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

        model = new DefaultTableModel(new Object[] { "ID", "Datum", "Vreme", "Trajanje", "Placeno", "Klijent ID", "Ime", "Prezime", "Email", "Telefon", "Pol", "Datum Rodjenja", "Ranije terapije", "Seansa"}, 0);
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
            for (Seansa seansa : JDBCUtils.getOdrzaneSeanse(psihoterapeut)) {
                Klijent klijent = seansa.getKlijent();
                model.addRow(new Object[]{
                        seansa.getId() , seansa.getDatum().toString(), seansa.getVreme().toString(), seansa.getTrajanje() + "min", seansa.isPlaceno() ? "DA" : "NE",
                        klijent.getId() + "", klijent.getIme(), klijent.getPrezime(), klijent.getEmail(), klijent.getTelefon(), klijent.getPol(), klijent.getDatumRodjenja().toString(), klijent.isRanijeTerapije() ? "DA" : "NE"});
            }
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.getColumn("Seansa").setMinWidth(150);
        table.getColumn("Seansa").setPreferredWidth(150);
        table.getColumn("Seansa").setCellRenderer(new BtnRenderer("Vise"));
        table.getColumn("Seansa").setCellEditor(new BtnEditor(table,"Vise"));
    }

    @Override
    public void update(Object value) {
        fetchData();
    }
}
