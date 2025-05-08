package savetovaliste.db.utility;

import savetovaliste.Session;
import savetovaliste.db.DBUtil;
import savetovaliste.model.*;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUtils {
    public static Psihoterapeut loginPsihoterapeut(String email, String jmbg) throws SQLException {
        String sql = "SELECT * FROM psihoterapeut WHERE email = ? AND jmbg = ?";
        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, jmbg);

        ResultSet rs = stmt.executeQuery();

        Psihoterapeut psihoterapeut = null;
        if (rs.next()) {
            int id = rs.getInt("psihoterapeut_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String jmbg1 = rs.getString("jmbg");
            String email1 = rs.getString("email");
            String telefon = rs.getString("telefon");
            Date date = rs.getDate("datum_rodjenja");
            int brojSertifikata = rs.getInt("sertifikat_id");
            int strukaId = rs.getInt("struka_id");
            psihoterapeut = new Psihoterapeut(id, ime, prezime, jmbg1, email1, telefon, date, brojSertifikata, strukaId);
        }
        rs.close();
        stmt.close();
        return psihoterapeut;
    }
    public static ArrayList<Psihoterapeut> getPsihoterapeuts() throws SQLException {
        String sql = "SELECT * FROM psihoterapeut";
        Statement stmt = DBUtil.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<Psihoterapeut> psihoterapeuti = new ArrayList<>();


        while (rs.next()) {
            int id = rs.getInt("psihoterapeut_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String jmbg1 = rs.getString("jmbg");
            String email1 = rs.getString("email");
            String telefon = rs.getString("telefon");
            Date date = rs.getDate("datum_rodjenja");
            int brojSertifikata = rs.getInt("sertifikat_id");
            int strukaId = rs.getInt("struka_id");
            Psihoterapeut psihoterapeut = new Psihoterapeut(id, ime, prezime, jmbg1, email1, telefon, date, brojSertifikata, strukaId);
            psihoterapeuti.add(psihoterapeut);
        }
        rs.close();
        stmt.close();
        return psihoterapeuti;
    }

    public static ArrayList<Seansa> getOdrzaneSeanse(Psihoterapeut psihoterapeut) throws SQLException {
        String sql = "SELECT s.*, k.*, p.*, cena_seanse.cena, cena_seanse.datum_promene as datum_promene_cene\n" +
                "                FROM seansa as s INNER JOIN cena_seanse ON cena_seanse.cena_seanse_id = s.cena_seanse_id\n" +
                "                INNER JOIN klijent as k ON k.klijent_id = s.klijent_id\n" +
                "                INNER JOIN prijava as p ON p.prijava_id = k.prijava_id\n" +
                "                WHERE p.psihoterapeut_id = ? AND (s.dan < CURRENT_DATE OR (s.dan = CURRENT_DATE AND ADDTIME(s.vreme, SEC_TO_TIME(s.vreme_trajanja * 60)) <= CURRENT_TIME))\n" +
                "                ORDER BY s.dan, s.vreme;";
        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setInt(1, psihoterapeut.getId());
        ResultSet rs = stmt.executeQuery();

        ArrayList<Seansa> seanse = new ArrayList<>();

        while (rs.next()) {
            int seansaId = rs.getInt("seansa_id");
            int klijentId = rs.getInt("klijent_id");
            int prijavaId = rs.getInt("prijava_id");
            int cenaId = rs.getInt("cena_seanse_id");

            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String telefon = rs.getString("broj");
            Date datumRodjenja = rs.getDate("datum_rodjenja");
            String pol = rs.getString("pol");
            boolean ranijeTerapije = rs.getBoolean("ranije_terapije");

            Klijent klijent = new Klijent(klijentId, ime, prezime, email, telefon, pol, datumRodjenja, ranijeTerapije);
            Prijava prijava = new Prijava(prijavaId, klijent, psihoterapeut);
            klijent.setPrijava(prijava);

            int cena = rs.getInt("cena");
            Date datumPromeneCene = rs.getDate("datum_promene_cene");

            CenaSeanse cenaSeanse = new CenaSeanse(cenaId, cena, datumPromeneCene);

            boolean prva = rs.getBoolean("prva");
            Date datum = rs.getDate("dan");
            Time vreme = rs.getTime("vreme");
            int trajanje = rs.getInt("vreme_trajanja");
            boolean naRate = rs.getBoolean("na_rate");
            boolean placeno = rs.getBoolean("placeno");


            Seansa seansa = new Seansa(seansaId, klijent, datum, vreme, trajanje, prva, naRate, placeno, cenaSeanse);
            seanse.add(seansa);
        }

        return seanse;
    }
    public static Klijent getKlijent(int klijentId) throws SQLException {
        String sql = "SELECT * FROM klijent WHERE klijent_id = ?";
        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setInt(1, klijentId);
        ResultSet rs = stmt.executeQuery();
        Klijent klijent;
        if(rs.next()){
            int id = rs.getInt("klijent_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String telefon = rs.getString("broj");
            Date datumRodjenja = rs.getDate("datum_rodjenja");
            String pol = rs.getString("pol");
            boolean ranijeTerapije = rs.getBoolean("ranije_terapije");
            klijent = new Klijent(id,ime,prezime,email,telefon,pol,datumRodjenja, ranijeTerapije);
        }else{
            System.out.println("nema Klijenta sa tim id");
            return null;
        }

        return klijent;
    }
    public static ArrayList<Klijent> getKlijents() throws SQLException {
        String sql = "SELECT k.* " +
                "FROM klijent k " +
                "JOIN prijava p ON k.prijava_id = p.prijava_id " +
                "WHERE p.psihoterapeut_id = ?";

        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setInt(1, Session.getInstance().getPsihoterapeut().getId());
        ResultSet rs = stmt.executeQuery();
        ArrayList<Klijent> klijenti = new ArrayList<>();
        while (rs.next()) {
            int klijentId = rs.getInt("klijent_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String telefon = rs.getString("broj");
            Date datumRodjenja = rs.getDate("datum_rodjenja");
            String pol = rs.getString("pol");
            boolean ranijeTerapije = rs.getBoolean("ranije_terapije");
            Klijent k = new Klijent(klijentId,ime,prezime,email,telefon,pol,datumRodjenja, ranijeTerapije);
            klijenti.add(k);
        }

        return klijenti;
    }
    public static ArrayList<Seansa> getBuduceSeanse(Psihoterapeut psihoterapeut) throws SQLException {
        String sql = "SELECT s.*, k.*, p.*, cena_seanse.cena, cena_seanse.datum_promene as datum_promene_cene " +
                "FROM seansa as s INNER JOIN cena_seanse ON cena_seanse.cena_seanse_id = s.cena_seanse_id " +
                "INNER JOIN klijent as k ON k.klijent_id = s.klijent_id " +
                "INNER JOIN prijava as p ON p.prijava_id = k.prijava_id " +
                "WHERE p.psihoterapeut_id = ? AND s.dan > CURRENT_DATE OR (s.dan = CURRENT_DATE AND ADDTIME(s.vreme, SEC_TO_TIME(s.vreme_trajanja * 60)) > CURRENT_TIME) " +
                "ORDER BY s.dan, s.vreme";

        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setInt(1, psihoterapeut.getId());
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Seansa> seanse = new ArrayList<>();

        while (rs.next()) {
            int seansaId = rs.getInt("seansa_id");
            int klijentId = rs.getInt("klijent_id");
            int prijavaId = rs.getInt("prijava_id");
            int cenaId = rs.getInt("cena_seanse_id");

            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String telefon = rs.getString("broj");
            Date datumRodjenja = rs.getDate("datum_rodjenja");
            String pol = rs.getString("pol");
            boolean ranijeTerapije = rs.getBoolean("ranije_terapije");

            Klijent klijent = new Klijent(klijentId, ime, prezime, email, telefon, pol, datumRodjenja, ranijeTerapije);
            Prijava prijava = new Prijava(prijavaId, klijent, psihoterapeut);
            klijent.setPrijava(prijava);

            int cena = rs.getInt("cena");
            Date datumPromeneCene = rs.getDate("datum_promene");

            CenaSeanse cenaSeanse = new CenaSeanse(cenaId, cena, datumPromeneCene);

            boolean prva = rs.getBoolean("prva");
            Date datum = rs.getDate("datum");
            Time vreme = rs.getTime("vreme");
            int trajanje = rs.getInt("vreme_trajanja");
            boolean naRate = rs.getBoolean("na_rate");
            boolean placeno = rs.getBoolean("placeno");


            Seansa seansa = new Seansa(seansaId, klijent, datum, vreme, trajanje, prva, naRate, placeno, cenaSeanse);
            seanse.add(seansa);
        }

        return seanse;
    }

    public static ArrayList<Struka> getStruke() throws SQLException {
        String sql = "SELECT * FROM struka";
        Statement stmt = DBUtil.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Struka> struke = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("struka_id");
            String naziv = rs.getString("naziv");
            struke.add(new Struka(id, naziv));
        }
        rs.close();
        stmt.close();
        return struke;
    }

    public static Sertifikat getSertifikat(int sertifikatId) throws SQLException {
        String sql = "SELECT datum_sertifikata, sertifikat.oblasti_id as oblast_id, naziv as oblast_naziv FROM sertifikat INNER JOIN oblasti_psihoterapije ON oblasti_psihoterapije.oblasti_id = sertifikat.oblasti_id WHERE sertifikat_id = ?";
        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setInt(1, sertifikatId);
        ResultSet rs = stmt.executeQuery();

        Sertifikat sertifikat = null;
        if(rs.next()) {
            Date datum = rs.getDate("datum_sertifikata");
            int oblastId = rs.getInt("oblast_id");
            String oblastNaziv = rs.getString("oblast_naziv");
            sertifikat = new Sertifikat(sertifikatId, datum, new Oblast(oblastId, oblastNaziv));
        }
        rs.close();
        stmt.close();
        return sertifikat;
    }

    public static ArrayList<Prijava> getPsihoterapeutPrijave(Psihoterapeut psihoterapeut) throws SQLException {
        ArrayList<Prijava> prijave = new ArrayList<>();
        String sql = "SELECT prijava.prijava_id as prijava_id, klijent_id, ime, prezime, datum_rodjenja, pol, email, broj, ranije_terapije FROM klijent INNER JOIN prijava ON prijava.prijava_id = klijent.prijava_id WHERE prijava.psihoterapeut_id = ?";
        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setInt(1, psihoterapeut.getId());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int prijavaId = rs.getInt("prijava_id");
            int klijentId = rs.getInt("klijent_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Date datumRodjenja = rs.getDate("datum_rodjenja");
            String pol = rs.getString("pol");
            String email = rs.getString("email");
            String broj = rs.getString("broj");
            boolean ranijeTerapije = rs.getBoolean("ranije_terapije");

            Klijent klijent = new Klijent(klijentId, ime, prezime, email, broj, pol, datumRodjenja, ranijeTerapije);
            Prijava prijava = new Prijava(prijavaId, klijent, psihoterapeut);
            klijent.setPrijava(prijava);

            prijave.add(prijava);
        }

        rs.close();
        stmt.close();
        return prijave;
    }

    public static int registerPsihoterapeut(String ime, String prezime, String jmbg, String email, String telefon, Date mysqlDate, int brojSertifikata, int strukaId) throws SQLException{
        try {
            String sql = "{CALL dodaj_psihoterapeuta(?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = DBUtil.getConnection().prepareCall(sql);
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            stmt.setString(3, jmbg);
            stmt.setString(4, email);
            stmt.setString(5, telefon);
            stmt.setDate(6, mysqlDate);
            stmt.setInt(7, brojSertifikata);
            stmt.setInt(8, strukaId);

            stmt.execute();
            stmt.close();
            return 0;
        }catch (SQLIntegrityConstraintViolationException e) {
            if (e.getMessage().contains("unique_jmbg"))
                return -1;
            if (e.getMessage().contains("unique_email"))
                return -2;
            if (e.getMessage().contains("unique_sertifikat_id"))
                return -3;
            return -4;
        }
    }
}
