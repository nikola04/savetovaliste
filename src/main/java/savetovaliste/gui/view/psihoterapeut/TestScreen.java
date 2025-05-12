package savetovaliste.gui.view.psihoterapeut;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TestScreen extends JFrame {
    private JPanel contentPane;
    private JLabel lblTestiranje;
    private JTable tblTestovi;
    private JScrollPane scrollTestovi;
    private DefaultTableModel tableModel;
    private static TestScreen instance = null;
    public static TestScreen getInstance() {
        if(instance == null){
            instance = new TestScreen();
        }
        instance.init();
        return instance;
    }

    private void init() {
        this.setTitle("Testovi");
        this.setSize(800,600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(10, 10));
        this.setContentPane(contentPane);
        lblTestiranje = new JLabel("Testovi");
        contentPane.add(lblTestiranje, BorderLayout.NORTH);
        DefaultTableModel model = new DefaultTableModel();
        scrollTestovi = new JScrollPane();
        tblTestovi = new JTable(model);
        scrollTestovi.setViewportView(tblTestovi);
        scrollTestovi.getViewport().setBackground(Color.LIGHT_GRAY);
        contentPane.add(scrollTestovi, BorderLayout.CENTER);


    }
}
