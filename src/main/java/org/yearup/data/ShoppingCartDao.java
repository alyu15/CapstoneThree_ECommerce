package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    ShoppingCart addProductToCart(int userId, int productId);
    void updateProductInCart(int userId , int productId, ShoppingCartItem shoppingCartItem);
    void emptyCart(int userId);
}
