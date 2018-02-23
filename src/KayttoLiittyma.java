
import java.sql.*;
import java.util.*;

public class KayttoLiittyma {
    // Oliomuuttujiksi Tietokantahaku-olio sekä staattinen laskuri oikeille vastauksille.
    private static int oikeatVastaukset;
    private Tietokantahaku t;

    // Lataa ajurin, heittää poikkeuksen jos ajuria ei löydy.
    private void lataaAjuri() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    // Käyttöliittymän runko, joka tulostaa alku- ja lopputekstit, lataa ajurin ja kutsuu
    // kysely-metodia itse sisällön tulostamiseen.
    public void kayta() throws ClassNotFoundException {
        System.out.println("Tervetuloa!");
        System.out.println("\n");
        lataaAjuri();

        // Olisi voinut luoda komentorivillä vielä yhteisen käyttäjän, jolla sama tunnus ja sama salasana.
        // Tällöin sama koodi toimii jaettuna kaikilla.
        try (Connection con = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/kysymykset?useSSL=false",
                            "root", "Academy18")) {
            kysely(con);

        } catch (SQLException e) {
            System.out.println("Virhe tietokantayhteyden luomisessa!");
        }
        System.out.println("Sait oikein: " + oikeatVastaukset);
        System.out.println("Kiitos käynnistä ja tervetuloa uudelleen!");
    }

    // Suorittaa varsinaisen kyselyn, eli tulostaa satunnaisessa järjestyksessä kymmenen
    // kysymystä ja niihin liittyvät vaihtoehdot.
    // Kysyy käyttäjältä vastausta ja selvittää, oliko vastaus oikein vai väärin.
    private void kysely(Connection con) throws SQLException {
        List<Integer> lista = randomGeneraattori();

        for (int i : lista) {
            t.tulostaKysymys(con, i);
            int oikea = t.tulostaKysymysvaihtoehdot(con, i);

            System.out.print("Anna vastaus: ");
            int vastaus = syotteenKasittely();

            if (vastaus == oikea) {
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

    // Palauttaa listan, jolla satunnaisessa järjestyksessä luvut 1-10.
    // Random ei toimi tässä, sillä se ei muista, mitä kysymyksiä on jo kertaalleen tulostanut.
    private List<Integer> randomGeneraattori() {
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
        return lista;
    }

    // Käsittelee käyttäjän mahdolliset virhesyötteet: tyhjä syöte, välilyönti, kirjain tai väärä numero.
    // Ohjelman ei siis pitäisi kaatua millään käyttäjän syötteellä.
    private int syotteenKasittely() {
        Scanner lukija = new Scanner(System.in);
        int kasiteltyVastaus;

        while (true) {
            if (lukija.hasNextLine()) {
                String vastaus = lukija.nextLine();

                if (vastaus.equals("1") || vastaus.equals("2") || vastaus.equals("3") || vastaus.equals("4")) {
                    kasiteltyVastaus = Integer.parseInt(vastaus);
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
        return kasiteltyVastaus;
    }
}