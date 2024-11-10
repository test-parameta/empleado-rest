//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package com.project.test.parameta.empleadorest.soap;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.NAMESPACE;


/**
 * <p>Clase Java para RespuestaGeneralElement complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="RespuestaGeneralElement">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="status" type="{http://example.com/soap}HttpStatusElement" minOccurs="0"/>
 *         <element name="data" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         <element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaGeneralElement", propOrder = {
    "status",
    "data",
    "message"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaGeneralElement {

    @XmlSchemaType(name = "string")
    @XmlElement(name = "status", namespace = NAMESPACE)
    protected HttpStatusElement status;
    @XmlElement(name = "data", namespace = NAMESPACE)
    protected String data;
    @XmlElement(name = "message", namespace = NAMESPACE)
    protected String message;
}
