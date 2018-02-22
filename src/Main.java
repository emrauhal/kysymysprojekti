import java.sql.*;
import java.util.Random;

public class Main {

    private static void lataaAjuri() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        lataaAjuri();
        try {
            Connection con = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/kysymykset?useSSL=false",
                            "root", "Academy18");
            tulostaKysymys(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





    private static void tulostaKysymys(Connection con) throws SQLException {
        Random randomi  = new Random ();
        String sql = "SELECT teksti FROM kysymys WHERE id=?";
        PreparedStatement lause = con.prepareStatement(sql);
        int i = randomi.nextInt(3) + 1;
        lause.setInt(1, i);
        ResultSet rs = lause.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        tulostaKysymysvaihtoehdot(con, i);
    }

    private static void tulostaKysymysvaihtoehdot(Connection con, int kysymyksenid) throws SQLException {
        String sql = "SELECT * FROM kysymysvaihtoehdot WHERE kysymysID=?";
        PreparedStatement lause = con.prepareStatement(sql);
        lause.setInt(1, kysymyksenid);
        ResultSet rs = lause.executeQuery();
        int k = 1;
        while (rs.next()) {
            System.out.println(k + ". " + rs.getString(2));
            k++;
        }

    }
}
