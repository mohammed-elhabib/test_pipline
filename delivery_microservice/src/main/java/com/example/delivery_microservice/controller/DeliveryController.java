package com.example.delivery_microservice.controller;


import com.example.delivery_microservice.Response.OrderResponse;
import com.example.delivery_microservice.dto.AddressPointLineDTO;
import com.example.delivery_microservice.dto.DeliveryInfoDTO;
import com.example.delivery_microservice.entity.*;
import com.example.delivery_microservice.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/delivery")


public class DeliveryController {




    private  DeliveryService deliveryService;
    private DeliveryPointLineService deliveryPointLineService;
    private AddressService addressService;
    private CommuneService communeService;
    private WilayaService wilayaService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
   public DeliveryController(DeliveryService deliveryService,DeliveryPointLineService deliveryPointLineService, AddressService addressService, CommuneService communeService, WilayaService wilayaService) {

       this.deliveryService=deliveryService;
        this.deliveryPointLineService = deliveryPointLineService;
        this.addressService = addressService;
        this.communeService = communeService;
        this.wilayaService=wilayaService;


    }


    private String Uri_Order="";
    private AddressPointLineDTO  AddressOfPoint ;
    private AddressPointLineDTO  AddressOfPointSeller ;

    String wilaya;
    String commune;
    AddressEntity addressEntity;
    CommuneEntity communeEntity;


    @GetMapping("/{idOrder}")
    public DeliveryInfoDTO get_Information_from_orderService(@PathVariable("idOrder") int id_order) {

        ArrayList<AddressPointLineDTO> ListAddressPoint = new ArrayList<>();



       // OrderResponse reponse= restTemplate.getForObject(Uri_Order,OrderResponse.class);

        OrderResponse reponse=new OrderResponse(15,1200,1,1,2);



       List<DeliveryPointLineEntity> ListPoint =deliveryPointLineService.findByIdEnterprise(reponse.getEnterprise_id());


      //address pointline
      for (DeliveryPointLineEntity deliveryPointLine:ListPoint)
      {
          addressEntity=addressService.findByIdAddress(deliveryPointLine.getIdAddress());
          communeEntity=communeService.findByIdCommune(addressEntity.getCommune().getIdCommune());
          commune=communeEntity.getCommuneName();
          wilaya=wilayaService.findByIdWilaya(communeEntity.getIdWilaya()).getWilayaName();
          this.AddressOfPoint=new AddressPointLineDTO(deliveryPointLine.getIdAddress(),wilaya,commune,addressEntity.getCity(),addressEntity.getStreet(),addressEntity.getHomeNumber(),
                  addressEntity.getFloor()
                  );
          ListAddressPoint.add(AddressOfPoint);

      }
      // address point seller
        addressEntity=addressService.findByIdAddress(reponse.getIdAddress_deliveryPoint());
        communeEntity=communeService.findByIdCommune(addressEntity.getCommune().getIdCommune());
        commune=communeEntity.getCommuneName();
        wilaya=wilayaService.findByIdWilaya(communeEntity.getIdWilaya()).getWilayaName();
        this.AddressOfPointSeller=new AddressPointLineDTO(reponse.getIdAddress_deliveryPoint(),wilaya,commune,addressEntity.getCity(),addressEntity.getStreet(),addressEntity.getHomeNumber(),
                addressEntity.getFloor()
        );




        DeliveryInfoDTO deliveryInfo =new DeliveryInfoDTO(reponse.getDelay(),reponse.getTransportcosts(),
                AddressOfPointSeller,reponse.getEnterprise_id(),id_order,ListAddressPoint,reponse.getBuyer_id() );


        return  deliveryInfo;

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,

            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addDelivery(@RequestBody DeliveryEntity deliveryEntity){

        if(deliveryService.saveDelivery(deliveryEntity))
            return true;
        else
            return false;
    }


}
