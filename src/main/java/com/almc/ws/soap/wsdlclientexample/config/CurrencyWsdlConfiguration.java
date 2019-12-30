package com.almc.ws.soap.wsdlclientexample.config;

import com.almc.ws.soap.wsdlclientexample.services.CurrencyWSDLService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CurrencyWsdlConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.almc.ws.soap.wsdlclientexample.wsdl");

        return marshaller;
    }

    @Bean
    public CurrencyWSDLService countryClient(Jaxb2Marshaller marshaller) {
        CurrencyWSDLService client = new CurrencyWSDLService();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        return client;
    }
}
