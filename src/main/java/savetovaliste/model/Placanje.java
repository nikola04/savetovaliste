package savetovaliste.model;

import java.util.Date;

public class Placanje {
    private final int id;
    private final Klijent klijent;
    private final double iznos;
    private final double iznosSaProvizijom;
    private final Valuta valuta;
    private final NacinPlacanja nacinPlacanja;
    private final SvrhaPlacanja svrhaPlacanja;
    private final Date datum;
    private final int rata;
    private final boolean naRate;
    private final int seansa;
    public Placanje(int id, Klijent klijent, double iznos, double iznosSaProvizijom, Valuta valuta, String nacinPlacanja, String svrhaPlacanja, Date datum, int rata, boolean naRate, int seansa) {
        this.id = id;
        this.klijent = klijent;
        this.iznos = iznos;
        this.iznosSaProvizijom = iznosSaProvizijom;
        this.valuta = valuta;
        if(nacinPlacanja.equals("kartica")){
            this.nacinPlacanja = NacinPlacanja.KARTICA;
        }else this.nacinPlacanja = NacinPlacanja.GOTOVINA;
        if(svrhaPlacanja.equals("seansa")){
            this.svrhaPlacanja = SvrhaPlacanja.SEANSA;
        }else this.svrhaPlacanja = SvrhaPlacanja.TEST;
        this.datum = datum;
        this.rata = rata;
        this.naRate = naRate;
        this.seansa = seansa;
    }

    public int getId() {
        return id;
    }
    public Klijent getKlijent() {
        return klijent;
    }
    public double getIznos() {
        return iznos;
    }
    public double getIznosSaProvizijom() {
        return iznosSaProvizijom;
    }
    public Valuta getValuta() {
        return valuta;
    }
    public NacinPlacanja getNacinPlacanja() {
        return nacinPlacanja;
    }
    public SvrhaPlacanja getSvrhaPlacanja() {
        return svrhaPlacanja;
    }
    public Date getDatum() {
        return datum;
    }
    public int getRata() {
        return rata;
    }
    public boolean isNaRate() {
        return naRate;
    }
    public int getSeansa() {
        return seansa;
    }
}
