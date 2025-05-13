package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class DugovanjeKlijenta extends JFrame {
    private static DugovanjeKlijenta instance;
    private int klijent_id;
    private ArrayList<Neplaceno> neplaceno = new ArrayList<>();

    private JTable table;
    private DefaultTableModel tableModel;

    private DugovanjeKlijenta() {
        super("Dugovanja Klijenta");
    }

    public static DugovanjeKlijenta getInstance(int klijent_id) {
        if (instance == null) {
            instance = new DugovanjeKlijenta();
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
            neplaceno = JDBCUtils.getNeplaceno(klijent);
            this.updateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        this.setSize(500,380);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        JLabel lblDugovanje = new JLabel("Dugovanja Klijenta");
        contentPane.add(lblDugovanje);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Tip");
        tableModel.addColumn("Iznos");
        tableModel.addColumn("Nedostaje");
        tableModel.addColumn("Placeno");
        tableModel.addColumn("Rata");

        table.setModel(tableModel);
        this.add(scrollPane);
    }

    private void updateTable(){
        tableModel.setRowCount(0);
        for(Neplaceno n : neplaceno) {
            String rate;
            if(n.isNaRate() && n.isIstekaoRok())
                rate = "Istekao rok";
            else if (!n.isNaRate()) {
                rate = "Nema";
            } else rate = "Nema rate";

            tableModel.addRow(new Object[] { n.getId(), n.getTip(), n.getIznos(), n.getNedostaje(), n.isPlaceno() ? "Da" : "Ne", rate });
        }
        table.repaint();
    }
}
