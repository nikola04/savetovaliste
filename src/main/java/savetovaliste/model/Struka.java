package savetovaliste.model;

public class Struka {
    private final int id;
    private final String naziv;
    public Struka(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
