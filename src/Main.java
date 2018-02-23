
public class Main {

    public static void main(String[] args) {
        KayttoLiittyma kl = new KayttoLiittyma();

        try {
            kl.kayta();
        } catch (ClassNotFoundException e) {
            System.out.println("Virhe ajurin lataamisessa!");
        }
        // Main-luokka ei mielellään saisi heittää poikkeusta, koska silloin poikkeus jää
        // ikäänkuin kokonaan käsittelemättä. Tämän vuoksi try-catch-rakenne, joka
        // yrittää ajaa käyttöliittymän. Jos ajurin lataamisessa tapahtuu virhe, catch-lohko
        // ottaa kiinni ClassNotFoundExceptionin.
    }
}