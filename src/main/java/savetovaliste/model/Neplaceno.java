package savetovaliste.model;

public class Neplaceno {
    private final int id; // seansaId ili testiranjeId zavisi od tip
    private final String tip; // seansa ili testiranje
    private final double iznos;
    private final boolean istekaoRok; // ako je na rate i istekao je rok / ako nije na rate i nikada nije uplacena prva rata bice false
    public Neplaceno(int id, String tip, double iznos, boolean istekaoRok) {
        this.id = id;
        this.tip = tip;
        this.iznos = iznos;
        this.istekaoRok = istekaoRok;
    }

    public int getId() {
        return id;
    }
    public String getTip() {
        return tip;
    }
    public double isIznos() {
        return iznos;
    }
    public boolean isIstekaoRok() {
        return istekaoRok;
    }
}
