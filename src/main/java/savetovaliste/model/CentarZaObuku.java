package savetovaliste.model;

public class CentarZaObuku {
    private final int id;
    private final String naziv;
    private final String email;
    private final String telefon;
    private final String ulica;
    private final int broj;
    private final String opstina;
    public CentarZaObuku(int id, String naziv, String email, String telefon, String ulica, int broj, String opstina) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.telefon = telefon;
        this.ulica = ulica;
        this.broj = broj;
        this.opstina = opstina;
    }

    public int getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefon() {
        return telefon;
    }
    public String getUlica() {
        return ulica;
    }
    public int getBroj() {
        return broj;
    }
    public String getOpstina() {
        return opstina;
    }
    @Override
    public String toString() {
        return naziv;
    }
}
