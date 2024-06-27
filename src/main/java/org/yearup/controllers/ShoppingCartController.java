package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("cart")
@CrossOrigin
@PreAuthorize("hasRole('ROLE_USER')")

public class ShoppingCartController {

    private ShoppingCartDao shoppingCartDao;
    private UserDao userDao;
    private ProductDao productDao;

    @Autowired
    public ShoppingCartController(ShoppingCartDao shoppingCartDao, UserDao userDao, ProductDao productDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    @GetMapping("")
    public ShoppingCart getCart(Principal principal) {

        try {

            int userId = getUserId(principal);

            return shoppingCartDao.getByUserId(userId);

        } catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PostMapping("/products/{productId}")
    public ShoppingCart addProductToCart(Principal principal, @PathVariable int productId) {

        try {

            int userId = getUserId(principal);
            ShoppingCart shoppingCart = getCart(principal);
            boolean productExistsInCart = false;

            for (ShoppingCartItem item : shoppingCart.getItems().values()) {
                if (item.getProductId() == productId) {
                    shoppingCartDao.incrementProductInCart(userId, productId, item);
                    productExistsInCart = true;
                    break;
                }
            }

            if (!productExistsInCart) {
                shoppingCartDao.addProductToCart(userId, productId);
            }

            return getCart(principal);

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }

    @PutMapping("/products/{productId}")
    public void incrementProductInCart(Principal principal, @PathVariable int productId, @RequestBody ShoppingCartItem item) {

        try {

            int userId = getUserId(principal);

            shoppingCartDao.incrementProductInCart(userId, productId, item);

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @DeleteMapping("")
    public void deleteProductsFromCart(Principal principal) {

        try {

            int userId = getUserId(principal);

            ShoppingCart cart = shoppingCartDao.getByUserId(userId);

            if(cart == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            shoppingCartDao.emptyCart(userId);

        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }

    }

    public int getUserId(Principal principal) {

        String userName = principal.getName();
        User user = userDao.getByUserName(userName);

        return user.getId();

    }

}
