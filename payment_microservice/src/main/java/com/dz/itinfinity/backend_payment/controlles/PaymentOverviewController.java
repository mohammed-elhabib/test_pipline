package com.dz.itinfinity.backend_payment.controlles;

import com.dz.itinfinity.backend_payment.domain.DTOs.*;
import com.dz.itinfinity.backend_payment.services.PaymentOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/overview")
@CrossOrigin("*")
public class PaymentOverviewController {
    @Autowired
    PaymentOverviewService paymentOverviewService;

    @GetMapping("/order_info/{orderId}")
    public OrderDto getOrderOverview(@PathVariable("orderId") Integer orderId) {
        return paymentOverviewService.getOrderOverview(orderId);
    }

    @GetMapping("/delivery_info/{deliveryId}")
    public DeliveryDto getDeliveryOverview(@PathVariable("deliveryId") Integer orderId) {
        return paymentOverviewService.getDeliveryOverview(orderId);
    }

    @GetMapping("/buyer_info/{buyerId}")
    public BuyerDto getBuyerOverview(@PathVariable("buyerId") Integer buyerId) {
        return paymentOverviewService.getBuyerOverview(buyerId);
    }

    @GetMapping("/seller_payments/{sellerId}")
    public SellerDto getSellerPayments(@PathVariable("sellerId") Integer sellerId) {
        return paymentOverviewService.getSeller(sellerId);
    }
}
