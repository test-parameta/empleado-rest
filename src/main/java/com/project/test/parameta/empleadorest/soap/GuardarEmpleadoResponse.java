package com.project.test.parameta.empleadorest.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.NAMESPACE;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"respuesta"}
)
@XmlRootElement(
        name = "guardarEmpleadoResponse",
        namespace = NAMESPACE
)
@Data
public class GuardarEmpleadoResponse {

    @XmlElement(name = "respuesta", namespace = NAMESPACE)
    protected RespuestaGeneralElement respuesta;

}
