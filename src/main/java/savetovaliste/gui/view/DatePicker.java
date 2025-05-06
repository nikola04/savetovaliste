package savetovaliste.gui.view;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.util.Properties;

public class DatePicker {

        public DatePicker() {
            JFrame frame = new JFrame("Izaberi datum");
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Podešavanja za tekst u DatePicker-u
            Properties p = new Properties();
            p.put("text.today", "Danas");
            p.put("text.month", "Mesec");
            p.put("text.year", "Godina");

            // Model za datum
            UtilDateModel model = new UtilDateModel();
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

            // Formatiranje prikaza
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

            // Dodaj u GUI
            frame.getContentPane().add(datePicker);
            frame.setVisible(true);

            // Možeš dohvatiti izabrani datum ovako:
            // Date selectedDate = (Date) datePicker.getModel().getValue();
        }

}
