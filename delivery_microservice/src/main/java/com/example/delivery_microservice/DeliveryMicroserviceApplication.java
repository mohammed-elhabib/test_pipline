package com.example.delivery_microservice;

import com.example.delivery_microservice.controller.DeliveryController;
import com.example.delivery_microservice.entity.AddressEntity;
import com.example.delivery_microservice.entity.CommuneEntity;
import com.example.delivery_microservice.entity.DeliveryPointLineEntity;
import com.example.delivery_microservice.entity.WilayaEntity;
import com.example.delivery_microservice.repository.AddressRepository;
import com.example.delivery_microservice.repository.CommuneRepository;
import com.example.delivery_microservice.repository.WilayaRepository;
import com.example.delivery_microservice.service.AddressService;
import com.example.delivery_microservice.service.WilayaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.delivery_microservice.repository.DeliveryPointLineRepository;


@SpringBootApplication
//@ComponentScan(basePackages ={"repository","entity","com.example.delivery_microservice"})
//@EntityScan("com.example.delivery_microservice.entity.DeliveryPointLineEntity")
//@EnableJpaRepositories(basePackages ={"com.example.delivery_microservice"})
public class DeliveryMicroserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(DeliveryMicroserviceApplication.class, args);
    }



  //  @Bean
    CommandLineRunner init(DeliveryPointLineRepository delp, DeliveryController delvcontrl, AddressRepository adrs, CommuneRepository cmn, WilayaRepository wl) {
       return args -> {
        delvcontrl.get_Information_from_orderService(1);
       // System.out.println(adrs.saveAddress(new AddressEntity(100,"00","000","0000","0254","00",1,cmn.findByIdCommune(1))));
         /*   Stream.of(1,2,3).forEach(gf -> {
              delivery d=new delivery(gf,gf);
              del.save(d);
                    });*/


          /*Stream.of("vend1", "vend1", "vend2").forEach(trnsp -> {
                DeliveryPointLineEntity delvp = new DeliveryPointLineEntity(12,1,1,"batna",trnsp,"vend1vill");
                delp.save(delvp);
           });*/
         /*  DeliveryPointLineEntity deliveryPointLine1 = new DeliveryPointLineEntity(1,12,16,1);
           DeliveryPointLineEntity deliveryPointLine2 = new DeliveryPointLineEntity(2,4,1,1);
           DeliveryPointLineEntity deliveryPointLine3 = new DeliveryPointLineEntity(3,5,1,1);
           delp.save(deliveryPointLine1);
           delp.save(deliveryPointLine2);
           delp.save(deliveryPointLine3);
           AddressEntity add1 =new AddressEntity(1,"5","85","2","city78","457",6);
           AddressEntity add2 =new AddressEntity(2,"74","85","2","city4","457",7);
            adrs.save(add1);
            adrs.save(add2);
            CommuneEntity cm1= new CommuneEntity(1,"barika",8) ;
           CommuneEntity cm2= new CommuneEntity(2,"mtousa",9) ;
            cmn.save(cm1);cmn.save(cm2);
           WilayaEntity w1= new WilayaEntity(1,"batna") ;
           WilayaEntity w2= new WilayaEntity(2,"khenchela") ;
           wl.save(w1);wl.save(w2);*/
            /*delp.findByIdEnterprise(1).forEach(trnsp -> {
                System.out.println(trnsp.getIdDeliveryPointLine());
            });*/
          /*  adrs.findAll().forEach(
                    a -> {
                        System.out.println(a.getIdAddress());
                    });*/
         /*  wl.findAll().forEach(
                   a -> {
                       System.out.println(a.getIdWilaya());
                   });*/
           // System.out.println(delvcontrl.get_Information_from_orderService(1));
            //del.findAll().forEach(System.out::println);

       };
    }


}

