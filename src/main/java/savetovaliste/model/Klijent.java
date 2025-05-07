package savetovaliste.model;

import java.util.Date;

public class Klijent {
    private final int id;
    private final String ime;
    private final String prezime;
    private final String email;
    private final String telefon;
    private final String pol;
    private final Date datumRodjenja;
    private final boolean ranijeTerapije;
    private Prijava prijava;
    public Klijent(int id, String ime, String prezime, String email, String telefon, String pol, Date datumRodjenja, boolean ranijeTerapije) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.ranijeTerapije = ranijeTerapije;
        this.prijava = null;
    }

    public Prijava getPrijava() {
        return prijava;
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
    public String getEmail() {
        return email;
    }
    public String getTelefon() {
        return telefon;
    }
    public String getPol() {
        return pol;
    }
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }
    public boolean isRanijeTerapije() {
        return ranijeTerapije;
    }
    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }
}
