package savetovaliste.model;

import savetovaliste.db.utility.JDBCUtils;

import java.sql.SQLException;
import java.util.Date;

public class Kandidat extends Psihoterapeut{
    private String prebivaliste;
    private int supervizorId;
    private Psihoterapeut supervizor;
    private int studijaId;
    private Studija studija;
    private int fakultetId;
    private Fakultet fakultet;
    private int centarZaObukuId;
    private CentarZaObuku centarZaObuku;

    public Kandidat(int id, String ime, String prezime, String jmbg, String email, String telefon, Date datumRodjenja, String prebivaliste, int supervizorId, int studijaId, int fakultetId, int centarZaObukuId) {
        super(id, ime, prezime, jmbg, email, telefon, datumRodjenja);
        this.supervizorId = supervizorId;
        this.studijaId = studijaId;
        this.fakultetId = fakultetId;
        this.centarZaObukuId = centarZaObukuId;
        this.supervizor = null;
        this.studija = null;
        this.fakultet = null;
        this.centarZaObuku = null;
        this.prebivaliste = prebivaliste;
    }

    public Kandidat(int id, String ime, String prezime, String jmbg, String email, String telefon, Date datumRodjenja, String prebivaliste, Psihoterapeut supervizor, Studija studija, Fakultet fakultet, CentarZaObuku centarZaObuku) {
        super(id, ime, prezime, jmbg, email, telefon, datumRodjenja);
        this.prebivaliste = prebivaliste;
        this.supervizor = supervizor;
        this.studija = studija;
        this.fakultet = fakultet;
        this.centarZaObuku = centarZaObuku;
        this.supervizorId = supervizor.getId();
        this.studijaId = studija.getId();
        this.fakultetId = fakultet.getId();
        this.centarZaObukuId = centarZaObuku.getId();
    }

    private void fetchKandidat(){
        try{
            Kandidat k = JDBCUtils.fetchKandidat(this);
            this.prebivaliste = k.prebivaliste;
            this.supervizorId = k.supervizorId;
            this.supervizor = k.getSupervizor();
            this.studijaId = k.studijaId;
            this.studija = k.getStudija();
            this.fakultetId = k.fakultetId;
            this.fakultet = k.getFakultet();
            this.centarZaObukuId = k.centarZaObukuId;
            this.centarZaObuku = this.getCentarZaObuku();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Psihoterapeut getSupervizor() {
        if(supervizor != null)
            return supervizor;

        fetchKandidat();
        return supervizor;
    }
    public Studija getStudija() {
        if(studija != null)
            return studija;

        fetchKandidat();
        return studija;
    }
    public Fakultet getFakultet() {
        if(fakultet != null)
            return fakultet;

        fetchKandidat();
        return fakultet;
    }
    public CentarZaObuku getCentarZaObuku() {
        if(centarZaObuku != null)
            return centarZaObuku;

        fetchKandidat();
        return centarZaObuku;
    }
}
