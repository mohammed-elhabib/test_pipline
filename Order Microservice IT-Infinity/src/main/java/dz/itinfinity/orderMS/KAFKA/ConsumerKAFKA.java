package dz.itinfinity.orderMS.KAFKA;

import dz.itinfinity.orderMS.Services.OthersServices.HelperService;
import dz.itinfinity.orderMS.Services.OthersServices.VerificationDataRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ConsumerKAFKA {

    @Autowired
    VerificationDataRequest verificationDataRequest;
    private static final Logger log = LoggerFactory.getLogger(HelperService.class);

    @KafkaListener(topics = "updateStatusTopic", groupId = "IT-Infinity", containerFactory = "updateStatusKafkaListenerFactory")
    public void consumeUpdateStatus(Map pathVarsMap){
        log.info("start KAFKA Listener updating status");

        int idOrder = Integer.parseInt((String) pathVarsMap.get("idOrder"));
        String status = (String) pathVarsMap.get("status");
        System.out.println("consume successfully "+pathVarsMap);

        if(status.equalsIgnoreCase("cancel")) {
            log.info("1 update status to "+status);
            String messageCancellation = (String) pathVarsMap.get("messageCancellation");
            verificationDataRequest.updateStatusOrder(idOrder,status,messageCancellation);
        }else {
            log.info("2 update status to "+status);
            verificationDataRequest.updateStatusOrder(idOrder, status, "");
        }
        log.info("Update Status successfully");
    }

    @KafkaListener(topics = "deliveryTopic", groupId = "IT-Infinity", containerFactory = "UpdateAddressBuyerKafkaListenerFactory")
    public void consumeUpdateAddressBuyer(Map pathVarsMap) {
        log.info("start KAFKA Listener updating address buyer");

        int idBuyer = Integer.parseInt((String) pathVarsMap.get("idBuyer"));
        int idAddress = Integer.parseInt((String) pathVarsMap.get("idAddress"));

        verificationDataRequest.updateAddressBuyer(idBuyer,idAddress);
        System.out.println("Update address buyer successfully");
    }
}
