package savetovaliste.model;


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

    public int getSertifikatId() {
        return sertifikatId;
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
