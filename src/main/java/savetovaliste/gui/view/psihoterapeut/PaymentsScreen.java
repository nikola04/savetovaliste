package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.btnactions.BtnEditor;
import savetovaliste.controller.btnactions.BtnRenderer;
import savetovaliste.controller.observer.ISubscriber;
import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.Klijent;
import savetovaliste.model.Prijava;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentsScreen  extends JPanel implements ISubscriber {
    private static PaymentsScreen instance;
    private JLabel lblHeading;
    private JTable klijentiTable;
    private JScrollPane scrollPane;
    private DefaultTableModel modelKlijenti;

    private JFrame frameUplateKlijenta;
    private JFrame frameDugovanjeKlijenta;

    public static PaymentsScreen getInstance(){
        if(instance == null) {
            instance = new PaymentsScreen();
            instance.initialize();
        }
        return instance;
    }

    private void initialize() {
        Session.getInstance().addSubscriber(this);
        lblHeading = new JLabel("Uplate i Dugovanja Klijenata");
        klijentiTable = new JTable();
        scrollPane = new JScrollPane(klijentiTable);
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


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.add(lblHeading);
        this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(10));

        setTables();
        klijentiTable.getColumn("Uplate").setCellRenderer(new BtnRenderer());
        klijentiTable.getColumn("Dugovanja").setCellRenderer(new BtnRenderer());
        klijentiTable.getColumn("Uplate").setCellEditor(new BtnEditor(klijentiTable,"Uplate"));
        klijentiTable.getColumn("Dugovanja").setCellEditor(new BtnEditor(klijentiTable,"Dugovanja"));
    }

    private void setTables() {
        ArrayList<Klijent> klijenti = new ArrayList<>();
        try {
            klijenti.addAll(JDBCUtils.getKlijents());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Klijent klijent : klijenti) {
            String s = "NE";
            if(klijent.isRanijeTerapije())
                s = "DA";

            modelKlijenti.addRow(new Object[]{klijent.getId(),klijent.getIme(), klijent.getPrezime(), klijent.getEmail(),klijent.getTelefon() ,klijent.getDatumRodjenja(), s,"Uplata","Dug"});
        }
        klijentiTable.setModel(modelKlijenti);
    }

    @Override
    public void update(Object value) {

    }
}
