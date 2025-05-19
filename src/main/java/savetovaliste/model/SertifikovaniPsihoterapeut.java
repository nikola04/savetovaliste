package savetovaliste.model;

import savetovaliste.db.utility.JDBCUtils;

import java.sql.SQLException;
import java.util.Date;

public class SertifikovaniPsihoterapeut extends Psihoterapeut {
    private final int sertifikatId;
    private final int strukaId;
    private Sertifikat sertifikat;
//    private Struka struka;

    public SertifikovaniPsihoterapeut(int id, String ime, String prezime, String jmbg, String email, String telefon, Date datumRodjenja, int sertifikatId, int strukaId ) {
        super(id, ime, prezime, jmbg, email, telefon, datumRodjenja);
        this.sertifikatId = sertifikatId;
        this.strukaId = strukaId;
        this.sertifikat = null;
//        this.struka = null;
    }

    public boolean fetchSertifikat(){
        if(sertifikatId == 0)
            return false;
        try{
            this.sertifikat = JDBCUtils.getSertifikat(this.sertifikatId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Sertifikat getSertifikat(){
        if(sertifikat != null)
            return sertifikat;
        if(fetchSertifikat())
            return sertifikat;

        return null;
    }

    public int getStrukaId() {
        return strukaId;
    }
}
