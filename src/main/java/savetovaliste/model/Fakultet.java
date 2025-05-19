package savetovaliste.model;

public class Fakultet {
    private final int id;
    private final String naziv;
    private final Univerzitet univerzitet;
    public Fakultet(int id, String naziv, Univerzitet univerzitet) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
    }

    public int getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public Univerzitet getUniverzitet() {
        return univerzitet;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
