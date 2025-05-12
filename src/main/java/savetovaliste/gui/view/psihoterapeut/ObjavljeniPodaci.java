package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.ObjavljenKome;
import savetovaliste.model.ObjavljeniPodatak;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ObjavljeniPodaci extends JPanel {
    private static ObjavljeniPodaci instance;
    private Seansa seansa;

    private JComboBox<String> dropdownKome;
    private JTextField razlogField;
    private JButton objaviButton;
    private DefaultTableModel tableModel;

    private ObjavljeniPodaci() {}
    public static ObjavljeniPodaci getInstance(Seansa seansa) {
        if(instance == null) {
            instance = new ObjavljeniPodaci();
            instance.initialize();
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
        verticalPanel.setBorder(new EmptyBorder(30, 50, 40, 50));

        JLabel lblRazlog = new JLabel("Razlog:");
        razlogField = new JTextField(30);
        razlogField.setMaximumSize(new Dimension(Integer.MAX_VALUE, razlogField.getPreferredSize().height));
        lblRazlog.setLabelFor(razlogField);
        verticalPanel.add(lblRazlog);
        verticalPanel.add(razlogField);
        verticalPanel.add(Box.createVerticalStrut(10));

        JPanel formRow = new JPanel();
        formRow.setLayout(new BoxLayout(formRow, BoxLayout.X_AXIS));

        dropdownKome = new JComboBox<>();
        objaviButton = new JButton("Objavi podatke");

        formRow.add(dropdownKome);
        formRow.add(Box.createHorizontalStrut(10));
        formRow.add(objaviButton);

        verticalPanel.add(formRow);
        verticalPanel.add(Box.createVerticalStrut(20));

        this.add(verticalPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Razlog", "Kome", "Datum"}, 0) {
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

    private void fetchData() {
        try {
            ArrayList<ObjavljeniPodatak> data = JDBCUtils.getObjavljeniPodaci(seansa);
            tableModel.setRowCount(0);

            dropdownKome.removeAllItems();
            razlogField.setText("");

            boolean imaPoliciju = false;
            boolean imaSud = false;

            for (ObjavljeniPodatak o : data) {
                tableModel.addRow(new Object[]{
                        o.getId(), o.getRazlog(), o.getKome(), o.getDatum()
                });

                if (o.getKome() == ObjavljenKome.POLICIJA) imaPoliciju = true;
                if (o.getKome() == ObjavljenKome.SUD) imaSud = true;
            }

            if (!imaPoliciju) dropdownKome.addItem("policija");
            if (!imaSud) dropdownKome.addItem("sud");

            boolean disableInput = imaPoliciju && imaSud;
            razlogField.setEnabled(!disableInput);
            dropdownKome.setEnabled(!disableInput);
            objaviButton.setEnabled(!disableInput);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerControllers() {
        objaviButton.addActionListener(e -> {
            if(razlogField.getText().isEmpty() || dropdownKome.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Morate uneti razlog i izabrati kome objavljujete!", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String razlog = razlogField.getText();
            String kome = dropdownKome.getSelectedItem().toString();
            LocalDate ld = LocalDate.now();
            java.sql.Date date = Date.valueOf(ld);
            try {
                JDBCUtils.objaviPodatake(seansa.getId(), razlog, kome, date);
                JOptionPane.showMessageDialog(this, "Podaci su objavljeni!", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                razlogField.setText("");
                dropdownKome.setEnabled(true);
                objaviButton.setEnabled(true);
                fetchData();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Greska prilikom objave podataka!", "Greska", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}
