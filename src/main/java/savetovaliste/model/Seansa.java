package savetovaliste.model;

import java.sql.Time;
import java.util.Date;

public class Seansa {
    private final int id;
    private final Klijent klijent;
    private final Date datum;
    private final Time vreme;
    private final int trajanje;
    private final boolean prva;
    private final boolean naRate;
    private final boolean placeno;
    private final CenaSeanse cenaSeanse;
    public Seansa(int id, Klijent klijent, Date datum, Time vreme, int trajanje, boolean prva, boolean naRate, boolean placeno, CenaSeanse cenaSeanse) {
        this.id = id;
        this.klijent = klijent;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.naRate = naRate;
        this.placeno = placeno;
        this.prva = prva;
        this.cenaSeanse = cenaSeanse;
    }

    public int getId() {
        return id;
    }
    public Klijent getKlijent() {
        return klijent;
    }
    public Date getDatum() {
        return datum;
    }
    public Time getVreme() {
        return vreme;
    }
    public int getTrajanje() {
        return trajanje;
    }
    public boolean isPrva() {
        return prva;
    }
    public boolean isNaRate() {
        return naRate;
    }
    public boolean isPlaceno() {
        return placeno;
    }
    public CenaSeanse getCenaSeanse() {
        return cenaSeanse;
    }
}
