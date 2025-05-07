package savetovaliste.gui.view.forms;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import savetovaliste.Session;
import savetovaliste.model.Psihoterapeut;
import savetovaliste.model.Struka;
import savetovaliste.data.utility.JDBCUtils;

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

public class SignUpForm extends JFrame {
    private JPanel contentPane;
    private JLabel lblHeading;
    private JLabel lblIme;
    private JLabel lblPrezime;
    private JLabel lblJmbg;
    private JLabel lblEmail;
    private JLabel lblTelefon;
    private JLabel lblDatumRodjenja;
    private JLabel lblBrojSertifikata;
    private JLabel lblStruka;
    private JTextField txtIme;
    private JTextField txtPrezime;
    private JTextField txtJmbg;
    private JTextField txtEmail;
    private JTextField txtTelefon;
    JDatePickerImpl datePicker;
    private JTextField txtbrojSertifikata;
    private JComboBox cbStruka;
    private JButton btn1;

    public SignUpForm() {
        setTitle("Sign In");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(400,550);
        setVisible(true);
        try {
            init();
            initControllers();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init() throws SQLException {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        lblHeading = new JLabel("Registracija Psihoterapeuta");
        lblHeading.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeading.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblHeading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        lblIme = new JLabel("Ime");
        lblPrezime = new JLabel("Prezime");
        lblJmbg = new JLabel("Jmbg");
        lblEmail = new JLabel("Email");
        lblTelefon = new JLabel("Telefon");
        lblDatumRodjenja = new JLabel("Datum Rodjenja");
        lblBrojSertifikata = new JLabel("Broj Sertifikata");
        lblStruka = new JLabel("Struka");

        txtIme = new JTextField();
        txtPrezime = new JTextField();
        txtJmbg = new JTextField();
        txtEmail = new JTextField();
        txtTelefon = new JTextField();
        txtbrojSertifikata = new JTextField();

        btn1 = new JButton("Sign up");

        Properties pr = new Properties();
        pr.put("text.today", "Danas");
        pr.put("text.month", "Mesec");
        pr.put("text.year", "Godina");
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, pr);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        ArrayList<Struka> struke = JDBCUtils.SveStruke();
        cbStruka = new JComboBox(struke.toArray());

        int row = 0;
        Component[][] fields = {
                {lblIme, txtIme},
                {lblPrezime, txtPrezime},
                {lblJmbg, txtJmbg},
                {lblEmail, txtEmail},
                {lblTelefon, txtTelefon},
                {lblDatumRodjenja, datePicker},
                {lblBrojSertifikata, txtbrojSertifikata},
                {lblStruka, cbStruka}
        };

        for (Component[] pair : fields) {
            gbc.gridx = 0; gbc.gridy = row++;
            formPanel.add(pair[0], gbc);

            gbc.gridx = 0; gbc.gridy = row++;
            formPanel.add(pair[1], gbc);
        }

        gbc.gridx = 0; gbc.gridy = row++;
        gbc.insets = new Insets(15, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btn1, gbc);

        contentPane.add(formPanel, BorderLayout.CENTER);
    }

    private void initControllers() {
        btn1.addActionListener(_ -> {
            Struka selektovanaStruka = (Struka) cbStruka.getSelectedItem();
            if(selektovanaStruka == null) {
                JOptionPane.showMessageDialog(null, "Morate izabrati struku.", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int strukaId = selektovanaStruka.getId();
            String ime = txtIme.getText();
            String prezime = txtPrezime.getText();
            String jmbg = txtJmbg.getText();
            String email = txtEmail.getText();
            String telefon = txtTelefon.getText();
            Date selectedDate = (Date) datePicker.getModel().getValue();
            java.sql.Date mysqlDate = new java.sql.Date(selectedDate.getTime());
            int brojSertifikata = Integer.parseInt(txtbrojSertifikata.getText());

            System.out.println(mysqlDate);

            try {
                int success = JDBCUtils.RegisterPsihoterapeut(ime,prezime,jmbg,email,telefon,mysqlDate,brojSertifikata,strukaId);
                if(success == 0) {
                    JOptionPane.showMessageDialog(null, "Uspesna registracija.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    Session.getInstance().logoutUser();
                    new LogInFrom();
                    return;
                }
                if(success == -1) {
                    JOptionPane.showMessageDialog(null, "JMBG je vec registrovan.", "Psihoterapeut je vec registrovan", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(success == -2) {
                    JOptionPane.showMessageDialog(null, "Email adresa je vec registrovan.", "Psihoterapeut je vec registrovan", JOptionPane.ERROR_MESSAGE);
                }
                if(success == -3) {
                    JOptionPane.showMessageDialog(null, "Broj sertifikata je vec registrovan.", "Broj sertifikata je vec registrovan", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Desila se greska prilikom unosa. Molimo vas pokusajte ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
