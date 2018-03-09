package com.yakimtsov.xml;

import com.yakimtsov.xml.entity.*;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VouchersStaxParser {

    private XMLInputFactory inputFactory;

    public VouchersStaxParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

//    public ArrayList<Voucher> getVouchers() {
//        return vouchers;
//    }

    public ArrayList<Voucher> parse(File file) throws ParseException {
        ArrayList<Voucher> vouchers = new ArrayList<>();
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;

        try {
            inputStream = new FileInputStream(file);
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    // System.out.println("NAME " + name);
                    if ("journey".equals(name) || "excursion".equals(name)) {
                        Voucher voucher = buildVoucher(reader, name);
                        vouchers.add(voucher);
                    }
                    //   System.out.println("TEXT " + reader.getText());

                }

            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ParseException(e);
        }
        return vouchers;
    }

    private Voucher buildVoucher(XMLStreamReader reader, String voucherType) throws XMLStreamException, ParseException {
        Voucher voucher;
        if ("journey".equals(voucherType)) {
            voucher = new Journey();
        } else {
            voucher = new Excursion();
        }
        String transport = reader.getAttributeValue(null, "transport");
        voucher.setTransport(transport);
        // System.out.println("VOUCHER " + voucherType);
        String name = "";
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "id":
                        voucher.setId(getXMLText(reader));
                        break;
                    case "country":
                        voucher.setCountry(getXMLText(reader));
                        break;
                    case "days-number":
                        voucher.setDaysNumber(Integer.valueOf(getXMLText(reader)));
                        break;
                    case "cost":
                        voucher.setCost(Integer.valueOf(getXMLText(reader)));
                        break;
                    case "excursion-language":
                        ((Excursion) voucher).setExcursionLanguage(getXMLText(reader));
                        break;
                    case "hotel":
                        Hotel hotel = buildHotel(reader);
                        ((Journey) voucher).setHotel(hotel);
                        break;
                }
            }
            if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if ("journey".equals(name) || "excursion".equals(name)) {
                    return voucher;
                }
            }
        }

        throw new ParseException("Unknown element in " + voucherType + "tag");
    }

    private Hotel buildHotel(XMLStreamReader reader) throws XMLStreamException {
        Hotel hotel = new Hotel();
        String hotelName = reader.getAttributeValue(null, "name");
        String hotelRate = reader.getAttributeValue(null, "rate");
        hotel.setName(hotelName);
        hotel.setRate(Integer.valueOf(hotelRate));
        String name = "";
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                switch (name) {
                    case "meal":
                        hotel.setMeal(Meal.fromValue(getXMLText(reader)));
                        break;
                    case "apartment-type":
                        hotel.setApartmentType(ApartmentType.fromValue(getXMLText(reader)));
                        break;
                    case "apartment-size":
                        hotel.setApartmentSize(Integer.valueOf(getXMLText(reader)));
                        break;
                    case "email":
                        hotel.setEmail(getXMLText(reader));
                        break;
                }
            }
            if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();
                if ("hotel".equals(name)) {
                    return hotel;
                }
            }
        }
        return hotel;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
