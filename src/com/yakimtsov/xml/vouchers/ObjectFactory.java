//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.03.07 at 07:48:41 PM MSK 
//


package com.yakimtsov.xml.vouchers;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.vouchers package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Voucher_QNAME = new QName("http://www.example.com/vouchers", "voucher");
    private final static QName _Journey_QNAME = new QName("http://www.example.com/vouchers", "journey");
    private final static QName _Excursion_QNAME = new QName("http://www.example.com/vouchers", "excursion");

    public ObjectFactory() {
    }

    public TouristVoucherList createTouristVouchers() {
        return new TouristVoucherList();
    }


    public Journey createJourney() {
        return new Journey();
    }


    public Excursion createExcursion() {
        return new Excursion();
    }


    public Hotel createHotel() {
        return new Hotel();
    }


    @XmlElementDecl(namespace = "http://www.example.com/vouchers", name = "voucher")
    public JAXBElement<Voucher> createVoucher(Voucher value) {
        return new JAXBElement<Voucher>(_Voucher_QNAME, Voucher.class, null, value);
    }


    @XmlElementDecl(namespace = "http://www.example.com/vouchers", name = "journey", substitutionHeadNamespace = "http://www.example.com/vouchers", substitutionHeadName = "voucher")
    public JAXBElement<Journey> createJourney(Journey value) {
        return new JAXBElement<Journey>(_Journey_QNAME, Journey.class, null, value);
    }


    @XmlElementDecl(namespace = "http://www.example.com/vouchers", name = "excursion", substitutionHeadNamespace = "http://www.example.com/vouchers", substitutionHeadName = "voucher")
    public JAXBElement<Excursion> createExcursion(Excursion value) {
        return new JAXBElement<Excursion>(_Excursion_QNAME, Excursion.class, null, value);
    }

}
