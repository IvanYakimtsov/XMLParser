package com.yakimtsov.xml;

import com.yakimtsov.xml.vouchers.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;

import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VouchersSaxParser extends DefaultHandler {
    private SAXParser saxParser;
    private String currentTag;
    private ArrayList<Voucher> vouchers = new ArrayList<>();
    private Voucher currentVoucher;
    private Hotel currentHotel;

    public VouchersSaxParser() throws ParseException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new ParseException(e);
        }
    }

    public ArrayList<Voucher> parse(File file) throws ParseException {
        try {
            saxParser.parse(file.getAbsolutePath(), this);
        } catch (SAXException | IOException e) {
            throw new ParseException(e);
        }

        return vouchers;
    }

    @Override
    public void startDocument() throws SAXException {
     //   System.out.println("start document");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    //    System.out.println("NEW ELEMENT");
     //   System.out.println("name " + qName);
        currentTag = qName;
      //  System.out.println("CURRENT TAG " + currentTag);
        switch (currentTag) {
            case "journey":
                currentVoucher = new Journey();
                currentVoucher.setTransport(attributes.getValue(0));
                vouchers.add(currentVoucher);
                break;
            case "excursion":
                currentVoucher = new Excursion();
                currentVoucher.setTransport(attributes.getValue(0));
                vouchers.add(currentVoucher);
                break;

            case "hotel":
                currentHotel = new Hotel();
                ((Journey) currentVoucher).setHotel(currentHotel);
                String firstAttr = attributes.getValue(0);
                String secondAttr = attributes.getValue(1);
                if ("name".equals(attributes.getQName(0))) {
                    currentHotel.setName(firstAttr);
                    currentHotel.setRate(Integer.valueOf(secondAttr));
                  //  System.out.println("HOTEL " + firstAttr +" " + secondAttr + " CASE 1");
                } else {
                    currentHotel.setRate(Integer.valueOf(firstAttr));
                    currentHotel.setName(secondAttr);
                //    System.out.println("HOTEL " + firstAttr +" " + secondAttr + " CASE 2");
                }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        //System.out.println("CH " + new String(ch, start, length));
        //System.out.println("chacracters method");
        switch (currentTag) {
            case "id":
                currentVoucher.setId(new String(ch, start, length));
                break;
            case "country":
                currentVoucher.setCountry(new String(ch, start, length));
                break;
            case "days-number":
                currentVoucher.setDaysNumber(Integer.valueOf(new String(ch, start, length)));
                break;
            case "cost":
                currentVoucher.setCost(Integer.valueOf(new String(ch, start, length)));
                break;
            case "meal":
                currentHotel.setMeal(Meal.fromValue(new String(ch, start, length)));
                break;
            case "apartment-type":
                currentHotel.setApartmentType(ApartmentType.fromValue(new String(ch, start, length)));
                break;
            case "apartment-size":
                currentHotel.setApartmentSize(Integer.valueOf(new String(ch, start, length)));
                break;
            case "email":
                currentHotel.setEmail(new String(ch, start, length));
                break;
            case "excursion-language":
                ((Excursion)currentVoucher).setExcursionLanguage(new String(ch, start, length));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       // System.out.println("END EL " + qName);
        super.endElement(uri, localName, qName);
        currentTag = "";
        if("journey".equals(qName)){
            System.out.println(currentVoucher.getId() + " " + currentVoucher.getCountry() + " " +
                    currentVoucher.getTransport() + " " + currentVoucher.getDaysNumber() + " " + currentVoucher.getCost()
                    + " " + ((Journey)currentVoucher).getHotel().getName() + " "
                    + ((Journey)currentVoucher).getHotel().getRate() + " "
                    + ((Journey)currentVoucher).getHotel().getMeal()
                    +" " + ((Journey)currentVoucher).getHotel().getApartmentSize()
                    + " " + ((Journey)currentVoucher).getHotel().getEmail() +
                    " " + ((Journey)currentVoucher).getHotel().getApartmentType());
        }

        if("excursion".equals(qName)){
            System.out.println(currentVoucher.getId() + " " + currentVoucher.getCountry() + " " +
                    currentVoucher.getTransport() + " " + currentVoucher.getDaysNumber() + " " + currentVoucher.getCost()
                    + " " + ((Excursion)currentVoucher).getExcursionLanguage());
        }
//        if ("journey".equals(qName) || "excursion".equals(qName)) {
//            vouchers.add(currentVoucher);
//        }
    }

    @Override
    public void endDocument() {
     //   System.out.println("end document");
    }

}
