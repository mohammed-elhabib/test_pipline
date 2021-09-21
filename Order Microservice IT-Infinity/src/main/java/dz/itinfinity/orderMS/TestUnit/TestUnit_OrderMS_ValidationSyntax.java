package dz.itinfinity.orderMS.TestUnit;

import dz.itinfinity.orderMS.Entities.TablesEnt.*;
import dz.itinfinity.orderMS.IDComposites.*;
import dz.itinfinity.orderMS.Requests.*;
import dz.itinfinity.orderMS.Services.EntityServices.*;
import dz.itinfinity.orderMS.Services.OthersServices.ValidationSyntaxRequest;
import dz.itinfinity.orderMS.Services.OthersServices.VerificationDataRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestUnit_OrderMS_ValidationSyntax {

    @InjectMocks
    ValidationSyntaxRequest validationSyntaxRequest;

    @Mock
    private ProductService productService;
    @Mock
    private EnterpriseService enterpriseService;
    @Mock
    private BuyerService buyerService;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderLineService orderLineService;
    @Mock
    private Product product;
    @Mock
    private Buyer buyer;
    @Mock
    private Enterprise enterprise;
    @Mock
    private OrderPay order;
    @Mock
    private OrderLine orderLine;
    @Mock
    private List<OrderLine> orderLineList;

    @Before
    public void initial(){
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void validationInputsOrder_Test_True(){
        OrderRequest orderRequest =new OrderRequest();
        assertEquals(true, validationSyntaxRequest.validationInputsOrder(orderRequest));
    }
    @Test
    public void validationInputsOrder_Test_Exception(){
        OrderRequest orderRequest =new OrderRequest();
        assertEquals(exceptionRule, validationSyntaxRequest.validationInputsOrder(orderRequest));
    }

    @Test
    public void validationInputsBuyer_Test_True(){
        BuyerRequest buyerRequest =new BuyerRequest();
        assertEquals(true, validationSyntaxRequest.validationInputsBuyer(buyerRequest));
    }
    @Test
    public void validationInputsBuyer_Test_Exception(){
        BuyerRequest buyerRequest =new BuyerRequest();
        assertEquals(exceptionRule, validationSyntaxRequest.validationInputsBuyer(buyerRequest));
    }

    @Test
    public void validationInputsProducts_Test_True(){
        ProductRequest productRequest =new ProductRequest();
        assertEquals(true, validationSyntaxRequest.validationInputsProducts(productRequest));
    }
    @Test
    public void validationInputsProducts_Test_Exception(){
        ProductRequest productRequest =new ProductRequest();
        assertEquals(exceptionRule, validationSyntaxRequest.validationInputsProducts(productRequest));
    }

    @Test
    public void validationInputsOrderLine_Test_True(){
        OrderLineRequest orderLineRequest =new OrderLineRequest();
        assertEquals(true, validationSyntaxRequest.validationInputsOrderLine(orderLineRequest));
    }
    @Test
    public void validationInputsOrderLine_Test_Exception(){
        OrderLineRequest orderLineRequest =new OrderLineRequest();
        assertEquals(exceptionRule, validationSyntaxRequest.validationInputsOrderLine(orderLineRequest));
    }

    @Test
    public void validationInputsEnterprise_Test_True(){
        EnterpriseRequest enterpriseRequest =new EnterpriseRequest();
        assertEquals(true, validationSyntaxRequest.validationInputsEnterprise(enterpriseRequest));
    }
    @Test
    public void validationInputsEnterprise_Test_Exception(){
        EnterpriseRequest enterpriseRequest =new EnterpriseRequest();
        assertEquals(exceptionRule, validationSyntaxRequest.validationInputsEnterprise(enterpriseRequest));
    }

}
