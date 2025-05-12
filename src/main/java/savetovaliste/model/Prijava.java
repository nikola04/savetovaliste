package savetovaliste.model;

public class Prijava {
    private final int id;
    private final Klijent klijent;
    private final Psihoterapeut psihoterapeut;
//    private final Kandidat kandidat;
    public Prijava(int id, Klijent klijent) {
        this.id = id;
        this.klijent = klijent;
        this.psihoterapeut = null;
    }
    public Prijava(int id, Klijent klijent, Psihoterapeut psihoterapeut) {
        this.id = id;
        this.klijent = klijent;
        this.psihoterapeut = psihoterapeut;
    }

    public int getId() {
        return id;
    }
    public Klijent getKlijent() {
        return klijent;
    }
    public Psihoterapeut getPsihoterapeut() {
        return psihoterapeut;
    }
}
