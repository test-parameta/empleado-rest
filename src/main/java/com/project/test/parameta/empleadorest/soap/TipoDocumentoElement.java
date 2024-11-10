//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package com.project.test.parameta.empleadorest.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.NAMESPACE;


/**
 * <p>Clase Java para TipoDocumentoElement complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="TipoDocumentoElement">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="idTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoDocumentoElement", propOrder = {
    "idTipoDocumento",
    "nombreTipoDocumento"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoElement {

    @XmlElement(name = "idTipoDocumento", namespace = NAMESPACE)
    protected Integer idTipoDocumento;
    @XmlElement(name = "nombreTipoDocumento", namespace = NAMESPACE)
    protected String nombreTipoDocumento;

}
