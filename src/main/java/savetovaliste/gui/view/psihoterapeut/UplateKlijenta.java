package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Klijent;
import savetovaliste.model.NacinPlacanja;
import savetovaliste.model.Placanje;
import savetovaliste.model.SvrhaPlacanja;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class UplateKlijenta extends JFrame {
    private static UplateKlijenta instance;
    private int klijent_id;
    private ArrayList<Placanje> placanja = new ArrayList<>();

    private JTable table;
    private DefaultTableModel tableModel;

    private UplateKlijenta() {
        super("Uplate Klijenta");
    }
    public static UplateKlijenta getInstance(int klijent_id) {
        if(instance == null) {
            instance = new UplateKlijenta();
            instance.initialize();
        }
        instance.klijent_id = klijent_id;
        instance.fetchData();
        return instance;
    }

    private void fetchData() {
        try {
            Klijent klijent = JDBCUtils.getKlijent(klijent_id);
            if(klijent == null) return;
            placanja = JDBCUtils.getPlacanja(klijent);
            this.updateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(500,380);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        JLabel lblUplate = new JLabel("Uplate Klijenta");
        contentPane.add(lblUplate);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Seansa");
        tableModel.addColumn("Iznos");
        tableModel.addColumn("Sa provizijom");
        tableModel.addColumn("Valuta");
        tableModel.addColumn("Nacin Placanja");
        tableModel.addColumn("Svrha placanja");
        tableModel.addColumn("Datum");
        tableModel.addColumn("Rata");

        table.setModel(tableModel);
        this.add(scrollPane);
    }

    private void updateTable(){
        tableModel.setRowCount(0);
        for(Placanje p : placanja) {
            String nacin = p.getNacinPlacanja() == NacinPlacanja.GOTOVINA ? "Gotovina" : "Kartica";
            String svrha = p.getSvrhaPlacanja() == SvrhaPlacanja.SEANSA ? "Seansa" : "Test";
            String rata = p.getRata() == 0 ? "Ne" : p.getRata() + "";
            tableModel.addRow(new Object[] { p.getId(), p.getSeansa(), p.getIznos(), p.getIznosSaProvizijom(), p.getValuta().toString(), nacin, svrha, p.getDatum().toString(), rata });
        }
        table.repaint();
    }
}
