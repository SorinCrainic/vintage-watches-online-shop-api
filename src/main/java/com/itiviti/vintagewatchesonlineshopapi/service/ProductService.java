package com.itiviti.vintagewatchesonlineshopapi.service;

        import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
        import com.itiviti.vintagewatchesonlineshopapi.exceptions.ProductNotFoundException;
        import com.itiviti.vintagewatchesonlineshopapi.repository.ProductRepository;
        import com.itiviti.vintagewatchesonlineshopapi.transfer.CreateProductRequest;
        import com.itiviti.vintagewatchesonlineshopapi.transfer.UpdateProductRequest;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.BeanUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    //IoC (Inversion on Control) and DI (Dependency Injection)
//    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Method for CREATING a product (Crud)
    public Product createProduct(CreateProductRequest request) {

        LOGGER.info("Creating product {}", request);

        Product product = new Product();

        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setImagePath(request.getImagePath());
        product.setProductDescription(request.getProductDescription());
        product.setProductRate(request.getProductRate());

        return productRepository.save(product);
    }

    //Method for RETRIEVING a product from db by productId (cRud)
    public Product getProduct(long id) throws ProductNotFoundException {
        LOGGER.info("Retrieving product {}", id);
        //using Optional wit orElseThrow; using Lambda expressions (anonymous methods)
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product " + id + " not exist."));
    }

    //Method for UPDATE-ing a product (crUd)
    public Product updateProduct(long id, UpdateProductRequest requestForUpdate) throws ProductNotFoundException {
        LOGGER.info("Update-ing product {} with {}", id, requestForUpdate);
        Product productToBeUpdated = getProduct(id);

        BeanUtils.copyProperties(requestForUpdate, productToBeUpdated);

        return productRepository.save(productToBeUpdated);
    }

    //Method for DELETE-ing a product (cruD)
    public void deleteProduct(long id) {
        LOGGER.info("Delete-ing product {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);
    }
}