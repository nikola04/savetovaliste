package savetovaliste.model;

public class Valuta {
    private final int id;
    private final String skraceno;
    private final String valuta;
    public Valuta(int id, String skraceno, String valuta){
        this.id = id;
        this.skraceno = skraceno;
        this.valuta = valuta;
    }

    public int getId() {
        return id;
    }

    public String getSkraceno() {
        return skraceno;
    }

    public String getValuta() {
        return valuta;
    }

    @Override
    public String toString() {
        return skraceno;
    }
}
