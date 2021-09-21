package dz.itinfinity.orderMS.Services.EntityServices;

import dz.itinfinity.orderMS.Entities.TablesEnt.Product;
import dz.itinfinity.orderMS.Exceptions.EntityNotFoundExceptions.ProductNotFoundException;
import dz.itinfinity.orderMS.IDComposites.ProductID;
import dz.itinfinity.orderMS.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

//////////////// CRUD ////////////////////////////////////////////////////////////////////////////////////////////////
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private Example<Product> exampleProduct;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /// ---------- Read --------------

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductByIDComposite(ProductID id){
        return productRepository.findById(id).
                orElse(null);
                //orElseThrow(() -> new ProductNotFoundException("Product by productRef: "+id.getProductRef()+" and productName: "+id.getProductName()+" and idEnterprise: "+id.getIdEnterprise()+" was not found"));
    }

    public Product findProductByID(int id){
        return productRepository.findByIdProduct(id);
    }

    /// ---------- Add --------------

    public Product addProduct(Product product){
        product = productRepository.saveAndFlush(product);
        return findProductByIDComposite(new ProductID(product.getProductRef(),product.getProductName(),product.getIdEnterprise()));
    }

    /// ---------- Delete --------------

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public void deleteProductByIdProduct(int idProduct){
        productRepository.deleteByIdProduct(idProduct);
    }

    public void deleteProductByID(ProductID id){
        productRepository.deleteById(id);
    }

    /// ---------- Update --------------

    public Product updateProduct(Product product){
//        productRepository.deleteById(new ProductID(product.getProductRef(),product.getProductName(),product.getIdEnterprise()));
        return productRepository.save(product);
    }

    /////////////////////////// Others Services ///////////////////////////////////////////////////////////

    /// ----------- find one product by information --------------------------

    public Product findOneProductByInformation(Product product){
                return productRepository.findProductByProductNameAndAndProductRefAndIdEnterprise(product.getProductName(), product.getProductRef(),product.getIdEnterprise());
//        exampleProduct = Example.of(product, ExampleMatcher.matching());
//        return productRepository.findOne(exampleProduct).orElseThrow(() -> new ProductNotFoundException("The '"+product.getProductName()+"' product is not found in the system"));
    }

    /// ----------- test existing product by information --------------------------

    public Boolean existProductByInformation(Product product){

        exampleProduct = Example.of(product, ExampleMatcher.matching());
        return productRepository.exists(exampleProduct);
    }


}
