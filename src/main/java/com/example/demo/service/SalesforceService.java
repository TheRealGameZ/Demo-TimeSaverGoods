package com.example.demo.service;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.util.concurrent.CompletableFuture;


@Service
public class SalesforceService 
{

    private static String accessToken;
    private static final String MAINURL = "https://resilient-bear-3oxray-dev-ed.trailblaze.my.salesforce.com/services/data/v57.0/commerce/webstores/0ZE68000000CwFKGA0";
    private static final String LOGIN_URL = "https://login.salesforce.com/services/Soap/u/56.0";
    
    private SalesforceService() 
    {
    }

    //Send HTTP-Requests to Salesforce
    public static ResponseEntity<String> salesforceApiCall(String url, String body, HttpMethod method)
    {         
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        try {
            if (accessToken == null || accessToken.equals("")) login();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);

        try{

            return restTemplate.exchange(MAINURL + url, method, entity, String.class);   
        } catch(Error e)
        {
            System.out.println(e.getMessage());

            CompletableFuture<Void> loginFuture = CompletableFuture.runAsync(() -> {
                login();
            });

            loginFuture.join();

            return restTemplate.exchange(MAINURL + url, method, entity, String.class);   
        }

    }

    //Send Patch-Requests to Salesforce
    public static String patchRequest(String url, String body)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(MAINURL + url);

        try 
        {
            StringEntity requestBody = new StringEntity(body);
                 
            httpPatch.setEntity(requestBody);
            httpPatch.setHeader("Content-type", "application/json");
            httpPatch.setHeader("Authorization", "Bearer "+accessToken);
            CloseableHttpResponse response = httpClient.execute(httpPatch);

            return response.toString();

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return null;

        } finally
        {
            try 
            {
                httpClient.close();
            } catch (IOException e) 
            {
                
                e.printStackTrace();
            }
        }
    }

    //Get Access via SOAP
    public static void login()
    {

        System.out.println("Token wird geholt");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", "login");

        HttpEntity<String> entity = new HttpEntity<>(setXML(), headers);
        RestTemplate restTemplate = new RestTemplate();

        try 
        {
            String response = restTemplate.postForObject(LOGIN_URL, entity, String.class);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(response));
            Document doc = builder.parse(src);

            NodeList sessionIdNode = doc.getElementsByTagName("sessionId");

            if (sessionIdNode.getLength() > 0) {
                Node node = sessionIdNode.item(0);
                accessToken = node.getTextContent();
            }

            System.out.println("Token ready: " + accessToken);

        } catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }  
    }

    //Create XML-File for SOAP-Request
    public static String setXML()
    {
        String userName = "a_young@dickenson.com";
        String password = "Der1Die2Das3";
        
        String XML_BODY = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:partner.soap.sforce.com\">\n" +
            "\n" +
            "<soapenv:Header>\n" +
            "\n" +
            "<urn:LoginScopeHeader>\n" +
            "\n" +
            "<urn:organizationId>00D68000004DKj3</urn:organizationId>\n" +
            "\n" +
            "</urn:LoginScopeHeader>\n" +
            "\n" +
            "</soapenv:Header>\n" +
            "\n" +
            "<soapenv:Body>\n" +
            "\n" +
            "<urn:login>\n" +
            "\n" +
            "<urn:username><![CDATA["+userName+"]]></urn:username>\n" +
            "\n" +
            "<urn:password><![CDATA["+password+"]]></urn:password>\n" +
            "\n" +
            "</urn:login>\n" +
            "\n" +
            "</soapenv:Body>\n" +
            "\n" +
            "</soapenv:Envelope>";

            return XML_BODY;
    }

}