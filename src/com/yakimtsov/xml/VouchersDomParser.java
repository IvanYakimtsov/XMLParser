package com.yakimtsov.xml;

import com.yakimtsov.xml.vouchers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VouchersDomParser {
//    private final int ID_NUMBER = 1;
//    private final int ID_COUNTRY = 3;
//    private final int DAYS_NUMBER = 7;

    Document doc;

    public ArrayList<Voucher> parse(File file) throws ParseException {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = null;
        try {
            builder = f.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParseException(e);
        }
        try {
            doc = builder.parse(file);
        } catch (SAXException | IOException e) {
            throw new ParseException(e);
        }
        NodeList journeyList = doc.getElementsByTagName("journey");
        NodeList excursionList = doc.getElementsByTagName("excursion");
        // System.out.println(rootElement.getNodeName());
        // NodeList vouchers = rootElement.getChildNodes();
        for (int index = 0; index < journeyList.getLength(); index++) {
            vouchers.add(buildJourney(journeyList.item(index)));
        }

        for (int index = 0; index < excursionList.getLength(); index++) {
            vouchers.add(buildExcursion(excursionList.item(index)));
        }

        return vouchers;
    }

    private Excursion buildExcursion(Node node) {
        Excursion excursion = new Excursion();
        NodeList excursionNodes = node.getChildNodes();
        fillVoucher(excursion,node);
        excursion.setExcursionLanguage(excursionNodes.item(9).getTextContent());
        System.out.println(excursion.getId() + " " + excursion.getCountry() + " " +
                excursion.getTransport() + " " + excursion.getDaysNumber() + " " + excursion.getCost()
                + " " + excursion.getExcursionLanguage());
        return excursion;
    }


    private Journey buildJourney(Node node) {
        Journey journey = new Journey();
        NodeList journeyNodes = node.getChildNodes();
        fillVoucher(journey,node);
        journey.setHotel(buildHotel(journeyNodes.item(9)));

        System.out.println(journey.getId() + " " + journey.getCountry() + " " +
        journey.getTransport() + " " + journey.getDaysNumber() + " " + journey.getCost()
        + " " + journey.getHotel().getName() + " "+ journey.getHotel().getRate() + " " + journey.getHotel().getMeal()
        +" " + journey.getHotel().getApartmentSize() + " " + journey.getHotel().getEmail() +
                " " + journey.getHotel().getApartmentType());
        return journey;
    }

    private void fillVoucher(Voucher voucher, Node node){
        NodeList journeyNodes = node.getChildNodes();
        voucher.setTransport(((Element) node).getAttribute("transport"));
        voucher.setId(journeyNodes.item(1).getTextContent());
        voucher.setCountry(journeyNodes.item(3).getTextContent());
        voucher.setDaysNumber(Integer.valueOf(journeyNodes.item(5).getTextContent()));
        voucher.setCost(Integer.valueOf(journeyNodes.item(7).getTextContent()));
    }

    private Hotel buildHotel(Node hotelNode){
        Hotel hotel = new Hotel();
        NodeList hotelNodes = hotelNode.getChildNodes();
        hotel.setName(((Element) hotelNode).getAttribute("name"));
        hotel.setRate(Integer.valueOf(((Element) hotelNode).getAttribute("rate")));
        hotel.setMeal(Meal.fromValue(hotelNodes.item(1).getTextContent()));
        hotel.setApartmentType(ApartmentType.fromValue(hotelNodes.item(3).getTextContent()));
        hotel.setApartmentSize(Integer.valueOf(hotelNodes.item(5).getTextContent()));
        hotel.setEmail(hotelNodes.item(7).getTextContent());

        return hotel;
    }
}