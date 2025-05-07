package savetovaliste.model;

public class Prijava {
    private final Integer id;
    private final Klijent klijent;
    private final Psihoterapeut psihoterapeut;
//    private final Kandidat kandidat;
    public Prijava(Integer id, Klijent klijent, Psihoterapeut psihoterapeut) {
        this.id = id;
        this.klijent = klijent;
        this.psihoterapeut = psihoterapeut;
    }

    public Integer getId() {
        return id;
    }
    public Klijent getKlijent() {
        return klijent;
    }
    public Psihoterapeut getPsihoterapeut() {
        return psihoterapeut;
    }
}
