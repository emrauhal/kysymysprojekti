
import java.sql.*;
import java.util.*;

public class KayttoLiittyma {
    private static int oikeatVastaukset;

    private static void lataaAjuri() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public void kayta() throws ClassNotFoundException {
        System.out.println("Tervetuloa!");
        System.out.println("\n");
        lataaAjuri();
        try {
            Connection con = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/kysymykset?useSSL=false",
                            "root", "Academy18");
            kysely(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Sait oikein: " + oikeatVastaukset);
        System.out.println("Kiitos käynnistä ja tervetuloa uudelleen!");
    }

    private static void kysely(Connection con) throws SQLException {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.add(8);
        lista.add(9);
        lista.add(10);
        Collections.shuffle(lista);

        for (int i = 1; i <= lista.size(); i++) {
            tulostaKysymys(con, i);
            int oikea = tulostaKysymysvaihtoehdot(con, i);

            Scanner lukija = new Scanner(System.in);
            System.out.print("Anna vastaus: ");
            String vastaus;

            while (true) {
                if (lukija.hasNextLine()) {
                    vastaus = lukija.nextLine();

                    if (vastaus.equals("1") || vastaus.equals("2") || vastaus.equals("3") || vastaus.equals("4")) {
                        break;
                    } else {
                        System.out.println("Syöte ei kelpaa!");
                        System.out.print("Anna vastaus: ");
                        continue;
                    }
                } else {
                    System.out.println("Tyhjä syöte!");
                    System.out.print("Anna vastaus: ");
                    continue;
                }
            }
            int vast = Integer.parseInt(vastaus);
            if (vast == oikea) {
                System.out.println("Oikein!");
                System.out.println("\n");
                oikeatVastaukset ++;
            } else {
                System.out.println("Väärin!");
                System.out.println("Oikea vaihtoehto olisi ollut: "+ oikea);
                System.out.println("\n");
            }
        }
    }

    private static void tulostaKysymys(Connection con, int kysymyksenIndeksi) throws SQLException {
        String sql = "SELECT teksti FROM kysymys WHERE id=?";
        PreparedStatement lause = con.prepareStatement(sql);
        lause.setInt(1, kysymyksenIndeksi);
        ResultSet rs = lause.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    private static int tulostaKysymysvaihtoehdot(Connection con, int kysymyksenIndeksi) throws SQLException {
        String sql2 = "SELECT * FROM kysymysvaihtoehdot WHERE kysymysID=?";
        PreparedStatement lause2 = con.prepareStatement(sql2);
        lause2.setInt(1, kysymyksenIndeksi);
        ResultSet rs2 = lause2.executeQuery();
        int k = 1;
        int oikea = 0;
        while (rs2.next()) {
            System.out.println(k + ". " + rs2.getString(2));
            if (rs2.getInt("oikeavastaus") == 1)
                oikea = k;
            k++;
        }
        return oikea;
    }
}
