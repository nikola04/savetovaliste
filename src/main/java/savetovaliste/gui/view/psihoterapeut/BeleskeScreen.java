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

public class BeleskeScreen extends JFrame {
    private JPanel contentPane;
    private JLabel lblBeleske;
    private static BeleskeScreen instance;
    private Seansa seansa;
    private JTable table;
    private DefaultTableModel tableModel;
    private ScrollPane scrollPane;
    private ArrayList<Beleske> beleske;
    private JButton btnDodaj;
    private JButton btnObrisi;
    JPanel verticalPanel;

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
        btnDodaj = new JButton("Dodaj belesku");
        btnObrisi = new JButton("Obrisi belesku");
        contentPane.add(lblBeleske, BorderLayout.NORTH);
        table = new JTable();
        scrollPane = new ScrollPane();
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        table.setModel(tableModel);
        tableModel.addColumn("ID Beleske");
        tableModel.addColumn("Tekst beleske");
        scrollPane.add(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.X_AXIS));
        verticalPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        verticalPanel.add(btnDodaj);
        verticalPanel.add(btnObrisi);
        contentPane.add(verticalPanel, BorderLayout.SOUTH);
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
        btnDodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newText = JOptionPane.showInputDialog("Unesite tekst nove beleške:");
                if (newText != null && !newText.trim().isEmpty()) {
                    // Poziv funkcije za dodavanje beleške u bazu
                    try {
                        JDBCUtils.addBeleske(newText,seansa.getId());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    init();
                }
            }
        });
        btnObrisi.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int beleskaId = (int) tableModel.getValueAt(selectedRow, 0);
                    try {
                        JDBCUtils.removeBeleske(beleskaId);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    init();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Izaberite belešku za brisanje.", "Greška", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        tableModel.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int col = e.getColumn();

                    // Ako se menja tekst (npr. kolona 1)
                    if (col == 1) {
                        int beleskaId = (int) tableModel.getValueAt(row, 0);
                        String noviTekst = (String) tableModel.getValueAt(row, 1);

                        int izbor = JOptionPane.showConfirmDialog(
                                null,
                                "Da li želite da sačuvate izmene u belešci?",
                                "Potvrda izmene",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (izbor == JOptionPane.YES_OPTION) {
                            try {
                                JDBCUtils.updateBeleska(beleskaId, noviTekst);
                                JOptionPane.showMessageDialog(null, "Uspešno izmenjena beleška.");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Greška prilikom izmene beleške.");
                            }
                        }else {
                            init();
                        }

                    }
                }
            }
        });
    }
}
