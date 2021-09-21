package com.dz.itinfinity.backend_payment.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductDto {


   private int  idProduct;
   private String productRef;
   String productName;
   int idEnterprise;
   float price;
   String  unit;
   String category;
   String    productImage;
   int quantityStock;
   String  productDescription;
   float total;
   float discount;
   float quantity;




}
