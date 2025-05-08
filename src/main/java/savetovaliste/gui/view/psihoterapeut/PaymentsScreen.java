package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.Session;
import savetovaliste.controller.observer.ISubscriber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PaymentsScreen  extends JPanel implements ISubscriber {
    private static PaymentsScreen instance;
    private JLabel lblHeading;
    private JTable klijentiTable;
    private JScrollPane scrollPane;
    private DefaultTableModel modelKlijenti;
    private JButton btnUplate;
    private JButton btnDugovanja;
    private JTable uplatedugovanjaTable;
    private DefaultTableModel modelUplateDugovanja;
    private JScrollPane scrollPaneUplateDugovanja;


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
        uplatedugovanjaTable = new JTable();
        modelKlijenti = new DefaultTableModel();
        modelUplateDugovanja = new DefaultTableModel();
        scrollPaneUplateDugovanja = new JScrollPane(uplatedugovanjaTable);
        btnUplate = new JButton("Uplate Klijenta");
        btnDugovanja = new JButton("Dugovanja Klijenta");
        modelKlijenti.addColumn("ID Klijenta");
        modelKlijenti.addColumn("Ime");
        modelKlijenti.addColumn("Prezime");
        modelKlijenti.addColumn("Jmbg");
        modelKlijenti.addColumn("Email");
        modelKlijenti.addColumn("Telefon");
        modelKlijenti.addColumn("Datum rodjenja");
        modelKlijenti.addColumn("Ranije terapije");
        modelUplateDugovanja.addColumn("Datum");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.add(lblHeading);
        this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(10));
        this.add(btnUplate);
        this.add(Box.createVerticalStrut(10));
        this.add(btnDugovanja);
        this.add(Box.createVerticalStrut(10));
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(scrollPaneUplateDugovanja);

        setTables();

    }

    private void setTables() {
    }

    @Override
    public void update(Object value) {

    }
}
