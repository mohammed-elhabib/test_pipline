package dz.itinfinity.orderMS.Services.OthersServices;

import dz.itinfinity.orderMS.Entities.Enums.BuyerStatus;
import dz.itinfinity.orderMS.Requests.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationSyntaxRequest {

    private static final Logger log = LoggerFactory.getLogger(ValidationSyntaxRequest.class);

    public static final String[] buyerStatus = {BuyerStatus.enterprise.toString(),BuyerStatus.person.toString()};

    public boolean validationInputsOrder(OrderRequest order) {

        log.info("- Start, verify validation syntax of new order inputs ...");

        HelperService.isNotBlankInput(order.getDateOrder(),"Date of order");
        HelperService.isNotBlankInput(order.getOrderNumber(),"OrderPay Number");

        HelperService.isNotBlankInput(order.getOrderTotal(),"Total price with tax");
        HelperService.isNotBlankInput(order.getTotalOutsideTax(),"Total price without tax");
        HelperService.isNotBlankInput(order.getTva(),"TVA cost");

        HelperService.isNotBlankInput(order.getOrderLineProducts(),"OrderPay Line of products");

        log.debug("*** All order inputs are not blank (or empty) or null ***");

        HelperService.isNumericValue(order.getOrderTotal(),"Total price with tax");
        HelperService.isNumericValue(order.getTotalOutsideTax(),"Total price without tax");
        HelperService.isNumericValue(order.getTva(),"TVA cost");

        HelperService.isValidFormatDate(order.getDateOrder().toString(),"Date of order");
        validationInputsBuyer(order.getBuyer());

        log.debug("*** All order inputs have valid values ***");

        return true;
    }

    public boolean validationInputsBuyer(BuyerRequest buyer) {
        log.info("- Start, verify validation syntax of the buyer inputs which possessed this new order ...");

        HelperService.isNotBlankInput(buyer.getFirstNameB(),"Buyer First Name");
        HelperService.isNotBlankInput(buyer.getLastNameB(),"Buyer Last Name");
        HelperService.isNotBlankInput(buyer.getBirthDayB(),"Buyer Birth Day");
        HelperService.isNotBlankInput(buyer.getEmailBuyer(),"Buyer Email");
        HelperService.isNotBlankInput(buyer.getPhoneBuyer(),"Buyer Phone");
        HelperService.isNotBlankInput(buyer.getBuyerStatus(),"Buyer status");

        log.debug("*** All Buyer inputs are not blank (or empty) or null ***");

        HelperService.isNumericValue(buyer.getPhoneBuyer(),"Buyer Phone");
        HelperService.isValidSyntaxChoiceEnum(buyer.getBuyerStatus().toString(),buyerStatus,"Buyer status");


        if (buyer.getBuyerStatus().toString().equalsIgnoreCase(BuyerStatus.enterprise.toString())) {

            HelperService.isNotBlankInput(buyer.getRegisterCommercialNumberB(), "Buyer Register Commercial Number");
            HelperService.isNotBlankInput(buyer.getTaxIdentificationNumberB(), "Buyer Tax identification Number");
        }


        HelperService.isValidFormatDate(buyer.getBirthDayB().toString(),"Buyer Birth Day");
        HelperService.isValidSyntaxEmail(buyer.getEmailBuyer(),"Buyer Email");
        HelperService.isValidSyntaxPhone(buyer.getPhoneBuyer(),"Buyer Phone");

        log.debug("*** All buyer inputs have valid values ***");

        return true;
    }

    public boolean validationInputsProducts(ProductRequest product) {
        log.info("- Start, verify validation syntax of the product list inputs for this new order ...");

        HelperService.isNotBlankInput(product.getProductName(),"Product name");
        HelperService.isNotBlankInput(product.getProductRef(),"Product reference");
        HelperService.isNotBlankInput(product.getCategory(),"Product category");
        HelperService.isNotBlankInput(product.getPrice(),"Product price");
        HelperService.isNotBlankInput(product.getUnit(),"Product unit");
//        HelperService.isNotBlankInput(product.getQuantityStock(),"Product quantity of stock") ;
//        HelperService.isNotBlankInput(product.getEnterprise(),"Product owner");
        HelperService.isNotBlankInput(product.getProductImage(),"Product image");
        HelperService.isNotBlankInput(product.getProductDescription(),"Product description");

        log.debug("*** All product list inputs are not blank (or empty) or null ***");

        HelperService.isNumericValue(product.getPrice(),"Product price");
//        HelperService.isNumericValue(product.getQuantityStock(),"Product quantity of stock");

        HelperService.isValidPath(product.getProductImage(),"Product image");
        log.debug("*** All  product list inputs have valid values ***");

//        validationInputsEnterprise(product.getEnterprise());

        return true;
    }

    public boolean validationInputsOrderLine(OrderLineRequest orderLine) {
        log.info("- Start, verify validation syntax of the order line inputs for this new order...");

        HelperService.isNotBlankInput(orderLine.getProduct(),"a product of order");
        HelperService.isNotBlankInput(orderLine.getTotal(),"Total of product : "+orderLine.getProduct().getProductName());
        HelperService.isNotBlankInput(orderLine.getDiscount(),"Discount of product : "+orderLine.getProduct().getProductName());
        HelperService.isNotBlankInput(orderLine.getQuantity(),"Total of quantity : "+orderLine.getProduct().getProductName());

        log.debug("*** All order line inputs are not blank (or empty) or null ***");

        HelperService.isNumericValue(orderLine.getTotal(),"Total price of product : "+orderLine.getProduct().getProductName());
        HelperService.isNumericValue(orderLine.getDiscount(),"Total price of product : "+orderLine.getProduct().getProductName());
        HelperService.isNumericValue(orderLine.getQuantity(),"Total price of product : "+orderLine.getProduct().getProductName());

        log.debug("*** All order line inputs have valid values ***");

        validationInputsProducts(orderLine.getProduct());

        return true;
    }

    public boolean validationInputsEnterprise(EnterpriseRequest enterprise) {
        log.info("- Start, verify validation syntax of the seller (enterprise) inputs for this new order ...");

        HelperService.isNotBlankInput(enterprise.getContact(),"Seller Contact");
        HelperService.isNotBlankInput(enterprise.getEnterpriseName(),"Enterprise Name");
        HelperService.isNotBlankInput(enterprise.getAddress(),"Enterprise Address");
        HelperService.isNotBlankInput(enterprise.getDeliveryPoints(),"Enterprise Delivery points");
        HelperService.isNotBlankInput(enterprise.getTown(),"Enterprise town");
        HelperService.isNotBlankInput(enterprise.getLogoImage(),"Enterprise Logo");
        HelperService.isNotBlankInput(enterprise.getWebSite(),"Enterprise Web site");
        HelperService.isNotBlankInput(enterprise.getTransportCosts(),"Enterprise Cost of transport");

        log.debug("*** All enterprise inputs are not blank (or empty) or null ***");

        HelperService.isNumericValue(enterprise.getTransportCosts(),"Enterprise Cost of transport");

        HelperService.isValidPath(enterprise.getLogoImage(),"Enterprise Logo");

        log.debug("*** All enterprise inputs have valid values ***");

        return true;
    }

}