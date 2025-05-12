package savetovaliste.db.utility;

import savetovaliste.Session;
import savetovaliste.db.DBUtil;
import savetovaliste.model.*;

import java.sql.*;
import java.util.ArrayList;

import static savetovaliste.db.DBUtil.getConnection;

public class JDBCUtils {
    public static Psihoterapeut loginPsihoterapeut(String email, String jmbg) throws SQLException {
        String sql = "SELECT * FROM psihoterapeut WHERE email = ? AND jmbg = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
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
        Statement stmt = getConnection().createStatement();
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
        PreparedStatement stmt = getConnection().prepareStatement(sql);
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

            double cena = rs.getDouble("cena");
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

        rs.close();
        stmt.close();

        return seanse;
    }
    public static Klijent getKlijent(int klijentId) throws SQLException {
        String sql = "SELECT * FROM klijent WHERE klijent_id = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
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

        rs.close();
        stmt.close();

        return klijent;
    }

    public static ArrayList<Neplaceno> getNeplaceno(Klijent klijent) throws SQLException {
        String sql = "SELECT testiranje_id as id, 'testiranje' as tip, te.cena as iznos, FALSE as na_rate, FALSE as placeno, te.cena as nedostaje, FALSE as istekao_rok\n" +
                "FROM testiranje AS t INNER JOIN test as te ON te.test_id = t.test_id \n" +
                "INNER JOIN seansa AS s ON t.seansa_id = s.seansa_id\n" +
                "WHERE t.placanje_id IS NULL AND s.klijent_id = ? \n" +
                "\n" +
                "UNION\n" +
                "\n" +
                "SELECT \n" +
                "    s.seansa_id as id, 'seansa' as tip, cs.cena as iznos, \n" +
                "    s.na_rate as na_rate,\n" +
                "    CASE\n" +
                "    \tWHEN s.na_rate = 1 AND (p1.iznos * k1.kurs_din + p2.iznos * k2.kurs_din) >= cs.cena\n" +
                "        THEN TRUE\n" +
                "        ELSE FALSE\n" +
                "    END as placeno,\n" +
                "    CASE\n" +
                "    \tWHEN (p1.iznos * k1.kurs_din + p2.iznos * k2.kurs_din) >= cs.cena\n" +
                "        THEN 0\n" +
                "        ELSE (cs.cena - COALESCE(p1.iznos * k1.kurs_din + p2.iznos * k2.kurs_din, 0))\n" +
                "    END as nedostaje,\n" +
                "    CASE \n" +
                "        WHEN s.na_rate = 1 AND (\n" +
                "            (p2.placanje_id IS NULL AND DATE_ADD(s.dan, INTERVAL 30 DAY) < CURRENT_DATE)\n" +
                "            OR\n" +
                "            (p2.placanje_id IS NOT NULL AND DATE_ADD(s.dan, INTERVAL 30 DAY) < p2.datum))\n" +
                "        THEN TRUE\n" +
                "        ELSE FALSE\n" +
                "    END as istekao_rok\n" +
                "FROM seansa s\n" +
                "LEFT JOIN placanje p1 ON p1.seansa_id = s.seansa_id AND p1.rata = 1\n" +
                "LEFT JOIN placanje p2 ON p2.seansa_id = s.seansa_id AND p2.rata = 2\n" +
                "\n" +
                "LEFT JOIN kurs_valute k1 ON k1.valuta_id = p1.valuta_valuta_id AND k1.datum = (\n" +
                "    SELECT MAX(datum) FROM kurs_valute\n" +
                "    WHERE valuta_id = p1.valuta_valuta_id AND datum <= p1.datum\n" +
                ")\n" +
                "LEFT JOIN kurs_valute k2 ON k2.valuta_id = p2.valuta_valuta_id AND k2.datum = (\n" +
                "    SELECT MAX(datum) FROM kurs_valute\n" +
                "    WHERE valuta_id = p2.valuta_valuta_id AND datum <= p2.datum\n" +
                ")\n" +
                "\n" +
                "INNER JOIN cena_seanse cs ON cs.cena_seanse_id = s.cena_seanse_id\n" +
                "WHERE s.klijent_id = ? AND (\n" +
                "    (s.na_rate = 0 AND NOT EXISTS(\n" +
                "        SELECT 1 FROM placanje as p WHERE p.seansa_id = s.seansa_id\n" +
                "    ))\n" +
                "    OR\n" +
                "    (s.na_rate = 1 AND (\n" +
                "        p1.placanje_id IS NULL\n" +
                "        OR\n" +
                "        p1.iznos * k1.kurs_din < 0.3 * cs.cena\n" +
                "        OR\n" +
                "        p2.placanje_id IS NULL\n" +
                "        OR\n" +
                "        (p1.iznos * k1.kurs_din + p2.iznos * k2.kurs_din) < cs.cena\n" +
                "        OR\n" +
                "        DATE_ADD(s.dan, INTERVAL 30 DAY) < p2.datum\n" +
                "    ))\n" +
                ");";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, klijent.getId());
        stmt.setInt(2, klijent.getId());
        ResultSet rs = stmt.executeQuery();
        ArrayList<Neplaceno> neplacene = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String tip = rs.getString("tip");
            double iznos = rs.getDouble("iznos");
            boolean naRate = rs.getBoolean("na_rate");
            boolean placeno = rs.getBoolean("placeno");
            boolean istekaoRok = rs.getBoolean("istekao_rok");
            double nedostaje = rs.getDouble("nedostaje");

            neplacene.add(new Neplaceno(id, tip, iznos, nedostaje, placeno, naRate, istekaoRok));
        }
        rs.close();
        stmt.close();
        return neplacene;
    }

    public static ArrayList<Placanje> getPlacanja(Klijent klijent) throws SQLException {
        String sql = "SELECT p.placanje_id, iznos, iznos_sa_provizijom, valuta_valuta_id as valuta_id, v.pun_naziv as valuta, v.skraceni_naziv as valuta_s, datum as datum_placanja, nacin_placanja, svrha_placanja, rata, s.seansa_id as seansa, na_rate FROM placanje as p INNER JOIN seansa as s ON s.seansa_id = p.seansa_id INNER JOIN valuta as v ON p.valuta_valuta_id = v.valuta_id WHERE s.klijent_id = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, klijent.getId());
        ResultSet rs = stmt.executeQuery();
        ArrayList<Placanje> placanja = new ArrayList<>();
        while (rs.next()) {
            int placanjeId = rs.getInt("placanje_id");
            int seansaId = rs.getInt("seansa");
            double iznos = rs.getDouble("iznos");
            double iznosSaProvizijom = rs.getDouble("iznos_sa_provizijom");
            int valutaId = rs.getInt("valuta_id");
            String valutaD = rs.getString("valuta");
            String valutaS = rs.getString("valuta_s");
            String svrhaPlacanja = rs.getString("svrha_placanja");
            Date datumPlacanja = rs.getDate("datum_placanja");
            String nacinPlacanja = rs.getString("nacin_placanja");
            boolean naRate = rs.getBoolean("na_rate");
            int rata = rs.getInt("rata");

            Valuta valuta = new Valuta(valutaId, valutaS, valutaD);
            placanja.add(new Placanje(placanjeId, klijent, iznos, iznosSaProvizijom, valuta, nacinPlacanja, svrhaPlacanja, datumPlacanja, rata, naRate, seansaId));
        }
        rs.close();
        stmt.close();
        return placanja;
    }

    public static ArrayList<Klijent> getKlijents() throws SQLException {
        String sql = "SELECT k.* " +
                "FROM klijent k " +
                "JOIN prijava p ON k.prijava_id = p.prijava_id " +
                "WHERE p.psihoterapeut_id = ?";

        PreparedStatement stmt = getConnection().prepareStatement(sql);
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
        rs.close();
        stmt.close();
        return klijenti;
    }
    public static ArrayList<Seansa> getBuduceSeanse(Psihoterapeut psihoterapeut) throws SQLException {
        String sql = "SELECT s.*, k.*, p.*, cena_seanse.cena, cena_seanse.datum_promene as datum_promene_cene " +
                "                FROM seansa as s INNER JOIN cena_seanse ON cena_seanse.cena_seanse_id = s.cena_seanse_id " +
                "                INNER JOIN klijent as k ON k.klijent_id = s.klijent_id " +
                "                INNER JOIN prijava as p ON p.prijava_id = k.prijava_id " +
                "                WHERE p.psihoterapeut_id = ? AND s.dan > CURRENT_DATE OR (s.dan = CURRENT_DATE AND ADDTIME(s.vreme, SEC_TO_TIME(s.vreme_trajanja * 60)) > CURRENT_TIME) " +
                "                ORDER BY s.dan, s.vreme;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
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

            double cena = rs.getDouble("cena");
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

        rs.close();
        stmt.close();
        return seanse;
    }
    public static Seansa getSeansa(int seansaId) throws SQLException {
        String sql = "SELECT *\n" +
                "FROM seansa s\n" +
                "INNER JOIN klijent k ON s.klijent_id = k.klijent_id\n" +
                "INNER JOIN cena_seanse c ON s.cena_seanse_id = c.cena_seanse_id\n" +
                "WHERE s.seansa_id =?;\n";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, seansaId);
        ResultSet rs = stmt.executeQuery();
        Seansa seansa = null;
        if(rs.next()) {
            int id = rs.getInt("seansa_id");

            int klijentId = rs.getInt("klijent_id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String telefon = rs.getString("broj");
            Date datumRodjenja = rs.getDate("datum_rodjenja");
            String pol = rs.getString("pol");
            boolean ranijeTerapije = rs.getBoolean("ranije_terapije");

            boolean prva =rs.getBoolean("prva");
            Date datumSeanse = rs.getDate("dan");
            Time vreme = rs.getTime("vreme");
            int trajanje = rs.getInt("vreme_trajanja");
            boolean naRate = rs.getBoolean("na_rate");
            boolean placeno = rs.getBoolean("placeno");
            int cenaId = rs.getInt("cena_seanse_id");
            int cena = rs.getInt("cena");
            Date datumPromene = rs.getDate("datum_promene");

            int prijavaId = rs.getInt("prijava_id");

            CenaSeanse cenaSeanse = new CenaSeanse(cenaId,cena,datumPromene);
            Klijent klijent = new Klijent(klijentId,ime,prezime,email,telefon,pol,datumRodjenja, ranijeTerapije);
            klijent.setPrijava(new Prijava(prijavaId, klijent));

            seansa = new Seansa(id,klijent,datumSeanse,vreme,trajanje,prva,naRate,placeno,cenaSeanse);
        }
        rs.close();
        stmt.close();
        return seansa;
    }

    public static ArrayList<Struka> getStruke() throws SQLException {
        String sql = "SELECT * FROM struka";
        Statement stmt = getConnection().createStatement();
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
        PreparedStatement stmt = getConnection().prepareStatement(sql);
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
        PreparedStatement stmt = getConnection().prepareStatement(sql);
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
            CallableStatement stmt = getConnection().prepareCall(sql);
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
        }catch (SQLException e) {
            if(e.getMessage().contains("sertifikat_not_found"))
                return -4;
        }
        return -5;
    }

    public static ArrayList<Beleske> getBeleske(Seansa seansa) throws SQLException {
        String sql = "SELECT beleske_id, tekst  FROM beleske WHERE seansa_id = ?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, seansa.getId());
        ResultSet rs = stmt.executeQuery();
        ArrayList<Beleske> beleskeList = new ArrayList<Beleske>();
        while (rs.next()) {
            int id = rs.getInt("beleske_id");
            String tekst = rs.getString("tekst");
            beleskeList.add(new Beleske(id, tekst, seansa));
        }
        rs.close();
        stmt.close();
        return beleskeList;
    }

    public static ArrayList<Testiranje> getTestiranja(Seansa seansa) throws SQLException {
        String sql = "SELECT t.testiranje_id, t.rezultat, t.test_id, te.naziv, te.cena, te.oblast_testa_id as oblast_id, ot.naziv as oblast, CASE \n" +
                "\tWHEN t.placanje_id IS NOT NULL\n" +
                "    THEN TRUE\n" +
                "    ELSE FALSE\n" +
                "END as placeno\n" +
                "FROM testiranje as t\n" +
                "INNER JOIN test as te ON te.test_id = t.test_id\n" +
                "INNER JOIN oblast_test as ot ON ot.oblast_testa_id = te.oblast_testa_id\n" +
                "WHERE t.seansa_id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, seansa.getId());
        ResultSet rs = stmt.executeQuery();
        ArrayList<Testiranje> testiranja = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("testiranje_id");
            int testId = rs.getInt("test_id");
            int oblastId = rs.getInt("oblast_id");
            double rezultat = rs.getDouble("rezultat");
            String naziv = rs.getString("naziv");
            int cena = rs.getInt("cena");
            String oblast = rs.getString("oblast");
            boolean placeno = rs.getBoolean("placeno");
            OblastTesta oblastTesta = new OblastTesta(oblastId, oblast);
            Test test = new Test(testId, naziv, cena, oblastTesta);
            testiranja.add(new Testiranje(id, test, rezultat, seansa, placeno));
        }
        rs.close();
        stmt.close();
        return testiranja;
    }

    public static ArrayList<ObjavljeniPodatak> getObjavljeniPodaci(Seansa seansa) throws SQLException {
        String sql = "SELECT objavljivanje_id, razlog, kome, datum_objave FROM `objavljivanje_podataka` WHERE seansa_id = ?;";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, seansa.getId());
        ResultSet rs = stmt.executeQuery();

        ArrayList<ObjavljeniPodatak> objavljivanja = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("objavljivanje_id");
            String razlog = rs.getString("razlog");
            String kome = rs.getString("kome");
            Date datum = rs.getDate("datum_objave");
            objavljivanja.add(new ObjavljeniPodatak(id, seansa, razlog, kome, datum));
        }
        rs.close();
        stmt.close();
        return objavljivanja;
    }

    public static void objaviPodatake(int seansaId, String razlog, String kome,  Date date) throws SQLException {
        String sql = "{CALL dodaj_objavljivanje(?, ?, ?, ?)}";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, seansaId);
        stmt.setString(2, kome.toLowerCase().trim());
        stmt.setString(3, razlog);
        stmt.setDate(4, date);
        stmt.execute();
        stmt.close();
    }

    public static void updateBeleska(int beleskaId, String noviTekst) throws SQLException {
        String sql = "{CALL update_beleska(?, ?)}";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, beleskaId);
        stmt.setString(2, noviTekst);
        stmt.executeUpdate();
        stmt.close();
    }

    public static void removeBeleske(int beleskaId) throws SQLException{
        String sql = "{CALL delete_beleska(?)}";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, beleskaId);
        stmt.executeUpdate();
        stmt.close();

    }

    public static void addBeleske(String newText, int seansa_id) throws SQLException{
        String sql = "{CALL insert_beleska(?, ?)}";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, seansa_id);
        stmt.setString(2, newText);
        stmt.executeUpdate();
        stmt.close();
    }

    public static ArrayList<Test> getTestovi() throws SQLException {
        String sql = "SELECT test_id, t.naziv, cena, t.oblast_testa_id as oblast_id, ot.naziv as oblast " +
                "FROM test as t " +
                "INNER JOIN oblast_test as ot ON t.oblast_testa_id = ot.oblast_testa_id;";
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<Test> testovi = new ArrayList<>();

        while(rs.next()) {
            int id = rs.getInt("test_id");
            String naziv = rs.getString("naziv");
            int cena = rs.getInt("cena");
            int oblastId = rs.getInt("oblast_id");
            String oblast = rs.getString("oblast");
            OblastTesta oblastTesta = new OblastTesta(oblastId, oblast);
            testovi.add(new Test(id, naziv, cena, oblastTesta));
        }
        rs.close();
        stmt.close();
        return testovi;
    }

    public static void addTestiranje(int testId, double rezultat, int seansaId) throws SQLException{
        String sql = "{CALL dodaj_testiranje(?, ?, ?)}";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setDouble(1, rezultat);
        stmt.setInt(2, testId);
        stmt.setInt(3, seansaId);
        stmt.executeUpdate();
        stmt.close();
    }
}
