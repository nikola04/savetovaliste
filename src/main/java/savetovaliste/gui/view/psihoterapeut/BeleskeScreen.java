package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Beleske;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class BeleskeScreen extends JPanel {
    private static BeleskeScreen instance;
    private Seansa seansa;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnDodaj;
    private JButton btnObrisi;

    private BeleskeScreen() {
    }
    public static BeleskeScreen getInstance(Seansa seansa) {
        if(instance == null){
            instance = new BeleskeScreen();
            instance.initialize();
            instance.initializeControllers();
        }
        instance.seansa = seansa;
        instance.fetchData();
        return instance;
    }

    private void initialize() {
        this.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{ "ID", "Tekst" }, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.add(scrollPane, BorderLayout.CENTER);

        JPanel verticalPanel = new JPanel();
        verticalPanel.setBorder(new EmptyBorder(20, 10, 35, 10));
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.X_AXIS));

        btnDodaj = new JButton("Dodaj belesku");
        btnObrisi = new JButton("Obrisi belesku");
        verticalPanel.add(btnDodaj);
        verticalPanel.add(btnObrisi);

        this.add(verticalPanel, BorderLayout.SOUTH);
    }

    private void fetchData(){
        try {
            ArrayList<Beleske> beleske = JDBCUtils.getBeleske(seansa);
            tableModel.setRowCount(0);

            for(Beleske beleska : beleske){
                int belskeId = beleska.getId();
                String text = beleska.getText();
                tableModel.addRow(new Object[]{belskeId,text});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   private void initializeControllers(){
        btnDodaj.addActionListener(e -> {
            String newText = JOptionPane.showInputDialog("Unesite tekst nove beleške:");
            if (newText != null && !newText.trim().isEmpty()) {
                try {
                    JDBCUtils.addBeleske(newText,seansa.getId());
                    fetchData();
                } catch (SQLException ex) {
                    if(ex.getMessage().equals("Seansa nije u toku ili nije dostupna za belešku.")){
                        JOptionPane.showMessageDialog(null, "Seansa nije u toku", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Desila se greska. Molimo vas pokusajte ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        btnObrisi.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int beleskaId = (int) tableModel.getValueAt(selectedRow, 0);
                try {
                    JDBCUtils.removeBeleske(beleskaId);
                    fetchData();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Desila se greska. Molimo vas pokusajte ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Izaberite belešku za brisanje.", "Greška", JOptionPane.WARNING_MESSAGE);
            }
        });
        tableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();

                if (col != 1)
                    return;

                int beleskaId = (int) tableModel.getValueAt(row, 0);
                String noviTekst = (String) tableModel.getValueAt(row, 1);

                if (JOptionPane.showConfirmDialog(null, "Da li želite da sačuvate izmene u belešci?", "Potvrda izmene", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        JDBCUtils.updateBeleska(beleskaId, noviTekst);
                        JOptionPane.showMessageDialog(null, "Uspešno izmenjena beleška.");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Greška prilikom izmene beleške.");
                        ex.printStackTrace();
                        return;
                    }
                }
                fetchData();
            }
        });
    }
}
