package savetovaliste.controller.btnactions;

import savetovaliste.gui.view.psihoterapeut.DugovanjeKlijenta;
import savetovaliste.gui.view.psihoterapeut.SeansaScreen;
import savetovaliste.gui.view.psihoterapeut.UplateKlijenta;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnEditor extends AbstractCellEditor implements TableCellEditor , ActionListener {
private final JButton button;
private final String label;
private final JTable table;

public BtnEditor(JTable table, String label) {
    this.table = table;
    this.button = new JButton();
    button.setText(label);
    this.button.addActionListener(this);
    this.label = label;
}

@Override
public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) {
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
    int klijentSeansaId = (int) table.getValueAt(row, 0);
    switch (label) {
        case "Uplate" -> {
            UplateKlijenta.getInstance(klijentSeansaId);
            UplateKlijenta.getInstance(klijentSeansaId).setVisible(true);
        }
        case "Dugovanja" -> {
            DugovanjeKlijenta.getInstance(klijentSeansaId);
            DugovanjeKlijenta.getInstance(klijentSeansaId).setVisible(true);
        }
        case "Vise" -> {
            SeansaScreen.getInstance(klijentSeansaId);
            SeansaScreen.getInstance(klijentSeansaId).setVisible(true);
        }
    }
    fireEditingStopped();
}
}

