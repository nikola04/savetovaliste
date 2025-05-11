package savetovaliste.gui.view.psihoterapeut;

import savetovaliste.db.utility.JDBCUtils;
import savetovaliste.model.CenaSeanse;
import savetovaliste.model.Klijent;
import savetovaliste.model.Seansa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class SeansaInfoScreen extends JFrame {
    public static SeansaInfoScreen instance;
    private JPanel contentPane;
    private static int seansaId;
    private Seansa seansa;
    private JLabel lblSeansa;
    private JLabel lblSeansaId;
    private JLabel lblPrva;
    private JLabel lblDan;
    private JLabel lblVreme;
    private JLabel lblVremeTrajanja;
    private JLabel lblNaRate;
    private JLabel lblPlaceno;
    private JLabel cena;
    private JLabel lblCena;
    private JLabel lblKlijentID;
    private JLabel lblKlijentIme;
    private JLabel lblKlijentPrezime;
    private JLabel lblKlijentDatum;
    private JLabel lblKlijentPol;
    private JLabel lblKlijentEmail;
    private JLabel lblKlijentTelefon;
    private JLabel lblklijentRanijeTerapije;
    private JLabel lblKlijentBrojPrijave;
    public static SeansaInfoScreen getInstance(int id){
        if(instance == null){
            instance = new SeansaInfoScreen();
        }

        seansaId = id;
        instance.init();
        instance.fetchData();
        return instance;
    }

    private  void fetchData() {
        try {
            seansa = JDBCUtils.getSeansa(seansaId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lblSeansa.setText("Seansa");
        lblSeansaId.setText("Seansa ID: " + seansa.getId());
        lblPrva.setText("Da li je prva seansa: " + (seansa.isPrva()? "Jeste": "Nije prva"));
        lblDan.setText("Datum Seanse: " + seansa.getDatum());
        lblVreme.setText("Vreme Seanse: " + seansa.getVreme());
        lblVremeTrajanja.setText("Vreme Trajanja Seanse: " + seansa.getTrajanje() +"h");
        lblNaRate.setText("Placanje na rate: " + (seansa.isNaRate()? "DA": "NE"));
        lblPlaceno.setText("Placeno: "+ (seansa.isPlaceno()? "Jeste": "Nije Placeno"));

        Klijent k = seansa.getKlijent();
        lblKlijentID.setText("Klijent ID: "+String.valueOf(k.getId()));
        lblKlijentIme.setText("Ime: "+k.getIme());
        lblKlijentPrezime.setText("Prezime: "+k.getPrezime());
        lblKlijentDatum.setText("Datum rodjenja: " + k.getDatumRodjenja());
        lblKlijentPol.setText("Pol: "+k.getPol());
        lblKlijentEmail.setText("Email: "+k.getEmail());
        lblKlijentTelefon.setText("Telefon: "+k.getTelefon());
        lblklijentRanijeTerapije.setText("Ranije Terapije: " + (k.isRanijeTerapije()? "Da": "Ne"));
        String pri;
        lblKlijentBrojPrijave.setText("Broj Prijave: ");

        CenaSeanse cs = seansa.getCenaSeanse();
        cena.setText("Cena Seanse: " + cs.getCena()+" RSD");
        lblCena.setText("Datum promene cene: " + cs.getDatumPromene());
    }

    private void init() {
        this.setTitle("Informacije o Seansi");
        this.setSize(800,600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER; // Poravnanje u centru
        gbc.fill = GridBagConstraints.HORIZONTAL; // Komponenta popunjava celu širinu kolone


        lblSeansa = new JLabel("Seansa");
        lblSeansa.setPreferredSize(new Dimension(200, 50));
        lblSeansa.setFont(new Font("Arial", Font.BOLD, 15));
        lblSeansaId = new JLabel("Seansa ID: ");
        lblSeansaId.setPreferredSize(new Dimension(200, 50));
        lblSeansaId.setFont(new Font("Arial", Font.BOLD, 15));
        lblPrva = new JLabel("Prva");
        lblPrva.setPreferredSize(new Dimension(200, 50));
        lblPrva.setFont(new Font("Arial", Font.BOLD, 15));
        lblDan = new JLabel("Dan");
        lblDan.setPreferredSize(new Dimension(200, 50));
        lblDan.setFont(new Font("Arial", Font.BOLD, 15));
        lblVreme = new JLabel("Vreme");
        lblVreme.setPreferredSize(new Dimension(200, 50));
        lblVreme.setFont(new Font("Arial", Font.BOLD, 15));
        lblVremeTrajanja = new JLabel("Vreme Trajanja");
        lblVremeTrajanja.setPreferredSize(new Dimension(200, 50));
        lblVremeTrajanja.setFont(new Font("Arial", Font.BOLD, 15));
        lblNaRate = new JLabel("Na Rate");
        lblNaRate.setPreferredSize(new Dimension(200, 50));
        lblNaRate.setFont(new Font("Arial", Font.BOLD, 15));
        lblPlaceno = new JLabel("Placeno");
        lblPlaceno.setPreferredSize(new Dimension(200, 50));
        lblPlaceno.setFont(new Font("Arial", Font.BOLD, 15));
        cena = new JLabel("Cena");
        cena.setPreferredSize(new Dimension(200, 50));
        cena.setFont(new Font("Arial", Font.BOLD, 15));
        lblCena = new JLabel("Cena");
        lblCena.setPreferredSize(new Dimension(200, 50));
        lblCena.setFont(new Font("Arial", Font.BOLD, 12));
        lblKlijentID = new JLabel("Klijent Id: ");
        lblKlijentID.setPreferredSize(new Dimension(200, 50));
        lblKlijentID.setFont(new Font("Arial", Font.BOLD, 15));
        lblKlijentIme = new JLabel("Ime: ");
        lblKlijentIme.setPreferredSize(new Dimension(200, 50));
        lblKlijentIme.setFont(new Font("Arial", Font.BOLD, 15));
        lblKlijentPrezime = new JLabel("Prezime: ");
        lblKlijentPrezime.setPreferredSize(new Dimension(200, 50));
        lblKlijentPrezime.setFont(new Font("Arial", Font.BOLD, 15));
        lblKlijentDatum = new JLabel("Datum rodjenja: ");
        lblKlijentDatum.setPreferredSize(new Dimension(200, 50));
        lblKlijentDatum.setFont(new Font("Arial", Font.BOLD, 15));
        lblKlijentPol = new JLabel("Pol: ");
        lblKlijentPol.setPreferredSize(new Dimension(200, 50));
        lblKlijentPol.setFont(new Font("Arial", Font.BOLD, 15));
        lblKlijentEmail = new JLabel("Email: ");
        lblKlijentEmail.setPreferredSize(new Dimension(200, 50));
        lblKlijentEmail.setFont(new Font("Arial", Font.BOLD, 15));
        lblKlijentTelefon = new JLabel("Telefon: ");
        lblKlijentTelefon.setPreferredSize(new Dimension(200, 50));
        lblKlijentTelefon.setFont(new Font("Arial", Font.BOLD, 15));
        lblklijentRanijeTerapije = new JLabel("Ranije Terapije: ");
        lblklijentRanijeTerapije.setPreferredSize(new Dimension(200, 50));
        lblklijentRanijeTerapije.setFont(new Font("Arial", Font.BOLD, 15));
        lblKlijentBrojPrijave = new JLabel("Broj Prijave: ");
        lblKlijentBrojPrijave.setPreferredSize(new Dimension(200, 50));
        lblKlijentBrojPrijave.setFont(new Font("Arial", Font.BOLD, 15));



        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(lblSeansa, gbc);
        gbc.gridy = 1;
        contentPane.add(lblPrva, gbc);
        gbc.gridy = 2;
        contentPane.add(lblDan, gbc);
        gbc.gridy = 3;
        contentPane.add(lblVreme, gbc);
        gbc.gridy = 4;
        contentPane.add(lblVremeTrajanja, gbc);
        gbc.gridy = 5;
        contentPane.add(lblNaRate, gbc);
        gbc.gridy = 6;
        contentPane.add(lblPlaceno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPane.add(lblKlijentID, gbc);
        gbc.gridy = 1;
        contentPane.add(lblKlijentIme, gbc);
        gbc.gridy = 2;
        contentPane.add(lblKlijentPrezime, gbc);
        gbc.gridy = 3;
        contentPane.add(lblKlijentDatum, gbc);
        gbc.gridy = 4;
        contentPane.add(lblKlijentPol, gbc);
        gbc.gridy = 5;
        contentPane.add(lblKlijentEmail, gbc);
        gbc.gridy = 6;
        contentPane.add(lblKlijentTelefon, gbc);
        gbc.gridy = 7;
        contentPane.add(lblklijentRanijeTerapije, gbc);
        gbc.gridy = 8;
        contentPane.add(lblKlijentBrojPrijave, gbc);


        gbc.gridx = 2;
        gbc.gridy = 0;
        contentPane.add(lblCena, gbc);
        gbc.gridy = 1;
        contentPane.add(cena, gbc);




    }
}
