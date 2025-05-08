package savetovaliste.model;

import java.util.Date;

public class CenaSeanse {
    private final int id;
    private final int cena;
    private final Date datumPromene;
    public CenaSeanse(int id, int cena, Date datumPromene) {
        this.id = id;
        this.cena = cena;
        this.datumPromene = datumPromene;
    }

    public int getId() {
        return id;
    }
    public int getCena() {
        return cena;
    }
    public Date getDatumPromene() {
        return datumPromene;
    }
}
