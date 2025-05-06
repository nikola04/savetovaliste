package savetovaliste.model;


import java.util.Date;

public class PsihoterapeutProfile {
    private String ime;
    private String prezime;
    private String jmbg;
    private String email;
    private String telefon;
    private Date datumRodjenja;
    private int sertifikatId;
    private int strukaId;

    public PsihoterapeutProfile(String ime, String prezime, String jmbg, String email, String telefon, Date datumRodjenja, int sertifikatId, int strukaId) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.email = email;
        this.telefon = telefon;
        this.datumRodjenja = datumRodjenja;
        this.sertifikatId = sertifikatId;
        this.strukaId = strukaId;
    }
}
