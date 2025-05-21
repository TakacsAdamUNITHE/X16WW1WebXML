package hu.domparse.neptunkod;

// Szükséges Java osztályok importálása fájlkezeléshez és DOM feldolgozáshoz
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMReadX16WW1 {

    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("XMLX16WW1.xml"); // Az XML fájl helyének megadása
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // DOM parser gyár példányosítása
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // Dokumentum építő létrehozása

            Document doc = dBuilder.parse(xmlFile); // XML dokumentum betöltése
            doc.getDocumentElement().normalize(); // Dokumentum normalizálása (pl. whitespace-ek eltávolítása)

            // A gyökérelem (root element) nevének kiírása
            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            // ---------------- Cégek bejárása ----------------
            NodeList cegList = doc.getElementsByTagName("Ceg"); // 'Ceg' elemek lekérése
            System.out.println("\n-- Cégek --");
            for (int i = 0; i < cegList.getLength(); i++) {
                Element ceg = (Element) cegList.item(i); // Egy adott 'Ceg' elem kiválasztása
                // A cég attribútumainak és al-elemeinek kiírása
                System.out.println("Cid: " + ceg.getAttribute("Cid"));
                System.out.println("Név: " + ceg.getElementsByTagName("nev").item(0).getTextContent());
                Element cim = (Element) ceg.getElementsByTagName("cim").item(0); // 'cim' elem lekérése
                System.out.println("Cím: " + cim.getElementsByTagName("varos").item(0).getTextContent() + ", " +
                        cim.getElementsByTagName("utca").item(0).getTextContent() + " " +
                        cim.getElementsByTagName("hazszam").item(0).getTextContent());
                System.out.println("Típus: " + ceg.getElementsByTagName("tipus").item(0).getTextContent());
                System.out.println();
            }

            // ---------------- Tárolók bejárása ----------------
            NodeList tarList = doc.getElementsByTagName("Tar"); // 'Tar' elemek lekérése
            System.out.println("-- Tárolók --");
            for (int i = 0; i < tarList.getLength(); i++) {
                Element tar = (Element) tarList.item(i);
                // Tároló attribútumainak és al-elemeinek kiírása
                System.out.println("Sid: " + tar.getAttribute("Sid") + ", Céges azonosító: " + tar.getAttribute("c_s"));
                System.out.println("Név: " + tar.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Polcszám: " + tar.getElementsByTagName("polcszam").item(0).getTextContent());
                System.out.println("Érkezési dátum: " + tar.getElementsByTagName("erkezesidatum").item(0).getTextContent());
                System.out.println();
            }

            // ---------------- Főfelügyelők bejárása ----------------
            NodeList felugyeloList = doc.getElementsByTagName("Föfelügyelö");
            System.out.println("-- Főfelügyelők --");
            for (int i = 0; i < felugyeloList.getLength(); i++) {
                Element fel = (Element) felugyeloList.item(i);
                // Főfelügyelő attribútumainak és al-elemeinek kiírása
                System.out.println("Fid: " + fel.getAttribute("Fid") + ", Raktár: " + fel.getAttribute("s_f"));
                System.out.println("Név: " + fel.getElementsByTagName("nev").item(0).getTextContent());
                // Több telefonszám bejárása
                NodeList telefonok = fel.getElementsByTagName("telefon");
                for (int j = 0; j < telefonok.getLength(); j++) {
                    System.out.println("Telefon: " + telefonok.item(j).getTextContent());
                }
                System.out.println();
            }

            // ---------------- Termékek bejárása ----------------
            NodeList termekList = doc.getElementsByTagName("Termek");
            System.out.println("-- Termékek --");
            for (int i = 0; i < termekList.getLength(); i++) {
                Element termek = (Element) termekList.item(i);
                // Termék attribútumainak és al-elemeinek kiírása
                System.out.println("Iid: " + termek.getAttribute("Iid") + ", Raktár: " + termek.getAttribute("s_i"));
                System.out.println("Név: " + termek.getElementsByTagName("nev").item(0).getTextContent());
                System.out.println("Kategória: " + termek.getElementsByTagName("kategoria").item(0).getTextContent());
                System.out.println("Mennyiség: " + termek.getElementsByTagName("menyiseg").item(0).getTextContent());
                System.out.println("Ár: " + termek.getElementsByTagName("ar").item(0).getTextContent());
                System.out.println();
            }

            // ---------------- Rendelések bejárása ----------------
            NodeList rendelesList = doc.getElementsByTagName("Rendeles");
            System.out.println("-- Rendelések --");
            for (int i = 0; i < rendelesList.getLength(); i++) {
                Element rendeles = (Element) rendelesList.item(i);
                // Rendeléshez kapcsolódó attribútumok és al-elemek kiírása
                System.out.println("Termék ID: " + rendeles.getAttribute("d_i_i") + ", Szállítás ID: " + rendeles.getAttribute("d_i_d"));
                System.out.println("Mennyiség: " + rendeles.getElementsByTagName("menyiseg").item(0).getTextContent());
                System.out.println("Cím: " + rendeles.getElementsByTagName("cim").item(0).getTextContent());
                System.out.println();
            }

            // ---------------- Szállítások bejárása ----------------
            NodeList szallitasList = doc.getElementsByTagName("Szallitas");
            System.out.println("-- Szállítások --");
            for (int i = 0; i < szallitasList.getLength(); i++) {
                Element szallitas = (Element) szallitasList.item(i);
                // Szállítás részleteinek kiírása
                System.out.println("Did: " + szallitas.getAttribute("Did"));
                System.out.println("Státusz: " + szallitas.getElementsByTagName("statusz").item(0).getTextContent());
                System.out.println("Érkezési dátum: " + szallitas.getElementsByTagName("erkezesidatum").item(0).getTextContent());
                System.out.println();
            }

        } catch (Exception e) {
            // Hibakezelés: a kivétel stack trace-ének kiírása
            e.printStackTrace();
        }
    }
}
