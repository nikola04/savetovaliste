package savetovaliste.model;

public class Test {
    private final int id;
    private final String naziv;
    private final int cena;
    private final OblastTesta oblast;
    public Test(int id, String naziv, int cena, OblastTesta oblast) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.oblast = oblast;
    }
    public int getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public int getCena() {
        return cena;
    }
    public OblastTesta getOblast() {
        return oblast;
    }
    @Override
    public String toString() {
        return naziv + " (" + cena + "RSD - " + oblast + ")";
    }
}
