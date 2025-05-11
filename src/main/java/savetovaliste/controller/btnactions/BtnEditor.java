package savetovaliste.controller.btnactions;

import savetovaliste.gui.view.psihoterapeut.DugovanjeKlijenta;
import savetovaliste.gui.view.psihoterapeut.UplateKlijenta;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnEditor extends AbstractCellEditor implements TableCellEditor , ActionListener {
private JButton button;
private String label;
private JTable table;

public BtnEditor(JTable table, String label) {
    this.table = table;
    this.button = new JButton();
    this.button.addActionListener(this);
    this.label = label;
}

@Override
public Component getTableCellEditorComponent(JTable table, Object value,
                                             boolean isSelected, int row, int column) {
    button.setText(label);
    return button;
}

@Override
public Object getCellEditorValue() {
    return label;
}

@Override
public void actionPerformed(ActionEvent e) {
    int row = table.getSelectedRow();
    int klijentId = (int) table.getValueAt(row, 0);  // pretpostavljam ID je u prvoj koloni
    if (label.equals("Uplate")) {
        UplateKlijenta.getInstance(klijentId);
        UplateKlijenta.getInstance(klijentId).setVisible(true);

    } else if (label.equals("Dugovanja")) {
        DugovanjeKlijenta.getInstance(klijentId);
        DugovanjeKlijenta.getInstance(klijentId).setVisible(true);
    }
    fireEditingStopped();
}
}

