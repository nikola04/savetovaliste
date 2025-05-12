package savetovaliste.controller.btnactions;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class BtnRenderer extends JButton implements TableCellRenderer {
        String text;
        public BtnRenderer(String text) {
            setOpaque(true);
            this.text=text;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setText(text);
            return this;
        }

}
