package org.yearup.data.mysql;

import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import javax.sql.DataSource;

public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCartDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        return null;
    }

    @Override
    public ShoppingCart addProductToCart(int userId, int productId) {
        return null;
    }

    @Override
    public void updateProductInCart(int userId, int productId, ShoppingCartItem shoppingCartItem) {

    }

    @Override
    public void emptyCart(int userId) {

    }

}
