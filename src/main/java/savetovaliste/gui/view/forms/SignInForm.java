package savetovaliste.gui.view.forms;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import savetovaliste.data.models.Struka;
import savetovaliste.data.utility.JDBCUtils;
import savetovaliste.gui.view.DatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class SignInForm extends JFrame {
    private JPanel contentPane;
    private JLabel lblIme;
    private JLabel lblPrezime;
    private JLabel lblJmbg;
    private JLabel lblEmail;
    private JLabel lblTelefon;
    private JLabel lblDatumRodjenja;
    private JLabel lblBrojSertifikata;
    private JLabel lblStruka;
    private TextField txtIme;
    private TextField txtPrezime;
    private TextField txtJmbg;
    private TextField txtEmail;
    private TextField txtTelefon;
    private JButton btnDate;
    private TextField brojSertifikata;
    private JComboBox cbStruka;
    private JButton btn1;

    public SignInForm() {
        setTitle("Sign In");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(400,550);
        setVisible(true);
        try {
            init();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init() throws SQLException {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        btn1 = new JButton("Sign in");
        lblIme = new JLabel("Ime");
        lblPrezime = new JLabel("Prezime");
        lblJmbg = new JLabel("Jmbg");
        lblEmail = new JLabel("Email");
        lblTelefon = new JLabel("Telefon");
        lblDatumRodjenja = new JLabel("Datum Rodjenja");
        lblBrojSertifikata = new JLabel("Broj Sertifikata");
        lblStruka = new JLabel("Struka");
        txtIme = new TextField();
        txtPrezime = new TextField();
        txtJmbg = new TextField();
        txtEmail = new TextField();
        txtTelefon = new TextField();
        btnDate = new JButton("Izaberi datum rodjenja");



        brojSertifikata = new TextField();
        ArrayList<Struka> struke = JDBCUtils.SveStruke();
        cbStruka = new JComboBox(struke.toArray());

        Properties pr = new Properties();
        pr.put("text.today", "Danas");
        pr.put("text.month", "Mesec");
        pr.put("text.year", "Godina");

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, pr);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        contentPane.add(lblIme);
        contentPane.add(txtIme);
        contentPane.add(lblPrezime);
        contentPane.add(txtPrezime);
        contentPane.add(lblJmbg);
        contentPane.add(txtJmbg);
        contentPane.add(lblEmail);
        contentPane.add(txtEmail);
        contentPane.add(lblTelefon);
        contentPane.add(txtTelefon);
        contentPane.add(lblDatumRodjenja);
        contentPane.add(datePicker);
        contentPane.add(lblBrojSertifikata);
        contentPane.add(brojSertifikata);
        contentPane.add(lblStruka);
        contentPane.add(cbStruka);
        contentPane.add(btn1);


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Struka selektovana = (Struka) cbStruka.getSelectedItem();
                System.out.println(selektovana.getId());
                System.out.println(selektovana.getNaziv());

                Date selectedDate = (Date) datePicker.getModel().getValue();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String mysqlDate = sdf.format(selectedDate);

                System.out.println(mysqlDate);
            }
        });

    }
}
