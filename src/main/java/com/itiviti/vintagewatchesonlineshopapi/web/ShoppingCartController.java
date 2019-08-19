package com.itiviti.vintagewatchesonlineshopapi.web;

import com.itiviti.vintagewatchesonlineshopapi.domain.ShoppingCart;
import com.itiviti.vintagewatchesonlineshopapi.exceptions.NotFoundException;
import com.itiviti.vintagewatchesonlineshopapi.service.ShoppingCartService;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.AddProductToShoppingCartRequest;
import com.itiviti.vintagewatchesonlineshopapi.transfer.shoppingCart.ShoppingCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shopping_cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    //an endpoint is a method which exposes a resource on the network
    //endpoint: POST (add/create) new product to shopping cart
    @PutMapping
    public ResponseEntity<ShoppingCart> addProductToShoppingCartController(@RequestBody @Valid AddProductToShoppingCartRequest requestAddProductToShoppingCartController) throws NotFoundException {
        shoppingCartService.addProductToShoppingCart(requestAddProductToShoppingCartController);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //endpoint: GET (read/retrieve) a product from the shopping cart
    @GetMapping("/{customerId}")
    public ResponseEntity<ShoppingCartDTO> getProductFromShoppingCart(@PathVariable("customerId") Long customerId) throws NotFoundException {
        ShoppingCartDTO productFromShoppingCart = shoppingCartService.getProductFromShoppingCart(customerId);
        return new ResponseEntity<>(productFromShoppingCart, HttpStatus.OK);
    }
}