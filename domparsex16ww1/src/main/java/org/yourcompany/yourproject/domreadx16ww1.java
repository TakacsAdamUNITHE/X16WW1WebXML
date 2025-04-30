package org.yourcompany.yourproject;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class domreadx16ww1 {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException{
        File xmlFile = new File("X16WW1XML.xml");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();
    
        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("etterem");

        for (int i = 0; i< nList.getLength(); i++){
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){

                Element elem = (Element) nNode;
                
                String id = elem.getAttribute("ekod");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("varos").item(0);
                String city = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("utca").item(0);
                String street = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("hazszam").item(0);
                String number = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("csillag").item(0);
                String stars = node5.getTextContent();

                String adr = city + ", " + street + " utca " + number + ".";

                System.out.println("Étterem ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Cím: " + adr);
                System.out.println("Csillag: " + stars);

            }
        }

        nList = doc.getElementsByTagName("foszakacs");

        for (int i = 0; i< nList.getLength(); i++){
            
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){

                Element elem = (Element) nNode;
                
                String id = elem.getAttribute("fkod");
                String eid = elem.getAttribute("e_f");

                String work = "Ez a főszakács a(z) " + eid + " azonosítójú étteremben dolgozik.";
               
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("eletkor").item(0);
                String age = node2.getTextContent();

                Node node3;
                String edu = "";

                for(int j=0;j<elem.getElementsByTagName("vegzettseg").getLength();j++){
                    node3 = elem.getElementsByTagName("vegzettseg").item(j);
                    if(j== elem.getElementsByTagName("vegzettseg").getLength()-1) {
                        edu += node3.getTextContent();
                    }else {
                        edu += node3.getTextContent() + ", ";
                    }
                }

                System.out.println("Főszakács ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Kor: " + age);
                System.out.println("Végzettségek " + edu);
                System.out.println(work);
            }

        }

        nList = doc.getElementsByTagName("szakacs");

        for (int i = 0; i< nList.getLength(); i++){
            
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){

                Element elem = (Element) nNode;
                
                String id = elem.getAttribute("szkod");
                String eid = elem.getAttribute("e_sz");

                String work = "Ez a szakács a(z) " + eid + " azonosítójú étteremben dolgozik.";
               
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("reszleg").item(0);
                String department = node2.getTextContent();

                Node node3;
                String edu = "";

                for(int j=0;j<elem.getElementsByTagName("vegzettseg").getLength();j++){
                    node3 = elem.getElementsByTagName("vegzettseg").item(j);
                    if(j== elem.getElementsByTagName("vegzettseg").getLength()-1) {
                        edu += node3.getTextContent();
                    }else {
                        edu += node3.getTextContent() + ", ";
                    }
                }

                System.out.println("Főszakács ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Részleg: " + department);
                System.out.println("Végzettségek " + edu);
                System.out.println(work);
            }

        }
        
        nList = doc.getElementsByTagName("gyakornok");

        for (int i = 0; i< nList.getLength(); i++){
            
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){

                Element elem = (Element) nNode;
                
                String id = elem.getAttribute("gykod");
                String eid = elem.getAttribute("e_gy");

                String work = "Ez a gyakornok a(z) " + eid + " azonosítójú étteremben dolgozik.";
               
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("kezdete").item(0);
                String start = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("idotartama").item(0);
                String duration = node3.getTextContent();

                String practical = "kezdete: " + start + ", időtartama: " + duration;

                Node node4;
                String shift = "";

                for (int j=0; j<elem.getElementsByTagName("muszak").getLength();j++){
                    node4 = elem.getElementsByTagName("muszak").item(j);
                    if (j == elem.getElementsByTagName("muszak").getLength()-1){
                        shift += node4.getTextContent();
                    } else{
                        shift += node4.getTextContent() + ", ";
                    }
                }

                System.out.println("Gyakornok ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Gyakorlat: " + practical);
                System.out.println("Végzettségek " + shift);
                System.out.println(work);
            }

        }

        nList = doc.getElementsByTagName("vendeg");

        for (int i = 0; i< nList.getLength(); i++){
            
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){

                Element elem = (Element) nNode;
                
                String id = elem.getAttribute("vkod");
               
                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("eletkor").item(0);
                String age = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("varos").item(0);
                String city = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("utca").item(0);
                String street = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("hazszam").item(0);
                String number = node5.getTextContent();

                String adr = city + " " + street + " utca " + number + ".";

                System.out.println("Vendég ID: " + id);
                System.out.println("Név: " + name);
                System.out.println("Kor: " + age);
                System.out.println("Cím " + adr);
            }

        }

        nList = doc.getElementsByTagName("rendeles");

        for (int i = 0; i< nList.getLength(); i++){
            
            Node nNode = nList.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE){

                Element elem = (Element) nNode;
                
                String eid = elem.getAttribute("e_v_e");
                String vid = elem.getAttribute("e_v_v");

                String dinner = "A(z) " + eid + " azonosítóval rendelkező étteremből rendelt a(z) " + vid + " azonosítójú vendég.";
               
                Node node1 = elem.getElementsByTagName("osszeg").item(0);
                String price = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("etel").item(0);
                String food = node2.getTextContent();

                System.out.println(dinner);
                System.out.println("Összeg: " + price);
                System.out.println("Étel: " + food);
            }

        }
    }
}