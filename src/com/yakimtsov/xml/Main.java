package com.yakimtsov.xml;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import com.yakimtsov.xml.vouchers.Excursion;
import com.yakimtsov.xml.vouchers.Journey;
import com.yakimtsov.xml.vouchers.TouristVoucherList;
import org.xml.sax.SAXException;

import java.io.File; // if you use File
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        File schemaFile = new File("/D:/Epam/WebParsing/Data/vouchers.xsd");
        File sourceFile = new File("/D:/Epam/WebParsing/Data/vouchers.xml");
        Source xmlFile = new StreamSource(sourceFile);
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("DOM -----------------------------");
        VouchersDomParser parser = new VouchersDomParser();
        try {
            parser.parse(sourceFile);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("SAX -----------------------------");
        try {
            VouchersSaxParser saxParser = new VouchersSaxParser();
          //  System.out.println(sourceFile.getAbsolutePath());
            saxParser.parse(sourceFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("JAXB -----------------------------");
        try {
            JAXBContext jc = JAXBContext.newInstance(TouristVoucherList.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader("data/vouchers.xml");
            TouristVoucherList vouchers = (TouristVoucherList) u.unmarshal(reader);

            Journey journey = (Journey) vouchers.getVoucher().get(0).getValue();
            System.out.println(journey.getId() + " " + journey.getCountry() + " " +
                    journey.getTransport() + " " + journey.getDaysNumber() + " " + journey.getCost()
                    + " " + journey.getHotel().getName() + " "+ journey.getHotel().getRate() + " " + journey.getHotel().getMeal()
                    +" " + journey.getHotel().getApartmentSize() + " " + journey.getHotel().getEmail() +
                    " " + journey.getHotel().getApartmentType());
            journey = (Journey) vouchers.getVoucher().get(1).getValue();
            System.out.println(journey.getId() + " " + journey.getCountry() + " " +
                    journey.getTransport() + " " + journey.getDaysNumber() + " " + journey.getCost()
                    + " " + journey.getHotel().getName() + " "+ journey.getHotel().getRate() + " " + journey.getHotel().getMeal()
                    +" " + journey.getHotel().getApartmentSize() + " " + journey.getHotel().getEmail() +
                    " " + journey.getHotel().getApartmentType());
            Excursion excursion = (Excursion) vouchers.getVoucher().get(2).getValue();
            System.out.println(excursion.getId() + " " + excursion.getCountry() + " " +
                    excursion.getTransport() + " " + excursion.getDaysNumber() + " " + excursion.getCost()
                    + " " + excursion.getExcursionLanguage());
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

