package com.almc.ws.soap.wsdlclientexample.services;

import com.almc.ws.soap.wsdlclientexample.wsdl.ConvertHistoricalValue;
import com.almc.ws.soap.wsdlclientexample.wsdl.ConvertHistoricalValueResponse;
import com.almc.ws.soap.wsdlclientexample.wsdl.Header;
import com.almc.ws.soap.wsdlclientexample.wsdl.ObjectFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.xml.transform.StringSource;

import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;


public class CurrencyWSDLService extends WebServiceGatewaySupport {


    public void getHistoricalValue() {

        ConvertHistoricalValue req = new ConvertHistoricalValue();
        req.setAmount(100);
        req.setAsOfDate("12/13/2019");
        req.setFixingTime("22:00");
        req.setFrom("EUR");
        req.setTo("USD");

        Header header = new Header();
        header.setUsername("B3B4C187FBA044CC93A83931908301E7");
        header.setTracer("aaaaa");




        ConvertHistoricalValueResponse res = (ConvertHistoricalValueResponse)getWebServiceTemplate()
                .marshalSendAndReceive(req, new SoapActionCallback("http://www.xignite.com/services/ConvertHistoricalValue") {
                    @Override
                    public void doWithMessage(WebServiceMessage message) throws IOException {
                        SoapMessage soapMessage = (SoapMessage)message;
                        SoapHeader header = soapMessage.getSoapHeader();


                        StringSource headerSource = new StringSource("<ser:Header xmlns:ser=\"http://www.xignite.com/services/\">\n" +
                                "<ser:Username>"+"B3B4C187FBA044CC93A83931908301E7"+"</ser:Username>\n" +
                                "<ser:Tracer>"+"111"+"</ser:Tracer>\n" +
                                "</ser:Header>");
                        try {
                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                            transformer.transform(headerSource, header.getResult());



                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        super.doWithMessage(message);

                    }
                });


        System.out.println(res);

        System.out.println(res.getConvertHistoricalValueResult().getMessage());
        System.out.println(res.getConvertHistoricalValueResult().getOutcome());

    }

}
