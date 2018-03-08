package com.yakimtsov.xml;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.xml.sax.SAXException;

import java.io.File; // if you use File
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

        VouchersDomParser parser = new VouchersDomParser();

        try {
            parser.parse(sourceFile);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            VouchersSaxParser saxParser = new VouchersSaxParser();
          //  System.out.println(sourceFile.getAbsolutePath());
            saxParser.parse(sourceFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            JAXBContext jc = JAXBContext.newInstance(TouristVouchers.class);
//            Unmarshaller u = jc.createUnmarshaller();
//            FileReader reader = new FileReader("data/vouchers.xml");
//            TouristVouchers vouchers = (TouristVouchers) u.unmarshal(reader);
//            vouchers.getVoucher().forEach(System.out::println);
//        } catch (JAXBException | FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

}

