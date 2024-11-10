package com.project.test.parameta.empleadorest.utils.constants;

/**
 * Clase que contiene las constantes utilizadas en el sistema.
 * <p>
 * Esta clase centraliza los mensajes, valores y nombres clave para facilitar
 * su reutilización y mantenimiento en diferentes partes del proyecto.
 * </p>
 */
public class Constantes {

    /**
     * Mensaje para indicar que el empleado fue guardado exitosamente.
     */
    public static final String MENSAJE_OK_GUARDAR_EMPLEADO = "Se guardó exitosamente el empleado.";

    /**
     * Mensaje de error al enviar la solicitud SOAP.
     */
    public static final String MENSAJE_ERROR_GUARDAR_EMPLEADO = "Error al enviar la solicitud SOAP: {}";

    /**
     * Mensaje genérico para indicar un error al procesar una solicitud.
     */
    public static final String ERROR_PROCESAR_SOLICITUD = "Error al procesar la solicitud.";

    /**
     * Mensaje para indicar que la persona ingresada no es mayor de edad.
     */
    public static final String PERSONA_NO_MAYOR_EDAD = "La persona ingresada no es mayor de edad.";

    /**
     * Mensaje para indicar que la persona ya existe en el sistema.
     */
    public static final String PERSONA_EXISTE_SISTEMA = "La persona ya existe en el sistema.";

    /**
     * Edad mínima requerida para ser considerado mayor de edad.
     */
    public static final Integer EDAD_MAYOR = 18;

    /**
     * Mensaje para indicar un inicio de sesión exitoso.
     */
    public static final String INICIO_EXITOSO = "Inicio de sesión exitoso.";

    /**
     * Mensaje para indicar un error durante la autenticación.
     */
    public static final String ERROR_AUTENTICACION = "Error en la autenticación: {}";

    /**
     * Mensaje para indicar problemas con las credenciales.
     */
    public static final String ERROR_CREDENCIALES = "Hubo un problema con las credenciales.";

    /**
     * Identificador para operaciones relacionadas con servicios REST.
     */
    public static final String REST = "rest";

    /**
     * Espacio de nombres utilizado en servicios SOAP.
     */
    public static final String NAMESPACE = "http://example.com/soap";

    /**
     * Mensaje para indicar que la consulta de empleados fue exitosa.
     */
    public static final String CONSULTA_CORRECTA_EMPLEADOS = "Se consultaron correctamente todos los empleados.";

    /**
     * Mensaje para indicar un error al consultar empleados.
     */
    public static final String ERROR_CONSULTA_EMPLEADOS = "Error al consultar los empleados: {}";

    /**
     * Mensaje genérico para indicar un error durante la consulta de empleados.
     */
    public static final String MENSAJE_ERROR_CONSULTA_EMPLEADOS = "Hubo un error en la consulta de los empleados.";

}
