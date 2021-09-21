package com.dz.itinfinity.backend_payment.services;

import com.dz.itinfinity.backend_payment.domain.DTOs.*;
import com.dz.itinfinity.backend_payment.domain.entities.Transaction;
import com.dz.itinfinity.backend_payment.domain.enums.PaymentMethod;
import com.dz.itinfinity.backend_payment.domain.enums.TransactionStatus;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
//import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.File;

import java.io.*;
import java.security.GeneralSecurityException;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GoogleDriveService {

    @Autowired
    static PaymentOverviewService paymentOverviewService=new PaymentOverviewService();


   // static TransactionRepository transactionRepository;
    private  final String APPLICATION_NAME = "Google Drive API";
    private  final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private  final String TOKENS_DIRECTORY_PATH = "tokens";

    private  final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private  final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    public Drive AuthorizedAPI() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return  service;
    }

    public String CrateFile(BuyerDto buyer, SellerDto seller, OrderDto orderDto,DeliveryDto delivery,Transaction transaction,FacteurDto facteur) throws GeneralSecurityException, IOException, ParseException {
        Drive service=AuthorizedAPI();
        Gson gson=new Gson();
      //  Bon de commande
        File orderfile = new File();
        orderfile.setName("order.txt");
        orderfile.setMimeType("text/plain");

       // Bon de livraison
        File deliveryfile = new File();
        deliveryfile.setName("delivery.txt");
        deliveryfile.setMimeType("text/plain");

        //facteur
        File facteurfile = new File();
        facteurfile.setName("facteur.txt");
        facteurfile.setMimeType("text/plain");


        //order file content

        String orderjson= gson.toJson(orderDto);

        JsonObject jsonorder = new JsonParser().parse(orderjson).getAsJsonObject();
        jsonorder.add("product",gson.toJsonTree(paymentOverviewService.getProduct(orderDto.getIdOrder())));


        String transactionjson= gson.toJson(transaction);
        JsonObject jsontransaction = new JsonParser().parse(transactionjson).getAsJsonObject();

        JsonObject OrderInfoAtach=new JsonObject();
        OrderInfoAtach.add("order",jsonorder);
        OrderInfoAtach.add("transaction",jsontransaction);

        AbstractInputStreamContent content_order = ByteArrayContent.fromString("text/plain",gson.toJson(OrderInfoAtach));

        File fileO=service.files().create(orderfile, content_order).execute();

        // delivery file content
        String deliveryjson= gson.toJson(delivery);
        JsonObject jsondelivery = new JsonParser().parse(deliveryjson).getAsJsonObject();
        jsondelivery.add("idAddress",paymentOverviewService.getAddress(delivery.getIdAddress()));

        JsonObject DeliveryInfoAtach=new JsonObject();
        DeliveryInfoAtach.add("order",jsonorder);
        DeliveryInfoAtach.add("transaction",jsontransaction);
        DeliveryInfoAtach.add("delivery",jsondelivery);
        AbstractInputStreamContent content_delivery = ByteArrayContent.fromString("text/plain",gson.toJson(DeliveryInfoAtach));

        File fileD=service.files().create(deliveryfile, content_delivery).execute();

        // facteur content

        String facteurjson= gson.toJson(facteur);
        JsonObject jsonfacteur = new JsonParser().parse(facteurjson).getAsJsonObject();


        JsonObject FacteurInfoAtach=new JsonObject();
        FacteurInfoAtach.add("order",jsonorder);
        FacteurInfoAtach.add("transaction",jsontransaction);
        FacteurInfoAtach.add("facteur",jsonfacteur );
        AbstractInputStreamContent content_facteur = ByteArrayContent.fromString("text/plain",gson.toJson(FacteurInfoAtach));

        File fileFact=service.files().create(facteurfile, content_facteur).execute();

        //buyer file info
        String buyerjson= gson.toJson(buyer);

        JsonObject jsonbuyer = new JsonParser().parse(buyerjson).getAsJsonObject();
        jsonbuyer.add("idAddress",gson.toJsonTree(paymentOverviewService.getAddress(buyer.getIdAddress())));
        jsonbuyer.addProperty("attache_facteur",fileFact.getId());

        //seller file info
        String sellerjson= gson.toJson(seller);



        JsonObject jsonseller = new JsonParser().parse(sellerjson).getAsJsonObject();
        jsonseller.add("idAddress",gson.toJsonTree(paymentOverviewService.getAddress(seller.getIdAddress())));



        ContactDto contact= paymentOverviewService.getContactEnterprise(seller.getIdEnterprise());
        String contactjson= gson.toJson(contact);
        JsonObject jsoncontact = new JsonParser().parse(contactjson).getAsJsonObject();
        jsoncontact.add("idAddress",paymentOverviewService.getAddress(seller.getIdAddress()));
        jsonseller.add("idContact",jsoncontact);
        jsonseller.addProperty("attache_order",fileO.getId());
        jsonseller.addProperty("attache_delivery",fileD.getId());

        File buyerfile = new File();
        buyerfile.setName("buyer.txt");

        File sellerfile = new File();
        sellerfile.setName("seller.txt");









        AbstractInputStreamContent content_buyer = ByteArrayContent.fromString("text/plain",gson.toJson(jsonbuyer));
        AbstractInputStreamContent content_seller = ByteArrayContent.fromString("text/plain",gson.toJson(jsonseller));

        File fileB=service.files().create(buyerfile, content_buyer).execute();
        File fileS=service.files().create(sellerfile, content_seller).execute();

      // application.proprities content

        File properties = new File();
        String pattern = "yyyyMMdd_HHmmss";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("fr", "FR"));
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        String NameFichierApp="SendEmail_"+date+".properties";
        properties.setName(NameFichierApp);
        properties.setMimeType("text/plain");

        JsonObject jsonProp=new JsonObject();
        JsonObject jsonAtchSeller=new JsonObject();
        JsonObject Message=new JsonObject();

        ArrayList<String> email_recipents=new ArrayList<>();




        email_recipents.add(buyer.getEmailBuyer());
        email_recipents.add(contact.getEmailSeller());

       jsonAtchSeller.addProperty("Bon_de_Commande",fileO.getId());
       jsonAtchSeller.addProperty("Bon_de_Livraison",fileD.getId());

       Message.addProperty("buyer",fileB.getId());
       Message.addProperty("seller",fileS.getId());

        jsonProp.addProperty("Sender name","dev0002@itinfinity-dz.com");
        jsonProp.add("Recipents",gson.toJsonTree(email_recipents));
        jsonProp.add("Message",Message);
        jsonProp.add("Attachments_for_Seller",jsonAtchSeller);
        jsonProp.addProperty("Attachments_for_Buyer",fileFact.getId());


        AbstractInputStreamContent properties_content = ByteArrayContent.fromString("text/plain",gson.toJson(jsonProp));

        File fileProp=service.files().create(properties,properties_content ).execute();

       return fileProp.getId();









    }

  /*  public static void main(String... args) throws IOException, GeneralSecurityException, ParseException {




       OrderDto o= paymentOverviewService.getOrderOverview(1L);
        BuyerDto b= paymentOverviewService.getBuyerOverview(1l);
        SellerDto s=paymentOverviewService.getSeller(1l);
        Transaction t=new Transaction(5," 45H6654G870G",new Date(),5, TransactionStatus.success
                , PaymentMethod.dhahabia,"","xx10","",15000.25);
        GoogleDriveService g=new GoogleDriveService();
        DeliveryDto d=paymentOverviewService.getDeliveryOverview(1l);
        FacteurDto f= new FacteurDto("12TGFR85LK",new Date(),new Date());
        g.CrateFile(b,s,o,d,t,f);


      //  System.out.println(transactionRepository.findAll());


    }*/



}
