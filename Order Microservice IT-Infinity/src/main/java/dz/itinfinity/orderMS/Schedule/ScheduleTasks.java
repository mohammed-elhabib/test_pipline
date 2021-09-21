package dz.itinfinity.orderMS.Schedule;


import dz.itinfinity.orderMS.Entities.Enums.OrderStatus;
import dz.itinfinity.orderMS.Entities.TablesEnt.OrderPay;
import dz.itinfinity.orderMS.Services.EntityServices.EnterpriseService;
import dz.itinfinity.orderMS.Services.EntityServices.OrderService;
import dz.itinfinity.orderMS.Services.OthersServices.HelperService;
import dz.itinfinity.orderMS.Services.OthersServices.VerificationDataRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleTasks {

    @Autowired
    OrderService orderService;
    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    VerificationDataRequest verificationDataRequest;

    List<OrderPay> listOrderPays = new ArrayList<>();
    boolean isPassed;

    private static final Logger log = LoggerFactory.getLogger(HelperService.class);

    @Scheduled(fixedRate = 5000*60)
    public void notConfirmOrder(){
        listOrderPays = orderService.findAllOrders();


        for (int i =0; i< listOrderPays.size(); i++) {

            if (listOrderPays.get(i).getOrderStatus() == OrderStatus.notConfirm){
                isPassed = HelperService.calculDelay(24, listOrderPays.get(i).getDateOrder());
            if (isPassed) {
                log.warn("change status to cancel for order id: " + listOrderPays.get(i).getIdOrder() + " in the date order " + listOrderPays.get(i).getDateOrder());
                verificationDataRequest.updateStatusOrder(listOrderPays.get(i).getIdOrder(), OrderStatus.cancel.toString(), "EXPIRED TIME: [Auto Cancelling Order not confirm by the buyer. So, it passed 24 h !!!!]");
            }
        }
            if (listOrderPays.get(i).getOrderStatus() == OrderStatus.reservationRequest){
                int acceptationDelay = enterpriseService.findEnterpriseByID(listOrderPays.get(i).getIdEnterprise()).getAcceptationTime();

            Date h = new Date((3600 * 24) * 1000);
            Date dateConfirmation = new Date(listOrderPays.get(i).getDateOrder().getTime() + h.getTime());

                isPassed = HelperService.calculDelay(acceptationDelay, dateConfirmation);
            if (isPassed) {
                log.warn("change status to cancel for order id: " + listOrderPays.get(i).getIdOrder() + " in the date order " + listOrderPays.get(i).getDateOrder());
                verificationDataRequest.updateStatusOrder(listOrderPays.get(i).getIdOrder(), OrderStatus.cancel.toString(), "EXPIRED TIME: [Auto Cancelling Order not accept by the seller. So, it passed " + acceptationDelay + " h !!!!]");
            }
         }
            if (listOrderPays.get(i).getOrderStatus()==OrderStatus.reserved){
                    int reservationDelay = enterpriseService.findEnterpriseByID(listOrderPays.get(i).getIdEnterprise()).getReservationTime();
                    isPassed = HelperService.calculDelay( reservationDelay, listOrderPays.get(i).getAcceptationDate() );
                    if(isPassed) {
                        log.warn("change status to cancel for order id: " + listOrderPays.get(i).getIdOrder() + " in the date order " + listOrderPays.get(i).getDateOrder());
                        verificationDataRequest.updateStatusOrder(listOrderPays.get(i).getIdOrder(),OrderStatus.cancel.toString(),"EXPIRED TIME: [Auto Cancelling Order not paid by the buyer. So, it passed "+reservationDelay+ "h !!!!]");
                    }

            }
        }
    }

}
