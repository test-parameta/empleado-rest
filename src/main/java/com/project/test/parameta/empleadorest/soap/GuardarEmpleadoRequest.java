package com.project.test.parameta.empleadorest.soap;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import static com.project.test.parameta.empleadorest.utils.constants.Constantes.NAMESPACE;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"empleado"}
)
@XmlRootElement(
        name = "guardarEmpleadoRequest",
        namespace = NAMESPACE
)
@Data
public class GuardarEmpleadoRequest {

    @XmlElement(name = "empleado", namespace = NAMESPACE)
    protected EmpleadoElement empleado;

}
