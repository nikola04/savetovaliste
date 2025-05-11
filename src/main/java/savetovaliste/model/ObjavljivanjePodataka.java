package savetovaliste.model;

import java.util.Date;

public class ObjavljivanjePodataka {
    private final int id;
    private final int seansaId;
    private final Date datum;
    private final  RazlogObjave razlog;
    private final String kome;

    public ObjavljivanjePodataka(int id, int seansaId, Date datum, String razlog,String kome) {
        this.id = id;
        this.seansaId = seansaId;
        this.datum = datum;
        if(razlog.equals("supervizija")){
            this.razlog = RazlogObjave.SUPERVIZIJA;
        }else if (razlog.equals("prijava policiji")){
            this.razlog = RazlogObjave.PRIJAVA_POLICIJI;
        } else if (razlog.equals("sud")) {
            this.razlog = RazlogObjave.SUD;
        }else this.razlog = null;
        this.kome = kome;
    }

    public String getKome() {
        return kome;
    }

    public int getId() {
        return id;
    }

    public int getSeansaId() {
        return seansaId;
    }

    public Date getDatum() {
        return datum;
    }

    public RazlogObjave getRazlog() {
        return razlog;
    }
}
