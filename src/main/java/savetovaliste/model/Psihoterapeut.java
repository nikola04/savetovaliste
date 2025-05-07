package savetovaliste.model;


import savetovaliste.data.utility.JDBCUtils;

import java.sql.SQLException;
import java.util.Date;

public class Psihoterapeut {
    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String email;
    private String telefon;
    private Date datumRodjenja;
    private int sertifikatId;
    private int strukaId;
    private Sertifikat sertifikat;

    public Psihoterapeut(int id, String ime, String prezime, String jmbg, String email, String telefon, Date datumRodjenja, int sertifikatId, int strukaId) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
        this.sertifikatId = sertifikatId;
        this.strukaId = strukaId;
    }

    public int getId() {
        return id;
    }

    public boolean fetchSertifikat(){
        try{
            this.sertifikat = JDBCUtils.GetSertifikat(this.sertifikatId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Sertifikat getSertifikat(){
        if(sertifikat != null)
            return sertifikat;
        if(fetchSertifikat())
            return sertifikat;

        return null;
    }

    public String getIme() {
        return ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public String getJmbg() {
        return jmbg;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefon() {
        return telefon;
    }
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }
    public int getStrukaId() {
        return strukaId;
    }
}
