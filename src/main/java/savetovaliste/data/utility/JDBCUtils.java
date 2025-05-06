package savetovaliste.data.utility;

import savetovaliste.data.DBUtil;
import savetovaliste.data.models.Struka;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {
    public static int LoginPsihoterapeut(String email, String jmbg) throws SQLException {
        String sql = "SELECT * FROM psihoterapeut WHERE email = ? AND jmbg = ?";
        PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, jmbg);

        ResultSet rs = stmt.executeQuery();

        int id = -1;
        if (rs.next())
            id = rs.getInt("psihoterapeut_id");
        rs.close();
        stmt.close();
        return id;
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
}
