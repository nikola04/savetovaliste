package savetovaliste.model;

public class UzeUsmerenjeUniverzitet {
    private final int id;
    private final String naziv;
    public UzeUsmerenjeUniverzitet(Number id, String naziv) {
        this.id = (int) id;
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
