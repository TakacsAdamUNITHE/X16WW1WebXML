package domx16ww1;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMQueryX16WW1 {

    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File inputFile = new File("XMLX16WW1.xml");

            // DOM parser inicializálása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Dokumentum objektum létrehozása a fájlból
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. LEKÉRDEZÉS: Az összes cég neve
            System.out.println("1. Összes cég neve:");
            NodeList cegList = doc.getElementsByTagName("Ceg");
            for (int i = 0; i < cegList.getLength(); i++) {
                Element ceg = (Element) cegList.item(i);
                // A <nev> elem tartalmának kiírása
                System.out.println(" - " + ceg.getElementsByTagName("nev").item(0).getTextContent());
            }

            // 2. LEKÉRDEZÉS: Raktárak nevei és polcszámai
            System.out.println("\n2. Raktárak nevei és polcszámai:");
            NodeList tarList = doc.getElementsByTagName("Tar");
            for (int i = 0; i < tarList.getLength(); i++) {
                Element tar = (Element) tarList.item(i);
                String nev = tar.getElementsByTagName("nev").item(0).getTextContent();
                String polc = tar.getElementsByTagName("polcszam").item(0).getTextContent();
                System.out.println(" - " + nev + " (Polcszám: " + polc + ")");
            }

            // 3. LEKÉRDEZÉS: Termékek nevei, árai és raktár-azonosítójuk
            System.out.println("\n3. Termékek nevei, árai és raktár azonosítójuk:");
            NodeList termekList = doc.getElementsByTagName("Termek");
            for (int i = 0; i < termekList.getLength(); i++) {
                Element termek = (Element) termekList.item(i);
                String nev = termek.getElementsByTagName("nev").item(0).getTextContent();
                String ar = termek.getElementsByTagName("ar").item(0).getTextContent();
                String sid = termek.getAttribute("s_i"); // raktár ID
                System.out.println(" - " + nev + ", Ár: " + ar + ", Raktár ID: " + sid);
            }

            // 4. LEKÉRDEZÉS: Rendelések termékei és mennyisége
            System.out.println("\n4. Rendelések termékei és mennyisége:");
            NodeList rendelesList = doc.getElementsByTagName("Rendeles");
            for (int i = 0; i < rendelesList.getLength(); i++) {
                Element rendeles = (Element) rendelesList.item(i);
                String iid = rendeles.getAttribute("d_i_i"); // Termék ID
                String meny = rendeles.getElementsByTagName("menyiseg").item(0).getTextContent();

                // A hozzá tartozó termék nevének megkeresése
                for (int j = 0; j < termekList.getLength(); j++) {
                    Element termek = (Element) termekList.item(j);
                    if (termek.getAttribute("Iid").equals(iid)) {
                        String nev = termek.getElementsByTagName("nev").item(0).getTextContent();
                        System.out.println(" - " + nev + ", Mennyiség: " + meny);
                        break; // ha megtaláltuk, nem kell tovább keresni
                    }
                }
            }

            // 5. LEKÉRDEZÉS: Szállítások státuszai és érkezési dátumai
            System.out.println("\n5. Szállítások státuszai és érkezési dátumai:");
            NodeList szallitList = doc.getElementsByTagName("Szallitas");
            for (int i = 0; i < szallitList.getLength(); i++) {
                Element szallit = (Element) szallitList.item(i);
                String statusz = szallit.getElementsByTagName("statusz").item(0).getTextContent();
                String datum = szallit.getElementsByTagName("erkezesidatum").item(0).getTextContent();
                System.out.println(" - Státusz: " + statusz + ", Érkezés: " + datum);
            }

        } catch (Exception e) {
            // Hiba esetén a kivétel kiírása
            e.printStackTrace();
        }
    }
}
