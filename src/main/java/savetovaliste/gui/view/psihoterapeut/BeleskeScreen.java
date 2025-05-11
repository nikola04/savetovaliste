package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Seansa;

import javax.swing.*;
import java.sql.SQLException;

public class BeleskeScreen extends JFrame {
    private JPanel contentPane;
    private JLabel lblBeleske;
    private static BeleskeScreen instance;
    private Seansa seansa;

    public static BeleskeScreen getInstance(Seansa seansa) {
        if(instance == null){
            instance = new BeleskeScreen();
        }
        instance.init();
        instance.seansa= seansa;
        return instance;
    }

    private void init() {
        this.setTitle("Beleske");
        this.setSize(800, 600);
        contentPane = new JPanel();
        this.setContentPane(contentPane);
        lblBeleske = new JLabel("Beleske sa Seanse");
        contentPane.add(lblBeleske);
        try {
            JDBCUtils.getBeleske(seansa);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
