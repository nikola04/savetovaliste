package savetovaliste.gui.view.forms;

import savetovaliste.db.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PsihoInfoForm {
    private JButton refreshBtn;
    private JPanel panel1;
    private JTable psihoTable;

    public PsihoInfoForm() {
        loadData();

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }
    public void loadData() {
        System.out.println("Konekcija uspešna!");
        try (Connection conn = DBUtil.getConnection()) {
            System.out.println("Konekcija uspešna!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM psihoterapeut");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Ime");
            model.addColumn("Prezime");
            model.addColumn("JMBG");
            model.addColumn("Email");
            model.addColumn("Telefon");
            model.addColumn("Datum Rodjenja");
            while (rs.next()) {
                int id = rs.getInt("psihoterapeut_id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String jmbg = rs.getString("jmbg");
                String email = rs.getString("email");
                String telefon = rs.getString("telefon");
                Date date = rs.getDate("datum_rodjenja");

                model.addRow(new Object[]{id, ime, prezime, jmbg,email, telefon, date});
            }
            psihoTable.setModel(model);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
