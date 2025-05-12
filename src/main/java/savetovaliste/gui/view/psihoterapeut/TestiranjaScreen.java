package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Seansa;
import savetovaliste.model.Test;
import savetovaliste.model.Testiranje;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestiranjaScreen extends JPanel {
    private static TestiranjaScreen instance = null;
    private Seansa seansa;

    private JSpinner spnRezultat;
    private JComboBox<Test> dropdownTest;
    private JButton btnDodaj;
    private DefaultTableModel tableModel;

    private TestiranjaScreen() {
    }
    public static TestiranjaScreen getInstance(Seansa seansa) {
        if(instance == null){
            instance = new TestiranjaScreen();
            instance.initialize();
            instance.fetchTests();
            instance.registerControllers();
        }
        instance.seansa = seansa;
        instance.fetchData();
        return instance;
    }

    private void initialize() {
        this.setLayout(new BorderLayout());

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setBorder(new EmptyBorder(30, 30, 50, 30));

        JPanel formGrid = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 5, 0, 5);


        JLabel lblTest = new JLabel("Test:");
        JLabel lblRezultat = new JLabel("Rezultat:");
        dropdownTest = new JComboBox<Test>();
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(50, 0, 100, 1);
        spnRezultat = new JSpinner(spinnerModel);
        btnDodaj = new JButton("Dodaj");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formGrid.add(lblTest, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formGrid.add(lblRezultat, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formGrid.add(dropdownTest, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formGrid.add(spnRezultat, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formGrid.add(btnDodaj, gbc);

        verticalPanel.add(formGrid);
        this.add(verticalPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Test", "Oblast", "Cena", "Rezultat", "Placeno"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void fetchTests(){
        try {
            ArrayList<Test> tests = JDBCUtils.getTestovi();
            dropdownTest.removeAllItems();
            for(Test test : tests){
                dropdownTest.addItem(test);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fetchData() {
        try {
            tableModel.setRowCount(0);
            ArrayList<Testiranje> testiranja = JDBCUtils.getTestiranja(seansa);

            for(Testiranje testiranje : testiranja){
                Test test = testiranje.getTest();
                String spnRezultat = String.format("%5.1f%%", testiranje.getRezultat() * 100);
                tableModel.addRow(new Object[]{ testiranje.getId(), test.getNaziv(), test.getOblast(), test.getCena(), spnRezultat, testiranje.isPlaceno() ? "DA" : "NE" });
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Greska prilikom prikaza testiranja!", "Greska", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void registerControllers() {
        btnDodaj.addActionListener(e -> {
            if(dropdownTest.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Morate izabrati test!", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Test test = (Test) dropdownTest.getSelectedItem();
            double rezultat = (double) ((int) spnRezultat.getValue()) / 100;
            try {
                JDBCUtils.addTestiranje(test.getId(), rezultat, seansa.getId());
                fetchData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Greska prilikom dodavanja testiranja!", "Greska", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}
