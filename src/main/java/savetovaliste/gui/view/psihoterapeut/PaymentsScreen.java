package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.btnactions.BtnEditor;
import savetovaliste.controller.btnactions.BtnRenderer;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Klijent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentsScreen  extends JPanel implements ISubscriber {
    private static PaymentsScreen instance;
    private JTable klijentiTable;
    private DefaultTableModel modelKlijenti;

    public static PaymentsScreen getInstance(){
        if(instance == null) {
            instance = new PaymentsScreen();
            instance.initialize();
        }
        instance.setTables();
        return instance;
    }

    private void initialize() {
        Session.getInstance().addSubscriber(this);
        JLabel lblHeading = new JLabel("Uplate i Dugovanja Klijenata");
        klijentiTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(klijentiTable);
        scrollPane.setBorder(new EmptyBorder(10, 20, 10, 20));
        modelKlijenti = new DefaultTableModel();

        modelKlijenti.addColumn("ID Klijenta");
        modelKlijenti.addColumn("Ime");
        modelKlijenti.addColumn("Prezime");
        modelKlijenti.addColumn("Email");
        modelKlijenti.addColumn("Telefon");
        modelKlijenti.addColumn("Datum rodjenja");
        modelKlijenti.addColumn("Ranije terapije");
        modelKlijenti.addColumn("Uplate");
        modelKlijenti.addColumn("Dugovanja");
        klijentiTable.setModel(modelKlijenti);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createVerticalStrut(10));
        this.add(lblHeading);
        this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(10));

        setTables();
        klijentiTable.getColumn("Uplate").setPreferredWidth(70);
        klijentiTable.getColumn("Dugovanja").setPreferredWidth(90);
        klijentiTable.getColumn("Uplate").setCellRenderer(new BtnRenderer("Uplate"));
        klijentiTable.getColumn("Dugovanja").setCellRenderer(new BtnRenderer("Dugovanja"));
        klijentiTable.getColumn("Uplate").setCellEditor(new BtnEditor(klijentiTable,"Uplate"));
        klijentiTable.getColumn("Dugovanja").setCellEditor(new BtnEditor(klijentiTable,"Dugovanja"));
    }

    private void setTables() {
        ArrayList<Klijent> klijenti = new ArrayList<>();
        try {
            klijenti.addAll(JDBCUtils.getKlijents(Session.getInstance().getPsihoterapeut()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelKlijenti.setRowCount(0);
        for(Klijent klijent : klijenti) {
            String s = "NE";
            if(klijent.isRanijeTerapije())
                s = "DA";

            modelKlijenti.addRow(new Object[]{klijent.getId(),klijent.getIme(), klijent.getPrezime(), klijent.getEmail(),klijent.getTelefon() ,klijent.getDatumRodjenja(), s,"Uplata","Dug"});
        }
    }

    @Override
    public void update(Object value) {

    }
}
