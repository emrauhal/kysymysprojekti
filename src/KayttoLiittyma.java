import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

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
            tulostaKysymys(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Sait oikein: " + oikeatVastaukset);
        System.out.println("Kiitos käynnistä ja tervetuloa uudelleen!");
    }

    private static void tulostaKysymys(Connection con) throws SQLException {
        //Random randomi  = new Random ();
        String sql = "SELECT teksti FROM kysymys WHERE id=?";
        PreparedStatement lause = con.prepareStatement(sql);
        //int i = randomi.nextInt(3) + 1;
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
            lause.setInt(1, i);
            ResultSet rs = lause.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            String sql2 = "SELECT * FROM kysymysvaihtoehdot WHERE kysymysID=?";
            PreparedStatement lause2 = con.prepareStatement(sql2);
            lause2.setInt(1, i);
            ResultSet rs2 = lause2.executeQuery();
            int k = 1;
            int oikea = 0;
            while (rs2.next()) {
                System.out.println(k + ". " + rs2.getString(2));
                if (rs2.getInt("oikeavastaus") == 1)
                    oikea = k;
                k++;
            }

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
                        continue;
                    }
                } else {
                    System.out.println("Tyhjä syöte!");
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
}
