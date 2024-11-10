//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v4.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
//


package com.project.test.parameta.empleadorest.soap;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.datatype.XMLGregorianCalendar;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.NAMESPACE;


/**
 * <p>Clase Java para EmpleadoElement complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="EmpleadoElement">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="codigoEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="apellidosEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="numeroDocumentoEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaNacimientoEmpleado" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         <element name="fechaVinculacionEmpleado" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         <element name="cargoFk" type="{http://example.com/soap}CargoElement" minOccurs="0"/>
 *         <element name="tipoDocumentoFk" type="{http://example.com/soap}TipoDocumentoElement" minOccurs="0"/>
 *         <element name="correoEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="salarioEmpleado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         <element name="hashPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmpleadoElement", propOrder = {
    "codigoEmpleado",
    "nombreEmpleado",
    "apellidosEmpleado",
    "numeroDocumentoEmpleado",
    "fechaNacimientoEmpleado",
    "fechaVinculacionEmpleado",
    "cargoFk",
    "tipoDocumentoFk",
    "correoEmpleado",
    "salarioEmpleado",
    "hashPassword"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoElement {

    @XmlElement(name = "codigoEmpleado", namespace = NAMESPACE)
    protected String codigoEmpleado;

    @XmlElement(name = "nombreEmpleado", namespace = NAMESPACE)
    protected String nombreEmpleado;

    @XmlElement(name = "apellidosEmpleado", namespace = NAMESPACE)
    protected String apellidosEmpleado;

    @XmlElement(name = "numeroDocumentoEmpleado", namespace = NAMESPACE)
    protected String numeroDocumentoEmpleado;

    @XmlSchemaType(name = "date")
    @XmlElement(name = "fechaNacimientoEmpleado", namespace = NAMESPACE)
    protected XMLGregorianCalendar fechaNacimientoEmpleado;

    @XmlSchemaType(name = "date")
    @XmlElement(name = "fechaVinculacionEmpleado", namespace = NAMESPACE)
    protected XMLGregorianCalendar fechaVinculacionEmpleado;

    @XmlElement(name = "cargoFk", namespace = NAMESPACE)
    protected CargoElement cargoFk;

    @XmlElement(name = "tipoDocumentoFk", namespace = NAMESPACE)
    protected TipoDocumentoElement tipoDocumentoFk;

    @XmlElement(name = "correoEmpleado", namespace = NAMESPACE)
    protected String correoEmpleado;

    @XmlElement(name = "salarioEmpleado", namespace = NAMESPACE)
    protected Double salarioEmpleado;

    @XmlElement(name = "hashPassword", namespace = NAMESPACE)
    protected String hashPassword;


}
