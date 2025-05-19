package savetovaliste.model;

public class Univerzitet {
    private final int id;
    private final String naziv;
    private final UzeUsmerenjeUniverzitet uzeUsmerenjeUniverzitet;
    public Univerzitet(int id, String naziv, UzeUsmerenjeUniverzitet uzeUsmerenjeUniverzitet) {
        this.id = id;
        this.naziv = naziv;
        this.uzeUsmerenjeUniverzitet = uzeUsmerenjeUniverzitet;
    }

    public int getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public UzeUsmerenjeUniverzitet getUzeUsmerenjeUniverzitet() {
        return uzeUsmerenjeUniverzitet;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
