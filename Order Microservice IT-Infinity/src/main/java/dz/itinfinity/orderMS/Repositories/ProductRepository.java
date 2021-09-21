package dz.itinfinity.orderMS.Repositories;

import dz.itinfinity.orderMS.Entities.TablesEnt.OrderPay;
import dz.itinfinity.orderMS.Entities.TablesEnt.Product;
import dz.itinfinity.orderMS.IDComposites.ProductID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, ProductID> {
    public Product findByIdProduct(int idProduct);
    public Product findProductByProductNameAndAndProductRefAndIdEnterprise(String productName,String productRef,int idEnterprise);
    public void deleteByIdProduct(int idProduct);
}