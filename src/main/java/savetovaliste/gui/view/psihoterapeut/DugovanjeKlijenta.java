package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Klijent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class DugovanjeKlijenta extends JFrame {
    private static DugovanjeKlijenta instance;
    private JPanel contentPane;
    private JLabel lblDugovanje;
    private int klijent_id;
    private Klijent klijent;

    private DugovanjeKlijenta() {
        super("Dugovanja Klijenta");
    }

    public static DugovanjeKlijenta getInstance(int klijent_id) {
        if (instance == null) {
            instance = new DugovanjeKlijenta();
            instance.initialize();
            //instance.initializeGUI();
            //instance.fetchData();

        }
        instance.klijent_id = klijent_id;
        instance.fetchData();
        return instance;

    }

    private void fetchData() {
        try {
            klijent = JDBCUtils.getKlijent(klijent_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initialize() {
        this.setSize(500,380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        lblDugovanje = new JLabel("Dugovanja Klijenta");
        contentPane.add(lblDugovanje);

    }
}
