package hu.domparse.neptunkod;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Takács Ádám (X16WW1)
 */

public class DOMModifyX16WW1 {

    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("XMLX16WW1.xml");

            // DOM parser létrehozása a fájl feldolgozásához
            DocumentBuilderFactory facotry = DocumentBuilderFactory.newInstance(); // Gyár létrehozása
            DocumentBuilder dBuilder = facotry.newDocumentBuilder(); // Dokumentum építő létrehozása
            
            Document doc = dBuilder.parse(xmlFile); // Az XML fájl beolvasása és DOM dokumentummá alakítása
            
            doc.getDocumentElement().normalize(); // A dokumentum normalizálása (pl. whitespace-ek kezelése)

            // 'Tar' elemek lekérése (tárolók)
            NodeList nList = doc.getElementsByTagName("Tar");

            // Tárolók bejárása
            for (int i = 0; i < nList.getLength(); i++){
                Node tar = doc.getElementsByTagName("Tar").item(i); // Egy adott 'Tar' csomópont
                NodeList list = tar.getChildNodes(); // A tároló alcsomópontjai (pl. név, polcszám, stb.)
                
                for (int temp = 0; temp < list.getLength(); temp++){
                    Node node = list.item(temp);
                    if (node.getNodeType() == Node.ELEMENT_NODE ) { // Csak elem típusú csomópontokkal dolgozunk
                        Element eElement = (Element) node;

                        // Polcszám "3C" értékének cseréje "3D"-re
                        if ("polcszám".equals(eElement.getNodeName())){
                            if ("3C".equals(eElement.getTextContent())){
                                eElement.setTextContent("3D");
                            }
                        }

                        // Tároló azonosító "S1" cseréje "S4"-re
                        if ("Sid".equals(eElement.getNodeName())){
                            if ("S1".equals(eElement.getTextContent())){
                                eElement.setTextContent("S4");
                            }
                        }

                        // Tároló nevének módosítása "Masodlagos Raktar B" → "Masodlagos Raktar C"
                        if ("nev".equals(eElement.getNodeName())){
                            if ("Masodlagos Raktar B".equals(eElement.getTextContent())){
                                eElement.setTextContent("Masodlagos Raktar C");
                            }
                        }

                        // Cég azonosító módosítása "C1" → "C2"
                        if ("c_s".equals(eElement.getNodeName())){
                            if ("C1".equals(eElement.getTextContent())){
                                eElement.setTextContent("C2");
                            }
                        }

                        // Érkezési dátum módosítása "2025-03-15" → "2025-04-15"
                        if ("erkezesidatum".equals(eElement.getNodeName())){
                            if ("2025-03-15".equals(eElement.getTextContent())){
                                eElement.setTextContent("2025-04-15");
                            }
                        }
                    }
                }
            }

            // A módosított dokumentum mentése és kiíratása konzolra
            TransformerFactory transformerFactory = TransformerFactory.newInstance(); // Transformer gyár
            Transformer transformer = transformerFactory.newTransformer(); // Transformer példány
            DOMSource source = new DOMSource(doc); // DOM forrás objektum létrehozása

            System.out.println("----Modified File----");
            StreamResult consoResult = new StreamResult(System.out); // Eredmény kiírása konzolra
            transformer.transform(source, consoResult); // A DOM dokumentum átalakítása és kiíratása

        } catch (Exception e) {
            // Hiba esetén a kivétel kiírása
            e.printStackTrace();
        }
    }
}
