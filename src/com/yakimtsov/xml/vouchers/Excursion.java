//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.03.07 at 07:48:41 PM MSK 
//


package com.yakimtsov.xml.vouchers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Excursion complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Excursion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.example.com/vouchers}Voucher"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="excursion-language" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Excursion", propOrder = {
    "excursionLanguage"
})
public class Excursion
    extends Voucher
{

    @XmlElement(name = "excursion-language", required = true)
    protected String excursionLanguage;

    /**
     * Gets the value of the excursionLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExcursionLanguage() {
        return excursionLanguage;
    }

    /**
     * Sets the value of the excursionLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExcursionLanguage(String value) {
        this.excursionLanguage = value;
    }

}