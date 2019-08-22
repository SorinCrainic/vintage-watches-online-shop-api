package com.itiviti.vintagewatchesonlineshopapi.service;

import com.itiviti.vintagewatchesonlineshopapi.domain.Customer;
import com.itiviti.vintagewatchesonlineshopapi.domain.Product;
import com.itiviti.vintagewatchesonlineshopapi.domain.ShoppingCart;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.repository.ShoppingCartRepository;
import com.itiviti.vintagewatchesonlineshopapi.transfer.customer.CustomerDTO;
import com.itiviti.vintagewatchesonlineshopapi.transfer.product.ProductDTO;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.AddProductToShoppingCartRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.ShoppingCartDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartService.class);

    private final ShoppingCartRepository shoppingCartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    //Inversion of Control (IoC)
    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, CustomerService customerService, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    //Method for ADDING a Product to a ShoppingCart
    @Transactional
    public void addProductToShoppingCart(AddProductToShoppingCartRequest requestAddProductToShoppingCart) throws NotFoundException {
        LOGGER.info("Adding product " + requestAddProductToShoppingCart + " to shopping cart.");

        //retrieve from db the existing customer which will be assigned to the shopping cart (the customer must already be created/exist into db)
        Customer customerAssignedToShoppingCart = customerService.getCustomer(requestAddProductToShoppingCart.getCustomerId());

        ShoppingCart shoppingCart = shoppingCartRepository.findById(requestAddProductToShoppingCart.getCustomerId()).orElse(new ShoppingCart());

        if (shoppingCart.getCustomerShoppingCart() == null) {
            shoppingCart.setCustomerShoppingCart(customerAssignedToShoppingCart);
        }

//        shoppingCart.setCustomerShoppingCart(customerAssignedToShoppingCart); // Asta trebuie aici????

        Product productToBeAddedToTheShoppingCart = productService.getProduct(requestAddProductToShoppingCart.getProductId());

        shoppingCart.addProductToCurrentShoppingCart(productToBeAddedToTheShoppingCart);

        shoppingCartRepository.save(shoppingCart);
    }

    //Method for RETRIEVING (GET) a Product from a ShoppingCart
    //customerId and cartId are the same (mapping rule implemented while defining OneToOne relationship between customer and shopping cart)
    @Transactional
    public ShoppingCartDTO getProductFromShoppingCart(Long customerId) throws NotFoundException {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(customerId).orElseThrow(() -> new NotFoundException
                ("Retrieved shopping cart " + customerId + " does not exist."));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(shoppingCart.getCustomerShoppingCart().getId());
        customerDTO.setCustomerFirstName(shoppingCart.getCustomerShoppingCart().getCustomerFirstName());
        customerDTO.setCustomerLastName(shoppingCart.getCustomerShoppingCart().getCustomerLastName());
        customerDTO.setCustomerAddress(shoppingCart.getCustomerShoppingCart().getCustomerAddress());

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setCustomer(customerDTO);

        shoppingCart.getProducts().forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setPrice(product.getPrice());
            productDTO.setImagePath(product.getImagePath());
            productDTO.setProductDescription(product.getProductDescription());
            productDTO.setProductRate(product.getProductRate());

            shoppingCartDTO.getProducts().add(productDTO);
        });

        return shoppingCartDTO;
    }
}