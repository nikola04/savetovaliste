package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.ObjavljivanjePodataka;
import savetovaliste.model.RazlogObjave;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ObjPodaciScreen extends JFrame {
    private JPanel contentPane;
    private static ObjPodaciScreen instance;
    private int seansaId;
    private ObjavljivanjePodataka objPodataka;
    private JLabel lvlNisuObjavljeni;
    private JButton btnObjavi;
    private JLabel lblObjID;
    private JLabel lblRazlog;
    private JLabel lblDatum;
    private JTextField txtKome;
    private JComboBox<RazlogObjave> comboBox;
    private JTextArea txtAKome;
    public static ObjPodaciScreen getInstance(int seansaId) {
        if (instance == null) {
            instance = new ObjPodaciScreen();
        }
        instance.seansaId = seansaId;
        instance.init();
        instance.pack();
        return instance;
    }

    private void init() {
        this.setSize(800,600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(5, 5));
        setContentPane(contentPane);
        try {
            objPodataka = JDBCUtils.getObjPodatke(seansaId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (objPodataka == null) {
            btnObjavi = new JButton("Objavi Podatke");
            lvlNisuObjavljeni = new JLabel("Podaci Nisu Objavljeni");
            comboBox = new JComboBox<>(RazlogObjave.values());
            txtKome = new JTextField();
            txtKome.setPreferredSize(new Dimension(100, 25));
            contentPane.add(lvlNisuObjavljeni,BorderLayout.NORTH);
            contentPane.add(btnObjavi,BorderLayout.SOUTH);
            contentPane.add(comboBox,BorderLayout.CENTER);
            contentPane.add(txtKome,BorderLayout.WEST);
            btnObjavi.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    LocalDate ld = LocalDate.now();
                    Date date = Date.valueOf(ld);
                    try {
                        JDBCUtils.setObjPodataka(seansaId,(RazlogObjave)comboBox.getSelectedItem(), date,txtKome.getText());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    init();
                }
            });
        }else{
            lblObjID = new JLabel("ID Objavljivanja: "+ objPodataka.getId());
            lblRazlog = new JLabel("Razlog: " + objPodataka.getRazlog());
            lblDatum = new JLabel("Datum: " + objPodataka.getDatum());
            txtAKome = new JTextArea();
            txtAKome.setEditable(false);
            txtAKome.setText(objPodataka.getKome());
            contentPane.add(lblObjID, BorderLayout.NORTH);
            contentPane.add(lblRazlog,BorderLayout.WEST);
            contentPane.add(txtAKome, BorderLayout.CENTER);
            contentPane.add(lblDatum,BorderLayout.SOUTH);
        }
        pack();

    }
}
