//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.03.08 at 06:56:51 PM MSK 
//


package com.yakimtsov.xml.entity;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Voucher complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Voucher"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}ID"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="days-number" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="transport" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Voucher", propOrder = {
    "id",
    "country",
    "daysNumber",
    "cost"
})
@XmlSeeAlso({
    Journey.class,
    Excursion.class
})
public abstract class Voucher {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;
    @XmlElement(required = true)
    private String country;
    @XmlElement(name = "days-number", required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer daysNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer cost;
    @XmlAttribute(name = "transport")
    private String transport;


    public String getId() {
        return id;
    }


    public void setId(String value) {
        this.id = value;
    }


    public Object getCountry() {
        return country;
    }


    public void setCountry(String value) {
        this.country = value;
    }


    public Integer getDaysNumber() {
        return daysNumber;
    }


    public void setDaysNumber(Integer value) {
        this.daysNumber = value;
    }


    public Integer getCost() {
        return cost;
    }


    public void setCost(Integer value) {
        this.cost = value;
    }


    public String getTransport() {
        return transport;
    }

    public void setTransport(String value) {
        this.transport = value;
    }

}