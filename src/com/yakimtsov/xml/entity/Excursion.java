//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0-b170531.0717 
//         See <a href="https://jaxb.java.net/">https://jaxb.java.net/</a> 
//         Any modifications to this file will be lost upon recompilation of the source schema. 
//         Generated on: 2018.03.07 at 07:48:41 PM MSK 
//


package com.yakimtsov.xml.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Excursion", propOrder = {
    "excursionLanguage"
})
public class Excursion
    extends Voucher
{

    @XmlElement(name = "excursion-language", required = true)
    protected String excursionLanguage;

    public String getExcursionLanguage() {
        return excursionLanguage;
    }

    public void setExcursionLanguage(String value) {
        this.excursionLanguage = value;
    }

}
