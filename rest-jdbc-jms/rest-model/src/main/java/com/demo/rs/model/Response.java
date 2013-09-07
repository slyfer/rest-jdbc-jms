
package com.demo.rs.model;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
    "id",
    "timestamp"
})
@XmlRootElement(name = "response")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2013-09-06T06:26:28+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
public class Response {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2013-09-06T06:26:28+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected String id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2013-09-06T06:26:28+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    protected XMLGregorianCalendar timestamp;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2013-09-06T06:26:28+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2013-09-06T06:26:28+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2013-09-06T06:26:28+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2013-09-06T06:26:28+02:00", comments = "JAXB RI vJAXB 2.1.10 in JDK 6")
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

}
