package com.aerolinea.util;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Fecha {
    
    public static XMLGregorianCalendar getXmlGregorianCalendarFromDate(final Date date) throws DatatypeConfigurationException{	
        GregorianCalendar calendar = new GregorianCalendar();	
        calendar.setTime(date);	
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);	
    }
    
}
