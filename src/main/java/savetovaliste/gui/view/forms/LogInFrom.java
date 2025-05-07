package savetovaliste.gui.view.forms;

import savetovaliste.Session;
import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Psihoterapeut;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LogInFrom extends JFrame {
    private String email;
    private String jmbg;
    private JPanel contentPane;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JButton btn1;
    private JTextField txt1;
    private JTextField txt2;
    public LogInFrom() {
        setTitle("Log In");
        setSize(320,400);
        setVisible(true);
        init();
    }

    private void init() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        Dimension itemSize = new Dimension(Integer.MAX_VALUE, 30); // širina automatska, visina 30

        lbl1 = new JLabel("Prijava Psihoterapeuta");
        lbl1.setFont(new Font("Arial", Font.BOLD, 18));
        lbl1.setHorizontalAlignment(JLabel.CENTER);
        lbl2 = new JLabel("Email");
        lbl3 = new JLabel("JMBG");
        txt1 = new JTextField();
        txt2 = new JTextField();
        btn1 = new JButton("Login");

        setItemHeight(lbl1, itemSize);
        setItemHeight(lbl2, itemSize);
        setItemHeight(txt1, itemSize);
        setItemHeight(lbl3, itemSize);
        setItemHeight(txt2, itemSize);
        setItemHeight(btn1, itemSize);

        contentPane.add(lbl1);
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(lbl2);
        contentPane.add(txt1);
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(lbl3);
        contentPane.add(txt2);
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(btn1);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(txt1.getText().isEmpty() || txt2.getText().isEmpty()) {
                    return;
                }
                email = txt1.getText();
                jmbg = txt2.getText();

                if(Session.getInstance().getUserId() != -1) {
                    JOptionPane.showMessageDialog(null, "Vec ste prijavljeni");
                    return;
                }
                try {
                    Psihoterapeut psihoterapeut = JDBCUtils.LoginPsihoterapeut(email, jmbg);
                    if(psihoterapeut == null) {
                        JOptionPane.showMessageDialog(null, "Korisnik nije pronađen.", "Greska", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Session.getInstance().loginUser(psihoterapeut);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    private void setItemHeight(JComponent comp, Dimension size) {
        comp.setMaximumSize(size);
        comp.setPreferredSize(size);
        comp.setMinimumSize(size);
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

}
