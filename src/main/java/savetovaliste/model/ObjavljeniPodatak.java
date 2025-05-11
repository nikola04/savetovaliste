package savetovaliste.model;

import java.util.Date;

public class ObjavljeniPodatak {
    private final int id;
    private final Seansa seansa;
    private final String razlog;
    private final ObjavljenKome kome;
    private final Date datum;
    public ObjavljeniPodatak(int id, Seansa seansa, String razlog, String kome, Date datum) {
        this.id = id;
        this.seansa = seansa;
        this.razlog = razlog;
        if(kome.equals("sud"))
            this.kome = ObjavljenKome.SUD;
        else this.kome = ObjavljenKome.POLICIJA;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }
    public Seansa getSeansa() {
        return seansa;
    }
    public String getRazlog() {
        return razlog;
    }
    public ObjavljenKome getKome() {
        return kome;
    }
    public Date getDatum() {
        return datum;
    }
}
