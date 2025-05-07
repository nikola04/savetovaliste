package savetovaliste.model;

import java.util.Date;

public class Sertifikat {
    private final int id;
    private final Date datum;
    private final Oblast oblast;
    public Sertifikat(int id, Date datum, Oblast oblast) {
        this.id = id;
        this.datum = datum;
        this.oblast = oblast;
    }

    public int getId() {
        return id;
    }
    public Date getDatum() {
        return datum;
    }
    public Oblast getOblast() {
        return oblast;
    }
}
