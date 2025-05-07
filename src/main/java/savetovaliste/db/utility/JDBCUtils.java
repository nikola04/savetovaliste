package savetovaliste.db.utility;

import savetovaliste.db.DBUtil;
import savetovaliste.model.Oblast;
import savetovaliste.model.Psihoterapeut;
import savetovaliste.model.Sertifikat;
import savetovaliste.model.Struka;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUtils {
    public static Psihoterapeut LoginPsihoterapeut(String email, String jmbg) throws SQLException {
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

    public static ArrayList<Struka> SveStruke() throws SQLException {
        String sql = "SELECT * FROM struka";
        Statement stmt = DBUtil.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Struka> struke = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("struka_id");
            String naziv = rs.getString("naziv");
            struke.add(new Struka(id, naziv));
        }

        return struke;
    }

    public static Sertifikat GetSertifikat(int sertifikatId) throws SQLException {
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

    public static int RegisterPsihoterapeut(String ime, String prezime, String jmbg, String email, String telefon, Date mysqlDate, int brojSertifikata, int strukaId) throws SQLException{
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
