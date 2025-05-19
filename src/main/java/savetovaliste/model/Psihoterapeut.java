package savetovaliste.model;

import java.util.Date;

public class Psihoterapeut {
    private final int id;
    private final String ime;
    private final String prezime;
    private final String jmbg;
    private final String email;
    private final String telefon;
    private final Date datumRodjenja;

    public Psihoterapeut(int id, String ime, String prezime, String jmbg, String email, String telefon, Date datumRodjenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
    }
    public int getId() {
        return id;
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
}
