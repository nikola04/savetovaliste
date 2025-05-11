package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Beleske;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class BeleskeScreen extends JFrame {
    private JPanel contentPane;
    private JLabel lblBeleske;
    private static BeleskeScreen instance;
    private Seansa seansa;
    private JTable table;
    private DefaultTableModel tableModel;
    private ScrollPane scrollPane;
    private ArrayList<Beleske> beleske;

    public static BeleskeScreen getInstance(Seansa seansa) {
        if(instance == null){
            instance = new BeleskeScreen();
        }
        instance.seansa= seansa;
        instance.init();
        return instance;
    }

    private void init() {
        this.setTitle("Beleske");
        this.setSize(700, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        this.setContentPane(contentPane);
        lblBeleske = new JLabel("Beleske sa  Seanse: " + seansa.getId());
        contentPane.add(lblBeleske, BorderLayout.NORTH);
        table = new JTable();
        scrollPane = new ScrollPane();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        tableModel.addColumn("ID Beleske");
        tableModel.addColumn("Tekst beleske");
        scrollPane.add(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        try {
            beleske = JDBCUtils.getBeleske(seansa);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableModel.setRowCount(0);
        for(Beleske beleske : beleske){
            int belskeId = beleske.getId();
            String text = beleske.getText();
            tableModel.addRow(new Object[]{belskeId,text});
        }
    }
}
