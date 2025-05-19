package savetovaliste.model;

public class Studija {
    private final int id;
    private final String naziv;
    public Studija(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
}
