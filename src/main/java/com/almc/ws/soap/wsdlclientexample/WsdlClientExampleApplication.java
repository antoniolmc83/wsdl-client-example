package com.almc.ws.soap.wsdlclientexample;

import com.almc.ws.soap.wsdlclientexample.services.CurrencyWSDLService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsdlClientExampleApplication {




	public static void main(String[] args) {
		SpringApplication.run(WsdlClientExampleApplication.class, args);
	}

	@Bean
	public String text(CurrencyWSDLService currencyWSDLService) {

//		currencyWSDLService.getHistoricalValue();
		currencyWSDLService.getCountry();

		return "";
	}

}
