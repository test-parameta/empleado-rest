package com.project.test.parameta.empleadorest.configuration;

import com.project.test.parameta.empleadorest.soap.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Clase de configuración para la comunicación con servicios SOAP.
 * Proporciona los beans necesarios para realizar llamadas SOAP utilizando
 * un {@link WebServiceTemplate} configurado con un {@link Jaxb2Marshaller}.
 * <p>
 * Esta configuración incluye la URL del servicio SOAP y la configuración
 * de las clases que deben ser enlazadas para la serialización y deserialización
 * de las solicitudes y respuestas SOAP.
 * </p>
 */
@Configuration
public class SoapClientConfig {

    /**
     * URL del servicio SOAP que se configurará como URI predeterminada
     * en el {@link WebServiceTemplate}.
     */
    @Value("${soap.service.url}")
    private String soapServiceUrl;

    /**
     * Crea un bean de tipo {@link Jaxb2Marshaller} para la serialización
     * y deserialización de las solicitudes y respuestas SOAP.
     * <p>
     * Este marshaller se configura para enlazar las clases:
     * {@link GuardarEmpleadoRequest} y {@link GuardarEmpleadoResponse}.
     * </p>
     *
     * @return un {@link Jaxb2Marshaller} configurado.
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
                GuardarEmpleadoRequest.class,
                GuardarEmpleadoResponse.class
        );
        return marshaller;
    }

    /**
     * Crea un bean de tipo {@link WebServiceTemplate} para realizar
     * llamadas a servicios SOAP.
     * <p>
     * Este {@link WebServiceTemplate} utiliza el {@link Jaxb2Marshaller} proporcionado
     * para la serialización y deserialización de las solicitudes y respuestas.
     * La URL predeterminada del servicio SOAP se configura con el valor
     * de la propiedad `soap.service.url`.
     * </p>
     *
     * @param marshaller el {@link Jaxb2Marshaller} configurado para la comunicación SOAP.
     * @return un {@link WebServiceTemplate} configurado.
     */
    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setDefaultUri(soapServiceUrl);
        return webServiceTemplate;
    }
}
