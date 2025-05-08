package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.DBUtil;
import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Klijent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.SQLException;

public class UplateKlijenta extends JFrame {
    private static UplateKlijenta instance;
    private JPanel contentPane;
    private JLabel lblUplate;
    private int id_klijenta;
    Klijent klijent;
    UplateKlijenta(int id_klijent) {
        super("Uplate Klijenta");
        try {
            JDBCUtils.getKlijent(id_klijent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.id_klijenta = id_klijent;
    }
    public static UplateKlijenta getInstance(int id_klijenta) {
        if(instance == null) {
            instance = new UplateKlijenta(id_klijenta);
            instance.initialize();
        }
        return instance;
    }
    private void initialize() {
        this.setSize(500,380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        lblUplate = new JLabel("Uplate Klijenta");
        contentPane.add(lblUplate);

    }
}
