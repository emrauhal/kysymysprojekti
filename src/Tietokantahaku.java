import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tietokantahaku {

    // Hakee ja tulostaa tietokannasta annettua indeksiä vastaava kysymys.
    public void tulostaKysymys(Connection con, int kysymyksenIndeksi) throws SQLException {

        String sql = "SELECT teksti FROM kysymys WHERE id=?";
        PreparedStatement lause = con.prepareStatement(sql);
        lause.setInt(1, kysymyksenIndeksi);
        ResultSet rs = lause.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // Hakee ja tulostaa tietokannasta annettua indeksiä vastaavaan kysymykseen liittyvät vaihtoehdot.
    // Lopuksi metodi palauttaa kysymyksen oikean vastauksen indeksin (luku 1-4).
    public int tulostaKysymysvaihtoehdot(Connection con, int kysymyksenIndeksi) throws SQLException {

        String sql2 = "SELECT * FROM kysymysvaihtoehdot WHERE kysymysID=?";
        PreparedStatement lause2 = con.prepareStatement(sql2);
        lause2.setInt(1, kysymyksenIndeksi);
        ResultSet rs2 = lause2.executeQuery();

        int vaihtoehdonIndeksi = 1;
        int oikeaIndeksi = 0;

        while (rs2.next()) {
            System.out.println(vaihtoehdonIndeksi + ". " + rs2.getString(2));
            if (rs2.getInt("oikeavastaus") == 1)
                oikeaIndeksi = vaihtoehdonIndeksi;
            vaihtoehdonIndeksi++;
        }
        return oikeaIndeksi;
    }
}