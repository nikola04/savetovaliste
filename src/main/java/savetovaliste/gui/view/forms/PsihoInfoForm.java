package savetovaliste.gui.view.forms;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Psihoterapeut;
import savetovaliste.model.SertifikovaniPsihoterapeut;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

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
        ArrayList<Psihoterapeut> psihoterapeuts = new ArrayList<>();
        try {
            psihoterapeuts.addAll(JDBCUtils.getPsihoterapeuts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Ime");
        model.addColumn("Prezime");
        model.addColumn("JMBG");
        model.addColumn("Email");
        model.addColumn("Telefon");
        model.addColumn("Datum Rodjenja");
        model.addColumn("Sertifikovan");
        for (Psihoterapeut p : psihoterapeuts) {
            int id = p.getId();
            String ime = p.getIme();
            String prezime = p.getPrezime();
            String jmbg = p.getJmbg();
            String email = p.getEmail();
            String telefon = p.getTelefon();
            java.util.Date date = p.getDatumRodjenja();
            String sertifikovan = (p instanceof SertifikovaniPsihoterapeut) ? "DA" : "NE";

            model.addRow(new Object[]{id, ime, prezime, jmbg,email, telefon, date, sertifikovan});
        }
        psihoTable.setModel(model);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
}
