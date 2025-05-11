package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;

import javax.swing.*;

public class BeleskeScreen extends JFrame {
    private JPanel contentPane;
    private JLabel lblBeleske;
    private static BeleskeScreen instance;
    private int seansaId;

    public static BeleskeScreen getInstance(int seansaId){
        if(instance == null){
            instance = new BeleskeScreen();
        }
        instance.init();
        instance.seansaId = seansaId;
        return instance;
    }

    private void init() {
        this.setTitle("Beleske");
        this.setSize(800, 600);
        contentPane = new JPanel();
        this.setContentPane(contentPane);
        lblBeleske = new JLabel("Beleske sa Seanse");
        contentPane.add(lblBeleske);
        //JDBCUtils.getBeleske(seansaId);
    }
}
