package savetovaliste.model;

public class Neplaceno {
    private final int id; // seansaId ili testiranjeId zavisi od tip
    private final String tip; // seansa ili testiranje
    private final double iznos;
    private final double nedostaje;
    private final boolean naRate;
    private final boolean placeno;
    private final boolean istekaoRok; // ako je na rate i istekao je rok / ako nije na rate i nikada nije uplacena prva rata bice false
    public Neplaceno(int id, String tip, double iznos, double nedostaje, boolean placeno, boolean naRate, boolean istekaoRok) {
        this.id = id;
        this.tip = tip;
        this.iznos = iznos;
        this.istekaoRok = istekaoRok;
        this.placeno = placeno;
        this.naRate = naRate;
        this.nedostaje = nedostaje;
    }

    public int getId() {
        return id;
    }
    public String getTip() {
        return tip;
    }
    public double getIznos() {
        return iznos;
    }
    public double getNedostaje() {
        return nedostaje;
    }
    public boolean isIstekaoRok() {
        return istekaoRok;
    }
    public boolean isPlaceno() {
        return placeno;
    }
    public boolean isNaRate() {
        return naRate;
    }
}
