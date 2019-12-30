package com.almc.ws.soap.wsdlclientexample.services;

//import com.almc.ws.soap.wsdlclientexample.wsdl.ConvertHistoricalValue;
//import com.almc.ws.soap.wsdlclientexample.wsdl.ConvertHistoricalValueResponse;
//import com.almc.ws.soap.wsdlclientexample.wsdl.Header;
import com.almc.ws.soap.wsdlclientexample.wsdl.GetCountryRequest;
import com.almc.ws.soap.wsdlclientexample.wsdl.GetCountryResponse;
import com.almc.ws.soap.wsdlclientexample.wsdl.ObjectFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.transform.StringSource;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;


public class CurrencyWSDLService extends WebServiceGatewaySupport {


    public void getCountry() {
        ObjectFactory objectFactory = new ObjectFactory();

        GetCountryRequest getCountryRequest = objectFactory.createGetCountryRequest();
        getCountryRequest.setName("Poland");

        try {
            MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
            SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
            WebServiceTemplate wsTemplate = getWebServiceTemplate();
            wsTemplate.setMessageFactory(saajSoapMessageFactory);

            String action = "http://example.com/TicketAgent/Country";


            GetCountryResponse getCountryResponse = (GetCountryResponse)getWebServiceTemplate().marshalSendAndReceive(getCountryRequest, new SoapActionCallback(action));
            System.out.println(getCountryResponse.getCountry().getCapital());

        } catch (Exception e) {
            e.printStackTrace();
        }







    }

/*
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
*/
}
