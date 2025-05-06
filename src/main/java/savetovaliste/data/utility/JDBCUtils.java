package savetovaliste.data.utility;

import savetovaliste.data.DBUtil;
import savetovaliste.model.Psihoterapeut;
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


    public static void RegisterPsihoterapeut(String ime, String prezime, String jmbg, String email, String telefon, Date mysqlDate, int brojSertifikata, int strukaId) throws SQLException{
        String sql = "{CALL dodaj_psihoterapeuta(?, ?, ?, ?, ?, ?, ?, ?)}";
        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setString(1, ime);
        stmt.setString(2, prezime);
        stmt.setString(3, jmbg);
        stmt.setString(4, email);
        stmt.setString(5, telefon);
        stmt.setDate(6, mysqlDate);
        stmt.setInt(7, brojSertifikata);
        stmt.setInt(8, strukaId);
        stmt.executeUpdate();
        stmt.close();

    }
}
