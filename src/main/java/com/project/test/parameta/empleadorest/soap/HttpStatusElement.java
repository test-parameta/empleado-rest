//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package com.project.test.parameta.empleadorest.soap;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;


/**
 * <p>Clase Java para HttpStatusElement.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="HttpStatusElement">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="OK"/>
 *     <enumeration value="CREATED"/>
 *     <enumeration value="BAD_REQUEST"/>
 *     <enumeration value="UNAUTHORIZED"/>
 *     <enumeration value="FORBIDDEN"/>
 *     <enumeration value="NOT_FOUND"/>
 *     <enumeration value="INTERNAL_SERVER_ERROR"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "HttpStatusElement")
@XmlEnum
public enum HttpStatusElement {

    OK,
    CREATED,
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    INTERNAL_SERVER_ERROR;

    public String value() {
        return name();
    }

    public static HttpStatusElement fromValue(String v) {
        return valueOf(v);
    }

}
